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

public class HauntedForestBiomes {

    public static Biome createHauntedForest(NRegistryContext<Biome> entries) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        spawnSettings.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityTypes.FOX, 2, 3));
        spawnSettings.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityTypes.WOLF, 3, 5));
        spawnSettings.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityTypes.SHEEP, 3, 5));
        spawnSettings.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityTypes.PIG, 3, 5));
        spawnSettings.addSpawn(MobCategory.CREATURE, 1, new MobSpawnSettings.SpawnerData(EntityTypes.CAT, 1, 1));
        BiomeDefaultFeatures.commonSpawns(spawnSettings);

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(entries.placedFeatures(), entries.configuredCarvers());

        addBasicFeatures(generationSettings);
        addTaigaFeatures(generationSettings);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, entries.ref(NPlacedFeatures.HAUNTED_FOREST_TREES_PLACED));
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, entries.ref(NPlacedFeatures.MOSS_CARPET_FEATURE));
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, entries.ref(NPlacedFeatures.COBWEB_FEATURE));
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, entries.ref(NPlacedFeatures.BUSH_FEATURE));

        return (new Biome.BiomeBuilder())
                .hasPrecipitation(true)
                .temperature(0.6F)
                .downfall(0.9F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .grassColorOverride(0x6F932A)
                        .foliageColorOverride(0x6F932A)
                        .waterColor(0xAEC1BE)
                        .build())
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0xC9DDDA)
                .setAttribute(EnvironmentAttributes.FOG_COLOR, 0x878787)
                .setAttribute(EnvironmentAttributes.SKY_COLOR, 0x878787)
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(Musics.GAME))
                .setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(Optional.of(SoundEvents.AMBIENT_WARPED_FOREST_LOOP), Optional.of(new AmbientMoodSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0D)), List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111D))))
                .mobSpawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }


    public static Biome createHauntedLakes(NRegistryContext<Biome> entries) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        spawnSettings.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityTypes.FOX, 2, 3));
        spawnSettings.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityTypes.WOLF, 3, 5));
        spawnSettings.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityTypes.SHEEP, 3, 5));
        spawnSettings.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityTypes.PIG, 3, 5));
        BiomeDefaultFeatures.commonSpawns(spawnSettings);

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(entries.placedFeatures(), entries.configuredCarvers());
        addBasicFeatures(generationSettings);
        addHauntedLakesFeatures(generationSettings);

        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, entries.ref(NPlacedFeatures.HAUNTED_FOREST_TREES_PLACED));
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, entries.ref(NPlacedFeatures.MOSS_CARPET_FEATURE));
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, entries.ref(NPlacedFeatures.COBWEB_FEATURE));
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, entries.ref(NPlacedFeatures.BUSH_FEATURE));
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, entries.ref(NPlacedFeatures.SOUL_LIGHT_FEATURE));

        return (new Biome.BiomeBuilder())
                .hasPrecipitation(true)
                .temperature(0.6F)
                .downfall(0.9F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .grassColorOverride(0x7EA530)
                        .foliageColorOverride(0x7EA530)
                        .waterColor(0x9B091C)
                        .build())
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0xD80D28)
                .setAttribute(EnvironmentAttributes.FOG_COLOR, 0xB2B2B2)
                .setAttribute(EnvironmentAttributes.SKY_COLOR, 0xB2B2B2)
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(Musics.GAME))
                .setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(Optional.of(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP), Optional.of(new AmbientMoodSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0D)), List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111D))))
                .mobSpawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();

    }


    public static Biome createErodedHauntedForest(NRegistryContext<Biome> entries) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        spawnSettings.addSpawn(MobCategory.MONSTER, 8, new MobSpawnSettings.SpawnerData(EntityTypes.WITHER_SKELETON, 2, 3));
        spawnSettings.addSpawn(MobCategory.MONSTER, 8, new MobSpawnSettings.SpawnerData(EntityTypes.SKELETON, 1, 2));
        spawnSettings.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityTypes.BAT, 1, 1));
        spawnSettings.addSpawn(MobCategory.CREATURE, 1, new MobSpawnSettings.SpawnerData(EntityTypes.CAT, 1, 1));

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(entries.placedFeatures(), entries.configuredCarvers());

        addBasicFeatures(generationSettings);
        addTaigaFeatures(generationSettings);


        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, entries.ref(NPlacedFeatures.ERODED_HAUNTED_FOREST_TREES_PLACED));
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, entries.ref(NPlacedFeatures.FALLEN_TREE_FEATURE));
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, entries.ref(NPlacedFeatures.COBWEB_FEATURE));
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, entries.ref(NPlacedFeatures.BUSH_FEATURE));
         

        return (new Biome.BiomeBuilder())
                .hasPrecipitation(true)
                .temperature(0.4F)
                .downfall(0.4F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .grassColorOverride(0x77AB2F)
                        .foliageColorOverride(0x77AB2F)
                        .waterColor(0xAEC1BE)
                        .build())
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0xC9DDDA)
                .setAttribute(EnvironmentAttributes.FOG_COLOR, 0x6B6B6B)
                .setAttribute(EnvironmentAttributes.SKY_COLOR, 0x6B6B6B)
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
    }

    private static void addTaigaFeatures(BiomeGenerationSettings.Builder generationSettings) {
        BiomeDefaultFeatures.addMossyStoneBlock(generationSettings);
        BiomeDefaultFeatures.addFerns(generationSettings);
        BiomeDefaultFeatures.addDefaultOres(generationSettings);
        BiomeDefaultFeatures.addDefaultSoftDisks(generationSettings);
        BiomeDefaultFeatures.addGiantTaigaVegetation(generationSettings);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addDefaultExtraVegetation(generationSettings, true);

    }


    private static void addHauntedLakesFeatures(BiomeGenerationSettings.Builder generationSettings) {
        BiomeDefaultFeatures.addDefaultOres(generationSettings);
        BiomeDefaultFeatures.addSwampClayDisk(generationSettings);
        BiomeDefaultFeatures.addMossyStoneBlock(generationSettings);
        BiomeDefaultFeatures.addFerns(generationSettings);
        BiomeDefaultFeatures.addGiantTaigaVegetation(generationSettings);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_WATERLILY);
        BiomeDefaultFeatures.addDefaultMushrooms(generationSettings);
        BiomeDefaultFeatures.addSwampExtraVegetation(generationSettings);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP);
    }



}
