package com.lion.nyctophobia.world.features.trees;

import com.lion.nyctophobia.world.features.surfaceFeatures.FeatureHelper;
import com.lion.nyctophobia.world.features.trees.config.NTreeFeatureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class LargeSpruceTree01 extends BaseSpruceTree {
    private final int trunkHeight = 18;


    public LargeSpruceTree01(Codec<NTreeFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NTreeFeatureConfig> context) {
        WorldGenLevel world = context.level();
        BlockPos blockPos = context.origin();
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(blockPos);
        BlockState wood = context.config().woodState;
        BlockState leaf = context.config().leafState;
        int offsetTrunk = context.random().nextInt(3);

        if (!FeatureHelper.canBePlaced(world, blockPos.below(), world.getBlockState(blockPos.below()))) {
            return false;
        }

        if (!FeatureHelper.canGenerate(world, blockPos, 18)) {
            return false;
        }

        for (int i = 0; i < trunkHeight + offsetTrunk; i++) {
            world.setBlock(blockPos.above(i), wood, 2);
            mutable.move(0, 1, 0);
        }

        setLeaves(context, mutable.offset(0, 2, 0), leaf);
        setLeaves(context, mutable.offset(0, 1, 0), leaf);
        setLeaves(context, mutable.offset(0, 0, 0), leaf);

        randomSpreadTwo(context, mutable.offset(0, -1, 0), true, 2);
        randomSpreadOne(context, mutable.offset(0, -2, 0), false, 2);
        generateOneStar(context, mutable.offset(0, -3, 0), false);
        randomSpreadTwo(context, mutable.offset(0, -3, 0), false, 5);
        randomSpreadTwo(context, mutable.offset(0, -4, 0), false, 2);
        randomSpreadTwo(context, mutable.offset(0, -5, 0), false, 2);
        generateTwoStar(context, mutable.offset(0, -6, 0), false);
        randomSpreadThree(context, mutable.offset(0, -6, 0), true, 4);
        generateBranchesOne(context, mutable.offset(0, -6, 0), 1);
        randomSpreadTwo(context, mutable.offset(0, -7, 0), false, 2);
        generateOneStar(context, mutable.offset(0, -8, 0), false);
        randomSpreadTwo(context, mutable.offset(0, -8, 0), false, 2);
        generateTwoStar(context, mutable.offset(0, -9, 0), false);
        generateTwoStar(context, mutable.offset(0, -10, 0), false);
        randomSpreadThree(context, mutable.offset(0, -10, 0), false, 4);
        generateBranchesOne(context, mutable.offset(0, -10, 0), 1);
        generateThreeStar(context, mutable.offset(0, -11, 0), true);
        generateThreeStar(context, mutable.offset(0, -12, 0), false);
        randomSpreadFour(context, mutable.offset(0, -12, 0), false, 4);
        generateFourStar(context, mutable.offset(0, -13, 0), false);
        generateThreeStar(context, mutable.offset(0, -14, 0), false);
        generateBranchesOne(context, mutable.offset(0, -14, 0), 1);
        generateFourStar(context, mutable.offset(0, -15, 0), false);
        generateTwoStar(context, mutable.offset(0, -16, 0), true);
        return false;
    }
}
