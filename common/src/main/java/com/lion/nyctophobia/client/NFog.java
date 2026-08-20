package com.lion.nyctophobia.client;

import com.lion.nyctophobia.Nyctophobia;
import com.lion.nyctophobia.config.BiomeFogConfigEntry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.block.enums.CameraSubmersionType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

import java.util.HashMap;
import java.util.Map;

/// All per-biome fog behaviour lives here so a port only has to re-aim the loader hook (a mixin on
/// Fabric, a render event on Forge) instead of rewriting the logic every MC version.
///
/// This computes and returns the plane distances rather than applying them, because Forge overwrites
/// whatever a handler sets directly and only honours values fed back through its event.
public final class NFog {

    /// Config keys carry this suffix, so haunted_forest_fog configures haunted_forest.
    private static final String FOG_SUFFIX = "_fog";

    /// The factor the planes are scaled by. At this value the fog is indistinguishable from none, so
    /// it doubles as the resting state. Fog eases towards the configured density instead of snapping,
    /// which is what makes leaving a foggy biome fade rather than pop.
    private static final float CLEAR = 32.0F;

    /// Rates per reference frame of real time, not per call. Each loader hooks a different point and
    /// the hook may fire several times per frame, so a rate stepped once per call would fade at
    /// whatever speed the loader and the frame rate happen to produce.
    private static final float FADE_IN = 0.975F;
    private static final float FADE_OUT = 1.05F;
    private static final float REFERENCE_FRAME_TIME = 1.0F / 60.0F;

    /// A gap long enough to carry the fade to its end in one step (window minimised, world still
    /// loading) is capped, so returning to the window does not snap the fog on.
    private static final float MAX_STEP = 6.0F;

    /// Returned by densityFor when this position has no fog at all.
    private static final float NO_DENSITY = -1.0F;

    /// Built once from the config rather than from a hardcoded list, so a new foggy biome needs a
    /// config entry and no code change. Resolving Identifiers here keeps them out of the render path.
    private static final Map<RegistryKey<Biome>, BiomeFogConfigEntry> FOG_BY_BIOME = resolveFogBiomes();

    private static float fogFactor = CLEAR;

    /// Real time of the previous step, so the fade rates above act as a speed. Zero means no step has
    /// been taken yet.
    private static long lastStepNanos;

    /// Switched from the client command. Purely visual and never sent anywhere, so it can differ from
    /// player to player on the same server.
    private static boolean enabled = true;

    private NFog() {}

    public static boolean isEnabled() {
        return enabled;
    }

    /// Returns the state it ended up in, so a caller can report it without asking again.
    public static boolean setEnabled(boolean value) {
        enabled = value;
        if (!enabled) {
            // Drop straight to clear rather than easing out, or the fog would linger for a second
            // after someone explicitly turned it off.
            fogFactor = CLEAR;
            lastStepNanos = 0L;
        }
        return enabled;
    }

    public static boolean toggle() {
        return setEnabled(!enabled);
    }

    /// The message both loaders show, so the wording cannot drift apart.
    public static Text feedback() {
        return Text.translatable(enabled ? "commands.nyctophobia.fog.on" : "commands.nyctophobia.fog.off");
    }

    /// Near and far plane distance for this frame, or null to leave vanilla fog untouched. Both
    /// loaders pass the game renderer's view distance, and both call this once per frame, so the fog
    /// comes out the same on either.
    public static FogPlanes compute(MinecraftClient client, Camera camera, float viewDistance) {
        if (!enabled || client.world == null) {
            return null;
        }

        float steps = elapsedSteps();
        BlockPos pos = camera.getBlockPos();
        RegistryKey<Biome> biome = client.world.getBiome(pos).getKey().orElse(null);
        float density = densityFor(biome, pos.getY());

        if (density < 0.0F) {
            if (fogFactor >= CLEAR) {
                fogFactor = CLEAR;
                return null;
            }
            fogFactor = Math.min(fogFactor * fade(FADE_OUT, steps), CLEAR);
        }

        if (camera.getSubmersionType() != CameraSubmersionType.NONE) {
            return null;
        }

        Entity entity = camera.getFocusedEntity();
        if (entity instanceof LivingEntity living && living.hasStatusEffect(StatusEffects.BLINDNESS)) {
            return null;
        }

        if (density >= 0.0F && fogFactor > density) {
            // Clamped, or a capped step could carry the factor past its target into thicker fog than
            // the biome is configured for.
            fogFactor = Math.max(fogFactor * fade(FADE_IN, steps), density);
        }

        float clamped = Math.max(viewDistance - 16.0F, CLEAR);
        return new FogPlanes(clamped * 0.05F * fogFactor, Math.min(clamped, 192.0F) * 0.5F * fogFactor);
    }

    /// Reference frames of real time since the previous step. The first step after a pause counts as
    /// one frame, so the fog picks up where it left off instead of jumping.
    private static float elapsedSteps() {
        long now = System.nanoTime();
        long previous = lastStepNanos;
        lastStepNanos = now;

        if (previous == 0L) {
            return 1.0F;
        }
        return Math.min((now - previous) / 1.0E9F / REFERENCE_FRAME_TIME, MAX_STEP);
    }

    private static float fade(float rate, float steps) {
        return (float) Math.pow(rate, steps);
    }

    /// Negative means no fog is configured here. A Y outside the configured band counts as no fog, so
    /// climbing out of a valley fades the fog away.
    private static float densityFor(RegistryKey<Biome> biome, int y) {
        if (biome == null) {
            return NO_DENSITY;
        }

        BiomeFogConfigEntry entry = FOG_BY_BIOME.get(biome);
        if (entry == null || !entry.enabled) {
            return NO_DENSITY;
        }
        if (y < entry.minY || y > entry.maxY) {
            return NO_DENSITY;
        }
        return entry.density;
    }

    private static Map<RegistryKey<Biome>, BiomeFogConfigEntry> resolveFogBiomes() {
        Map<RegistryKey<Biome>, BiomeFogConfigEntry> byBiome = new HashMap<>();

        Nyctophobia.config.biomeFogConfigEntries.forEach((key, entry) -> {
            if (!key.endsWith(FOG_SUFFIX)) {
                Nyctophobia.LOGGER.error("Fog config key \"" + key + "\" does not end in " + FOG_SUFFIX + ", ignoring it.");
                return;
            }

            String biome = key.substring(0, key.length() - FOG_SUFFIX.length());
            byBiome.put(RegistryKey.of(RegistryKeys.BIOME, Identifier.of(Nyctophobia.MOD_ID, biome)), entry);
        });

        return byBiome;
    }

    public record FogPlanes(float near, float far) {}
}
