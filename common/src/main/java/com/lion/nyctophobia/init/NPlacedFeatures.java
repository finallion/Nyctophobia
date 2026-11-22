package com.lion.nyctophobia.init;

import com.lion.nyctophobia.Nyctophobia;
import com.lion.nyctophobia.data.NRegistryContext;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.NoiseBasedCountPlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;
import java.util.List;

public class NPlacedFeatures {

    public static void init() {}

    public static ResourceKey<PlacedFeature> of(String id) {
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(Nyctophobia.MOD_ID, id));
    }

    public static final ResourceKey<PlacedFeature> MOSS_CARPET_FEATURE = of("moss_carpet_feature");
    public static final ResourceKey<PlacedFeature> COBWEB_FEATURE = of("cobweb_feature");
    public static final ResourceKey<PlacedFeature> BUSH_FEATURE = of("bush_feature");
    public static final ResourceKey<PlacedFeature> DEEP_DARK_FOREST_BUSH_FEATURE = of("deep_dark_forest_bush_feature");
    public static final ResourceKey<PlacedFeature> SOUL_LIGHT_FEATURE = of("soul_light_feature");
    public static final ResourceKey<PlacedFeature> FALLEN_TREE_FEATURE = of("fallen_tree_feature");
    public static final ResourceKey<PlacedFeature> DEAD_CORAL_PATCH_FEATURE = of("dead_coral_patch_feature");
    public static final ResourceKey<PlacedFeature> DEEP_DARK_OAK = of("deep_dark_oak");
    public static final ResourceKey<PlacedFeature> MARSH_CANDLE_FEATURE = of("marsh_candle_feature");
    public static final ResourceKey<PlacedFeature> MARSH_ROOTS_PLACED = of("marsh_roots");
    public static final ResourceKey<PlacedFeature> MARSH_POOL_PLACED = of("marsh_pool");
    public static final ResourceKey<PlacedFeature> MARSH_THICKET_PLACED = of("marsh_thicket");
    public static final ResourceKey<PlacedFeature> TG_PATCH_LARGE_FERN = of("tg_patch_large_fern_config");
    public static final ResourceKey<PlacedFeature> TG_PATCH_LEAVES = of("tg_patch_leaves_config");
    public static final ResourceKey<PlacedFeature> TG_PATCH_MEADOW_FLOWER = of("tg_patch_meadow_flower_config");

    public static final ResourceKey<PlacedFeature> HAUNTED_FOREST_TREES_PLACED = of("haunted_forest_trees");
    public static final ResourceKey<PlacedFeature> ERODED_HAUNTED_FOREST_TREES_PLACED = of("eroded_haunted_forest_trees");
    public static final ResourceKey<PlacedFeature> BURNT_FOREST_TREES_PLACED = of("burnt_forest_trees");
    public static final ResourceKey<PlacedFeature> BURNT_LICHEN_FEATURE = of("burnt_lichen_feature");
    public static final ResourceKey<PlacedFeature> ANCIENT_DEAD_REEF_VEGETATION_PLACED = of("ancient_dead_reef_vegetation");
    public static final ResourceKey<PlacedFeature> ANCIENT_DEAD_REEF_WATER_VEGETATION_PLACED = of("ancient_dead_reef_water_vegetation");
    public static final ResourceKey<PlacedFeature> DEEP_DARK_FOREST_VEGETATION_PLACED = of("deep_dark_forest_vegetation");

    public static final ResourceKey<ConfiguredFeature<?, ?>> HAUNTED_FOREST_TREES = NConfiguredFeatures.of("haunted_forest_trees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ERODED_HAUNTED_FOREST_TREES = NConfiguredFeatures.of("eroded_haunted_forest_trees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BURNT_FOREST_TREES = NConfiguredFeatures.of("burnt_forest_trees");
    
    public static final ResourceKey<ConfiguredFeature<?, ?>> ANCIENT_DEAD_REEF_VEGETATION = NConfiguredFeatures.of("ancient_dead_reef_vegetation");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEEP_DARK_FOREST_VEGETATION = NConfiguredFeatures.of("deep_dark_forest_vegetation");

    public static final ResourceKey<PlacedFeature> SMALL_SPRUCE_TREE_01_STRIPPED = of("small_spruce_tree_stripped_01");
    public static final ResourceKey<PlacedFeature> SMALL_SPRUCE_TREE_01 = of("small_spruce_tree_01");
    public static final ResourceKey<PlacedFeature> SMALL_SPRUCE_TREE_02_STRIPPED = of("small_spruce_tree_stripped_02");
    public static final ResourceKey<PlacedFeature> SMALL_SPRUCE_TREE_02 = of("small_spruce_tree_02");
    public static final ResourceKey<PlacedFeature> SMALL_SPRUCE_TREE_03_STRIPPED = of("small_spruce_tree_stripped_03");
    public static final ResourceKey<PlacedFeature> SMALL_SPRUCE_TREE_03 = of("small_spruce_tree_03");
    public static final ResourceKey<PlacedFeature> SMALL_SPRUCE_TREE_04_STRIPPED = of("small_spruce_tree_stripped_04");
    public static final ResourceKey<PlacedFeature> SMALL_SPRUCE_TREE_04 = of("small_spruce_tree_04");
    public static final ResourceKey<PlacedFeature> SMALL_SPRUCE_TREE_05_STRIPPED = of("small_spruce_tree_stripped_05");
    public static final ResourceKey<PlacedFeature> SMALL_SPRUCE_TREE_05 = of("small_spruce_tree_05");
    public static final ResourceKey<PlacedFeature> SMALL_SPRUCE_TREE_06_STRIPPED = of("small_spruce_tree_stripped_06");
    public static final ResourceKey<PlacedFeature> SMALL_SPRUCE_TREE_06 = of("small_spruce_tree_06");
    public static final ResourceKey<PlacedFeature> SMALL_BENT_SPRUCE_TREE_01_STRIPPED = of("small_bent_spruce_tree_stripped_01");
    public static final ResourceKey<PlacedFeature> SMALL_BENT_SPRUCE_TREE_01 = of("small_bent_spruce_tree_01");
    public static final ResourceKey<PlacedFeature> FALLEN_SPRUCE_TREE_STRIPPED = of("fallen_spruce_stripped_tree");
    public static final ResourceKey<PlacedFeature> FALLEN_SPRUCE_TREE = of("fallen_spruce_tree");
    public static final ResourceKey<PlacedFeature> LARGE_SPRUCE_TREE_01_STRIPPED = of("large_spruce_tree_stripped_01");
    public static final ResourceKey<PlacedFeature> LARGE_SPRUCE_TREE_01 = of("large_spruce_tree_01");
    public static final ResourceKey<PlacedFeature> LARGE_SPRUCE_TREE_02_STRIPPED = of("large_spruce_tree_stripped_02");
    public static final ResourceKey<PlacedFeature> LARGE_SPRUCE_TREE_02 = of("large_spruce_tree_02");
    public static final ResourceKey<PlacedFeature> LARGE_SPRUCE_TREE_03_STRIPPED = of("large_spruce_tree_stripped_03");
    public static final ResourceKey<PlacedFeature> LARGE_SPRUCE_TREE_03 = of("large_spruce_tree_03");
    public static final ResourceKey<PlacedFeature> LARGE_BENT_SPRUCE_TREE_01_STRIPPED = of("large_bent_spruce_tree_stripped_01");
    public static final ResourceKey<PlacedFeature> LARGE_BENT_SPRUCE_TREE_01 = of("large_bent_spruce_tree_01");
    public static final ResourceKey<PlacedFeature> LARGE_BENT_SPRUCE_TREE_02_STRIPPED = of("large_bent_spruce_tree_stripped_02");
    public static final ResourceKey<PlacedFeature> LARGE_BENT_SPRUCE_TREE_02 = of("large_bent_spruce_tree_02");


    public static PlacedFeature placeFeature(
            NRegistryContext<PlacedFeature> entries,
            ResourceKey<ConfiguredFeature<?, ?>> feature,
            PlacementModifier... list
    ) {
        return new PlacedFeature(entries.ref(feature), List.of(list));
    }


    public static void populateSelectors(NRegistryContext<ConfiguredFeature<?, ?>> entries) {
        entries.add(
            HAUNTED_FOREST_TREES,
            NConfiguredFeatures.configureFeature(
                Feature.RANDOM_SELECTOR,
                new RandomFeatureConfiguration(
                    List.of(
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.SMALL_SPRUCE_TREE_01), new PlacementModifier[0]), 0.25F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.SMALL_SPRUCE_TREE_02), new PlacementModifier[0]), 0.25F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.SMALL_SPRUCE_TREE_03), new PlacementModifier[0]), 0.375F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.SMALL_SPRUCE_TREE_04), new PlacementModifier[0]), 0.1F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.SMALL_SPRUCE_TREE_05), new PlacementModifier[0]), 0.1F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.SMALL_SPRUCE_TREE_06), new PlacementModifier[0]), 0.1F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.SMALL_BENT_SPRUCE_TREE_01), new PlacementModifier[0]), 0.1F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.FALLEN_SPRUCE_TREE), new PlacementModifier[0]), 0.1F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.LARGE_SPRUCE_TREE_01), new PlacementModifier[0]), 0.25F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.LARGE_SPRUCE_TREE_02), new PlacementModifier[0]), 0.1F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.LARGE_SPRUCE_TREE_03), new PlacementModifier[0]), 0.1F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.SMALL_SPRUCE_TREE_01_STRIPPED)), 0.25F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.SMALL_SPRUCE_TREE_02_STRIPPED), new PlacementModifier[0]), 0.25F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.SMALL_SPRUCE_TREE_03_STRIPPED), new PlacementModifier[0]), 0.375F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.SMALL_SPRUCE_TREE_04_STRIPPED), new PlacementModifier[0]), 0.1F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.SMALL_SPRUCE_TREE_05_STRIPPED), new PlacementModifier[0]), 0.1F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.SMALL_SPRUCE_TREE_06_STRIPPED), new PlacementModifier[0]), 0.1F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.SMALL_BENT_SPRUCE_TREE_01_STRIPPED), new PlacementModifier[0]), 0.1F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.FALLEN_SPRUCE_TREE_STRIPPED), new PlacementModifier[0]), 0.1F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.LARGE_SPRUCE_TREE_01_STRIPPED), new PlacementModifier[0]), 0.25F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.LARGE_SPRUCE_TREE_02_STRIPPED), new PlacementModifier[0]), 0.1F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.LARGE_SPRUCE_TREE_03_STRIPPED), new PlacementModifier[0]), 0.1F)
                    ),
                    entries.ref(TreePlacements.SPRUCE_CHECKED)
                )
            )
        );

        entries.add(
            ERODED_HAUNTED_FOREST_TREES,
            NConfiguredFeatures.configureFeature(
                Feature.RANDOM_SELECTOR,
                new RandomFeatureConfiguration(
                    List.of(
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.SMALL_BENT_SPRUCE_TREE_01), new PlacementModifier[0]), 0.1F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.FALLEN_SPRUCE_TREE), new PlacementModifier[0]), 0.0075F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.LARGE_BENT_SPRUCE_TREE_01), new PlacementModifier[0]), 0.1F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.LARGE_BENT_SPRUCE_TREE_02), new PlacementModifier[0]), 0.1F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.LARGE_SPRUCE_TREE_01), new PlacementModifier[0]), 0.5F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.LARGE_SPRUCE_TREE_02), new PlacementModifier[0]), 0.1F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.LARGE_SPRUCE_TREE_03), new PlacementModifier[0]), 0.1F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.SMALL_BENT_SPRUCE_TREE_01_STRIPPED), new PlacementModifier[0]), 0.1F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.FALLEN_SPRUCE_TREE_STRIPPED), new PlacementModifier[0]), 0.0075F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.LARGE_BENT_SPRUCE_TREE_01_STRIPPED), new PlacementModifier[0]), 0.1F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.LARGE_BENT_SPRUCE_TREE_02_STRIPPED), new PlacementModifier[0]), 0.1F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.LARGE_SPRUCE_TREE_01_STRIPPED), new PlacementModifier[0]), 0.5F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.LARGE_SPRUCE_TREE_02_STRIPPED), new PlacementModifier[0]), 0.1F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.LARGE_SPRUCE_TREE_03_STRIPPED), new PlacementModifier[0]), 0.1F)
                    ),
                    entries.ref(TreePlacements.SPRUCE_CHECKED)
                )
            )
        );

        entries.add(
            BURNT_FOREST_TREES,
            NConfiguredFeatures.configureFeature(
                Feature.RANDOM_SELECTOR,
                new RandomFeatureConfiguration(
                    List.of(
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.BURNT_TREE_SMALL_02), new PlacementModifier[0]), 0.2F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.BURNT_TREE_BENT), new PlacementModifier[0]), 0.2F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.BURNT_TREE_FALLEN), new PlacementModifier[0]), 0.2F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.BURNT_TREE_LARGE_BENT), new PlacementModifier[0]), 0.2F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.BURNT_TREE_LARGE), new PlacementModifier[0]), 0.3F)
                    ),
                    PlacementUtils.inlinePlaced(entries.ref(NConfiguredFeatures.BURNT_TREE_SMALL_01), new PlacementModifier[0])
                )
            )
        );

        entries.add(
            ANCIENT_DEAD_REEF_VEGETATION,
            NConfiguredFeatures.configureFeature(
                Feature.SIMPLE_RANDOM_SELECTOR,
                new SimpleRandomFeatureConfiguration(
                    HolderSet.direct(
                        PlacementUtils.inlinePlaced(NFeatures.DEAD_CORAL_TREE_FEATURE.get(), FeatureConfiguration.NONE, new PlacementModifier[0]),
                        PlacementUtils.inlinePlaced(NFeatures.DEAD_CORAL_CLAW_FEATURE.get(), FeatureConfiguration.NONE, new PlacementModifier[0]),
                        PlacementUtils.inlinePlaced(NFeatures.DEAD_CORAL_MUSHROOM_FEATURE.get(), FeatureConfiguration.NONE, new PlacementModifier[0])
                    )
                )
            )
        );


        // Entries are conditional chances. The first hit wins.
        entries.add(
            DEEP_DARK_FOREST_VEGETATION,
            NConfiguredFeatures.configureFeature(
                Feature.RANDOM_SELECTOR,
                new RandomFeatureConfiguration(
                    List.of(
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(TreeFeatures.HUGE_BROWN_MUSHROOM), new PlacementModifier[0]), 0.025F),
                        new WeightedPlacedFeature(PlacementUtils.inlinePlaced(entries.ref(TreeFeatures.HUGE_RED_MUSHROOM), new PlacementModifier[0]), 0.025F),
                        new WeightedPlacedFeature(entries.ref(NPlacedFeatures.DEEP_DARK_OAK), 0.45F),
                        new WeightedPlacedFeature(entries.ref(TreePlacements.DARK_OAK_CHECKED), 0.4F)
                    ),
                    entries.ref(TreePlacements.DARK_OAK_CHECKED)
                )
            )
        );
    }

    public static void populate(NRegistryContext<PlacedFeature> entries) {
        entries.add(SMALL_SPRUCE_TREE_01, placeFeature(entries, NConfiguredFeatures.SMALL_SPRUCE_TREE_01, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
        entries.add(SMALL_SPRUCE_TREE_01_STRIPPED, placeFeature(entries, NConfiguredFeatures.SMALL_SPRUCE_TREE_01_STRIPPED, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
        entries.add(SMALL_SPRUCE_TREE_02, placeFeature(entries, NConfiguredFeatures.SMALL_SPRUCE_TREE_02, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
        entries.add(SMALL_SPRUCE_TREE_02_STRIPPED, placeFeature(entries, NConfiguredFeatures.SMALL_SPRUCE_TREE_02_STRIPPED, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
        entries.add(SMALL_SPRUCE_TREE_03, placeFeature(entries, NConfiguredFeatures.SMALL_SPRUCE_TREE_03, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
        entries.add(SMALL_SPRUCE_TREE_03_STRIPPED, placeFeature(entries, NConfiguredFeatures.SMALL_SPRUCE_TREE_03_STRIPPED, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
        entries.add(SMALL_SPRUCE_TREE_04, placeFeature(entries, NConfiguredFeatures.SMALL_SPRUCE_TREE_04, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
        entries.add(SMALL_SPRUCE_TREE_04_STRIPPED, placeFeature(entries, NConfiguredFeatures.SMALL_SPRUCE_TREE_04_STRIPPED, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
        entries.add(SMALL_SPRUCE_TREE_05, placeFeature(entries, NConfiguredFeatures.SMALL_SPRUCE_TREE_05, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
        entries.add(SMALL_SPRUCE_TREE_05_STRIPPED, placeFeature(entries, NConfiguredFeatures.SMALL_SPRUCE_TREE_05_STRIPPED, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
        entries.add(SMALL_SPRUCE_TREE_06, placeFeature(entries, NConfiguredFeatures.SMALL_SPRUCE_TREE_06, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
        entries.add(SMALL_SPRUCE_TREE_06_STRIPPED, placeFeature(entries, NConfiguredFeatures.SMALL_SPRUCE_TREE_06_STRIPPED, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
        entries.add(SMALL_BENT_SPRUCE_TREE_01, placeFeature(entries, NConfiguredFeatures.SMALL_BENT_SPRUCE_TREE_01, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
        entries.add(SMALL_BENT_SPRUCE_TREE_01_STRIPPED, placeFeature(entries, NConfiguredFeatures.SMALL_BENT_SPRUCE_TREE_01_STRIPPED, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
        entries.add(LARGE_BENT_SPRUCE_TREE_01, placeFeature(entries, NConfiguredFeatures.LARGE_BENT_SPRUCE_TREE_01, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
        entries.add(LARGE_BENT_SPRUCE_TREE_01_STRIPPED, placeFeature(entries, NConfiguredFeatures.LARGE_BENT_SPRUCE_TREE_01_STRIPPED, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
        entries.add(LARGE_BENT_SPRUCE_TREE_02, placeFeature(entries, NConfiguredFeatures.LARGE_BENT_SPRUCE_TREE_02, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
        entries.add(LARGE_BENT_SPRUCE_TREE_02_STRIPPED, placeFeature(entries, NConfiguredFeatures.LARGE_BENT_SPRUCE_TREE_02_STRIPPED, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
        entries.add(FALLEN_SPRUCE_TREE, placeFeature(entries, NConfiguredFeatures.FALLEN_SPRUCE_TREE, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
        entries.add(FALLEN_SPRUCE_TREE_STRIPPED, placeFeature(entries, NConfiguredFeatures.FALLEN_SPRUCE_TREE_STRIPPED, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
        entries.add(LARGE_SPRUCE_TREE_01, placeFeature(entries, NConfiguredFeatures.LARGE_SPRUCE_TREE_01, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
        entries.add(LARGE_SPRUCE_TREE_01_STRIPPED, placeFeature(entries, NConfiguredFeatures.LARGE_SPRUCE_TREE_01_STRIPPED, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
        entries.add(LARGE_SPRUCE_TREE_02, placeFeature(entries, NConfiguredFeatures.LARGE_SPRUCE_TREE_02, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
        entries.add(LARGE_SPRUCE_TREE_02_STRIPPED, placeFeature(entries, NConfiguredFeatures.LARGE_SPRUCE_TREE_02_STRIPPED, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
        entries.add(LARGE_SPRUCE_TREE_03, placeFeature(entries, NConfiguredFeatures.LARGE_SPRUCE_TREE_03, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
        entries.add(LARGE_SPRUCE_TREE_03_STRIPPED, placeFeature(entries, NConfiguredFeatures.LARGE_SPRUCE_TREE_03_STRIPPED, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));

        entries.add(MOSS_CARPET_FEATURE, placeFeature(entries, NConfiguredFeatures.MOSS_CARPET_FEATURE, RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, CountPlacement.of(100)));
        entries.add(COBWEB_FEATURE, placeFeature(entries, NConfiguredFeatures.COBWEB_FEATURE, RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, CountPlacement.of(40)));
        entries.add(BUSH_FEATURE, placeFeature(entries, NConfiguredFeatures.BUSH_FEATURE, RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, CountPlacement.of(35)));
        entries.add(DEEP_DARK_FOREST_BUSH_FEATURE, placeFeature(entries, NConfiguredFeatures.DEEP_DARK_FOREST_BUSH_FEATURE, RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, CountPlacement.of(35)));
        entries.add(SOUL_LIGHT_FEATURE, placeFeature(entries, NConfiguredFeatures.SOUL_LIGHT_FEATURE, RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, CountPlacement.of(100)));
        entries.add(FALLEN_TREE_FEATURE, placeFeature(entries, NConfiguredFeatures.FALLEN_TREE_FEATURE, RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, CountPlacement.of(20)));
        entries.add(DEAD_CORAL_PATCH_FEATURE, placeFeature(entries, NConfiguredFeatures.DEAD_CORAL_PATCH_FEATURE, RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, CountPlacement.of(35)));
        entries.add(MARSH_CANDLE_FEATURE, placeFeature(entries, NConfiguredFeatures.MARSH_CANDLE_FEATURE, RarityFilter.onAverageOnceEvery(6), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        entries.add(MARSH_ROOTS_PLACED, placeFeature(entries, NConfiguredFeatures.MARSH_ROOTS_FEATURE, RarityFilter.onAverageOnceEvery(2), CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome()));
        entries.add(MARSH_POOL_PLACED, placeFeature(entries, NConfiguredFeatures.MARSH_POOL_FEATURE, CountPlacement.of(8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome()));
        entries.add(MARSH_THICKET_PLACED, placeFeature(entries, NConfiguredFeatures.MARSH_THICKET_FEATURE, CountPlacement.of(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome()));

        entries.add(DEEP_DARK_OAK, placeFeature(entries, NConfiguredFeatures.DEEP_DARK_OAK, PlacementUtils.filteredByBlockSurvival(Blocks.DARK_OAK_SAPLING)));
        entries.add(TG_PATCH_LARGE_FERN, placeFeature(entries, NConfiguredFeatures.TG_PATCH_LARGE_FERN, RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), CountPlacement.of(96), RandomOffsetPlacement.ofTriangle(7, 3), PlacementUtils.isEmpty()));
        entries.add(TG_PATCH_LEAVES, placeFeature(entries, NConfiguredFeatures.TG_PATCH_LEAVES, RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), CountPlacement.of(64), RandomOffsetPlacement.ofTriangle(6, 0), PlacementUtils.isEmpty()));
        entries.add(TG_PATCH_MEADOW_FLOWER, placeFeature(entries, NConfiguredFeatures.TG_PATCH_MEADOW_FLOWER, InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome(), CountPlacement.of(96), RandomOffsetPlacement.ofTriangle(6, 2), PlacementUtils.isEmpty()));

        entries.add(ERODED_HAUNTED_FOREST_TREES_PLACED, placeFeature(entries, ERODED_HAUNTED_FOREST_TREES, PlacementUtils.countExtra(10, 0.1F, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        entries.add(HAUNTED_FOREST_TREES_PLACED, placeFeature(entries, HAUNTED_FOREST_TREES, PlacementUtils.countExtra(15, 0.1F, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        entries.add(BURNT_FOREST_TREES_PLACED, placeFeature(entries, BURNT_FOREST_TREES, PlacementUtils.countExtra(4, 0.1F, 1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        // Runs after the trees, so the trunks already stand.
        entries.add(BURNT_LICHEN_FEATURE, placeFeature(entries, NConfiguredFeatures.BURNT_LICHEN_FEATURE, CountPlacement.of(24), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
        entries.add(ANCIENT_DEAD_REEF_VEGETATION_PLACED, placeFeature(entries, ANCIENT_DEAD_REEF_VEGETATION, NoiseBasedCountPlacement.of(20, 400.0D, 0.0D), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
        entries.add(ANCIENT_DEAD_REEF_WATER_VEGETATION_PLACED, placeFeature(entries, ANCIENT_DEAD_REEF_VEGETATION, NoiseBasedCountPlacement.of(20, 200.0D, 0.0D), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome()));
        entries.add(DEEP_DARK_FOREST_VEGETATION_PLACED, placeFeature(entries, DEEP_DARK_FOREST_VEGETATION, PlacementUtils.countExtra(16, 0.1F, 1), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome()));
    }
}
