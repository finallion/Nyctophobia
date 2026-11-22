package com.lion.nyctophobia.init;

import com.lion.nyctophobia.world.features.trees.*;
import com.lion.nyctophobia.Nyctophobia;
import com.lion.nyctophobia.world.features.trees.config.NTreeFeatureConfig;
import com.lion.nyctophobia.world.trunk.DeepDarkOakTrunkPlacer;
import com.lion.nyctophobia.data.NRegistryContext;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BlockStateProviders;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.util.InclusiveRange;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.MultifaceGrowthFeature;
import net.minecraft.world.level.levelgen.feature.SimpleBlockFeature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.DarkOakFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.DualNoiseProvider;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import java.util.List;
import java.util.OptionalInt;

public class NConfiguredFeatures {
    public static ResourceKey<Feature> of(String id) {
        return ResourceKey.create(Registries.FEATURE, Identifier.fromNamespaceAndPath(Nyctophobia.MOD_ID, id));
    }

    public static void init() {}

    public static final ResourceKey<Feature> MOSS_CARPET_FEATURE = of("moss_carpet_feature");
    public static final ResourceKey<Feature> COBWEB_FEATURE = of("cobweb_feature");
    public static final ResourceKey<Feature> BUSH_FEATURE = of("bush_feature");
    public static final ResourceKey<Feature> DEEP_DARK_FOREST_BUSH_FEATURE = of("deep_dark_forest_bush_feature");
    public static final ResourceKey<Feature> SOUL_LIGHT_FEATURE = of("soul_light_feature");
    public static final ResourceKey<Feature> FALLEN_TREE_FEATURE = of("fallen_tree_feature");
    public static final ResourceKey<Feature> DEAD_CORAL_PATCH_FEATURE = of("dead_coral_patch_feature");
    public static final ResourceKey<Feature> SMALL_SPRUCE_TREE_01_STRIPPED = of("small_spruce_tree_stripped_01");
    public static final ResourceKey<Feature> SMALL_SPRUCE_TREE_01 = of("small_spruce_tree_01");
    public static final ResourceKey<Feature> SMALL_SPRUCE_TREE_02_STRIPPED = of("small_spruce_tree_stripped_02");
    public static final ResourceKey<Feature> SMALL_SPRUCE_TREE_02 = of("small_spruce_tree_02");
    public static final ResourceKey<Feature> SMALL_SPRUCE_TREE_03_STRIPPED = of("small_spruce_tree_stripped_03");
    public static final ResourceKey<Feature> SMALL_SPRUCE_TREE_03 = of("small_spruce_tree_03");
    public static final ResourceKey<Feature> SMALL_SPRUCE_TREE_04_STRIPPED = of("small_spruce_tree_stripped_04");
    public static final ResourceKey<Feature> SMALL_SPRUCE_TREE_04 = of("small_spruce_tree_04");
    public static final ResourceKey<Feature> SMALL_SPRUCE_TREE_05_STRIPPED = of("small_spruce_tree_stripped_05");
    public static final ResourceKey<Feature> SMALL_SPRUCE_TREE_05 = of("small_spruce_tree_05");
    public static final ResourceKey<Feature> SMALL_SPRUCE_TREE_06_STRIPPED = of("small_spruce_tree_stripped_06");
    public static final ResourceKey<Feature> SMALL_SPRUCE_TREE_06 = of("small_spruce_tree_06");
    public static final ResourceKey<Feature> SMALL_BENT_SPRUCE_TREE_01_STRIPPED = of("small_bent_spruce_tree_stripped_01");
    public static final ResourceKey<Feature> SMALL_BENT_SPRUCE_TREE_01 = of("small_bent_spruce_tree_01");
    public static final ResourceKey<Feature> FALLEN_SPRUCE_TREE_STRIPPED = of("fallen_spruce_stripped_tree");
    public static final ResourceKey<Feature> FALLEN_SPRUCE_TREE = of("fallen_spruce_tree");
    public static final ResourceKey<Feature> LARGE_SPRUCE_TREE_01_STRIPPED = of("large_spruce_tree_stripped_01");
    public static final ResourceKey<Feature> LARGE_SPRUCE_TREE_01 = of("large_spruce_tree_01");
    public static final ResourceKey<Feature> LARGE_SPRUCE_TREE_02_STRIPPED = of("large_spruce_tree_stripped_02");
    public static final ResourceKey<Feature> LARGE_SPRUCE_TREE_02 = of("large_spruce_tree_02");
    public static final ResourceKey<Feature> LARGE_SPRUCE_TREE_03_STRIPPED = of("large_spruce_tree_stripped_03");
    public static final ResourceKey<Feature> LARGE_SPRUCE_TREE_03 = of("large_spruce_tree_03");
    public static final ResourceKey<Feature> LARGE_BENT_SPRUCE_TREE_01_STRIPPED = of("large_bent_spruce_tree_stripped_01");
    public static final ResourceKey<Feature> LARGE_BENT_SPRUCE_TREE_01 = of("large_bent_spruce_tree_01");
    public static final ResourceKey<Feature> LARGE_BENT_SPRUCE_TREE_02_STRIPPED = of("large_bent_spruce_tree_stripped_02");
    public static final ResourceKey<Feature> LARGE_BENT_SPRUCE_TREE_02 = of("large_bent_spruce_tree_02");

