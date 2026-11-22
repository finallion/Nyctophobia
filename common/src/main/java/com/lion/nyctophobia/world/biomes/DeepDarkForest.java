package com.lion.nyctophobia.world.biomes;

import com.lion.nyctophobia.init.NPlacedFeatures;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
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
import net.minecraft.world.attribute.BackgroundMusic;
import com.lion.nyctophobia.data.NRegistryContext;


public class DeepDarkForest {

    public static Biome createDeepDarkForest(NRegistryContext<Biome> entries) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        spawnSettings.addSpawn(MobCategory.CREATURE, 8, new MobSpawnSettings.SpawnerData(EntityType.FOX, 2, 3));
        spawnSettings.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.SHEEP, 3, 5));
        spawnSettings.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.COW, 3, 5));
        spawnSettings.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityType.PIG, 3, 5));
        BiomeDefaultFeatures.commonSpawns(spawnSettings);

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(entries.placedFeatures(), entries.configuredCarvers());
        addBasicFeatures(generationSettings);
        addTaigaFeatures(generationSettings);
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, entries.ref(NPlacedFeatures.DEEP_DARK_FOREST_VEGETATION_PLACED));
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, entries.ref(NPlacedFeatures.TG_PATCH_LARGE_FERN));
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, entries.ref(NPlacedFeatures.DEEP_DARK_FOREST_BUSH_FEATURE));
        generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, entries.ref(NPlacedFeatures.TG_PATCH_MEADOW_FLOWER));

        return (new Biome.BiomeBuilder())
                .hasPrecipitation(true)
                .temperature(0.7F)
                .downfall(0.8F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .grassColorOverride(0x6D902C)
                        .foliageColorOverride(0x407F23)
                        .waterColor(0x9B091C)
                        .build())
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0xD80D28)
                .setAttribute(EnvironmentAttributes.FOG_COLOR, 12638463)
                .setAttribute(EnvironmentAttributes.SKY_COLOR, getSkyColor(0.7F))
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(Musics.GAME))
                .setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(Optional.of(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP), Optional.of(new AmbientMoodSettings(SoundEvents.AMBIENT_CRIMSON_FOREST_MOOD, 6000, 8, 2.0D)), List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS, 0.0111D))))
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

    protected static int getSkyColor(float temperature) {
        float f = temperature / 3.0F;
        f = Mth.clamp(f, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
    }

}
