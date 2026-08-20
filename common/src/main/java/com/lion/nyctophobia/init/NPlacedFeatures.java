package com.lion.nyctophobia.init;

import com.lion.nyctophobia.Nyctophobia;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;

import java.util.List;

public class NPlacedFeatures {

    public static void init() {}

    public static RegistryKey<PlacedFeature> of(String id) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(Nyctophobia.MOD_ID, id));
    }

    public static final RegistryKey<PlacedFeature> MOSS_CARPET_FEATURE = of("moss_carpet_feature");
    public static final RegistryKey<PlacedFeature> COBWEB_FEATURE = of("cobweb_feature");
    public static final RegistryKey<PlacedFeature> BUSH_FEATURE = of("bush_feature");
    public static final RegistryKey<PlacedFeature> DEEP_DARK_FOREST_BUSH_FEATURE = of("deep_dark_forest_bush_feature");
    public static final RegistryKey<PlacedFeature> SOUL_LIGHT_FEATURE = of("soul_light_feature");
    public static final RegistryKey<PlacedFeature> FALLEN_TREE_FEATURE = of("fallen_tree_feature");
    public static final RegistryKey<PlacedFeature> DEAD_CORAL_PATCH_FEATURE = of("dead_coral_patch_feature");
    public static final RegistryKey<PlacedFeature> DEEP_DARK_OAK = of("deep_dark_oak");
    public static final RegistryKey<PlacedFeature> MARSH_CANDLE_FEATURE = of("marsh_candle_feature");
    public static final RegistryKey<PlacedFeature> MARSH_ROOTS_PLACED = of("marsh_roots");
    public static final RegistryKey<PlacedFeature> MARSH_POOL_PLACED = of("marsh_pool");
    public static final RegistryKey<PlacedFeature> MARSH_TREE_PLACED = of("marsh_tree");
    public static final RegistryKey<PlacedFeature> MARSH_THICKET_PLACED = of("marsh_thicket");
    public static final RegistryKey<PlacedFeature> TG_PATCH_LARGE_FERN = of("tg_patch_large_fern_config");
    public static final RegistryKey<PlacedFeature> TG_PATCH_LEAVES = of("tg_patch_leaves_config");
    public static final RegistryKey<PlacedFeature> TG_PATCH_MEADOW_FLOWER = of("tg_patch_meadow_flower_config");

    public static final RegistryKey<PlacedFeature> HAUNTED_FOREST_TREES_PLACED = of("haunted_forest_trees");
    public static final RegistryKey<PlacedFeature> ERODED_HAUNTED_FOREST_TREES_PLACED = of("eroded_haunted_forest_trees");
    public static final RegistryKey<PlacedFeature> BURNT_FOREST_TREES_PLACED = of("burnt_forest_trees");
    public static final RegistryKey<PlacedFeature> BURNT_LICHEN_FEATURE = of("burnt_lichen_feature");
    public static final RegistryKey<PlacedFeature> ANCIENT_DEAD_REEF_VEGETATION_PLACED = of("ancient_dead_reef_vegetation");
    public static final RegistryKey<PlacedFeature> ANCIENT_DEAD_REEF_WATER_VEGETATION_PLACED = of("ancient_dead_reef_water_vegetation");
    public static final RegistryKey<PlacedFeature> DEEP_DARK_FOREST_VEGETATION_PLACED = of("deep_dark_forest_vegetation");

    public static final RegistryKey<ConfiguredFeature<?, ?>> HAUNTED_FOREST_TREES = NConfiguredFeatures.of("haunted_forest_trees");
    public static final RegistryKey<ConfiguredFeature<?, ?>> ERODED_HAUNTED_FOREST_TREES = NConfiguredFeatures.of("eroded_haunted_forest_trees");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BURNT_FOREST_TREES = NConfiguredFeatures.of("burnt_forest_trees");
    
    public static final RegistryKey<ConfiguredFeature<?, ?>> ANCIENT_DEAD_REEF_VEGETATION = NConfiguredFeatures.of("ancient_dead_reef_vegetation");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DEEP_DARK_FOREST_VEGETATION = NConfiguredFeatures.of("deep_dark_forest_vegetation");

    public static final RegistryKey<PlacedFeature> SMALL_SPRUCE_TREE_01_STRIPPED = of("small_spruce_tree_stripped_01");
    public static final RegistryKey<PlacedFeature> SMALL_SPRUCE_TREE_01 = of("small_spruce_tree_01");
    public static final RegistryKey<PlacedFeature> SMALL_SPRUCE_TREE_02_STRIPPED = of("small_spruce_tree_stripped_02");
    public static final RegistryKey<PlacedFeature> SMALL_SPRUCE_TREE_02 = of("small_spruce_tree_02");
    public static final RegistryKey<PlacedFeature> SMALL_SPRUCE_TREE_03_STRIPPED = of("small_spruce_tree_stripped_03");
    public static final RegistryKey<PlacedFeature> SMALL_SPRUCE_TREE_03 = of("small_spruce_tree_03");
    public static final RegistryKey<PlacedFeature> SMALL_SPRUCE_TREE_04_STRIPPED = of("small_spruce_tree_stripped_04");
    public static final RegistryKey<PlacedFeature> SMALL_SPRUCE_TREE_04 = of("small_spruce_tree_04");
    public static final RegistryKey<PlacedFeature> SMALL_SPRUCE_TREE_05_STRIPPED = of("small_spruce_tree_stripped_05");
    public static final RegistryKey<PlacedFeature> SMALL_SPRUCE_TREE_05 = of("small_spruce_tree_05");
    public static final RegistryKey<PlacedFeature> SMALL_SPRUCE_TREE_06_STRIPPED = of("small_spruce_tree_stripped_06");
    public static final RegistryKey<PlacedFeature> SMALL_SPRUCE_TREE_06 = of("small_spruce_tree_06");
    public static final RegistryKey<PlacedFeature> SMALL_BENT_SPRUCE_TREE_01_STRIPPED = of("small_bent_spruce_tree_stripped_01");
    public static final RegistryKey<PlacedFeature> SMALL_BENT_SPRUCE_TREE_01 = of("small_bent_spruce_tree_01");
    public static final RegistryKey<PlacedFeature> FALLEN_SPRUCE_TREE_STRIPPED = of("fallen_spruce_stripped_tree");
    public static final RegistryKey<PlacedFeature> FALLEN_SPRUCE_TREE = of("fallen_spruce_tree");
    public static final RegistryKey<PlacedFeature> LARGE_SPRUCE_TREE_01_STRIPPED = of("large_spruce_tree_stripped_01");
    public static final RegistryKey<PlacedFeature> LARGE_SPRUCE_TREE_01 = of("large_spruce_tree_01");
    public static final RegistryKey<PlacedFeature> LARGE_SPRUCE_TREE_02_STRIPPED = of("large_spruce_tree_stripped_02");
    public static final RegistryKey<PlacedFeature> LARGE_SPRUCE_TREE_02 = of("large_spruce_tree_02");
    public static final RegistryKey<PlacedFeature> LARGE_SPRUCE_TREE_03_STRIPPED = of("large_spruce_tree_stripped_03");
    public static final RegistryKey<PlacedFeature> LARGE_SPRUCE_TREE_03 = of("large_spruce_tree_03");
    public static final RegistryKey<PlacedFeature> LARGE_BENT_SPRUCE_TREE_01_STRIPPED = of("large_bent_spruce_tree_stripped_01");
    public static final RegistryKey<PlacedFeature> LARGE_BENT_SPRUCE_TREE_01 = of("large_bent_spruce_tree_01");
    public static final RegistryKey<PlacedFeature> LARGE_BENT_SPRUCE_TREE_02_STRIPPED = of("large_bent_spruce_tree_stripped_02");
    public static final RegistryKey<PlacedFeature> LARGE_BENT_SPRUCE_TREE_02 = of("large_bent_spruce_tree_02");


    public static PlacedFeature placeFeature(FabricDynamicRegistryProvider.Entries entries, RegistryKey<ConfiguredFeature<?, ?>> feature, PlacementModifier... list) {
        return new PlacedFeature(entries.ref(feature), List.of(list));
    }


    public static void populate(FabricDynamicRegistryProvider.Entries entries) {
        entries.add(SMALL_SPRUCE_TREE_01, placeFeature(entries, NConfiguredFeatures.SMALL_SPRUCE_TREE_01, PlacedFeatures.wouldSurvive(Blocks.SPRUCE_SAPLING)));
        entries.add(SMALL_SPRUCE_TREE_01_STRIPPED, placeFeature(entries, NConfiguredFeatures.SMALL_SPRUCE_TREE_01_STRIPPED, PlacedFeatures.wouldSurvive(Blocks.SPRUCE_SAPLING)));
        entries.add(SMALL_SPRUCE_TREE_02, placeFeature(entries, NConfiguredFeatures.SMALL_SPRUCE_TREE_02, PlacedFeatures.wouldSurvive(Blocks.SPRUCE_SAPLING)));
        entries.add(SMALL_SPRUCE_TREE_02_STRIPPED, placeFeature(entries, NConfiguredFeatures.SMALL_SPRUCE_TREE_02_STRIPPED, PlacedFeatures.wouldSurvive(Blocks.SPRUCE_SAPLING)));
        entries.add(SMALL_SPRUCE_TREE_03, placeFeature(entries, NConfiguredFeatures.SMALL_SPRUCE_TREE_03, PlacedFeatures.wouldSurvive(Blocks.SPRUCE_SAPLING)));
        entries.add(SMALL_SPRUCE_TREE_03_STRIPPED, placeFeature(entries, NConfiguredFeatures.SMALL_SPRUCE_TREE_03_STRIPPED, PlacedFeatures.wouldSurvive(Blocks.SPRUCE_SAPLING)));
        entries.add(SMALL_SPRUCE_TREE_04, placeFeature(entries, NConfiguredFeatures.SMALL_SPRUCE_TREE_04, PlacedFeatures.wouldSurvive(Blocks.SPRUCE_SAPLING)));
        entries.add(SMALL_SPRUCE_TREE_04_STRIPPED, placeFeature(entries, NConfiguredFeatures.SMALL_SPRUCE_TREE_04_STRIPPED, PlacedFeatures.wouldSurvive(Blocks.SPRUCE_SAPLING)));
        entries.add(SMALL_SPRUCE_TREE_05, placeFeature(entries, NConfiguredFeatures.SMALL_SPRUCE_TREE_05, PlacedFeatures.wouldSurvive(Blocks.SPRUCE_SAPLING)));
        entries.add(SMALL_SPRUCE_TREE_05_STRIPPED, placeFeature(entries, NConfiguredFeatures.SMALL_SPRUCE_TREE_05_STRIPPED, PlacedFeatures.wouldSurvive(Blocks.SPRUCE_SAPLING)));
        entries.add(SMALL_SPRUCE_TREE_06, placeFeature(entries, NConfiguredFeatures.SMALL_SPRUCE_TREE_06, PlacedFeatures.wouldSurvive(Blocks.SPRUCE_SAPLING)));
        entries.add(SMALL_SPRUCE_TREE_06_STRIPPED, placeFeature(entries, NConfiguredFeatures.SMALL_SPRUCE_TREE_06_STRIPPED, PlacedFeatures.wouldSurvive(Blocks.SPRUCE_SAPLING)));
        entries.add(SMALL_BENT_SPRUCE_TREE_01, placeFeature(entries, NConfiguredFeatures.SMALL_BENT_SPRUCE_TREE_01, PlacedFeatures.wouldSurvive(Blocks.SPRUCE_SAPLING)));
        entries.add(SMALL_BENT_SPRUCE_TREE_01_STRIPPED, placeFeature(entries, NConfiguredFeatures.SMALL_BENT_SPRUCE_TREE_01_STRIPPED, PlacedFeatures.wouldSurvive(Blocks.SPRUCE_SAPLING)));
        entries.add(LARGE_BENT_SPRUCE_TREE_01, placeFeature(entries, NConfiguredFeatures.LARGE_BENT_SPRUCE_TREE_01, PlacedFeatures.wouldSurvive(Blocks.SPRUCE_SAPLING)));
        entries.add(LARGE_BENT_SPRUCE_TREE_01_STRIPPED, placeFeature(entries, NConfiguredFeatures.LARGE_BENT_SPRUCE_TREE_01_STRIPPED, PlacedFeatures.wouldSurvive(Blocks.SPRUCE_SAPLING)));
        entries.add(LARGE_BENT_SPRUCE_TREE_02, placeFeature(entries, NConfiguredFeatures.LARGE_BENT_SPRUCE_TREE_02, PlacedFeatures.wouldSurvive(Blocks.SPRUCE_SAPLING)));
        entries.add(LARGE_BENT_SPRUCE_TREE_02_STRIPPED, placeFeature(entries, NConfiguredFeatures.LARGE_BENT_SPRUCE_TREE_02_STRIPPED, PlacedFeatures.wouldSurvive(Blocks.SPRUCE_SAPLING)));
        entries.add(FALLEN_SPRUCE_TREE, placeFeature(entries, NConfiguredFeatures.FALLEN_SPRUCE_TREE, PlacedFeatures.wouldSurvive(Blocks.SPRUCE_SAPLING)));
        entries.add(FALLEN_SPRUCE_TREE_STRIPPED, placeFeature(entries, NConfiguredFeatures.FALLEN_SPRUCE_TREE_STRIPPED, PlacedFeatures.wouldSurvive(Blocks.SPRUCE_SAPLING)));
        entries.add(LARGE_SPRUCE_TREE_01, placeFeature(entries, NConfiguredFeatures.LARGE_SPRUCE_TREE_01, PlacedFeatures.wouldSurvive(Blocks.SPRUCE_SAPLING)));
        entries.add(LARGE_SPRUCE_TREE_01_STRIPPED, placeFeature(entries, NConfiguredFeatures.LARGE_SPRUCE_TREE_01_STRIPPED, PlacedFeatures.wouldSurvive(Blocks.SPRUCE_SAPLING)));
        entries.add(LARGE_SPRUCE_TREE_02, placeFeature(entries, NConfiguredFeatures.LARGE_SPRUCE_TREE_02, PlacedFeatures.wouldSurvive(Blocks.SPRUCE_SAPLING)));
        entries.add(LARGE_SPRUCE_TREE_02_STRIPPED, placeFeature(entries, NConfiguredFeatures.LARGE_SPRUCE_TREE_02_STRIPPED, PlacedFeatures.wouldSurvive(Blocks.SPRUCE_SAPLING)));
        entries.add(LARGE_SPRUCE_TREE_03, placeFeature(entries, NConfiguredFeatures.LARGE_SPRUCE_TREE_03, PlacedFeatures.wouldSurvive(Blocks.SPRUCE_SAPLING)));
        entries.add(LARGE_SPRUCE_TREE_03_STRIPPED, placeFeature(entries, NConfiguredFeatures.LARGE_SPRUCE_TREE_03_STRIPPED, PlacedFeatures.wouldSurvive(Blocks.SPRUCE_SAPLING)));

        entries.add(MOSS_CARPET_FEATURE, placeFeature(entries, NConfiguredFeatures.MOSS_CARPET_FEATURE, RarityFilterPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, CountPlacementModifier.of(100)));
        entries.add(COBWEB_FEATURE, placeFeature(entries, NConfiguredFeatures.COBWEB_FEATURE, RarityFilterPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, CountPlacementModifier.of(40)));
        entries.add(BUSH_FEATURE, placeFeature(entries, NConfiguredFeatures.BUSH_FEATURE, RarityFilterPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, CountPlacementModifier.of(35)));
        entries.add(DEEP_DARK_FOREST_BUSH_FEATURE, placeFeature(entries, NConfiguredFeatures.DEEP_DARK_FOREST_BUSH_FEATURE, RarityFilterPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, CountPlacementModifier.of(35)));
        entries.add(SOUL_LIGHT_FEATURE, placeFeature(entries, NConfiguredFeatures.SOUL_LIGHT_FEATURE, RarityFilterPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, CountPlacementModifier.of(100)));
        entries.add(FALLEN_TREE_FEATURE, placeFeature(entries, NConfiguredFeatures.FALLEN_TREE_FEATURE, RarityFilterPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, CountPlacementModifier.of(20)));
        entries.add(DEAD_CORAL_PATCH_FEATURE, placeFeature(entries, NConfiguredFeatures.DEAD_CORAL_PATCH_FEATURE, RarityFilterPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, CountPlacementModifier.of(35)));
        // Rare on purpose: a handful of flames across a marsh reads as something left behind, a
        // candle in every chunk reads as decoration.
        entries.add(MARSH_CANDLE_FEATURE, placeFeature(entries, NConfiguredFeatures.MARSH_CANDLE_FEATURE, RarityFilterPlacementModifier.of(6), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of()));
        // One tangle per chunk on average. Eight attempts used to bury the marsh in root stubs; a
        // tangle is a structure now, not a stub, and wants room around it.
        entries.add(MARSH_ROOTS_PLACED, placeFeature(entries, NConfiguredFeatures.MARSH_ROOTS_FEATURE, RarityFilterPlacementModifier.of(2), CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of()));
        // Many small patches rather than a few large ones is what makes a marsh read as wet, so this
        // count is the knob for the whole biome: each attempt covers six to eighteen columns.
        entries.add(MARSH_POOL_PLACED, placeFeature(entries, NConfiguredFeatures.MARSH_POOL_FEATURE, CountPlacementModifier.of(8), SquarePlacementModifier.of(), PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of()));
        // Roughly one tree per twelve chunks. The marsh lives off its open horizon, so a tree has to
        // be an event; a rarity filter is the only way to get below one attempt per chunk.
        entries.add(MARSH_TREE_PLACED, placeFeature(entries, NConfiguredFeatures.MARSH_TREE_FEATURE, RarityFilterPlacementModifier.of(12), CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of()));
        // Five clumps of six to fourteen columns each. Together with the puddles this is what makes
        // crossing the marsh a detour rather than a walk.
        entries.add(MARSH_THICKET_PLACED, placeFeature(entries, NConfiguredFeatures.MARSH_THICKET_FEATURE, CountPlacementModifier.of(5), SquarePlacementModifier.of(), PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of()));

        entries.add(DEEP_DARK_OAK, placeFeature(entries, NConfiguredFeatures.DEEP_DARK_OAK, PlacedFeatures.wouldSurvive(Blocks.DARK_OAK_SAPLING)));
        entries.add(TG_PATCH_LARGE_FERN, placeFeature(entries, NConfiguredFeatures.TG_PATCH_LARGE_FERN, RarityFilterPlacementModifier.of(5), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));
        entries.add(TG_PATCH_LEAVES, placeFeature(entries, NConfiguredFeatures.TG_PATCH_LEAVES, RarityFilterPlacementModifier.of(5), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));
        entries.add(TG_PATCH_MEADOW_FLOWER, placeFeature(entries, NConfiguredFeatures.TG_PATCH_MEADOW_FLOWER, SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));


        entries.add(HAUNTED_FOREST_TREES, NConfiguredFeatures.configureFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfig(List.of(
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.SMALL_SPRUCE_TREE_01), new PlacementModifier[0]), 0.25F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.SMALL_SPRUCE_TREE_02), new PlacementModifier[0]), 0.25F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.SMALL_SPRUCE_TREE_03), new PlacementModifier[0]), 0.375F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.SMALL_SPRUCE_TREE_04), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.SMALL_SPRUCE_TREE_05), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.SMALL_SPRUCE_TREE_06), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.SMALL_BENT_SPRUCE_TREE_01), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.FALLEN_SPRUCE_TREE), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.LARGE_SPRUCE_TREE_01), new PlacementModifier[0]), 0.25F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.LARGE_SPRUCE_TREE_02), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.LARGE_SPRUCE_TREE_03), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.SMALL_SPRUCE_TREE_01_STRIPPED)), 0.25F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.SMALL_SPRUCE_TREE_02_STRIPPED), new PlacementModifier[0]), 0.25F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.SMALL_SPRUCE_TREE_03_STRIPPED), new PlacementModifier[0]), 0.375F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.SMALL_SPRUCE_TREE_04_STRIPPED), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.SMALL_SPRUCE_TREE_05_STRIPPED), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.SMALL_SPRUCE_TREE_06_STRIPPED), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.SMALL_BENT_SPRUCE_TREE_01_STRIPPED), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.FALLEN_SPRUCE_TREE_STRIPPED), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.LARGE_SPRUCE_TREE_01_STRIPPED), new PlacementModifier[0]), 0.25F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.LARGE_SPRUCE_TREE_02_STRIPPED), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.LARGE_SPRUCE_TREE_03_STRIPPED), new PlacementModifier[0]), 0.1F)),
                entries.ref(TreePlacedFeatures.SPRUCE_CHECKED))));

        entries.add(ERODED_HAUNTED_FOREST_TREES, NConfiguredFeatures.configureFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfig(List.of(
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.SMALL_BENT_SPRUCE_TREE_01), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.FALLEN_SPRUCE_TREE), new PlacementModifier[0]), 0.0075F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.LARGE_BENT_SPRUCE_TREE_01), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.LARGE_BENT_SPRUCE_TREE_02), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.LARGE_SPRUCE_TREE_01), new PlacementModifier[0]), 0.5F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.LARGE_SPRUCE_TREE_02), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.LARGE_SPRUCE_TREE_03), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.SMALL_BENT_SPRUCE_TREE_01_STRIPPED), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.FALLEN_SPRUCE_TREE_STRIPPED), new PlacementModifier[0]), 0.0075F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.LARGE_BENT_SPRUCE_TREE_01_STRIPPED), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.LARGE_BENT_SPRUCE_TREE_02_STRIPPED), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.LARGE_SPRUCE_TREE_01_STRIPPED), new PlacementModifier[0]), 0.5F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.LARGE_SPRUCE_TREE_02_STRIPPED), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.LARGE_SPRUCE_TREE_03_STRIPPED), new PlacementModifier[0]), 0.1F)),
                entries.ref(TreePlacedFeatures.SPRUCE_CHECKED))));

        entries.add(BURNT_FOREST_TREES, NConfiguredFeatures.configureFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfig(List.of(
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.BURNT_TREE_SMALL_02), new PlacementModifier[0]), 0.2F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.BURNT_TREE_BENT), new PlacementModifier[0]), 0.2F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.BURNT_TREE_FALLEN), new PlacementModifier[0]), 0.2F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.BURNT_TREE_LARGE_BENT), new PlacementModifier[0]), 0.2F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.BURNT_TREE_LARGE), new PlacementModifier[0]), 0.3F)),
                PlacedFeatures.createEntry(entries.ref(NConfiguredFeatures.BURNT_TREE_SMALL_01), new PlacementModifier[0]))));

        entries.add(ANCIENT_DEAD_REEF_VEGETATION, NConfiguredFeatures.configureFeature(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfig(RegistryEntryList.of(
                PlacedFeatures.createEntry(NFeatures.DEAD_CORAL_TREE_FEATURE.get(), FeatureConfig.DEFAULT, new PlacementModifier[0]),
                PlacedFeatures.createEntry(NFeatures.DEAD_CORAL_CLAW_FEATURE.get(), FeatureConfig.DEFAULT, new PlacementModifier[0]),
                PlacedFeatures.createEntry(NFeatures.DEAD_CORAL_MUSHROOM_FEATURE.get(), FeatureConfig.DEFAULT, new PlacementModifier[0])))));


        // Entries are drawn in order and the first hit wins, so these are conditional chances, not
        // shares: the giant oak at 0.45 ends up on roughly 43 percent of the attempts and the
        // vanilla dark oak on the rest. At the previous 0.8667 the biome grew only one kind of tree.
        entries.add(DEEP_DARK_FOREST_VEGETATION, NConfiguredFeatures.configureFeature(Feature.RANDOM_SELECTOR, new RandomFeatureConfig(List.of(
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(TreeConfiguredFeatures.HUGE_BROWN_MUSHROOM), new PlacementModifier[0]), 0.025F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(entries.ref(TreeConfiguredFeatures.HUGE_RED_MUSHROOM), new PlacementModifier[0]), 0.025F),
                new RandomFeatureEntry(entries.ref(NPlacedFeatures.DEEP_DARK_OAK), 0.45F),
                new RandomFeatureEntry(entries.ref(TreePlacedFeatures.DARK_OAK_CHECKED), 0.4F)),
                entries.ref(TreePlacedFeatures.DARK_OAK_CHECKED))));


        entries.add(ERODED_HAUNTED_FOREST_TREES_PLACED, placeFeature(entries, ERODED_HAUNTED_FOREST_TREES, PlacedFeatures.createCountExtraModifier(10, 0.1F, 1), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of()));
        entries.add(HAUNTED_FOREST_TREES_PLACED, placeFeature(entries, HAUNTED_FOREST_TREES, PlacedFeatures.createCountExtraModifier(15, 0.1F, 1), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of()));
        // Far thinner than the living woods on purpose - a burnt forest is mostly the gaps.
        entries.add(BURNT_FOREST_TREES_PLACED, placeFeature(entries, BURNT_FOREST_TREES, PlacedFeatures.createCountExtraModifier(4, 0.1F, 1), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of()));
        // Runs after the trees so the trunks it is meant to grow on already stand.
        entries.add(BURNT_LICHEN_FEATURE, placeFeature(entries, NConfiguredFeatures.BURNT_LICHEN_FEATURE, CountPlacementModifier.of(24), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of()));
        entries.add(ANCIENT_DEAD_REEF_VEGETATION_PLACED, placeFeature(entries, ANCIENT_DEAD_REEF_VEGETATION, NoiseBasedCountPlacementModifier.of(20, 400.0D, 0.0D), SquarePlacementModifier.of(), PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP, BiomePlacementModifier.of()));
        entries.add(ANCIENT_DEAD_REEF_WATER_VEGETATION_PLACED, placeFeature(entries, ANCIENT_DEAD_REEF_VEGETATION, NoiseBasedCountPlacementModifier.of(20, 200.0D, 0.0D), SquarePlacementModifier.of(), PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of()));
        // Level with vanilla dark_forest_vegetation, which also tries 16 times per chunk. The extra
        // count keeps the density uneven enough to leave clearings between the giants.
        entries.add(DEEP_DARK_FOREST_VEGETATION_PLACED, placeFeature(entries, DEEP_DARK_FOREST_VEGETATION, PlacedFeatures.createCountExtraModifier(16, 0.1F, 1), SquarePlacementModifier.of(), SurfaceWaterDepthFilterPlacementModifier.of(0), PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of()));
    }

    /*
    public static void bootstrap(Registerable<PlacedFeature> featureRegisterable) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = featureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        PlacedFeatures.register(featureRegisterable, MOSS_CARPET_FEATURE, registryEntryLookup.getOrThrow(TGConfiguredFeatures.MOSS_CARPET_FEATURE), RarityFilterPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, CountPlacementModifier.of(100));
        PlacedFeatures.register(featureRegisterable, COBWEB_FEATURE, registryEntryLookup.getOrThrow(TGConfiguredFeatures.COBWEB_FEATURE), RarityFilterPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, CountPlacementModifier.of(40));
        PlacedFeatures.register(featureRegisterable, BUSH_FEATURE, registryEntryLookup.getOrThrow(TGConfiguredFeatures.BUSH_FEATURE), RarityFilterPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, CountPlacementModifier.of(35));
        PlacedFeatures.register(featureRegisterable, DEEP_DARK_FOREST_BUSH_FEATURE, registryEntryLookup.getOrThrow(TGConfiguredFeatures.DEEP_DARK_FOREST_BUSH_FEATURE), RarityFilterPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, CountPlacementModifier.of(35));
        PlacedFeatures.register(featureRegisterable, SOUL_LIGHT_FEATURE, registryEntryLookup.getOrThrow(TGConfiguredFeatures.SOUL_LIGHT_FEATURE), RarityFilterPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, CountPlacementModifier.of(100));
        PlacedFeatures.register(featureRegisterable, FALLEN_TREE_FEATURE, registryEntryLookup.getOrThrow(TGConfiguredFeatures.FALLEN_TREE_FEATURE), RarityFilterPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, CountPlacementModifier.of(20));
        PlacedFeatures.register(featureRegisterable, DEAD_CORAL_PATCH_FEATURE, registryEntryLookup.getOrThrow(TGConfiguredFeatures.DEAD_CORAL_PATCH_FEATURE), RarityFilterPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, CountPlacementModifier.of(35));

        PlacedFeatures.register(featureRegisterable, ERODED_HAUNTED_FOREST_TREES, registryEntryLookup.getOrThrow(TGConfiguredFeatures.ERODED_HAUNTED_FOREST_TREES), PlacedFeatures.createCountExtraModifier(10, 0.1F, 1), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());
        PlacedFeatures.register(featureRegisterable, HAUNTED_FOREST_TREES, registryEntryLookup.getOrThrow(TGConfiguredFeatures.HAUNTED_FOREST_TREES), PlacedFeatures.createCountExtraModifier(15, 0.1F, 1), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());
        PlacedFeatures.register(featureRegisterable, ANCIENT_DEAD_REEF_VEGETATION, registryEntryLookup.getOrThrow(TGConfiguredFeatures.ANCIENT_DEAD_REEF_VEGETATION), NoiseBasedCountPlacementModifier.of(20, 400.0D, 0.0D), SquarePlacementModifier.of(), PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP, BiomePlacementModifier.of());
        PlacedFeatures.register(featureRegisterable, ANCIENT_DEAD_REEF_WATER_VEGETATION, registryEntryLookup.getOrThrow(TGConfiguredFeatures.ANCIENT_DEAD_REEF_VEGETATION), NoiseBasedCountPlacementModifier.of(20, 200.0D, 0.0D), SquarePlacementModifier.of(), PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of());
        PlacedFeatures.register(featureRegisterable, DEEP_DARK_FOREST_VEGETATION, registryEntryLookup.getOrThrow(TGConfiguredFeatures.DEEP_DARK_FOREST_VEGETATION), CountPlacementModifier.of(35), SquarePlacementModifier.of(), SurfaceWaterDepthFilterPlacementModifier.of(0), PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of());
        PlacedFeatures.register(featureRegisterable, DEEP_DARK_OAK, registryEntryLookup.getOrThrow(TGConfiguredFeatures.DEEP_DARK_OAK), PlacedFeatures.wouldSurvive(Blocks.DARK_OAK_SAPLING));
        PlacedFeatures.register(featureRegisterable, TG_PATCH_LARGE_FERN, registryEntryLookup.getOrThrow(TGConfiguredFeatures.TG_PATCH_LARGE_FERN), RarityFilterPlacementModifier.of(5), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
        PlacedFeatures.register(featureRegisterable, TG_PATCH_LEAVES, registryEntryLookup.getOrThrow(TGConfiguredFeatures.TG_PATCH_LEAVES), RarityFilterPlacementModifier.of(5), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
        PlacedFeatures.register(featureRegisterable, TG_PATCH_MEADOW_FLOWER, registryEntryLookup.getOrThrow(TGConfiguredFeatures.TG_PATCH_MEADOW_FLOWER), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    }

     */


}