    public static final ResourceKey<Feature> DEEP_DARK_OAK = of("deep_dark_oak");
    public static final ResourceKey<Feature> MARSH_CANDLE_FEATURE = of("marsh_candle_feature");
    public static final ResourceKey<Feature> MARSH_ROOTS_FEATURE = of("marsh_roots_feature");
    public static final ResourceKey<Feature> MARSH_POOL_FEATURE = of("marsh_pool_feature");
    public static final ResourceKey<Feature> MARSH_THICKET_FEATURE = of("marsh_thicket_feature");

    public static final ResourceKey<Feature> BURNT_TREE_SMALL_01 = of("burnt_tree_small_01");
    public static final ResourceKey<Feature> BURNT_TREE_SMALL_02 = of("burnt_tree_small_02");
    public static final ResourceKey<Feature> BURNT_TREE_BENT = of("burnt_tree_bent");
    public static final ResourceKey<Feature> BURNT_TREE_LARGE_BENT = of("burnt_tree_large_bent");
    public static final ResourceKey<Feature> BURNT_TREE_LARGE = of("burnt_tree_large");
    public static final ResourceKey<Feature> BURNT_TREE_FALLEN = of("burnt_tree_fallen");
    public static final ResourceKey<Feature> BURNT_LICHEN_FEATURE = of("burnt_lichen_feature");
    public static final ResourceKey<Feature> TG_PATCH_LARGE_FERN = of("tg_patch_large_fern_config");
    public static final ResourceKey<Feature> TG_PATCH_LEAVES = of("tg_patch_leaves_config");
    public static final ResourceKey<Feature> TG_PATCH_MEADOW_FLOWER = of("tg_patch_meadow_flower_config");

    private static NTreeFeatureConfig burntTree() {
        return new NTreeFeatureConfig(Blocks.POLISHED_BASALT.defaultBlockState(), Blocks.OAK_LEAVES.defaultBlockState());
    }

