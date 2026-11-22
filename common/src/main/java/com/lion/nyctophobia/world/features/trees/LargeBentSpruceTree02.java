package com.lion.nyctophobia.world.features.trees;

import com.lion.nyctophobia.world.features.NPlaceContext;
import com.mojang.serialization.MapCodec;

import com.lion.nyctophobia.world.features.surfaceFeatures.FeatureHelper;
import com.lion.nyctophobia.world.features.trees.config.NTreeFeatureConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;

public class LargeBentSpruceTree02 extends BaseSpruceTree {
    public static final MapCodec<LargeBentSpruceTree02> CODEC = codecOf(LargeBentSpruceTree02::new);

    @Override
    public MapCodec<LargeBentSpruceTree02> codec() {
        return CODEC;
    }

    private final int trunkHeight = 25;

    public LargeBentSpruceTree02(NTreeFeatureConfig config) {
        super(config);
    }

    @Override
    public boolean place(NPlaceContext<NTreeFeatureConfig> context) {
        WorldGenLevel world = context.level();
        BlockPos blockPos = context.origin();
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(blockPos);
        BlockState wood = context.config().woodState;
        BlockState leaf = context.config().leafState;
        RandomSource random = context.random();
        int offsetTrunk = random.nextInt(3);
        Direction.Axis axis = random.nextBoolean() ? Direction.Axis.X : Direction.Axis.Z;
        Direction direction = Direction.fromAxisAndDirection(axis, random.nextBoolean() ? Direction.AxisDirection.POSITIVE : Direction.AxisDirection.NEGATIVE);


        if (!FeatureHelper.canBePlaced(world, blockPos.below(), world.getBlockState(blockPos.below()))) {
            return false;
        }

        if (!FeatureHelper.canGenerate(world, blockPos, 25)) {
            return false;
        }


        for (int i = 0; i <= trunkHeight - 10 - 8 + offsetTrunk; i++) {
            world.setBlock(blockPos.above(i), wood, 2);
        }

        for (int i = 10 + offsetTrunk; i < trunkHeight - 7 + offsetTrunk; i++) {
            world.setBlock(blockPos.above(i).relative(direction), wood, 2);
        }


        for (int i = 18 + offsetTrunk; i <= trunkHeight + offsetTrunk; i++) {
            world.setBlock(blockPos.above(i).relative(direction, 2), wood, 2);
        }

        mutable.move(0, trunkHeight + offsetTrunk, 0);
        mutable.move(direction, 2);

        setLeaves(context, mutable.offset(0, 6, 0).relative(direction, 2), leaf);
        setLeaves(context, mutable.offset(0, 5, 0).relative(direction, 2), leaf);
        setLeaves(context, mutable.offset(0, 5, 0).relative(direction), leaf);
        setLeaves(context, mutable.offset(0, 4, 0).relative(direction), leaf);
        setLeaves(context, mutable.offset(0, 3, 0).relative(direction), leaf);
        setLeaves(context, mutable.offset(0, 3, 0), leaf);
        setLeaves(context, mutable.offset(0, 2, 0), leaf);
        setLeaves(context, mutable.offset(0, 1, 0), leaf);
        setLeaves(context, mutable.offset(0, 2, 0).relative(direction.getOpposite()), leaf);

        generateOneStar(context, mutable.offset(0, 1, 0), false);
        generateTwoStar(context, mutable.offset(0, 0, 0), true);
        generateOneStar(context, mutable.offset(0, -1, 0), false);
        randomSpreadOne(context, mutable.offset(0, -2, 0), false, 2);
        generateTwoStar(context, mutable.offset(0, -3, 0), false);
        randomSpreadThree(context, mutable.offset(0, -3, 0), true, 2);
        randomSpreadTwo(context, mutable.offset(0, -4, 0), false, 2);
        generateOneStar(context, mutable.offset(0, -5, 0), false);
        generateTwoStar(context, mutable.offset(0, -6, 0), false);
        generateThreeStar(context, mutable.offset(0, -7, 0), true);

        mutable.move(direction.getOpposite(), 1);
        randomSpreadThree(context, mutable.offset(0, -7, 0), true, 2);
        generateOneStar(context, mutable.offset(0, -8, 0), false);
        randomSpreadTwo(context, mutable.offset(0, -8, 0), false, 4);
        generateTwoStar(context, mutable.offset(0, -9, 0), false);
        generateThreeStar(context, mutable.offset(0, -10, 0), false);
        randomSpreadTwo(context, mutable.offset(0, -11, 0), true, 2);
        generateOneStar(context, mutable.offset(0, -12, 0), false);
        randomSpreadTwo(context, mutable.offset(0, -12, 0), false, 4);
        generateTwoStar(context, mutable.offset(0, -13, 0), false);

        mutable.move(direction.getOpposite(), 1);
        generateTwoStar(context, mutable.offset(0, -13, 0), false);
        randomSpreadFour(context, mutable.offset(0, -13, 0), false, 4);
        generateThreeStar(context, mutable.offset(0, -14, 0), false);
        randomSpreadFour(context, mutable.offset(0, -14, 0), false, 4);
        generateFourStar(context, mutable.offset(0, -15, 0), true);
        randomSpreadFour(context, mutable.offset(0, -15, 0), false, 4);
        generateTwoStar(context, mutable.offset(0, -16, 0), false);
        randomSpreadFour(context, mutable.offset(0, -16, 0), false, 8);
        generateThreeStar(context, mutable.offset(0, -17, 0), false);
        randomSpreadFour(context, mutable.offset(0, -17, 0), false, 4);
        generateFourStar(context, mutable.offset(0, -18, 0), false);
        randomSpreadFive(context, mutable.offset(0, -18, 0), false, 4);
        generateFiveStar(context, mutable.offset(0, -19, 0), true);
        generateThreeStar(context, mutable.offset(0, -20, 0), false);
        randomSpreadTwo(context, mutable.offset(0, -21, 0), false, 4);
        generateOneStar(context, mutable.offset(0, -22, 0), false);

        return false;
    }
}
