package com.lion.nyctophobia.init;

import com.google.common.base.Suppliers;
import com.lion.nyctophobia.platform.RegistryHelper;
import com.lion.nyctophobia.world.features.surfaceFeatures.*;
import com.lion.nyctophobia.world.features.surfaceFeatures.coralFeatures.DeadCoralClawFeature;
import com.lion.nyctophobia.world.features.surfaceFeatures.coralFeatures.DeadCoralMushroomFeature;
import com.lion.nyctophobia.world.features.surfaceFeatures.coralFeatures.DeadCoralTreeFeature;
import com.lion.nyctophobia.world.features.trees.*;
import com.mojang.serialization.MapCodec;
import java.util.function.Supplier;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.levelgen.feature.Feature;

public class NFeatures {

    public static void init() {}

    public static final Supplier<MapCodec<? extends Feature>> SMALL_SPRUCE_TREE_01 = registerType("small_spruce_tree_01", () -> SmallSpruceTree01.CODEC);
    public static final Supplier<MapCodec<? extends Feature>> SMALL_SPRUCE_TREE_02 = registerType("small_spruce_tree_02", () -> SmallSpruceTree02.CODEC);
    public static final Supplier<MapCodec<? extends Feature>> SMALL_SPRUCE_TREE_03 = registerType("small_spruce_tree_03", () -> SmallSpruceTree03.CODEC);
    public static final Supplier<MapCodec<? extends Feature>> SMALL_SPRUCE_TREE_04 = registerType("small_spruce_tree_04", () -> SmallSpruceTree04.CODEC);
    public static final Supplier<MapCodec<? extends Feature>> SMALL_SPRUCE_TREE_05 = registerType("small_spruce_tree_05", () -> SmallSpruceTree05.CODEC);
    public static final Supplier<MapCodec<? extends Feature>> SMALL_SPRUCE_TREE_06 = registerType("small_spruce_tree_06", () -> SmallSpruceTree06.CODEC);
    public static final Supplier<MapCodec<? extends Feature>> SMALL_BENT_SPRUCE_TREE_01 = registerType("small_bent_spruce_tree_01", () -> SmallBentSpruceTree01.CODEC);
    public static final Supplier<MapCodec<? extends Feature>> LARGE_BENT_SPRUCE_TREE_01 = registerType("large_bent_spruce_tree_01", () -> LargeBentSpruceTree01.CODEC);
    public static final Supplier<MapCodec<? extends Feature>> LARGE_BENT_SPRUCE_TREE_02 = registerType("large_bent_spruce_tree_02", () -> LargeBentSpruceTree02.CODEC);
    public static final Supplier<MapCodec<? extends Feature>> LARGE_SPRUCE_TREE_01 = registerType("large_spruce_tree_01", () -> LargeSpruceTree01.CODEC);
    public static final Supplier<MapCodec<? extends Feature>> LARGE_SPRUCE_TREE_02 = registerType("large_spruce_tree_02", () -> LargeSpruceTree02.CODEC);
    public static final Supplier<MapCodec<? extends Feature>> LARGE_SPRUCE_TREE_03 = registerType("large_spruce_tree_03", () -> LargeSpruceTree03.CODEC);
    public static final Supplier<MapCodec<? extends Feature>> FALLEN_SPRUCE_TREE = registerType("fallen_spruce_tree", () -> FallenSpruceTree.MOSSY_CODEC);
    public static final Supplier<MapCodec<? extends Feature>> FALLEN_BURNT_TREE = registerType("fallen_burnt_tree", () -> FallenSpruceTree.BURNT_CODEC);

    public static final Supplier<MossCarpetFeature> MOSS_CARPET_FEATURE = registerInstance("moss_carpet_feature", () -> new MossCarpetFeature());
    public static final Supplier<CobwebFeature> COBWEB_FEATURE = registerInstance("cobweb_feature", () -> new CobwebFeature());
    public static final Supplier<BushFeature> BUSH_FEATURE = registerInstance("bush_feature", () -> new BushFeature((LeavesBlock) Blocks.SPRUCE_LEAVES));
    public static final Supplier<BushFeature> DEEP_DARK_FOREST_BUSH_FEATURE = registerInstance("deep_dark_forest_bush_feature", () -> new BushFeature((LeavesBlock) Blocks.DARK_OAK_LEAVES));
    public static final Supplier<SoulLightFeature> SOUL_LIGHT_FEATURE = registerInstance("soul_light_feature", () -> new SoulLightFeature());
    public static final Supplier<FloatingCandleFeature> FLOATING_CANDLE_FEATURE = registerInstance("floating_candle_feature", () -> new FloatingCandleFeature());
    public static final Supplier<MarshRootsFeature> MARSH_ROOTS_FEATURE = registerInstance("marsh_roots_feature", () -> new MarshRootsFeature());
    public static final Supplier<MarshPoolFeature> MARSH_POOL_FEATURE = registerInstance("marsh_pool_feature", () -> new MarshPoolFeature());
    public static final Supplier<MarshThicketFeature> MARSH_THICKET_FEATURE = registerInstance("marsh_thicket_feature", () -> new MarshThicketFeature());
    public static final Supplier<FallenTreeFeature> FALLEN_TREE_FEATURE = registerInstance("fallen_tree_feature", () -> new FallenTreeFeature());
    public static final Supplier<DeadCoralTreeFeature> DEAD_CORAL_TREE_FEATURE = registerInstance("dead_coral_tree_feature", () -> new DeadCoralTreeFeature());
    public static final Supplier<DeadCoralClawFeature> DEAD_CORAL_CLAW_FEATURE = registerInstance("dead_coral_claw_feature", () -> new DeadCoralClawFeature());
    public static final Supplier<DeadCoralMushroomFeature> DEAD_CORAL_MUSHROOM_FEATURE = registerInstance("dead_coral_mushroom_feature", () -> new DeadCoralMushroomFeature());
    public static final Supplier<DeadCoralPatchFeature> DEAD_CORAL_PATCH_FEATURE = registerInstance("dead_coral_patch_feature", () -> new DeadCoralPatchFeature());

    private static Supplier<MapCodec<? extends Feature>> registerType(
            String id,
            Supplier<MapCodec<? extends Feature>> codec
    ) {
        return RegistryHelper.registerFeatureType(id, codec);
    }

    private static <F extends Feature> Supplier<F> registerInstance(String id, Supplier<F> factory) {
        Supplier<F> instance = Suppliers.memoize(factory::get);
        RegistryHelper.registerFeatureType(id, () -> instance.get().codec());

        return instance;
    }
}
