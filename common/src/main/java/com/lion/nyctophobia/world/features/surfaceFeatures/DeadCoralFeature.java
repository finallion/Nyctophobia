package com.lion.nyctophobia.world.features.surfaceFeatures;

import com.lion.nyctophobia.util.NTags;
import com.mojang.serialization.Codec;
import java.util.Iterator;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.BaseCoralWallFanBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public abstract class DeadCoralFeature extends Feature<NoneFeatureConfiguration> {
    public DeadCoralFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        net.minecraft.util.RandomSource random = context.random();
        WorldGenLevel structureWorldAccess = context.level();
        BlockPos blockPos = context.origin();

        Optional<Block> optional = BuiltInRegistries.BLOCK.get(NTags.DEAD_CORAL_BLOCKS).flatMap((blocks) -> {
            return blocks.getRandomElement(random);
        }).map(Holder::value);
        return optional.isEmpty() ? false : this.generateCoral(structureWorldAccess, random, blockPos, ((Block)optional.get()).defaultBlockState());
    }

    protected abstract boolean generateCoral(
            LevelAccessor world,
            net.minecraft.util.RandomSource random,
            BlockPos pos,
            BlockState state
    );

    protected boolean generateCoralPiece(LevelAccessor world, RandomSource random, BlockPos pos, BlockState state) {
        BlockPos blockPos = pos.above();
        BlockState blockState = world.getBlockState(pos);

        if (world.getBlockState(pos.below()).is(Blocks.WATER) || world.getBlockState(pos.below()).is(Blocks.AIR) || !world.getBlockState(pos.below()).isRedstoneConductor(world, pos.below())) {
            return false;
        }

        if (((blockState.is(Blocks.AIR) || blockState.is(Blocks.WATER)) || blockState.is(NTags.DEAD_CORALS)) && (world.getBlockState(blockPos).is(Blocks.AIR) || world.getBlockState(blockPos).is(Blocks.WATER))) {
            world.setBlock(pos, state, 3);

            if (random.nextFloat() < 0.25F) {
                if (world.getBlockState(blockPos).is(Blocks.WATER)) {
                    BuiltInRegistries.BLOCK.get(NTags.DEAD_CORALS).flatMap((blocks) -> {
                        return blocks.getRandomElement(random);
                    }).map(Holder::value).ifPresent((block) -> {
                        world.setBlock(blockPos, block.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true), 2);
                    });
                } else if (world.getBlockState(blockPos).is(Blocks.AIR)) {
                    BuiltInRegistries.BLOCK.get(NTags.DEAD_CORALS).flatMap((blocks) -> {
                        return blocks.getRandomElement(random);
                    }).map(Holder::value).ifPresent((block) -> {
                        world.setBlock(blockPos, block.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, false), 2);
                    });
                }
            }

            Iterator var7 = Direction.Plane.HORIZONTAL.iterator();

            while(var7.hasNext()) {
                Direction direction = (Direction)var7.next();

                if (random.nextFloat() < 0.2F) {
                    BlockPos blockPos2 = pos.relative(direction);

                    if (world.getBlockState(blockPos2).is(Blocks.WATER)) {
                        BuiltInRegistries.BLOCK.get(NTags.DEAD_WALL_CORALS).flatMap((blocks) -> {
                            return blocks.getRandomElement(random);
                        }).map(Holder::value).ifPresent((block) -> {
                            BlockState blockState2 = block.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true);

                            if (blockState2.hasProperty(BaseCoralWallFanBlock.FACING)) {
                                blockState2 = (BlockState)blockState2.setValue(BaseCoralWallFanBlock.FACING, direction);
                            }

                            world.setBlock(blockPos2, blockState2, 2);
                        });
                    } else if (world.getBlockState(blockPos2).is(Blocks.AIR)) {
                        BuiltInRegistries.BLOCK.get(NTags.DEAD_WALL_CORALS).flatMap((blocks) -> {
                            return blocks.getRandomElement(random);
                        }).map(Holder::value).ifPresent((block) -> {
                            BlockState blockState2 = block.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, false);

                            if (blockState2.hasProperty(BaseCoralWallFanBlock.FACING)) {
                                blockState2 = (BlockState)blockState2.setValue(BaseCoralWallFanBlock.FACING, direction);
                            }

                            world.setBlock(blockPos2, blockState2, 2);
                        });
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }
}
