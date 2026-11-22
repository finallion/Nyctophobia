package com.lion.nyctophobia.world.features.trees;

import com.lion.nyctophobia.world.features.NPlaceContext;
import com.mojang.serialization.MapCodec;

import com.lion.nyctophobia.world.features.surfaceFeatures.FeatureHelper;
import com.lion.nyctophobia.world.features.trees.config.NTreeFeatureConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;

public class SmallSpruceTree03 extends BaseSpruceTree {
    public static final MapCodec<SmallSpruceTree03> CODEC = codecOf(SmallSpruceTree03::new);

    @Override
    public MapCodec<SmallSpruceTree03> codec() {
        return CODEC;
    }

    private final int trunkHeight = 13;


    public SmallSpruceTree03(NTreeFeatureConfig config) {
        super(config);
    }

    @Override
    public boolean place(NPlaceContext<NTreeFeatureConfig> context) {
        WorldGenLevel world = context.level();
        BlockPos blockPos = context.origin();
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(blockPos);
        BlockState wood = context.config().woodState;
        BlockState leaf = context.config().leafState;
        int offsetTrunk = context.random().nextInt(3);

        if (!FeatureHelper.canBePlaced(world, blockPos.below(), world.getBlockState(blockPos.below()))) {
            return false;
        }

        if (!FeatureHelper.canGenerate(world, blockPos, 13)) {
            return false;
        }

        for (int i = 0; i < trunkHeight + offsetTrunk; i++) {
            world.setBlock(blockPos.above(i), wood, 2);
            mutable.move(0, 1, 0);
        }

        setLeaves(context, mutable.offset(0, 1, 0), leaf);
        setLeaves(context, mutable.offset(0, 0, 0), leaf);
        setLeaves(context, mutable.offset(0, -1, 0), leaf);
        generateOneStar(context, mutable.offset(0, -1, 0), false);

        randomSpreadOne(context, mutable.offset(0, -2, 0), false, 2);
        randomSpreadOne(context, mutable.offset(0, -3, 0), false, 2);
        generateOneStar(context, mutable.offset(0, -4, 0), false);
        generateTwoStar(context, mutable.offset(0, -5, 0), false);
        generateOneStar(context, mutable.offset(0, -6, 0), false);
        randomSpreadTwo(context, mutable.offset(0, -6, 0), false, 3);
        generateOneStar(context, mutable.offset(0, -7, 0), false);
        randomSpreadTwo(context, mutable.offset(0, -7, 0), false, 3);
        generateTwoStar(context, mutable.offset(0, -8, 0), false);
        generateBranchesOne(context, mutable.offset(0, -8, 0), 1);
        randomSpreadTwo(context, mutable.offset(0, -9, 0), false, 3);
        generateThreeStar(context, mutable.offset(0, -10, 0), false);
        generateFourStar(context, mutable.offset(0, -11, 0), true);

        return false;
    }
}
