package com.lion.nyctophobia.world.biomes;

import com.lion.nyctophobia.init.NPlacedFeatures;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
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
import net.minecraft.world.attribute.BackgroundMusic;
import com.lion.nyctophobia.data.NRegistryContext;

/// Do not use addSwampVegetation. It adds swamp trees.
public class Marshlands {

    public static Biome createMarshlands(NRegistryContext<Biome> entries) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        spawnSettings.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityTypes.FROG, 2, 5));
        spawnSettings.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityTypes.SHEEP, 2, 4));
        spawnSettings.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityTypes.BAT, 1, 2));
        spawnSettings.addSpawn(MobCategory.AMBIENT, 10, new MobSpawnSettings.SpawnerData(EntityTypes.BAT, 8, 8));
        spawnSettings.addSpawn(MobCategory.MONSTER, 8, new MobSpawnSettings.SpawnerData(EntityTypes.SLIME, 1, 2));
        spawnSettings.addSpawn(MobCategory.MONSTER, 4, new MobSpawnSettings.SpawnerData(EntityTypes.WITCH, 1, 1));
        BiomeDefaultFeatures.commonSpawns(spawnSettings);

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(entries.placedFeatures(), entries.configuredCarvers());
        addBasicFeatures(generationSettings);
        addWetlandFeatures(generationSettings, entries);

        return (new Biome.BiomeBuilder())
                .hasPrecipitation(true)
                .temperature(0.7F)
                .downfall(0.9F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .grassColorOverride(0x6E7A45)
                        .foliageColorOverride(0x69793F)
                        .waterColor(0x53613A)
                        .build())
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x3B4529)
                .setAttribute(EnvironmentAttributes.FOG_COLOR, 0x9AA098)
                .setAttribute(EnvironmentAttributes.SKY_COLOR, 0x9AA098)
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(Musics.GAME))
                .setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(Optional.of(SoundEvents.AMBIENT_WARPED_FOREST_LOOP), Optional.of(new AmbientMoodSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0D)), List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111D))))
                .mobSpawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }

    private static void addBasicFeatures(BiomeGenerationSettings.Builder generationSettings) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(generationSettings);
        BiomeDefaultFeatures.addDefaultMonsterRoom(generationSettings);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(generationSettings);
        BiomeDefaultFeatures.addDefaultSprings(generationSettings);
        BiomeDefaultFeatures.addSurfaceFreezing(generationSettings);
        BiomeDefaultFeatures.addDefaultOres(generationSettings);
        BiomeDefaultFeatures.addSwampClayDisk(generationSettings);
    }

    /// Feature order must agree across all biomes, or chunk generation fails.
    private static void addWetlandFeatures(
            BiomeGenerationSettings.Builder generationSettings,
            NRegistryContext<Biome> entries
    ) {
        generationSettings.addFeature(GenerationStep.Decoration.LAKES, entries.ref(NPlacedFeatures.MARSH_POOL_PLACED));

        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_LARGE_FERN);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_TALL_GRASS_2);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FLOWER_SWAMP);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_NORMAL);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_WATERLILY);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.BROWN_MUSHROOM_SWAMP);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.RED_MUSHROOM_SWAMP);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP);

        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, entries.ref(NPlacedFeatures.MARSH_THICKET_PLACED));
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, entries.ref(NPlacedFeatures.MARSH_ROOTS_PLACED));
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, entries.ref(NPlacedFeatures.MARSH_CANDLE_FEATURE));
    }
}
