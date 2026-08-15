package com.lion.nyctophobia.client;

import com.lion.nyctophobia.Nyctophobia;
import com.lion.nyctophobia.config.BiomeFogConfigEntry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
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
    private static final float FADE_IN = 0.975F;
    private static final float FADE_OUT = 1.05F;

    /// Returned by densityFor when this position has no fog at all.
    private static final float NO_DENSITY = -1.0F;

    /// Built once from the config rather than from a hardcoded list, so a new foggy biome needs a
    /// config entry and no code change. Resolving Identifiers here keeps them out of the render path.
    private static final Map<RegistryKey<Biome>, BiomeFogConfigEntry> FOG_BY_BIOME = resolveFogBiomes();

    private static float fogFactor = CLEAR;

    private NFog() {}

    /// Near and far plane distance for this frame, or null to leave vanilla fog untouched. Both
    /// loaders call this with the view distance their hook gives them.
    public static FogPlanes compute(MinecraftClient client, Camera camera, float viewDistance) {
        if (client.world == null) {
            return null;
        }

        BlockPos pos = camera.getBlockPos();
        RegistryKey<Biome> biome = client.world.getBiome(pos).getKey().orElse(null);
        float density = densityFor(biome, pos.getY());

        if (density < 0.0F) {
            if (fogFactor >= CLEAR) {
                fogFactor = CLEAR;
                return null;
            }
            fogFactor *= FADE_OUT;
        }

        if (camera.getSubmersionType() != CameraSubmersionType.NONE) {
            return null;
        }

        Entity entity = camera.getFocusedEntity();
        if (entity instanceof LivingEntity living && living.hasStatusEffect(StatusEffects.BLINDNESS)) {
            return null;
        }

        if (density >= 0.0F && fogFactor > density) {
            fogFactor *= FADE_IN;
        }

        float clamped = Math.max(viewDistance - 16.0F, CLEAR);
        return new FogPlanes(clamped * 0.05F * fogFactor, Math.min(clamped, 192.0F) * 0.5F * fogFactor);
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
            byBiome.put(RegistryKey.of(RegistryKeys.BIOME, new Identifier(Nyctophobia.MOD_ID, biome)), entry);
        });

        return byBiome;
    }

    public record FogPlanes(float near, float far) {}
}
