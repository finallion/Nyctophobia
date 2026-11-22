package com.lion.nyctophobia.world.biomes;

import com.lion.nyctophobia.init.NPlacedFeatures;
import com.lion.nyctophobia.data.NRegistryContext;
import net.minecraft.sound.MusicType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.sound.BiomeAdditionsSound;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.BiomeParticleConfig;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

public class BurntForest {

    public static Biome createBurntForest(NRegistryContext<Biome> entries) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        spawnSettings.spawn(SpawnGroup.MONSTER, 10, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 2, 4));
        spawnSettings.spawn(SpawnGroup.MONSTER, 5, new SpawnSettings.SpawnEntry(EntityType.WITHER_SKELETON, 1, 2));
        spawnSettings.spawn(SpawnGroup.MONSTER, 4, new SpawnSettings.SpawnEntry(EntityType.SILVERFISH, 1, 2));
        spawnSettings.spawn(SpawnGroup.CREATURE, 10, new SpawnSettings.SpawnEntry(EntityType.BAT, 1, 2));
        DefaultBiomeFeatures.addBatsAndMonsters(spawnSettings);

        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(entries.placedFeatures(), entries.configuredCarvers());
        addBasicFeatures(generationSettings);
        addBurntFeatures(generationSettings, entries);

        return (new Biome.Builder())
                .precipitation(true)
                .temperature(0.5F)
                .downfall(0.2F)
                .effects((new BiomeEffects.Builder())
                        .grassColor(0x737068)
                        .foliageColor(0x5E5A52)
                        .waterColor(0x63615A)
                        .waterFogColor(0x4A4842)
                        .fogColor(0x585858)
                        .skyColor(0x585858)
                        .particleConfig(new BiomeParticleConfig(ParticleTypes.ASH, 0.09F))
                        .music(MusicType.GAME)
                        .loopSound(SoundEvents.AMBIENT_BASALT_DELTAS_LOOP)
                        .moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0D))
                        .additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111D))
                        .build())
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }

    private static void addBasicFeatures(GenerationSettings.LookupBackedBuilder generationSettings) {
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addAmethystGeodes(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addSprings(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings);
    }

    /// Feature order must agree across all biomes, or chunk generation fails.
    private static void addBurntFeatures(
            GenerationSettings.LookupBackedBuilder generationSettings,
            NRegistryContext<Biome> entries
    ) {
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_LARGE_FERN);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_DEAD_BUSH);

        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, entries.ref(NPlacedFeatures.BURNT_FOREST_TREES_PLACED));
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, entries.ref(NPlacedFeatures.BURNT_LICHEN_FEATURE));
    }
}
