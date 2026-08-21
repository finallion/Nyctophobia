package com.lion.nyctophobia.init;

import java.util.function.Supplier;
import com.lion.nyctophobia.Nyctophobia;
import com.lion.nyctophobia.world.features.trees.config.NTreeFeatureConfig;
import com.lion.nyctophobia.world.trunk.DeepDarkOakTrunkPlacer;
import com.lion.nyctophobia.data.NRegistryContext;
import net.minecraft.block.*;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Range;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.ThreeLayersFeatureSize;
import net.minecraft.world.gen.foliage.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.DualNoiseBlockStateProvider;

import java.util.List;
import java.util.OptionalInt;

public class NConfiguredFeatures {
    public static RegistryKey<ConfiguredFeature<?, ?>> of(String id) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(Nyctophobia.MOD_ID, id));
    }

    public static void init() {}

    public static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> registerable, RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        registerable.register(key, new ConfiguredFeature<>(feature, config));
    }

    public static final RegistryKey<ConfiguredFeature<?, ?>> MOSS_CARPET_FEATURE = of("moss_carpet_feature");
    public static final RegistryKey<ConfiguredFeature<?, ?>> COBWEB_FEATURE = of("cobweb_feature");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BUSH_FEATURE = of("bush_feature");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DEEP_DARK_FOREST_BUSH_FEATURE = of("deep_dark_forest_bush_feature");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SOUL_LIGHT_FEATURE = of("soul_light_feature");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FALLEN_TREE_FEATURE = of("fallen_tree_feature");
    public static final RegistryKey<ConfiguredFeature<?, ?>> DEAD_CORAL_PATCH_FEATURE = of("dead_coral_patch_feature");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SMALL_SPRUCE_TREE_01_STRIPPED = of("small_spruce_tree_stripped_01");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SMALL_SPRUCE_TREE_01 = of("small_spruce_tree_01");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SMALL_SPRUCE_TREE_02_STRIPPED = of("small_spruce_tree_stripped_02");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SMALL_SPRUCE_TREE_02 = of("small_spruce_tree_02");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SMALL_SPRUCE_TREE_03_STRIPPED = of("small_spruce_tree_stripped_03");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SMALL_SPRUCE_TREE_03 = of("small_spruce_tree_03");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SMALL_SPRUCE_TREE_04_STRIPPED = of("small_spruce_tree_stripped_04");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SMALL_SPRUCE_TREE_04 = of("small_spruce_tree_04");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SMALL_SPRUCE_TREE_05_STRIPPED = of("small_spruce_tree_stripped_05");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SMALL_SPRUCE_TREE_05 = of("small_spruce_tree_05");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SMALL_SPRUCE_TREE_06_STRIPPED = of("small_spruce_tree_stripped_06");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SMALL_SPRUCE_TREE_06 = of("small_spruce_tree_06");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SMALL_BENT_SPRUCE_TREE_01_STRIPPED = of("small_bent_spruce_tree_stripped_01");
    public static final RegistryKey<ConfiguredFeature<?, ?>> SMALL_BENT_SPRUCE_TREE_01 = of("small_bent_spruce_tree_01");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FALLEN_SPRUCE_TREE_STRIPPED = of("fallen_spruce_stripped_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> FALLEN_SPRUCE_TREE = of("fallen_spruce_tree");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LARGE_SPRUCE_TREE_01_STRIPPED = of("large_spruce_tree_stripped_01");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LARGE_SPRUCE_TREE_01 = of("large_spruce_tree_01");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LARGE_SPRUCE_TREE_02_STRIPPED = of("large_spruce_tree_stripped_02");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LARGE_SPRUCE_TREE_02 = of("large_spruce_tree_02");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LARGE_SPRUCE_TREE_03_STRIPPED = of("large_spruce_tree_stripped_03");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LARGE_SPRUCE_TREE_03 = of("large_spruce_tree_03");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LARGE_BENT_SPRUCE_TREE_01_STRIPPED = of("large_bent_spruce_tree_stripped_01");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LARGE_BENT_SPRUCE_TREE_01 = of("large_bent_spruce_tree_01");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LARGE_BENT_SPRUCE_TREE_02_STRIPPED = of("large_bent_spruce_tree_stripped_02");
    public static final RegistryKey<ConfiguredFeature<?, ?>> LARGE_BENT_SPRUCE_TREE_02 = of("large_bent_spruce_tree_02");

    public static final RegistryKey<ConfiguredFeature<?, ?>> DEEP_DARK_OAK = of("deep_dark_oak");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MARSH_CANDLE_FEATURE = of("marsh_candle_feature");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MARSH_ROOTS_FEATURE = of("marsh_roots_feature");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MARSH_POOL_FEATURE = of("marsh_pool_feature");
    public static final RegistryKey<ConfiguredFeature<?, ?>> MARSH_THICKET_FEATURE = of("marsh_thicket_feature");

    public static final RegistryKey<ConfiguredFeature<?, ?>> BURNT_TREE_SMALL_01 = of("burnt_tree_small_01");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BURNT_TREE_SMALL_02 = of("burnt_tree_small_02");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BURNT_TREE_BENT = of("burnt_tree_bent");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BURNT_TREE_LARGE_BENT = of("burnt_tree_large_bent");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BURNT_TREE_LARGE = of("burnt_tree_large");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BURNT_TREE_FALLEN = of("burnt_tree_fallen");
    public static final RegistryKey<ConfiguredFeature<?, ?>> BURNT_LICHEN_FEATURE = of("burnt_lichen_feature");
    public static final RegistryKey<ConfiguredFeature<?, ?>> TG_PATCH_LARGE_FERN = of("tg_patch_large_fern_config");
    public static final RegistryKey<ConfiguredFeature<?, ?>> TG_PATCH_LEAVES = of("tg_patch_leaves_config");
    public static final RegistryKey<ConfiguredFeature<?, ?>> TG_PATCH_MEADOW_FLOWER = of("tg_patch_meadow_flower_config");

    private static NTreeFeatureConfig burntTree() {
        return new NTreeFeatureConfig(Blocks.POLISHED_BASALT.getDefaultState(), Blocks.OAK_LEAVES.getDefaultState());
    }

    public static <FC extends FeatureConfig, F extends Feature<FC>> ConfiguredFeature<FC, ?> configureFeature(F feature, FC config) {
        return new ConfiguredFeature<>(feature, config);
    }

    /// Overload for the mod's own features, which are Suppliers so that Forge can fill them from a
    /// DeferredRegister. Vanilla features keep using the direct overload above.
    public static <FC extends FeatureConfig, F extends Feature<FC>> ConfiguredFeature<FC, ?> configureFeature(Supplier<F> feature, FC config) {
        return new ConfiguredFeature<>(feature.get(), config);
    }

    public static void populate(NRegistryContext<ConfiguredFeature<?, ?>> entries) {
        entries.add(MOSS_CARPET_FEATURE, configureFeature(NFeatures.MOSS_CARPET_FEATURE, FeatureConfig.DEFAULT));
        entries.add(COBWEB_FEATURE, configureFeature(NFeatures.COBWEB_FEATURE, FeatureConfig.DEFAULT));
        entries.add(BUSH_FEATURE, configureFeature(NFeatures.BUSH_FEATURE, FeatureConfig.DEFAULT));
        entries.add(DEEP_DARK_FOREST_BUSH_FEATURE, configureFeature(NFeatures.DEEP_DARK_FOREST_BUSH_FEATURE, FeatureConfig.DEFAULT));
        entries.add(SOUL_LIGHT_FEATURE, configureFeature(NFeatures.SOUL_LIGHT_FEATURE, FeatureConfig.DEFAULT));
        entries.add(FALLEN_TREE_FEATURE, configureFeature(NFeatures.FALLEN_TREE_FEATURE, FeatureConfig.DEFAULT));
        entries.add(DEAD_CORAL_PATCH_FEATURE, configureFeature(NFeatures.DEAD_CORAL_PATCH_FEATURE, FeatureConfig.DEFAULT));

        entries.add(SMALL_SPRUCE_TREE_01_STRIPPED, configureFeature(NFeatures.SMALL_SPRUCE_TREE_01, new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState())));
        entries.add(SMALL_SPRUCE_TREE_01, configureFeature(NFeatures.SMALL_SPRUCE_TREE_01, new NTreeFeatureConfig(Blocks.SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState())));
        entries.add(SMALL_SPRUCE_TREE_02_STRIPPED, configureFeature(NFeatures.SMALL_SPRUCE_TREE_02, new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState())));
        entries.add(SMALL_SPRUCE_TREE_02, configureFeature(NFeatures.SMALL_SPRUCE_TREE_02, new NTreeFeatureConfig(Blocks.SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState())));
        entries.add(SMALL_SPRUCE_TREE_03_STRIPPED, configureFeature(NFeatures.SMALL_SPRUCE_TREE_03, new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState())));
        entries.add(SMALL_SPRUCE_TREE_03, configureFeature(NFeatures.SMALL_SPRUCE_TREE_03, new NTreeFeatureConfig(Blocks.SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState())));
        entries.add(SMALL_SPRUCE_TREE_04_STRIPPED, configureFeature(NFeatures.SMALL_SPRUCE_TREE_04, new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState())));
        entries.add(SMALL_SPRUCE_TREE_04, configureFeature(NFeatures.SMALL_SPRUCE_TREE_04, new NTreeFeatureConfig(Blocks.SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState())));
        entries.add(SMALL_SPRUCE_TREE_05_STRIPPED, configureFeature(NFeatures.SMALL_SPRUCE_TREE_05, new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState())));
        entries.add(SMALL_SPRUCE_TREE_05, configureFeature(NFeatures.SMALL_SPRUCE_TREE_05, new NTreeFeatureConfig(Blocks.SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState())));
        entries.add(SMALL_SPRUCE_TREE_06_STRIPPED, configureFeature(NFeatures.SMALL_SPRUCE_TREE_06, new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState())));
        entries.add(SMALL_SPRUCE_TREE_06, configureFeature(NFeatures.SMALL_SPRUCE_TREE_06, new NTreeFeatureConfig(Blocks.SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState())));
        entries.add(SMALL_BENT_SPRUCE_TREE_01_STRIPPED, configureFeature(NFeatures.SMALL_BENT_SPRUCE_TREE_01, new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState())));
        entries.add(SMALL_BENT_SPRUCE_TREE_01, configureFeature(NFeatures.SMALL_BENT_SPRUCE_TREE_01, new NTreeFeatureConfig(Blocks.SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState())));
        entries.add(FALLEN_SPRUCE_TREE_STRIPPED, configureFeature(NFeatures.FALLEN_SPRUCE_TREE, new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState())));
        entries.add(FALLEN_SPRUCE_TREE, configureFeature(NFeatures.FALLEN_SPRUCE_TREE, new NTreeFeatureConfig(Blocks.SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState())));
        entries.add(LARGE_SPRUCE_TREE_01_STRIPPED, configureFeature(NFeatures.LARGE_SPRUCE_TREE_01, new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState())));
        entries.add(LARGE_SPRUCE_TREE_01, configureFeature(NFeatures.LARGE_SPRUCE_TREE_01, new NTreeFeatureConfig(Blocks.SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState())));
        entries.add(LARGE_SPRUCE_TREE_02_STRIPPED, configureFeature(NFeatures.LARGE_SPRUCE_TREE_02, new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState())));
        entries.add(LARGE_SPRUCE_TREE_02, configureFeature(NFeatures.LARGE_SPRUCE_TREE_02, new NTreeFeatureConfig(Blocks.SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState())));
        entries.add(LARGE_SPRUCE_TREE_03_STRIPPED, configureFeature(NFeatures.LARGE_SPRUCE_TREE_03, new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState())));
        entries.add(LARGE_SPRUCE_TREE_03, configureFeature(NFeatures.LARGE_SPRUCE_TREE_03, new NTreeFeatureConfig(Blocks.SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState())));
        entries.add(LARGE_BENT_SPRUCE_TREE_01_STRIPPED, configureFeature(NFeatures.LARGE_BENT_SPRUCE_TREE_01, new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState())));
        entries.add(LARGE_BENT_SPRUCE_TREE_01, configureFeature(NFeatures.LARGE_BENT_SPRUCE_TREE_01, new NTreeFeatureConfig(Blocks.SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState())));
        entries.add(LARGE_BENT_SPRUCE_TREE_02_STRIPPED, configureFeature(NFeatures.LARGE_BENT_SPRUCE_TREE_02, new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState())));
        entries.add(LARGE_BENT_SPRUCE_TREE_02, configureFeature(NFeatures.LARGE_BENT_SPRUCE_TREE_02, new NTreeFeatureConfig(Blocks.SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState())));

        entries.add(DEEP_DARK_OAK, configureFeature(Feature.TREE, (new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.DARK_OAK_LOG), new DeepDarkOakTrunkPlacer(13, 4, 2), BlockStateProvider.of(Blocks.DARK_OAK_LEAVES), new DarkOakFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)), new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()))).ignoreVines().build()));
        // Charred trees. Stripped spruce reads as bare, not burnt, so the trunk is polished basalt -
        // its vertical grain sells a charred log, and the fallen variant needs the axis property to
        // turn it on its side.
        //
        // Leaves are oak, not spruce: spruce leaves carry a fixed colour and ignore the biome, so they
        // would stay green in a burnt wood. Oak leaves take the biome foliage colour.
        entries.add(BURNT_TREE_SMALL_01, configureFeature(NFeatures.SMALL_SPRUCE_TREE_01, burntTree()));
        entries.add(BURNT_TREE_SMALL_02, configureFeature(NFeatures.SMALL_SPRUCE_TREE_03, burntTree()));
        entries.add(BURNT_TREE_BENT, configureFeature(NFeatures.SMALL_BENT_SPRUCE_TREE_01, burntTree()));
        entries.add(BURNT_TREE_LARGE_BENT, configureFeature(NFeatures.LARGE_BENT_SPRUCE_TREE_01, burntTree()));
        entries.add(BURNT_TREE_LARGE, configureFeature(NFeatures.LARGE_SPRUCE_TREE_01, burntTree()));
        entries.add(BURNT_TREE_FALLEN, configureFeature(NFeatures.FALLEN_BURNT_TREE, burntTree()));

        entries.add(BURNT_LICHEN_FEATURE, configureFeature(Feature.MULTIFACE_GROWTH, new MultifaceGrowthFeatureConfig(
                (MultifaceGrowthBlock) Blocks.GLOW_LICHEN, 20, false, true, true, 0.5F,
                RegistryEntryList.of(Blocks.POLISHED_BASALT.getRegistryEntry(), Blocks.BLACKSTONE.getRegistryEntry(), Blocks.BASALT.getRegistryEntry()))));

        entries.add(MARSH_CANDLE_FEATURE, configureFeature(NFeatures.FLOATING_CANDLE_FEATURE, FeatureConfig.DEFAULT));
        entries.add(MARSH_ROOTS_FEATURE, configureFeature(NFeatures.MARSH_ROOTS_FEATURE, FeatureConfig.DEFAULT));
        entries.add(MARSH_POOL_FEATURE, configureFeature(NFeatures.MARSH_POOL_FEATURE, FeatureConfig.DEFAULT));
        entries.add(MARSH_THICKET_FEATURE, configureFeature(NFeatures.MARSH_THICKET_FEATURE, FeatureConfig.DEFAULT));

        entries.add(TG_PATCH_LARGE_FERN, configureFeature(Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(96, 7, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.LARGE_FERN))))));
        entries.add(TG_PATCH_LEAVES, configureFeature(Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(64, 6, 0, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.OAK_LEAVES.getDefaultState().with(Properties.PERSISTENT, true)))))));
        entries.add(TG_PATCH_MEADOW_FLOWER, configureFeature(Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(96, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new DualNoiseBlockStateProvider(new Range(1, 3), new DoublePerlinNoiseSampler.NoiseParameters(-10, 1.0D), 1.0F, 2345L, new DoublePerlinNoiseSampler.NoiseParameters(-3, 1.0D, new double[0]), 1.0F, List.of(Blocks.TALL_GRASS.getDefaultState(), Blocks.ALLIUM.getDefaultState(), Blocks.POPPY.getDefaultState(), Blocks.AZURE_BLUET.getDefaultState(), Blocks.DANDELION.getDefaultState(), Blocks.CORNFLOWER.getDefaultState(), Blocks.OXEYE_DAISY.getDefaultState(), Blocks.SHORT_GRASS.getDefaultState())))))));
    }



    /*
    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> featureRegisterable) {
        ConfiguredFeatures.register(featureRegisterable, MOSS_CARPET_FEATURE, TGFeatures.MOSS_CARPET_FEATURE, FeatureConfig.DEFAULT);
        ConfiguredFeatures.register(featureRegisterable, COBWEB_FEATURE, TGFeatures.COBWEB_FEATURE, FeatureConfig.DEFAULT);
        ConfiguredFeatures.register(featureRegisterable, BUSH_FEATURE, TGFeatures.BUSH_FEATURE, FeatureConfig.DEFAULT);
        ConfiguredFeatures.register(featureRegisterable, DEEP_DARK_FOREST_BUSH_FEATURE, TGFeatures.DEEP_DARK_FOREST_BUSH_FEATURE, FeatureConfig.DEFAULT);
        ConfiguredFeatures.register(featureRegisterable, SOUL_LIGHT_FEATURE, TGFeatures.SOUL_LIGHT_FEATURE, FeatureConfig.DEFAULT);
        ConfiguredFeatures.register(featureRegisterable, FALLEN_TREE_FEATURE, TGFeatures.FALLEN_TREE_FEATURE, FeatureConfig.DEFAULT);
        ConfiguredFeatures.register(featureRegisterable, DEAD_CORAL_PATCH_FEATURE, TGFeatures.DEAD_CORAL_PATCH_FEATURE, FeatureConfig.DEFAULT);

        ConfiguredFeatures.register(featureRegisterable, SMALL_SPRUCE_TREE_01_STRIPPED, TGFeatures.SMALL_SPRUCE_TREE_01, new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, SMALL_SPRUCE_TREE_01, TGFeatures.SMALL_SPRUCE_TREE_01, new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, SMALL_SPRUCE_TREE_02_STRIPPED, TGFeatures.SMALL_SPRUCE_TREE_02, new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, SMALL_SPRUCE_TREE_02, TGFeatures.SMALL_SPRUCE_TREE_02, new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, SMALL_SPRUCE_TREE_03_STRIPPED, TGFeatures.SMALL_SPRUCE_TREE_03, new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, SMALL_SPRUCE_TREE_03, TGFeatures.SMALL_SPRUCE_TREE_03, new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, SMALL_SPRUCE_TREE_04_STRIPPED, TGFeatures.SMALL_SPRUCE_TREE_04, new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, SMALL_SPRUCE_TREE_04, TGFeatures.SMALL_SPRUCE_TREE_04, new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, SMALL_SPRUCE_TREE_05_STRIPPED, TGFeatures.SMALL_SPRUCE_TREE_05, new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, SMALL_SPRUCE_TREE_05, TGFeatures.SMALL_SPRUCE_TREE_05, new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, SMALL_SPRUCE_TREE_06_STRIPPED, TGFeatures.SMALL_SPRUCE_TREE_06, new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, SMALL_SPRUCE_TREE_06, TGFeatures.SMALL_SPRUCE_TREE_06, new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, SMALL_BENT_SPRUCE_TREE_01_STRIPPED, TGFeatures.SMALL_BENT_SPRUCE_TREE_01, new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, SMALL_BENT_SPRUCE_TREE_01, TGFeatures.SMALL_BENT_SPRUCE_TREE_01, new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, FALLEN_SPRUCE_TREE_STRIPPED, TGFeatures.FALLEN_SPRUCE_TREE, new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, FALLEN_SPRUCE_TREE, TGFeatures.FALLEN_SPRUCE_TREE, new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, LARGE_SPRUCE_TREE_01_STRIPPED, TGFeatures.LARGE_SPRUCE_TREE_01, new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, LARGE_SPRUCE_TREE_01, TGFeatures.LARGE_SPRUCE_TREE_01, new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, LARGE_SPRUCE_TREE_02_STRIPPED, TGFeatures.LARGE_SPRUCE_TREE_02, new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, LARGE_SPRUCE_TREE_02, TGFeatures.LARGE_SPRUCE_TREE_02, new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, LARGE_SPRUCE_TREE_03_STRIPPED, TGFeatures.LARGE_SPRUCE_TREE_03, new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, LARGE_SPRUCE_TREE_03, TGFeatures.LARGE_SPRUCE_TREE_03, new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, LARGE_BENT_SPRUCE_TREE_01_STRIPPED, TGFeatures.LARGE_BENT_SPRUCE_TREE_01, new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, LARGE_BENT_SPRUCE_TREE_01, TGFeatures.LARGE_BENT_SPRUCE_TREE_01, new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, LARGE_BENT_SPRUCE_TREE_02_STRIPPED, TGFeatures.LARGE_BENT_SPRUCE_TREE_02, new TGTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState()));
        ConfiguredFeatures.register(featureRegisterable, LARGE_BENT_SPRUCE_TREE_02, TGFeatures.LARGE_BENT_SPRUCE_TREE_02, new TGTreeFeatureConfig(Blocks.SPRUCE_LOG.getDefaultState(), Blocks.SPRUCE_LEAVES.getDefaultState()));

        ConfiguredFeatures.register(featureRegisterable, DEEP_DARK_OAK, Feature.TREE, (new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.DARK_OAK_LOG), new DeepDarkOakTrunkPlacer(13, 4, 2), BlockStateProvider.of(Blocks.DARK_OAK_LEAVES), new DarkOakFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)), new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()))).ignoreVines().build());
        ConfiguredFeatures.register(featureRegisterable, TG_PATCH_LARGE_FERN, Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(96, 7, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.LARGE_FERN)))));
        ConfiguredFeatures.register(featureRegisterable, TG_PATCH_LEAVES, Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(64, 6, 0, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.OAK_LEAVES.getDefaultState().with(Properties.PERSISTENT, true))))));
        ConfiguredFeatures.register(featureRegisterable, TG_PATCH_MEADOW_FLOWER, Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(96, 6, 2, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new DualNoiseBlockStateProvider(new Range(1, 3), new DoublePerlinNoiseSampler.NoiseParameters(-10, 1.0D), 1.0F, 2345L, new DoublePerlinNoiseSampler.NoiseParameters(-3, 1.0D, new double[0]), 1.0F, List.of(Blocks.TALL_GRASS.getDefaultState(), Blocks.ALLIUM.getDefaultState(), Blocks.POPPY.getDefaultState(), Blocks.AZURE_BLUET.getDefaultState(), Blocks.DANDELION.getDefaultState(), Blocks.CORNFLOWER.getDefaultState(), Blocks.OXEYE_DAISY.getDefaultState(), Blocks.SHORT_GRASS.getDefaultState()))))));

        RegistryEntryLookup<PlacedFeature> registryEntryLookup = featureRegisterable.getRegistryLookup(RegistryKeys.PLACED_FEATURE);
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup2 = featureRegisterable.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        ConfiguredFeatures.register(featureRegisterable, HAUNTED_FOREST_TREES, Feature.RANDOM_SELECTOR, new RandomFeatureConfig(List.of(
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(SMALL_SPRUCE_TREE_01), new PlacementModifier[0]), 0.25F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(SMALL_SPRUCE_TREE_02), new PlacementModifier[0]), 0.25F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(SMALL_SPRUCE_TREE_03), new PlacementModifier[0]), 0.375F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(SMALL_SPRUCE_TREE_04), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(SMALL_SPRUCE_TREE_05), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(SMALL_SPRUCE_TREE_06), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(SMALL_BENT_SPRUCE_TREE_01), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(FALLEN_SPRUCE_TREE), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(LARGE_SPRUCE_TREE_01), new PlacementModifier[0]), 0.25F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(LARGE_SPRUCE_TREE_02), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(LARGE_SPRUCE_TREE_03), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(SMALL_SPRUCE_TREE_01_STRIPPED), new PlacementModifier[0]), 0.25F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(SMALL_SPRUCE_TREE_02_STRIPPED), new PlacementModifier[0]), 0.25F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(SMALL_SPRUCE_TREE_03_STRIPPED), new PlacementModifier[0]), 0.375F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(SMALL_SPRUCE_TREE_04_STRIPPED), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(SMALL_SPRUCE_TREE_05_STRIPPED), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(SMALL_SPRUCE_TREE_06_STRIPPED), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(SMALL_BENT_SPRUCE_TREE_01_STRIPPED), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(FALLEN_SPRUCE_TREE_STRIPPED), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(LARGE_SPRUCE_TREE_01_STRIPPED), new PlacementModifier[0]), 0.25F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(LARGE_SPRUCE_TREE_02_STRIPPED), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(LARGE_SPRUCE_TREE_03_STRIPPED), new PlacementModifier[0]), 0.1F)),
                registryEntryLookup.getOrThrow(TreePlacedFeatures.SPRUCE_CHECKED)));

        ConfiguredFeatures.register(featureRegisterable, ERODED_HAUNTED_FOREST_TREES, Feature.RANDOM_SELECTOR, new RandomFeatureConfig(List.of(
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(SMALL_BENT_SPRUCE_TREE_01), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(FALLEN_SPRUCE_TREE), new PlacementModifier[0]), 0.0075F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(LARGE_BENT_SPRUCE_TREE_01), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(LARGE_BENT_SPRUCE_TREE_02), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(LARGE_SPRUCE_TREE_01), new PlacementModifier[0]), 0.5F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(LARGE_SPRUCE_TREE_02), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(LARGE_SPRUCE_TREE_03), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(SMALL_BENT_SPRUCE_TREE_01_STRIPPED), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(FALLEN_SPRUCE_TREE_STRIPPED), new PlacementModifier[0]), 0.0075F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(LARGE_BENT_SPRUCE_TREE_01_STRIPPED), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(LARGE_BENT_SPRUCE_TREE_02_STRIPPED), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(LARGE_SPRUCE_TREE_01_STRIPPED), new PlacementModifier[0]), 0.5F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(LARGE_SPRUCE_TREE_02_STRIPPED), new PlacementModifier[0]), 0.1F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(LARGE_SPRUCE_TREE_03_STRIPPED), new PlacementModifier[0]), 0.1F)),
                registryEntryLookup.getOrThrow(TreePlacedFeatures.SPRUCE_CHECKED)));

        ConfiguredFeatures.register(featureRegisterable, ANCIENT_DEAD_REEF_VEGETATION, Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfig(RegistryEntryList.of(
                PlacedFeatures.createEntry(TGFeatures.DEAD_CORAL_TREE_FEATURE, FeatureConfig.DEFAULT, new PlacementModifier[0]),
                PlacedFeatures.createEntry(TGFeatures.DEAD_CORAL_CLAW_FEATURE, FeatureConfig.DEFAULT, new PlacementModifier[0]),
                PlacedFeatures.createEntry(TGFeatures.DEAD_CORAL_MUSHROOM_FEATURE, FeatureConfig.DEFAULT, new PlacementModifier[0]))));


        ConfiguredFeatures.register(featureRegisterable, DEEP_DARK_FOREST_VEGETATION, Feature.RANDOM_SELECTOR, new RandomFeatureConfig(List.of(
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(TreeConfiguredFeatures.HUGE_BROWN_MUSHROOM), new PlacementModifier[0]), 0.025F),
                new RandomFeatureEntry(PlacedFeatures.createEntry(registryEntryLookup2.getOrThrow(TreeConfiguredFeatures.HUGE_RED_MUSHROOM), new PlacementModifier[0]), 0.025F),
                new RandomFeatureEntry(registryEntryLookup.getOrThrow(TGPlacedFeatures.DEEP_DARK_OAK), 0.8666667F),
                new RandomFeatureEntry(registryEntryLookup.getOrThrow(TreePlacedFeatures.DARK_OAK_CHECKED), 0.1F)),
                        registryEntryLookup.getOrThrow(TreePlacedFeatures.DARK_OAK_CHECKED)));
    }

     */


}