    public static void populate(NRegistryContext<Feature> entries) {
        entries.add(MOSS_CARPET_FEATURE, NFeatures.MOSS_CARPET_FEATURE.get());
        entries.add(COBWEB_FEATURE, NFeatures.COBWEB_FEATURE.get());
        entries.add(BUSH_FEATURE, NFeatures.BUSH_FEATURE.get());
        entries.add(DEEP_DARK_FOREST_BUSH_FEATURE, NFeatures.DEEP_DARK_FOREST_BUSH_FEATURE.get());
        entries.add(SOUL_LIGHT_FEATURE, NFeatures.SOUL_LIGHT_FEATURE.get());
        entries.add(FALLEN_TREE_FEATURE, NFeatures.FALLEN_TREE_FEATURE.get());
        entries.add(DEAD_CORAL_PATCH_FEATURE, NFeatures.DEAD_CORAL_PATCH_FEATURE.get());

        entries.add(SMALL_SPRUCE_TREE_01_STRIPPED, new SmallSpruceTree01(new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(SMALL_SPRUCE_TREE_01, new SmallSpruceTree01(new NTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(SMALL_SPRUCE_TREE_02_STRIPPED, new SmallSpruceTree02(new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(SMALL_SPRUCE_TREE_02, new SmallSpruceTree02(new NTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(SMALL_SPRUCE_TREE_03_STRIPPED, new SmallSpruceTree03(new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(SMALL_SPRUCE_TREE_03, new SmallSpruceTree03(new NTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(SMALL_SPRUCE_TREE_04_STRIPPED, new SmallSpruceTree04(new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(SMALL_SPRUCE_TREE_04, new SmallSpruceTree04(new NTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(SMALL_SPRUCE_TREE_05_STRIPPED, new SmallSpruceTree05(new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(SMALL_SPRUCE_TREE_05, new SmallSpruceTree05(new NTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(SMALL_SPRUCE_TREE_06_STRIPPED, new SmallSpruceTree06(new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(SMALL_SPRUCE_TREE_06, new SmallSpruceTree06(new NTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(SMALL_BENT_SPRUCE_TREE_01_STRIPPED, new SmallBentSpruceTree01(new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(SMALL_BENT_SPRUCE_TREE_01, new SmallBentSpruceTree01(new NTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(FALLEN_SPRUCE_TREE_STRIPPED, new FallenSpruceTree(new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState()), true));
        entries.add(FALLEN_SPRUCE_TREE, new FallenSpruceTree(new NTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState()), true));
        entries.add(LARGE_SPRUCE_TREE_01_STRIPPED, new LargeSpruceTree01(new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(LARGE_SPRUCE_TREE_01, new LargeSpruceTree01(new NTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(LARGE_SPRUCE_TREE_02_STRIPPED, new LargeSpruceTree02(new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(LARGE_SPRUCE_TREE_02, new LargeSpruceTree02(new NTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(LARGE_SPRUCE_TREE_03_STRIPPED, new LargeSpruceTree03(new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(LARGE_SPRUCE_TREE_03, new LargeSpruceTree03(new NTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(LARGE_BENT_SPRUCE_TREE_01_STRIPPED, new LargeBentSpruceTree01(new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(LARGE_BENT_SPRUCE_TREE_01, new LargeBentSpruceTree01(new NTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(LARGE_BENT_SPRUCE_TREE_02_STRIPPED, new LargeBentSpruceTree02(new NTreeFeatureConfig(Blocks.STRIPPED_SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));
        entries.add(LARGE_BENT_SPRUCE_TREE_02, new LargeBentSpruceTree02(new NTreeFeatureConfig(Blocks.SPRUCE_LOG.defaultBlockState(), Blocks.SPRUCE_LEAVES.defaultBlockState())));

        entries.add(DEEP_DARK_OAK, new TreeFeature.Builder(BlockStateProvider.of(Blocks.DARK_OAK_LOG), new DeepDarkOakTrunkPlacer(13, 4, 2), BlockStateProvider.of(Blocks.DARK_OAK_LEAVES), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()), entries.ref(BlockStateProviders.SOIL_BENEATH_TREE)).ignoreVines().build());
        entries.add(BURNT_TREE_SMALL_01, new SmallSpruceTree01(burntTree()));
        entries.add(BURNT_TREE_SMALL_02, new SmallSpruceTree03(burntTree()));
        entries.add(BURNT_TREE_BENT, new SmallBentSpruceTree01(burntTree()));
        entries.add(BURNT_TREE_LARGE_BENT, new LargeBentSpruceTree01(burntTree()));
        entries.add(BURNT_TREE_LARGE, new LargeSpruceTree01(burntTree()));
        entries.add(BURNT_TREE_FALLEN, new FallenSpruceTree(burntTree(), false));

        entries.add(
                BURNT_LICHEN_FEATURE,
                new MultifaceGrowthFeature(
                        Blocks.GLOW_LICHEN,
                        20,
                        false,
                        true,
                        true,
                        0.5F,
                        HolderSet.direct(Blocks.POLISHED_BASALT.builtInRegistryHolder(), Blocks.BLACKSTONE.builtInRegistryHolder(), Blocks.BASALT.builtInRegistryHolder())
                )
        );

        entries.add(MARSH_CANDLE_FEATURE, NFeatures.FLOATING_CANDLE_FEATURE.get());
        entries.add(MARSH_ROOTS_FEATURE, NFeatures.MARSH_ROOTS_FEATURE.get());
        entries.add(MARSH_POOL_FEATURE, NFeatures.MARSH_POOL_FEATURE.get());
        entries.add(MARSH_THICKET_FEATURE, NFeatures.MARSH_THICKET_FEATURE.get());

        entries.add(TG_PATCH_LARGE_FERN, new SimpleBlockFeature(BlockStateProvider.of(Blocks.LARGE_FERN)));
        entries.add(TG_PATCH_LEAVES, new SimpleBlockFeature(BlockStateProvider.of(Blocks.OAK_LEAVES.defaultBlockState().setValue(BlockStateProperties.PERSISTENT, true))));
        entries.add(TG_PATCH_MEADOW_FLOWER, new SimpleBlockFeature(new DualNoiseProvider(new InclusiveRange(1, 3), NormalNoise.createParity(-10, 1.0D), 1.0F, 2345L, NormalNoise.createParity(-3, 1.0D), 1.0F, List.of(Blocks.TALL_GRASS.defaultBlockState(), Blocks.ALLIUM.defaultBlockState(), Blocks.POPPY.defaultBlockState(), Blocks.AZURE_BLUET.defaultBlockState(), Blocks.DANDELION.defaultBlockState(), Blocks.CORNFLOWER.defaultBlockState(), Blocks.OXEYE_DAISY.defaultBlockState(), Blocks.SHORT_GRASS.defaultBlockState()))));
    }
}
