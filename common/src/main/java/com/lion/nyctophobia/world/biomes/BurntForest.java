package com.lion.nyctophobia.world.biomes;

import com.lion.nyctophobia.init.NPlacedFeatures;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.attribute.AmbientMoodSettings;
import net.minecraft.world.attribute.AmbientAdditionsSettings;
import net.minecraft.world.attribute.AmbientSounds;
import java.util.List;
import java.util.Optional;
import net.minecraft.world.attribute.AmbientParticle;
import net.minecraft.world.attribute.BackgroundMusic;
import com.lion.nyctophobia.data.NRegistryContext;

public class BurntForest {

    public static Biome createBurntForest(NRegistryContext<Biome> entries) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        spawnSettings.addSpawn(MobCategory.MONSTER, 10, new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 2, 4));
        spawnSettings.addSpawn(MobCategory.MONSTER, 5, new MobSpawnSettings.SpawnerData(EntityType.WITHER_SKELETON, 1, 2));
        spawnSettings.addSpawn(MobCategory.MONSTER, 4, new MobSpawnSettings.SpawnerData(EntityType.SILVERFISH, 1, 2));
        spawnSettings.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.BAT, 1, 2));
        BiomeDefaultFeatures.commonSpawns(spawnSettings);

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(entries.placedFeatures(), entries.configuredCarvers());
        addBasicFeatures(generationSettings);
        addBurntFeatures(generationSettings, entries);

        return (new Biome.BiomeBuilder())
                .hasPrecipitation(true)
                .temperature(0.5F)
                .downfall(0.2F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .grassColorOverride(0x737068)
                        .foliageColorOverride(0x5E5A52)
                        .waterColor(0x63615A)
                        .build())
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x4A4842)
                .setAttribute(EnvironmentAttributes.FOG_COLOR, 0x585858)
                .setAttribute(EnvironmentAttributes.SKY_COLOR, 0x585858)
                .setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, AmbientParticle.of(ParticleTypes.ASH, 0.09F))
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(Musics.GAME))
                .setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(Optional.of(SoundEvents.AMBIENT_BASALT_DELTAS_LOOP), Optional.of(new AmbientMoodSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0D)), List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111D))))
                .mobSpawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }

    private static void addBasicFeatures(BiomeGenerationSettings.Builder generationSettings) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(generationSettings);
        BiomeDefaultFeatures.addDefaultCrystalFormations(generationSettings);
        BiomeDefaultFeatures.addDefaultMonsterRoom(generationSettings);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(generationSettings);
        BiomeDefaultFeatures.addDefaultSprings(generationSettings);
        BiomeDefaultFeatures.addSurfaceFreezing(generationSettings);
        BiomeDefaultFeatures.addDefaultOres(generationSettings);
        BiomeDefaultFeatures.addDefaultSoftDisks(generationSettings);
    }

    /// Feature order must agree across all biomes, or chunk generation fails.
    private static void addBurntFeatures(
            BiomeGenerationSettings.Builder generationSettings,
            NRegistryContext<Biome> entries
    ) {
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_LARGE_FERN);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH);

        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, entries.ref(NPlacedFeatures.BURNT_FOREST_TREES_PLACED));
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, entries.ref(NPlacedFeatures.BURNT_LICHEN_FEATURE));
    }
}
