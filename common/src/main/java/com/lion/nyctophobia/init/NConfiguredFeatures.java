package com.lion.nyctophobia.init;

import java.util.function.Supplier;
import com.lion.nyctophobia.Nyctophobia;
import com.lion.nyctophobia.world.features.trees.config.NTreeFeatureConfig;
import com.lion.nyctophobia.world.trunk.DeepDarkOakTrunkPlacer;
import com.lion.nyctophobia.data.NRegistryContext;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.util.InclusiveRange;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MultifaceSpreadeableBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.MultifaceGrowthConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.DarkOakFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.DualNoiseProvider;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import java.util.List;
import java.util.OptionalInt;

public class NConfiguredFeatures {
    public static ResourceKey<ConfiguredFeature<?, ?>> of(String id) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(Nyctophobia.MOD_ID, id));
    }

    public static void init() {}

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(
            BootstrapContext<ConfiguredFeature<?, ?>> registerable,
            ResourceKey<ConfiguredFeature<?, ?>> key,
            F feature,
            FC config
    ) {
        registerable.register(key, new ConfiguredFeature<>(feature, config));
    }

    public static final ResourceKey<ConfiguredFeature<?, ?>> MOSS_CARPET_FEATURE = of("moss_carpet_feature");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COBWEB_FEATURE = of("cobweb_feature");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BUSH_FEATURE = of("bush_feature");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEEP_DARK_FOREST_BUSH_FEATURE = of("deep_dark_forest_bush_feature");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SOUL_LIGHT_FEATURE = of("soul_light_feature");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_TREE_FEATURE = of("fallen_tree_feature");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEAD_CORAL_PATCH_FEATURE = of("dead_coral_patch_feature");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_SPRUCE_TREE_01_STRIPPED = of("small_spruce_tree_stripped_01");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_SPRUCE_TREE_01 = of("small_spruce_tree_01");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_SPRUCE_TREE_02_STRIPPED = of("small_spruce_tree_stripped_02");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_SPRUCE_TREE_02 = of("small_spruce_tree_02");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_SPRUCE_TREE_03_STRIPPED = of("small_spruce_tree_stripped_03");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_SPRUCE_TREE_03 = of("small_spruce_tree_03");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_SPRUCE_TREE_04_STRIPPED = of("small_spruce_tree_stripped_04");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_SPRUCE_TREE_04 = of("small_spruce_tree_04");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_SPRUCE_TREE_05_STRIPPED = of("small_spruce_tree_stripped_05");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_SPRUCE_TREE_05 = of("small_spruce_tree_05");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_SPRUCE_TREE_06_STRIPPED = of("small_spruce_tree_stripped_06");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_SPRUCE_TREE_06 = of("small_spruce_tree_06");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_BENT_SPRUCE_TREE_01_STRIPPED = of("small_bent_spruce_tree_stripped_01");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_BENT_SPRUCE_TREE_01 = of("small_bent_spruce_tree_01");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_SPRUCE_TREE_STRIPPED = of("fallen_spruce_stripped_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_SPRUCE_TREE = of("fallen_spruce_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_SPRUCE_TREE_01_STRIPPED = of("large_spruce_tree_stripped_01");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_SPRUCE_TREE_01 = of("large_spruce_tree_01");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_SPRUCE_TREE_02_STRIPPED = of("large_spruce_tree_stripped_02");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_SPRUCE_TREE_02 = of("large_spruce_tree_02");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_SPRUCE_TREE_03_STRIPPED = of("large_spruce_tree_stripped_03");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_SPRUCE_TREE_03 = of("large_spruce_tree_03");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_BENT_SPRUCE_TREE_01_STRIPPED = of("large_bent_spruce_tree_stripped_01");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_BENT_SPRUCE_TREE_01 = of("large_bent_spruce_tree_01");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_BENT_SPRUCE_TREE_02_STRIPPED = of("large_bent_spruce_tree_stripped_02");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_BENT_SPRUCE_TREE_02 = of("large_bent_spruce_tree_02");

    public static final ResourceKey<ConfiguredFeature<?, ?>> DEEP_DARK_OAK = of("deep_dark_oak");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MARSH_CANDLE_FEATURE = of("marsh_candle_feature");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MARSH_ROOTS_FEATURE = of("marsh_roots_feature");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MARSH_POOL_FEATURE = of("marsh_pool_feature");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MARSH_THICKET_FEATURE = of("marsh_thicket_feature");

    public static final ResourceKey<ConfiguredFeature<?, ?>> BURNT_TREE_SMALL_01 = of("burnt_tree_small_01");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BURNT_TREE_SMALL_02 = of("burnt_tree_small_02");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BURNT_TREE_BENT = of("burnt_tree_bent");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BURNT_TREE_LARGE_BENT = of("burnt_tree_large_bent");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BURNT_TREE_LARGE = of("burnt_tree_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BURNT_TREE_FALLEN = of("burnt_tree_fallen");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BURNT_LICHEN_FEATURE = of("burnt_lichen_feature");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TG_PATCH_LARGE_FERN = of("tg_patch_large_fern_config");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TG_PATCH_LEAVES = of("tg_patch_leaves_config");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TG_PATCH_MEADOW_FLOWER = of("tg_patch_meadow_flower_config");

    private static NTreeFeatureConfig burntTree() {
        return new NTreeFeatureConfig(Blocks.POLISHED_BASALT.defaultBlockState(), Blocks.OAK_LEAVES.defaultBlockState());
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> ConfiguredFeature<FC, ?> configureFeature(
            F feature,
            FC config
    ) {
        return new ConfiguredFeature<>(feature, config);
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> ConfiguredFeature<FC, ?> configureFeature(
            Supplier<F> feature,
            FC config
    ) {
        return new ConfiguredFeature<>(feature.get(), config);
    }

    public static void populate(NRegistryContext<ConfiguredFeature<?, ?>> entries) {
        entries.add(MOSS_CARPET_FEATURE, configureFeature(NFeatures.MOSS_CARPET_FEATURE, FeatureConfiguration.NONE));
        entries.add(COBWEB_FEATURE, configureFeature(NFeatures.COBWEB_FEATURE, FeatureConfiguration.NONE));
        entries.add(BUSH_FEATURE, configureFeature(NFeatures.BUSH_FEATURE, FeatureConfiguration.NONE));
        entries.add(DEEP_DARK_FOREST_BUSH_FEATURE, configureFeature(NFeatures.DEEP_DARK_FOREST_BUSH_FEATURE, FeatureConfiguration.NONE));
        entries.add(SOUL_LIGHT_FEATURE, configureFeature(NFeatures.SOUL_LIGHT_FEATURE, FeatureConfiguration.NONE));
        entries.add(FALLEN_TREE_FEATURE, configureFeature(NFeatures.FALLEN_TREE_FEATURE, FeatureConfiguration.NONE));
        entries.add(DEAD_CORAL_PATCH_FEATURE, configureFeature(NFeatures.DEAD_CORAL_PATCH_FEATURE, FeatureConfiguration.NONE));

        entries.add(SMALL_SPRUCE_TREE_01_STRIPPED, configureFeature(NFeatures.SMALL_SPRUCE_TREE_01, new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(SMALL_SPRUCE_TREE_01, configureFeature(NFeatures.SMALL_SPRUCE_TREE_01, new NTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(SMALL_SPRUCE_TREE_02_STRIPPED, configureFeature(NFeatures.SMALL_SPRUCE_TREE_02, new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(SMALL_SPRUCE_TREE_02, configureFeature(NFeatures.SMALL_SPRUCE_TREE_02, new NTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(SMALL_SPRUCE_TREE_03_STRIPPED, configureFeature(NFeatures.SMALL_SPRUCE_TREE_03, new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(SMALL_SPRUCE_TREE_03, configureFeature(NFeatures.SMALL_SPRUCE_TREE_03, new NTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(SMALL_SPRUCE_TREE_04_STRIPPED, configureFeature(NFeatures.SMALL_SPRUCE_TREE_04, new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(SMALL_SPRUCE_TREE_04, configureFeature(NFeatures.SMALL_SPRUCE_TREE_04, new NTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(SMALL_SPRUCE_TREE_05_STRIPPED, configureFeature(NFeatures.SMALL_SPRUCE_TREE_05, new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(SMALL_SPRUCE_TREE_05, configureFeature(NFeatures.SMALL_SPRUCE_TREE_05, new NTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(SMALL_SPRUCE_TREE_06_STRIPPED, configureFeature(NFeatures.SMALL_SPRUCE_TREE_06, new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(SMALL_SPRUCE_TREE_06, configureFeature(NFeatures.SMALL_SPRUCE_TREE_06, new NTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(SMALL_BENT_SPRUCE_TREE_01_STRIPPED, configureFeature(NFeatures.SMALL_BENT_SPRUCE_TREE_01, new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(SMALL_BENT_SPRUCE_TREE_01, configureFeature(NFeatures.SMALL_BENT_SPRUCE_TREE_01, new NTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(FALLEN_SPRUCE_TREE_STRIPPED, configureFeature(NFeatures.FALLEN_SPRUCE_TREE, new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(FALLEN_SPRUCE_TREE, configureFeature(NFeatures.FALLEN_SPRUCE_TREE, new NTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(LARGE_SPRUCE_TREE_01_STRIPPED, configureFeature(NFeatures.LARGE_SPRUCE_TREE_01, new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(LARGE_SPRUCE_TREE_01, configureFeature(NFeatures.LARGE_SPRUCE_TREE_01, new NTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(LARGE_SPRUCE_TREE_02_STRIPPED, configureFeature(NFeatures.LARGE_SPRUCE_TREE_02, new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(LARGE_SPRUCE_TREE_02, configureFeature(NFeatures.LARGE_SPRUCE_TREE_02, new NTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(LARGE_SPRUCE_TREE_03_STRIPPED, configureFeature(NFeatures.LARGE_SPRUCE_TREE_03, new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(LARGE_SPRUCE_TREE_03, configureFeature(NFeatures.LARGE_SPRUCE_TREE_03, new NTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(LARGE_BENT_SPRUCE_TREE_01_STRIPPED, configureFeature(NFeatures.LARGE_BENT_SPRUCE_TREE_01, new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(LARGE_BENT_SPRUCE_TREE_01, configureFeature(NFeatures.LARGE_BENT_SPRUCE_TREE_01, new NTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(LARGE_BENT_SPRUCE_TREE_02_STRIPPED, configureFeature(NFeatures.LARGE_BENT_SPRUCE_TREE_02, new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(LARGE_BENT_SPRUCE_TREE_02, configureFeature(NFeatures.LARGE_BENT_SPRUCE_TREE_02, new NTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));

        entries.add(DEEP_DARK_OAK, configureFeature(Feature.TREE, (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.DARK_OAK_LOG), new DeepDarkOakTrunkPlacer(13, 4, 2), BlockStateProvider.simple(Blocks.DARK_OAK_LEAVES), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()), RuleBasedStateProvider.ifTrueThenProvide(TreeConfiguration.CAN_PLACE_BELOW_TREE_TRUNKS, Blocks.DIRT))).ignoreVines().build()));
        // Spruce leaves ignore the biome colour, so use oak leaves.
        entries.add(BURNT_TREE_SMALL_01, configureFeature(NFeatures.SMALL_SPRUCE_TREE_01, burntTree()));
        entries.add(BURNT_TREE_SMALL_02, configureFeature(NFeatures.SMALL_SPRUCE_TREE_03, burntTree()));
        entries.add(BURNT_TREE_BENT, configureFeature(NFeatures.SMALL_BENT_SPRUCE_TREE_01, burntTree()));
        entries.add(BURNT_TREE_LARGE_BENT, configureFeature(NFeatures.LARGE_BENT_SPRUCE_TREE_01, burntTree()));
        entries.add(BURNT_TREE_LARGE, configureFeature(NFeatures.LARGE_SPRUCE_TREE_01, burntTree()));
        entries.add(BURNT_TREE_FALLEN, configureFeature(NFeatures.FALLEN_BURNT_TREE, burntTree()));

        entries.add(
                BURNT_LICHEN_FEATURE,
                configureFeature(
                        Feature.MULTIFACE_GROWTH,
                        new MultifaceGrowthConfiguration(
                                (MultifaceSpreadeableBlock) Blocks.GLOW_LICHEN,
                                20,
                                false,
                                true,
                                true,
                                0.5F,
                                HolderSet.direct(
                                        Blocks.POLISHED_BASALT.builtInRegistryHolder(),
                                        Blocks.BLACKSTONE.builtInRegistryHolder(),
                                        Blocks.BASALT.builtInRegistryHolder()
                                )
                        )
                )
        );

        entries.add(MARSH_CANDLE_FEATURE, configureFeature(NFeatures.FLOATING_CANDLE_FEATURE, FeatureConfiguration.NONE));
        entries.add(MARSH_ROOTS_FEATURE, configureFeature(NFeatures.MARSH_ROOTS_FEATURE, FeatureConfiguration.NONE));
        entries.add(MARSH_POOL_FEATURE, configureFeature(NFeatures.MARSH_POOL_FEATURE, FeatureConfiguration.NONE));
        entries.add(MARSH_THICKET_FEATURE, configureFeature(NFeatures.MARSH_THICKET_FEATURE, FeatureConfiguration.NONE));

        entries.add(TG_PATCH_LARGE_FERN, configureFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.LARGE_FERN))));
        entries.add(TG_PATCH_LEAVES, configureFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.OAK_LEAVES.defaultBlockState().setValue(BlockStateProperties.PERSISTENT, true)))));
        entries.add(TG_PATCH_MEADOW_FLOWER, configureFeature(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new DualNoiseProvider(new InclusiveRange(1, 3), new NormalNoise.NoiseParameters(-10, 1.0D), 1.0F, 2345L, new NormalNoise.NoiseParameters(-3, 1.0D, new double[0]), 1.0F, List.of(Blocks.TALL_GRASS.defaultBlockState(), Blocks.ALLIUM.defaultBlockState(), Blocks.POPPY.defaultBlockState(), Blocks.AZURE_BLUET.defaultBlockState(), Blocks.DANDELION.defaultBlockState(), Blocks.CORNFLOWER.defaultBlockState(), Blocks.OXEYE_DAISY.defaultBlockState(), Blocks.SHORT_GRASS.defaultBlockState())))));
    }
}
