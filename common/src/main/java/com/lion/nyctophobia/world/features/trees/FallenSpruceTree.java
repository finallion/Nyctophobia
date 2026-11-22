package com.lion.nyctophobia.world.features.trees;


import com.lion.nyctophobia.world.features.trees.config.NTreeFeatureConfig;
import com.lion.nyctophobia.world.features.NPlaceContext;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class FallenSpruceTree extends BaseSpruceTree {

    /// Burnt trunks grow no moss.
    private final boolean mossy;

    public static final MapCodec<FallenSpruceTree> MOSSY_CODEC = NTreeFeatureConfig.MAP_CODEC.xmap(config -> new FallenSpruceTree(config, true), BaseSpruceTree::config);
    public static final MapCodec<FallenSpruceTree> BURNT_CODEC = NTreeFeatureConfig.MAP_CODEC.xmap(config -> new FallenSpruceTree(config, false), BaseSpruceTree::config);

    public FallenSpruceTree(NTreeFeatureConfig config, boolean mossy) {
        super(config);
        this.mossy = mossy;
    }

    @Override
    public MapCodec<FallenSpruceTree> codec() {
        return mossy ? MOSSY_CODEC : BURNT_CODEC;
    }

    @Override
    public boolean place(NPlaceContext<NTreeFeatureConfig> context) {
        WorldGenLevel world = context.level();
        BlockPos blockPos = context.origin();
        RandomSource random = context.random();
        BlockState wood = context.config().woodState;

        Direction.Axis axis = random.nextBoolean() ? Direction.Axis.X : Direction.Axis.Z;
        Direction direction = Direction.fromAxisAndDirection(axis, random.nextBoolean() ? Direction.AxisDirection.POSITIVE : Direction.AxisDirection.NEGATIVE);


        int length = random.nextInt(9) + 2;

        if (!world.getBlockState(blockPos).isAir()) {
            return false;
        }

        for (int i = 0; i < length; i++) {
            if ((world.getBlockState(blockPos.relative(direction, i).below())
                    .isRedstoneConductor(world, blockPos.relative(direction, i).below())
                    || (world.getBlockState(blockPos.relative(direction, i).below()).getBlock() == Blocks.SOUL_SAND))
                    && canBeReplaced(world.getBlockState(blockPos.relative(direction, i)))) {
                world.setBlock(blockPos.relative(direction, i), wood.setValue(BlockStateProperties.AXIS, axis), 2);

                if (mossy && random.nextBoolean() && world.getBlockState(blockPos.relative(direction, i).above()).isAir()) {
                    world.setBlock(blockPos.relative(direction, i).above(), Blocks.MOSS_CARPET.defaultBlockState(), 2);
                }
            } else {
                break;
            }
        }



        return false;

    }

    private boolean canBeReplaced(BlockState state) {
        if (state.getBlock() instanceof VegetationBlock || state.isAir()) {
            return true;
        }

        return false;
    }
}
