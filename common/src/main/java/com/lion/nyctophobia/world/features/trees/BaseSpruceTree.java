package com.lion.nyctophobia.world.features.trees;

import com.lion.nyctophobia.world.features.trees.config.NTreeFeatureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;

public abstract class BaseSpruceTree extends Feature<NTreeFeatureConfig> {


    public BaseSpruceTree(Codec<NTreeFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NTreeFeatureConfig> context) {
        return false;
    }


    public void setLeaves(FeaturePlaceContext<NTreeFeatureConfig> context, BlockPos pos, BlockState state) {
        if (canReplace(context.level(), pos)) {
            context.level().setBlock(pos, state, 2);
        }
    }

    public void setLeavesRandomized(
            FeaturePlaceContext<NTreeFeatureConfig> context,
            BlockPos pos,
            BlockState state,
            int chance
    ) {
        if (canReplace(context.level(), pos) && context.random().nextInt(chance) == 0) {
            context.level().setBlock(pos, state, 2);
        }
    }

    public void setBranchRandomized(
            FeaturePlaceContext<NTreeFeatureConfig> context,
            BlockPos pos,
            BlockState state,
            int chance
    ) {
        if (canReplace(context.level(), pos) && context.random().nextInt(chance) == 0) {
            context.level().setBlock(pos, state, 2);
        }
    }

    public static boolean canReplace(LevelSimulatedReader world, BlockPos pos) {
        return world.isStateAtPosition(pos, (state) -> {
            return state.isAir() || state.is(BlockTags.REPLACEABLE_BY_TREES);
        });
    }

