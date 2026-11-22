package com.lion.nyctophobia.init;

import com.lion.nyctophobia.platform.RegistryHelper;
import com.lion.nyctophobia.world.features.surfaceFeatures.*;
import com.lion.nyctophobia.world.features.surfaceFeatures.coralFeatures.DeadCoralClawFeature;
import com.lion.nyctophobia.world.features.surfaceFeatures.coralFeatures.DeadCoralMushroomFeature;
import com.lion.nyctophobia.world.features.surfaceFeatures.coralFeatures.DeadCoralTreeFeature;
import com.lion.nyctophobia.world.features.trees.*;
import com.lion.nyctophobia.world.features.trees.config.NTreeFeatureConfig;
import java.util.function.Supplier;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class NFeatures {


    public static void init() {

    }

    public static final Supplier<Feature<NTreeFeatureConfig>> SMALL_SPRUCE_TREE_01 = registerFeature("small_spruce_tree_01", () -> new SmallSpruceTree01(NTreeFeatureConfig.CODEC));
    public static final Supplier<Feature<NTreeFeatureConfig>> SMALL_SPRUCE_TREE_02 = registerFeature("small_spruce_tree_02", () -> new SmallSpruceTree02(NTreeFeatureConfig.CODEC));
    public static final Supplier<Feature<NTreeFeatureConfig>> SMALL_SPRUCE_TREE_03 = registerFeature("small_spruce_tree_03", () -> new SmallSpruceTree03(NTreeFeatureConfig.CODEC));
    public static final Supplier<Feature<NTreeFeatureConfig>> SMALL_SPRUCE_TREE_04 = registerFeature("small_spruce_tree_04", () -> new SmallSpruceTree04(NTreeFeatureConfig.CODEC));
    public static final Supplier<Feature<NTreeFeatureConfig>> SMALL_SPRUCE_TREE_05 = registerFeature("small_spruce_tree_05", () -> new SmallSpruceTree05(NTreeFeatureConfig.CODEC));
    public static final Supplier<Feature<NTreeFeatureConfig>> SMALL_SPRUCE_TREE_06 = registerFeature("small_spruce_tree_06", () -> new SmallSpruceTree06(NTreeFeatureConfig.CODEC));
    public static final Supplier<Feature<NTreeFeatureConfig>> SMALL_BENT_SPRUCE_TREE_01 = registerFeature("small_bent_spruce_tree_01", () -> new SmallBentSpruceTree01(NTreeFeatureConfig.CODEC));
    public static final Supplier<Feature<NTreeFeatureConfig>> FALLEN_SPRUCE_TREE = registerFeature("fallen_spruce_tree", () -> new FallenSpruceTree(NTreeFeatureConfig.CODEC, true));
    public static final Supplier<Feature<NTreeFeatureConfig>> FALLEN_BURNT_TREE = registerFeature("fallen_burnt_tree", () -> new FallenSpruceTree(NTreeFeatureConfig.CODEC, false));
    public static final Supplier<Feature<NTreeFeatureConfig>> LARGE_BENT_SPRUCE_TREE_01 = registerFeature("large_bent_spruce_tree_01", () -> new LargeBentSpruceTree01(NTreeFeatureConfig.CODEC));
    public static final Supplier<Feature<NTreeFeatureConfig>> LARGE_BENT_SPRUCE_TREE_02 = registerFeature("large_bent_spruce_tree_02", () -> new LargeBentSpruceTree02(NTreeFeatureConfig.CODEC));
    public static final Supplier<Feature<NTreeFeatureConfig>> LARGE_SPRUCE_TREE_01 = registerFeature("large_spruce_tree_01", () -> new LargeSpruceTree01(NTreeFeatureConfig.CODEC));
    public static final Supplier<Feature<NTreeFeatureConfig>> LARGE_SPRUCE_TREE_02 = registerFeature("large_spruce_tree_02", () -> new LargeSpruceTree02(NTreeFeatureConfig.CODEC));
    public static final Supplier<Feature<NTreeFeatureConfig>> LARGE_SPRUCE_TREE_03 = registerFeature("large_spruce_tree_03", () -> new LargeSpruceTree03(NTreeFeatureConfig.CODEC));

    public static final Supplier<Feature<NoneFeatureConfiguration>> MOSS_CARPET_FEATURE = registerFeature("moss_carpet_feature", () -> new MossCarpetFeature(NoneFeatureConfiguration.CODEC));
    public static final Supplier<Feature<NoneFeatureConfiguration>> COBWEB_FEATURE = registerFeature("cobweb_feature", () -> new CobwebFeature(NoneFeatureConfiguration.CODEC));
    public static final Supplier<Feature<NoneFeatureConfiguration>> BUSH_FEATURE = registerFeature("bush_feature", () -> new BushFeature(NoneFeatureConfiguration.CODEC, (LeavesBlock) Blocks.SPRUCE_LEAVES));
    public static final Supplier<Feature<NoneFeatureConfiguration>> DEEP_DARK_FOREST_BUSH_FEATURE = registerFeature("deep_dark_forest_bush_feature", () -> new BushFeature(NoneFeatureConfiguration.CODEC, (LeavesBlock) Blocks.DARK_OAK_LEAVES));
    public static final Supplier<Feature<NoneFeatureConfiguration>> SOUL_LIGHT_FEATURE = registerFeature("soul_light_feature", () -> new SoulLightFeature(NoneFeatureConfiguration.CODEC));
    public static final Supplier<Feature<NoneFeatureConfiguration>> FLOATING_CANDLE_FEATURE = registerFeature("floating_candle_feature", () -> new FloatingCandleFeature(NoneFeatureConfiguration.CODEC));
    public static final Supplier<Feature<NoneFeatureConfiguration>> MARSH_ROOTS_FEATURE = registerFeature("marsh_roots_feature", () -> new MarshRootsFeature(NoneFeatureConfiguration.CODEC));
    public static final Supplier<Feature<NoneFeatureConfiguration>> MARSH_POOL_FEATURE = registerFeature("marsh_pool_feature", () -> new MarshPoolFeature(NoneFeatureConfiguration.CODEC));
    public static final Supplier<Feature<NoneFeatureConfiguration>> MARSH_THICKET_FEATURE = registerFeature("marsh_thicket_feature", () -> new MarshThicketFeature(NoneFeatureConfiguration.CODEC));
    public static final Supplier<Feature<NoneFeatureConfiguration>> FALLEN_TREE_FEATURE = registerFeature("fallen_tree_feature", () -> new FallenTreeFeature(NoneFeatureConfiguration.CODEC));
    public static final Supplier<Feature<NoneFeatureConfiguration>> DEAD_CORAL_TREE_FEATURE = registerFeature("dead_coral_tree_feature", () -> new DeadCoralTreeFeature(NoneFeatureConfiguration.CODEC));
    public static final Supplier<Feature<NoneFeatureConfiguration>> DEAD_CORAL_CLAW_FEATURE = registerFeature("dead_coral_claw_feature", () -> new DeadCoralClawFeature(NoneFeatureConfiguration.CODEC));
    public static final Supplier<Feature<NoneFeatureConfiguration>> DEAD_CORAL_MUSHROOM_FEATURE = registerFeature("dead_coral_mushroom_feature", () -> new DeadCoralMushroomFeature(NoneFeatureConfiguration.CODEC));
    public static final Supplier<Feature<NoneFeatureConfiguration>> DEAD_CORAL_PATCH_FEATURE = registerFeature("dead_coral_patch_feature", () -> new DeadCoralPatchFeature(NoneFeatureConfiguration.CODEC));


    private static <C extends FeatureConfiguration, F extends Feature<C>> Supplier<F> registerFeature(
            String id,
            Supplier<F> feature
    ) {
        return RegistryHelper.registerFeature(id, feature);
    }
}
