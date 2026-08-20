package com.lion.nyctophobia.world.biomes;

import com.lion.nyctophobia.init.NPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.sound.MusicType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.sound.BiomeAdditionsSound;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.OceanPlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

/// Open wetland: standing water, low scrub and candle flames, and no trees at all. The vegetation is
/// listed out rather than taken from DefaultBiomeFeatures.addSwampVegetation, because that bundle
/// carries TREES_SWAMP with it and the whole point of this biome is the open horizon.
public class Marshlands {

    public static Biome createMarshlands(FabricDynamicRegistryProvider.Entries entries) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.FROG, 10, 2, 5));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 8, 2, 4));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.BAT, 10, 1, 2));
        spawnSettings.spawn(SpawnGroup.AMBIENT, new SpawnSettings.SpawnEntry(EntityType.BAT, 10, 8, 8));
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SLIME, 8, 1, 2));
        spawnSettings.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.WITCH, 4, 1, 1));
        DefaultBiomeFeatures.addBatsAndMonsters(spawnSettings);

        GenerationSettings.LookupBackedBuilder generationSettings = new GenerationSettings.LookupBackedBuilder(entries.placedFeatures(), entries.configuredCarvers());
        addBasicFeatures(generationSettings);
        addWetlandFeatures(generationSettings, entries);

        return (new Biome.Builder())
                .precipitation(true)
                .temperature(0.7F)
                .downfall(0.9F)
                .effects((new BiomeEffects.Builder())
                        .grassColor(0x6E7A45)
                        .foliageColor(0x69793F)
                        .waterColor(0x53613A)
                        .waterFogColor(0x3B4529)
                        .fogColor(0x9AA098)
                        .skyColor(0x9AA098)
                        .music(MusicType.GAME)
                        .loopSound(SoundEvents.AMBIENT_WARPED_FOREST_LOOP)
                        .moodSound(new BiomeMoodSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0D))
                        .additionsSound(new BiomeAdditionsSound(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111D))
                        .build())
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }

    private static void addBasicFeatures(GenerationSettings.LookupBackedBuilder generationSettings) {
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addSprings(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addClayDisk(generationSettings);
    }

    /// Order matters beyond this biome: a feature's position has to agree across every biome in the
    /// world, or chunk generation dies with "Feature order cycle found". These follow vanilla swamp
    /// first, then this mod's own features in the order the haunted lakes already established.
    private static void addWetlandFeatures(GenerationSettings.LookupBackedBuilder generationSettings, FabricDynamicRegistryProvider.Entries entries) {
        // Dug before anything grows, so the vegetation steps see the pools and the waterlilies and
        // seagrass below have water to sit on.
        generationSettings.feature(GenerationStep.Feature.LAKES, entries.ref(NPlacedFeatures.MARSH_POOL_PLACED));

        // Vanilla puts large ferns and tall grass ahead of flowers and grass; keeping that order here
        // is what stops a cycle once another biome uses the same features.
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_LARGE_FERN);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_TALL_GRASS_2);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.FLOWER_SWAMP);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_NORMAL);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_DEAD_BUSH);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_WATERLILY);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.BROWN_MUSHROOM_SWAMP);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.RED_MUSHROOM_SWAMP);
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.SEAGRASS_SWAMP);

        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, entries.ref(NPlacedFeatures.MARSH_TREE_PLACED));
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, entries.ref(NPlacedFeatures.MARSH_THICKET_PLACED));
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, entries.ref(NPlacedFeatures.MARSH_ROOTS_PLACED));
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, entries.ref(NPlacedFeatures.MOSS_CARPET_FEATURE));
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, entries.ref(NPlacedFeatures.BUSH_FEATURE));
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, entries.ref(NPlacedFeatures.SOUL_LIGHT_FEATURE));
        generationSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, entries.ref(NPlacedFeatures.MARSH_CANDLE_FEATURE));
    }
}