    public void generateBranchesOne(FeaturePlaceContext<NTreeFeatureConfig> context, BlockPos pos, int chance) {

        setBranchRandomized(context, pos.offset(1, 0, 0), context.config().woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.X), chance);
        setBranchRandomized(context, pos.offset(0, 0, 1), context.config().woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.Z), chance);
        setBranchRandomized(context, pos.offset(-1, 0, 0), context.config().woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.X), chance);
        setBranchRandomized(context, pos.offset(0, 0, -1), context.config().woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.Z), chance);

    }

    public void generateBranchesTwo(FeaturePlaceContext<NTreeFeatureConfig> context, BlockPos pos, int chance) {
        setBranchRandomized(context, pos.offset(1, 0, 0), context.config().woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.X), chance);
        setBranchRandomized(context, pos.offset(0, 0, 1), context.config().woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.Z), chance);
        setBranchRandomized(context, pos.offset(-1, 0, 0), context.config().woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.X), chance);
        setBranchRandomized(context, pos.offset(0, 0, -1), context.config().woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.Z), chance);
        setBranchRandomized(context, pos.offset(2, 0, 0), context.config().woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.X), chance);
        setBranchRandomized(context, pos.offset(0, 0, 2), context.config().woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.Z), chance);
        setBranchRandomized(context, pos.offset(-2, 0, 0), context.config().woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.X), chance);
        setBranchRandomized(context, pos.offset(0, 0, -2), context.config().woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.Z), chance);
    }



    public void randomSpreadOne(
            FeaturePlaceContext<NTreeFeatureConfig> context,
            BlockPos pos,
            boolean beSquare,
            int chance
    ) {
        setLeavesRandomized(context, pos.offset(1, 0, 0), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(0, 0, 1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-1, 0, 0), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(0, 0, -1), context.config().leafState, chance);

    }

    public void randomSpreadTwo(
            FeaturePlaceContext<NTreeFeatureConfig> context,
            BlockPos pos,
            boolean beSquare,
            int chance
    ) {
        setLeavesRandomized(context, pos.offset(1, 0, 0), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(0, 0, 1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-1, 0, 0), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(0, 0, -1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-1, 0, -1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(1, 0, 1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-1, 0, 1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(1, 0, -1), context.config().leafState, chance);

        if (!beSquare) {
            setLeavesRandomized(context, pos.offset(2, 0, 0), context.config().leafState, chance);
            setLeavesRandomized(context, pos.offset(0, 0, 2), context.config().leafState, chance);
            setLeavesRandomized(context, pos.offset(-2, 0, 0), context.config().leafState, chance);
            setLeavesRandomized(context, pos.offset(0, 0, -2), context.config().leafState, chance);
        }
    }

    public void randomSpreadThree(
            FeaturePlaceContext<NTreeFeatureConfig> context,
            BlockPos pos,
            boolean beSquare,
            int chance
    ) {
        setLeavesRandomized(context, pos.offset(1, 0, 0), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(0, 0, 1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-1, 0, 0), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(0, 0, -1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-1, 0, -1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(1, 0, 1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-1, 0, 1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(1, 0, -1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(2, 0, 0), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(0, 0, 2), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-2, 0, 0), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(0, 0, -2), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(2, 0, 1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(1, 0, 2), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-2, 0, 1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(1, 0, -2), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(2, 0, -1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-1, 0, 2), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-2, 0, -1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-1, 0, -2), context.config().leafState, chance);

        if (!beSquare) {
            setLeavesRandomized(context, pos.offset(3, 0, 0), context.config().leafState, chance);
            setLeavesRandomized(context, pos.offset(0, 0, 3), context.config().leafState, chance);
            setLeavesRandomized(context, pos.offset(-3, 0, 0), context.config().leafState, chance);
            setLeavesRandomized(context, pos.offset(0, 0, -3), context.config().leafState, chance);
        }
    }

    public void randomSpreadFour(
            FeaturePlaceContext<NTreeFeatureConfig> context,
            BlockPos pos,
            boolean beSquare,
            int chance
    ) {
        setLeavesRandomized(context, pos.offset(1, 0, 0), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(0, 0, 1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-1, 0, 0), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(0, 0, -1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-1, 0, -1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(1, 0, 1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-1, 0, 1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(1, 0, -1), context.config().leafState, chance);

        setLeavesRandomized(context, pos.offset(2, 0, 0), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(0, 0, 2), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-2, 0, 0), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(0, 0, -2), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(2, 0, 1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(1, 0, 2), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-2, 0, 1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(1, 0, -2), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(2, 0, -1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-1, 0, 2), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-2, 0, -1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-1, 0, -2), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(2, 0, 2), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-2, 0, 2), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-2, 0, -2), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(2, 0, -2), context.config().leafState, chance);

        setLeavesRandomized(context, pos.offset(3, 0, 0), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(0, 0, 3), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-3, 0, 0), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(0, 0, -3), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(3, 0, 1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(1, 0, 3), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-3, 0, 1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(1, 0, -3), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(3, 0, -1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-1, 0, 3), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-3, 0, -1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-1, 0, -3), context.config().leafState, chance);

        if (!beSquare) {
            setLeavesRandomized(context, pos.offset(4, 0, 0), context.config().leafState, chance);
            setLeavesRandomized(context, pos.offset(0, 0, 4), context.config().leafState, chance);
            setLeavesRandomized(context, pos.offset(-4, 0, 0), context.config().leafState, chance);
            setLeavesRandomized(context, pos.offset(0, 0, -4), context.config().leafState, chance);
        }
    }

    public void randomSpreadFive(
            FeaturePlaceContext<NTreeFeatureConfig> context,
            BlockPos pos,
            boolean beSquare,
            int chance
    ) {
        setLeavesRandomized(context, pos.offset(1, 0, 0), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(0, 0, 1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-1, 0, 0), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(0, 0, -1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-1, 0, -1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(1, 0, 1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-1, 0, 1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(1, 0, -1), context.config().leafState, chance);

        setLeavesRandomized(context, pos.offset(2, 0, 0), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(0, 0, 2), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-2, 0, 0), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(0, 0, -2), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(2, 0, 1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(1, 0, 2), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-2, 0, 1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(1, 0, -2), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(2, 0, -1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-1, 0, 2), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-2, 0, -1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-1, 0, -2), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(2, 0, 2), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-2, 0, 2), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-2, 0, -2), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(2, 0, -2), context.config().leafState, chance);

        setLeavesRandomized(context, pos.offset(3, 0, 0), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(0, 0, 3), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-3, 0, 0), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(0, 0, -3), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(3, 0, 1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(1, 0, 3), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-3, 0, 1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(1, 0, -3), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(3, 0, -1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-1, 0, 3), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-3, 0, -1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-1, 0, -3), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(3, 0, 2), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(2, 0, 3), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-3, 0, 2), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(2, 0, -3), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(3, 0, -2), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-2, 0, 3), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-3, 0, -2), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-2, 0, -3), context.config().leafState, chance);

        setLeavesRandomized(context, pos.offset(4, 0, 0), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(0, 0, 4), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-4, 0, 0), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(0, 0, -4), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(4, 0, 1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(1, 0, 4), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-4, 0, 1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(1, 0, -4), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(4, 0, -1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-1, 0, 4), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-4, 0, -1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-1, 0, -4), context.config().leafState, chance);

        if (!beSquare) {
            setLeavesRandomized(context, pos.offset(5, 0, 0), context.config().leafState, chance);
            setLeavesRandomized(context, pos.offset(0, 0, 5), context.config().leafState, chance);
            setLeavesRandomized(context, pos.offset(-5, 0, 0), context.config().leafState, chance);
            setLeavesRandomized(context, pos.offset(0, 0, -5), context.config().leafState, chance);
        }
    }

    public void generateOneStar(FeaturePlaceContext<NTreeFeatureConfig> context, BlockPos pos, boolean beSquare) {
        setLeaves(context, pos.offset(1, 0, 0), context.config().leafState);
        setLeaves(context, pos.offset(0, 0, 1), context.config().leafState);
        setLeaves(context, pos.offset(-1, 0, 0), context.config().leafState);
        setLeaves(context, pos.offset(0, 0, -1), context.config().leafState);

    }


    public void generateTwoStar(FeaturePlaceContext<NTreeFeatureConfig> context, BlockPos pos, boolean beSquare) {
        setLeaves(context, pos.offset(1, 0, 0), context.config().leafState);
        setLeaves(context, pos.offset(0, 0, 1), context.config().leafState);
        setLeaves(context, pos.offset(-1, 0, 0), context.config().leafState);
        setLeaves(context, pos.offset(0, 0, -1), context.config().leafState);
        setLeaves(context, pos.offset(-1, 0, -1), context.config().leafState);
        setLeaves(context, pos.offset(1, 0, 1), context.config().leafState);
        setLeaves(context, pos.offset(-1, 0, 1), context.config().leafState);
        setLeaves(context, pos.offset(1, 0, -1), context.config().leafState);

        if (!beSquare) {
            setLeaves(context, pos.offset(2, 0, 0), context.config().leafState);
            setLeaves(context, pos.offset(0, 0, 2), context.config().leafState);
            setLeaves(context, pos.offset(-2, 0, 0), context.config().leafState);
            setLeaves(context, pos.offset(0, 0, -2), context.config().leafState);
        }
    }



    public void generateThreeStar(FeaturePlaceContext<NTreeFeatureConfig> context, BlockPos pos, boolean beSquare) {
        setLeaves(context, pos.offset(1, 0, 0), context.config().leafState);
        setLeaves(context, pos.offset(0, 0, 1), context.config().leafState);
        setLeaves(context, pos.offset(-1, 0, 0), context.config().leafState);
        setLeaves(context, pos.offset(0, 0, -1), context.config().leafState);
        setLeaves(context, pos.offset(-1, 0, -1), context.config().leafState);
        setLeaves(context, pos.offset(1, 0, 1), context.config().leafState);
        setLeaves(context, pos.offset(-1, 0, 1), context.config().leafState);
        setLeaves(context, pos.offset(1, 0, -1), context.config().leafState);
        setLeaves(context, pos.offset(2, 0, 0), context.config().leafState);
        setLeaves(context, pos.offset(0, 0, 2), context.config().leafState);
        setLeaves(context, pos.offset(-2, 0, 0), context.config().leafState);
        setLeaves(context, pos.offset(0, 0, -2), context.config().leafState);
        setLeaves(context, pos.offset(2, 0, 1), context.config().leafState);
        setLeaves(context, pos.offset(1, 0, 2), context.config().leafState);
        setLeaves(context, pos.offset(-2, 0, 1), context.config().leafState);
        setLeaves(context, pos.offset(1, 0, -2), context.config().leafState);
        setLeaves(context, pos.offset(2, 0, -1), context.config().leafState);
        setLeaves(context, pos.offset(-1, 0, 2), context.config().leafState);
        setLeaves(context, pos.offset(-2, 0, -1), context.config().leafState);
        setLeaves(context, pos.offset(-1, 0, -2), context.config().leafState);

        if (!beSquare) {
            setLeaves(context, pos.offset(3, 0, 0), context.config().leafState);
            setLeaves(context, pos.offset(0, 0, 3), context.config().leafState);
            setLeaves(context, pos.offset(-3, 0, 0), context.config().leafState);
            setLeaves(context, pos.offset(0, 0, -3), context.config().leafState);
        }

    }


    public void generateFourStar(FeaturePlaceContext<NTreeFeatureConfig> context, BlockPos pos, boolean beSquare) {
        setLeaves(context, pos.offset(1, 0, 0), context.config().leafState);
        setLeaves(context, pos.offset(0, 0, 1), context.config().leafState);
        setLeaves(context, pos.offset(-1, 0, 0), context.config().leafState);
        setLeaves(context, pos.offset(0, 0, -1), context.config().leafState);
        setLeaves(context, pos.offset(-1, 0, -1), context.config().leafState);
        setLeaves(context, pos.offset(1, 0, 1), context.config().leafState);
        setLeaves(context, pos.offset(-1, 0, 1), context.config().leafState);
        setLeaves(context, pos.offset(1, 0, -1), context.config().leafState);

        setLeaves(context, pos.offset(2, 0, 0), context.config().leafState);
        setLeaves(context, pos.offset(0, 0, 2), context.config().leafState);
        setLeaves(context, pos.offset(-2, 0, 0), context.config().leafState);
        setLeaves(context, pos.offset(0, 0, -2), context.config().leafState);
        setLeaves(context, pos.offset(2, 0, 1), context.config().leafState);
        setLeaves(context, pos.offset(1, 0, 2), context.config().leafState);
        setLeaves(context, pos.offset(-2, 0, 1), context.config().leafState);
        setLeaves(context, pos.offset(1, 0, -2), context.config().leafState);
        setLeaves(context, pos.offset(2, 0, -1), context.config().leafState);
        setLeaves(context, pos.offset(-1, 0, 2), context.config().leafState);
        setLeaves(context, pos.offset(-2, 0, -1), context.config().leafState);
        setLeaves(context, pos.offset(-1, 0, -2), context.config().leafState);
        setLeaves(context, pos.offset(2, 0, 2), context.config().leafState);
        setLeaves(context, pos.offset(-2, 0, 2), context.config().leafState);
        setLeaves(context, pos.offset(-2, 0, -2), context.config().leafState);
        setLeaves(context, pos.offset(2, 0, -2), context.config().leafState);

        setLeaves(context, pos.offset(3, 0, 0), context.config().leafState);
        setLeaves(context, pos.offset(0, 0, 3), context.config().leafState);
        setLeaves(context, pos.offset(-3, 0, 0), context.config().leafState);
        setLeaves(context, pos.offset(0, 0, -3), context.config().leafState);
        setLeaves(context, pos.offset(3, 0, 1), context.config().leafState);
        setLeaves(context, pos.offset(1, 0, 3), context.config().leafState);
        setLeaves(context, pos.offset(-3, 0, 1), context.config().leafState);
        setLeaves(context, pos.offset(1, 0, -3), context.config().leafState);
        setLeaves(context, pos.offset(3, 0, -1), context.config().leafState);
        setLeaves(context, pos.offset(-1, 0, 3), context.config().leafState);
        setLeaves(context, pos.offset(-3, 0, -1), context.config().leafState);
        setLeaves(context, pos.offset(-1, 0, -3), context.config().leafState);

        if (!beSquare) {
            setLeaves(context, pos.offset(4, 0, 0), context.config().leafState);
            setLeaves(context, pos.offset(0, 0, 4), context.config().leafState);
            setLeaves(context, pos.offset(-4, 0, 0), context.config().leafState);
            setLeaves(context, pos.offset(0, 0, -4), context.config().leafState);
        }
    }

    public void generateFiveStar(FeaturePlaceContext<NTreeFeatureConfig> context, BlockPos pos, boolean beSquare) {
        setLeaves(context, pos.offset(1, 0, 0), context.config().leafState);
        setLeaves(context, pos.offset(0, 0, 1), context.config().leafState);
        setLeaves(context, pos.offset(-1, 0, 0), context.config().leafState);
        setLeaves(context, pos.offset(0, 0, -1), context.config().leafState);
        setLeaves(context, pos.offset(-1, 0, -1), context.config().leafState);
        setLeaves(context, pos.offset(1, 0, 1), context.config().leafState);
        setLeaves(context, pos.offset(-1, 0, 1), context.config().leafState);
        setLeaves(context, pos.offset(1, 0, -1), context.config().leafState);

        setLeaves(context, pos.offset(2, 0, 0), context.config().leafState);
        setLeaves(context, pos.offset(0, 0, 2), context.config().leafState);
        setLeaves(context, pos.offset(-2, 0, 0), context.config().leafState);
        setLeaves(context, pos.offset(0, 0, -2), context.config().leafState);
        setLeaves(context, pos.offset(2, 0, 1), context.config().leafState);
        setLeaves(context, pos.offset(1, 0, 2), context.config().leafState);
        setLeaves(context, pos.offset(-2, 0, 1), context.config().leafState);
        setLeaves(context, pos.offset(1, 0, -2), context.config().leafState);
        setLeaves(context, pos.offset(2, 0, -1), context.config().leafState);
        setLeaves(context, pos.offset(-1, 0, 2), context.config().leafState);
        setLeaves(context, pos.offset(-2, 0, -1), context.config().leafState);
        setLeaves(context, pos.offset(-1, 0, -2), context.config().leafState);
        setLeaves(context, pos.offset(2, 0, 2), context.config().leafState);
        setLeaves(context, pos.offset(-2, 0, 2), context.config().leafState);
        setLeaves(context, pos.offset(-2, 0, -2), context.config().leafState);
        setLeaves(context, pos.offset(2, 0, -2), context.config().leafState);

        setLeaves(context, pos.offset(3, 0, 0), context.config().leafState);
        setLeaves(context, pos.offset(0, 0, 3), context.config().leafState);
        setLeaves(context, pos.offset(-3, 0, 0), context.config().leafState);
        setLeaves(context, pos.offset(0, 0, -3), context.config().leafState);
        setLeaves(context, pos.offset(3, 0, 1), context.config().leafState);
        setLeaves(context, pos.offset(1, 0, 3), context.config().leafState);
        setLeaves(context, pos.offset(-3, 0, 1), context.config().leafState);
        setLeaves(context, pos.offset(1, 0, -3), context.config().leafState);
        setLeaves(context, pos.offset(3, 0, -1), context.config().leafState);
        setLeaves(context, pos.offset(-1, 0, 3), context.config().leafState);
        setLeaves(context, pos.offset(-3, 0, -1), context.config().leafState);
        setLeaves(context, pos.offset(-1, 0, -3), context.config().leafState);
        setLeaves(context, pos.offset(3, 0, 2), context.config().leafState);
        setLeaves(context, pos.offset(2, 0, 3), context.config().leafState);
        setLeaves(context, pos.offset(-3, 0, 2), context.config().leafState);
        setLeaves(context, pos.offset(2, 0, -3), context.config().leafState);
        setLeaves(context, pos.offset(3, 0, -2), context.config().leafState);
        setLeaves(context, pos.offset(-2, 0, 3), context.config().leafState);
        setLeaves(context, pos.offset(-3, 0, -2), context.config().leafState);
        setLeaves(context, pos.offset(-2, 0, -3), context.config().leafState);

        setLeaves(context, pos.offset(4, 0, 0), context.config().leafState);
        setLeaves(context, pos.offset(0, 0, 4), context.config().leafState);
        setLeaves(context, pos.offset(-4, 0, 0), context.config().leafState);
        setLeaves(context, pos.offset(0, 0, -4), context.config().leafState);
        setLeaves(context, pos.offset(4, 0, 1), context.config().leafState);
        setLeaves(context, pos.offset(1, 0, 4), context.config().leafState);
        setLeaves(context, pos.offset(-4, 0, 1), context.config().leafState);
        setLeaves(context, pos.offset(1, 0, -4), context.config().leafState);
        setLeaves(context, pos.offset(4, 0, -1), context.config().leafState);
        setLeaves(context, pos.offset(-1, 0, 4), context.config().leafState);
        setLeaves(context, pos.offset(-4, 0, -1), context.config().leafState);
        setLeaves(context, pos.offset(-1, 0, -4), context.config().leafState);

        if (!beSquare) {
            setLeaves(context, pos.offset(5, 0, 0), context.config().leafState);
            setLeaves(context, pos.offset(0, 0, 5), context.config().leafState);
            setLeaves(context, pos.offset(-5, 0, 0), context.config().leafState);
            setLeaves(context, pos.offset(0, 0, -5), context.config().leafState);
        }

    }


}
