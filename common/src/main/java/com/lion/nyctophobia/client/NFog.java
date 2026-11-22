package com.lion.nyctophobia.client;

import com.lion.nyctophobia.Nyctophobia;
import com.lion.nyctophobia.config.BiomeFogConfigEntry;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.material.FogType;

/// It returns the planes, because the loader event ignores direct changes.
public final class NFog {

    private static final String FOG_SUFFIX = "_fog";

    private static final float CLEAR = 32.0F;

    /// The fog hook can fire more than once per frame. Fade rates use a reference frame.
    private static final float FADE_IN = 0.975F;
    private static final float FADE_OUT = 1.05F;
    private static final float REFERENCE_FRAME_TIME = 1.0F / 60.0F;

    /// Cap one step, so the fog does not snap on after a long pause.
    private static final float MAX_STEP = 6.0F;

    private static final float NO_DENSITY = -1.0F;

    private static final Map<ResourceKey<Biome>, BiomeFogConfigEntry> FOG_BY_BIOME = resolveFogBiomes();

    private static float fogFactor = CLEAR;

    private static long lastStepNanos;

    private static boolean enabled = true;

    private NFog() {}

    public static boolean isEnabled() {
        return enabled;
    }

    public static boolean setEnabled(boolean value) {
        enabled = value;

        if (!enabled) {
            fogFactor = CLEAR;
            lastStepNanos = 0L;
        }

        return enabled;
    }

    public static boolean toggle() {
        return setEnabled(!enabled);
    }

    public static Component feedback() {
        return Component.translatable(enabled ? "commands.nyctophobia.fog.on" : "commands.nyctophobia.fog.off");
    }

    /// The result never passes the vanilla planes.
    public static FogPlanes compute(
            Minecraft client,
            Camera camera,
            float viewDistance,
            float vanillaNear,
            float vanillaFar
    ) {
        if (!enabled || client.level == null) {
            return null;
        }

        float steps = elapsedSteps();
        BlockPos pos = camera.blockPosition();
        ResourceKey<Biome> biome = client.level.getBiome(pos).unwrapKey().orElse(null);
        float density = densityFor(biome, pos.getY());

        if (density < 0.0F) {
            if (fogFactor >= CLEAR) {
                fogFactor = CLEAR;
                return null;
            }

            fogFactor = Math.min(fogFactor * fade(FADE_OUT, steps), CLEAR);
        }

        if (camera.getFluidInCamera() != FogType.NONE) {
            return null;
        }

        Entity entity = camera.entity();

        if (entity instanceof LivingEntity living && living.hasEffect(MobEffects.BLINDNESS)) {
            return null;
        }

        if (density >= 0.0F && fogFactor > density) {
            fogFactor = Math.max(fogFactor * fade(FADE_IN, steps), density);
        }

        float clamped = Math.max(viewDistance - 16.0F, CLEAR);
        float near = Math.min(clamped * 0.05F * fogFactor, vanillaNear);
        float far = Math.min(Math.min(clamped, 192.0F) * 0.5F * fogFactor, vanillaFar);

        if (near >= vanillaNear && far >= vanillaFar) {
            return null;
        }

        return new FogPlanes(near, far);
    }

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

    private static float densityFor(ResourceKey<Biome> biome, int y) {
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

    private static Map<ResourceKey<Biome>, BiomeFogConfigEntry> resolveFogBiomes() {
        Map<ResourceKey<Biome>, BiomeFogConfigEntry> byBiome = new HashMap<>();

        Nyctophobia.config.biomeFogConfigEntries.forEach((key, entry) -> {
            if (!key.endsWith(FOG_SUFFIX)) {
                Nyctophobia.LOGGER.error("Fog config key \"" + key + "\" does not end in " + FOG_SUFFIX + ", ignoring it.");
                return;
            }

            String biome = key.substring(0, key.length() - FOG_SUFFIX.length());
            byBiome.put(ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Nyctophobia.MOD_ID, biome)), entry);
        });

        return byBiome;
    }

    public record FogPlanes(float near, float far) {}
}
