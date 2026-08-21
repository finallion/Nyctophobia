package com.lion.nyctophobia.init;

import com.lion.nyctophobia.platform.RegistryHelper;
import com.lion.nyctophobia.world.features.surfaceFeatures.*;
import com.lion.nyctophobia.world.features.surfaceFeatures.coralFeatures.DeadCoralClawFeature;
import com.lion.nyctophobia.world.features.surfaceFeatures.coralFeatures.DeadCoralMushroomFeature;
import com.lion.nyctophobia.world.features.surfaceFeatures.coralFeatures.DeadCoralTreeFeature;
import com.lion.nyctophobia.world.features.trees.*;
import com.lion.nyctophobia.world.features.trees.config.NTreeFeatureConfig;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

import java.util.function.Supplier;

public class NFeatures {


    public static void init() {

    }

    // tree features
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

    // other features
    public static final Supplier<Feature<DefaultFeatureConfig>> MOSS_CARPET_FEATURE = registerFeature("moss_carpet_feature", () -> new MossCarpetFeature(DefaultFeatureConfig.CODEC));
    public static final Supplier<Feature<DefaultFeatureConfig>> COBWEB_FEATURE = registerFeature("cobweb_feature", () -> new CobwebFeature(DefaultFeatureConfig.CODEC));
    public static final Supplier<Feature<DefaultFeatureConfig>> BUSH_FEATURE = registerFeature("bush_feature", () -> new BushFeature(DefaultFeatureConfig.CODEC, (LeavesBlock) Blocks.SPRUCE_LEAVES));
    public static final Supplier<Feature<DefaultFeatureConfig>> DEEP_DARK_FOREST_BUSH_FEATURE = registerFeature("deep_dark_forest_bush_feature", () -> new BushFeature(DefaultFeatureConfig.CODEC, (LeavesBlock) Blocks.DARK_OAK_LEAVES));
    public static final Supplier<Feature<DefaultFeatureConfig>> SOUL_LIGHT_FEATURE = registerFeature("soul_light_feature", () -> new SoulLightFeature(DefaultFeatureConfig.CODEC));
    public static final Supplier<Feature<DefaultFeatureConfig>> FLOATING_CANDLE_FEATURE = registerFeature("floating_candle_feature", () -> new FloatingCandleFeature(DefaultFeatureConfig.CODEC));
    public static final Supplier<Feature<DefaultFeatureConfig>> MARSH_ROOTS_FEATURE = registerFeature("marsh_roots_feature", () -> new MarshRootsFeature(DefaultFeatureConfig.CODEC));
    public static final Supplier<Feature<DefaultFeatureConfig>> MARSH_POOL_FEATURE = registerFeature("marsh_pool_feature", () -> new MarshPoolFeature(DefaultFeatureConfig.CODEC));
    public static final Supplier<Feature<DefaultFeatureConfig>> MARSH_THICKET_FEATURE = registerFeature("marsh_thicket_feature", () -> new MarshThicketFeature(DefaultFeatureConfig.CODEC));
    public static final Supplier<Feature<DefaultFeatureConfig>> FALLEN_TREE_FEATURE = registerFeature("fallen_tree_feature", () -> new FallenTreeFeature(DefaultFeatureConfig.CODEC));
    public static final Supplier<Feature<DefaultFeatureConfig>> DEAD_CORAL_TREE_FEATURE = registerFeature("dead_coral_tree_feature", () -> new DeadCoralTreeFeature(DefaultFeatureConfig.CODEC));
    public static final Supplier<Feature<DefaultFeatureConfig>> DEAD_CORAL_CLAW_FEATURE = registerFeature("dead_coral_claw_feature", () -> new DeadCoralClawFeature(DefaultFeatureConfig.CODEC));
    public static final Supplier<Feature<DefaultFeatureConfig>> DEAD_CORAL_MUSHROOM_FEATURE = registerFeature("dead_coral_mushroom_feature", () -> new DeadCoralMushroomFeature(DefaultFeatureConfig.CODEC));
    public static final Supplier<Feature<DefaultFeatureConfig>> DEAD_CORAL_PATCH_FEATURE = registerFeature("dead_coral_patch_feature", () -> new DeadCoralPatchFeature(DefaultFeatureConfig.CODEC));


    private static <C extends FeatureConfig, F extends Feature<C>> Supplier<F> registerFeature(String id, Supplier<F> feature) {
        return RegistryHelper.registerFeature(id, feature);
    }
}
