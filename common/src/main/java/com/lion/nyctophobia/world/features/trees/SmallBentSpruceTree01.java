package com.lion.nyctophobia.world.features.trees;

import com.lion.nyctophobia.world.features.surfaceFeatures.FeatureHelper;
import com.lion.nyctophobia.world.features.trees.config.NTreeFeatureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public class SmallBentSpruceTree01 extends BaseSpruceTree {
    private final int trunkHeight = 12;

    public SmallBentSpruceTree01(Codec<NTreeFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NTreeFeatureConfig> context) {
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

        if (!FeatureHelper.canGenerate(world, blockPos, 12)) {
            return false;
        }


        for (int i = 0; i < trunkHeight - 6 + offsetTrunk; i++) {
            world.setBlock(blockPos.above(i), wood, 2);
        }

        for (int i = 6 + offsetTrunk; i < trunkHeight  + offsetTrunk; i++) {
            world.setBlock(blockPos.above(i).relative(direction), wood, 2);
        }

        mutable.move(0, trunkHeight + offsetTrunk, 0);
        mutable.move(direction);

        setLeaves(context, mutable.offset(0, 3, 0), leaf);
        setLeaves(context, mutable.offset(0, 2, 0), leaf);
        setLeaves(context, mutable.offset(0, 1, 0), leaf);
        setLeaves(context, mutable.offset(0, 0, 0), leaf);

        generateOneStar(context, mutable.offset(0, 0, 0), false);
        randomSpreadTwo(context, mutable.offset(0, -1, 0), true, 2);

        generateOneStar(context, mutable.offset(0, -3, 0), false);
        generateTwoStar(context, mutable.offset(0, -4, 0), false);

        randomSpreadTwo(context, mutable.offset(0, -5, 0), false, 2);

        generateTwoStar(context, mutable.offset(0, -6, 0), false);
        generateThreeStar(context, mutable.offset(0, -7, 0), true);

        mutable.move(direction.getOpposite());
        randomSpreadOne(context, mutable.offset(0, -8, 0), false, 4);

        generateTwoStar(context, mutable.offset(0, -9, 0), false);
        generateThreeStar(context, mutable.offset(0, -10, 0), false);




        return false;
    }
}
