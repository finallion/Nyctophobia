package com.lion.nyctophobia.world.features.trees;

import com.lion.nyctophobia.world.features.NPlaceContext;
import com.lion.nyctophobia.world.features.NFeature;

import com.lion.nyctophobia.world.features.trees.config.NTreeFeatureConfig;
import com.mojang.serialization.MapCodec;
import java.util.function.Function;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public abstract class BaseSpruceTree extends NFeature<NTreeFeatureConfig> {


    public BaseSpruceTree(NTreeFeatureConfig config) {
        super(config);
    }

    protected static <T extends BaseSpruceTree> MapCodec<T> codecOf(Function<NTreeFeatureConfig, T> constructor) {
        return NTreeFeatureConfig.MAP_CODEC.xmap(constructor, BaseSpruceTree::config);
    }

    @Override
    public boolean place(NPlaceContext<NTreeFeatureConfig> context) {
        return false;
    }


    public void setLeaves(NPlaceContext<NTreeFeatureConfig> context, BlockPos pos, BlockState state) {
        if (canReplace(context.level(), pos)) {
            context.level().setBlock(pos, state, 2);
        }
    }

    public void setLeavesRandomized(
            NPlaceContext<NTreeFeatureConfig> context,
            BlockPos pos,
            BlockState state,
            int chance
    ) {
        if (canReplace(context.level(), pos) && context.random().nextInt(chance) == 0) {
            context.level().setBlock(pos, state, 2);
        }
    }

    public void setBranchRandomized(
            NPlaceContext<NTreeFeatureConfig> context,
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

    public void generateBranchesOne(NPlaceContext<NTreeFeatureConfig> context, BlockPos pos, int chance) {

        setBranchRandomized(context, pos.offset(1, 0, 0), context.config().woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.X), chance);
        setBranchRandomized(context, pos.offset(0, 0, 1), context.config().woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.Z), chance);
        setBranchRandomized(context, pos.offset(-1, 0, 0), context.config().woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.X), chance);
        setBranchRandomized(context, pos.offset(0, 0, -1), context.config().woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.Z), chance);

    }

    public void generateBranchesTwo(NPlaceContext<NTreeFeatureConfig> context, BlockPos pos, int chance) {
        setBranchRandomized(context, pos.offset(1, 0, 0), context.config().woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.X), chance);
        setBranchRandomized(context, pos.offset(0, 0, 1), context.config().woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.Z), chance);
        setBranchRandomized(context, pos.offset(-1, 0, 0), context.config().woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.X), chance);
        setBranchRandomized(context, pos.offset(0, 0, -1), context.config().woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.Z), chance);
        setBranchRandomized(context, pos.offset(2, 0, 0), context.config().woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.X), chance);
        setBranchRandomized(context, pos.offset(0, 0, 2), context.config().woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.Z), chance);
        setBranchRandomized(context, pos.offset(-2, 0, 0), context.config().woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.X), chance);
        setBranchRandomized(context, pos.offset(0, 0, -2), context.config().woodState.setValue(BlockStateProperties.AXIS, Direction.Axis.Z), chance);
    }



    public void randomSpreadOne(NPlaceContext<NTreeFeatureConfig> context, BlockPos pos, boolean beSquare, int chance) {
        setLeavesRandomized(context, pos.offset(1, 0, 0), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(0, 0, 1), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(-1, 0, 0), context.config().leafState, chance);
        setLeavesRandomized(context, pos.offset(0, 0, -1), context.config().leafState, chance);

    }

    public void randomSpreadTwo(NPlaceContext<NTreeFeatureConfig> context, BlockPos pos, boolean beSquare, int chance) {
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
            NPlaceContext<NTreeFeatureConfig> context,
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
            NPlaceContext<NTreeFeatureConfig> context,
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
            NPlaceContext<NTreeFeatureConfig> context,
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

    public void generateOneStar(NPlaceContext<NTreeFeatureConfig> context, BlockPos pos, boolean beSquare) {
        setLeaves(context, pos.offset(1, 0, 0), context.config().leafState);
        setLeaves(context, pos.offset(0, 0, 1), context.config().leafState);
        setLeaves(context, pos.offset(-1, 0, 0), context.config().leafState);
        setLeaves(context, pos.offset(0, 0, -1), context.config().leafState);

    }


    public void generateTwoStar(NPlaceContext<NTreeFeatureConfig> context, BlockPos pos, boolean beSquare) {

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



    public void generateThreeStar(NPlaceContext<NTreeFeatureConfig> context, BlockPos pos, boolean beSquare) {

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


    public void generateFourStar(NPlaceContext<NTreeFeatureConfig> context, BlockPos pos, boolean beSquare) {

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

    public void generateFiveStar(NPlaceContext<NTreeFeatureConfig> context, BlockPos pos, boolean beSquare) {
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
