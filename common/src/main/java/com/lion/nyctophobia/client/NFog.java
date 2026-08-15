package com.lion.nyctophobia.client;

import com.lion.nyctophobia.Nyctophobia;
import com.lion.nyctophobia.config.BiomeFogConfigEntry;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

/// All per-biome fog behaviour lives here so a port only has to re-aim the loader hook (a mixin on
/// Fabric, a render event on Forge) instead of rewriting the logic every MC version.
///
/// This computes and returns the plane distances rather than applying them, because Forge overwrites
/// whatever a handler sets directly and only honours values fed back through its event.
public final class NFog {

    /// Fog is off at this factor. The value eases towards it instead of snapping, so leaving a foggy
    /// biome fades out rather than popping.
    private static final float NO_FOG = 32.0F;
    private static final float FADE_IN = 0.975F;
    private static final float FADE_OUT = 1.05F;

    private static final String[] FOGGY_BIOMES = {"haunted_forest", "haunted_lakes", "eroded_haunted_forest"};

    private static float fogStart = NO_FOG;

    private NFog() {}

    /// Near and far plane distance for this frame, or null to leave vanilla fog untouched.
    public static float[] compute(Camera camera, float viewDistance, BlockPos pos, String biomePath) {
        float density = densityFor(biomePath, pos.getY());

        if (density < 0.0F) {
            if (fogStart >= NO_FOG) {
                fogStart = NO_FOG;
                return null;
            }
            fogStart *= FADE_OUT;
        }

        if (camera.getSubmersionType() != CameraSubmersionType.NONE) {
            return null;
        }

        Entity entity = camera.getFocusedEntity();
        if (entity instanceof LivingEntity living && living.hasStatusEffect(StatusEffects.BLINDNESS)) {
            return null;
        }

        if (density >= 0.0F && fogStart > density) {
            fogStart *= FADE_IN;
        }

        float clamped = Math.max(viewDistance - 16.0F, NO_FOG);
        return new float[] {
                clamped * 0.05F * fogStart,
                Math.min(clamped, 192.0F) * 0.5F * fogStart
        };
    }

    /// Negative means no fog is configured here. A Y outside the configured band counts as no fog, so
    /// climbing out of a valley fades the fog away.
    private static float densityFor(String biomePath, int y) {
        if (!anyFogEnabled()) {
            return -1.0F;
        }

        for (String biome : FOGGY_BIOMES) {
            if (!biome.equals(biomePath)) {
                continue;
            }
            Identifier id = fogId(biome);
            if (!Nyctophobia.config.biomeFogSpawn(id)) {
                return -1.0F;
            }
            BiomeFogConfigEntry entry = Nyctophobia.config.getFog(id);
            if (y > entry.maxY || y < entry.minY) {
                return -1.0F;
            }
            return entry.density;
        }
        return -1.0F;
    }

    private static boolean anyFogEnabled() {
        for (String biome : FOGGY_BIOMES) {
            if (Nyctophobia.config.biomeFogSpawn(fogId(biome))) {
                return true;
            }
        }
        return false;
    }

    private static Identifier fogId(String biome) {
        return new Identifier(Nyctophobia.MOD_ID, biome + "_fog");
    }
}
