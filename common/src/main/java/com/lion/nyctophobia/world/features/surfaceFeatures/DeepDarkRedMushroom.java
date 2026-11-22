package com.lion.nyctophobia.world.features.surfaceFeatures;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.AbstractHugeMushroomFeature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;

public class DeepDarkRedMushroom extends AbstractHugeMushroomFeature {


    public DeepDarkRedMushroom(Codec<HugeMushroomFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    protected void makeCap(
            LevelAccessor world,
            RandomSource random,
            BlockPos start,
            int y,
            BlockPos.MutableBlockPos mutable,
            HugeMushroomFeatureConfiguration config
    ) {

    }

    @Override
    protected void placeTrunk(
            LevelAccessor world,
            RandomSource random,
            BlockPos pos,
            HugeMushroomFeatureConfiguration config,
            int height,
            BlockPos.MutableBlockPos mutablePos
    ) {
        mutablePos.move(Direction.DOWN, 5);

        for (int i = -2; i < 3; i++) {
            if (!world.getBlockState(mutablePos.below().relative(Direction.Axis.X, i)).is(Blocks.GRASS_BLOCK)) {
                return;
            }

            if (!world.getBlockState(mutablePos.below().relative(Direction.Axis.Z, i)).is(Blocks.GRASS_BLOCK)) {
                return;
            }
        }

        this.setBlock(world, mutablePos, config.stemProvider.getState(random, pos));
        for (int i = 1; i <= 3; i++) {
            this.setBlock(world, mutablePos.relative(Direction.EAST, i), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.WEST, i), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.NORTH, i), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.SOUTH, i), config.stemProvider.getState(random, pos));

            this.setBlock(world, mutablePos.relative(Direction.EAST, 1).relative(Direction.SOUTH, i), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.WEST, 1).relative(Direction.NORTH, i), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.NORTH, 1).relative(Direction.EAST, i), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.SOUTH, 1).relative(Direction.WEST, i), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.SOUTH, 1).relative(Direction.EAST, i), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.NORTH, 1).relative(Direction.WEST, i), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.EAST, 1).relative(Direction.NORTH, i), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.WEST, 1).relative(Direction.SOUTH, i), config.stemProvider.getState(random, pos));
        }
        this.setBlock(world, mutablePos.relative(Direction.EAST, 2).relative(Direction.SOUTH, 2), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.WEST, 2).relative(Direction.NORTH, 2), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.NORTH, 2).relative(Direction.EAST, 2), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.SOUTH, 2).relative(Direction.WEST, 2), config.stemProvider.getState(random, pos));

        mutablePos.move(Direction.UP);

        this.setBlock(world, mutablePos, config.stemProvider.getState(random, pos));
        for (int i = 1; i <= 3; i++) {
            this.setBlock(world, mutablePos.relative(Direction.EAST, i), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.WEST, i), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.NORTH, i), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.SOUTH, i), config.stemProvider.getState(random, pos));
        }

        this.setBlock(world, mutablePos.relative(Direction.EAST, 1).relative(Direction.SOUTH, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.WEST, 1).relative(Direction.NORTH, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.NORTH, 1).relative(Direction.EAST, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.SOUTH, 1).relative(Direction.WEST, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.EAST, 1).relative(Direction.SOUTH, 2), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.WEST, 1).relative(Direction.NORTH, 2), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.NORTH, 1).relative(Direction.EAST, 2), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.SOUTH, 1).relative(Direction.WEST, 2), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.EAST, 2).relative(Direction.SOUTH, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.WEST, 2).relative(Direction.NORTH, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.NORTH, 2).relative(Direction.EAST, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.SOUTH, 2).relative(Direction.WEST, 1), config.stemProvider.getState(random, pos));

        this.setBlock(world, mutablePos.relative(Direction.WEST, 2).relative(Direction.NORTH, 2), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.EAST, 3).relative(Direction.NORTH, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.EAST, 2).relative(Direction.SOUTH, 2), config.stemProvider.getState(random, pos));

        mutablePos.move(Direction.UP);
        this.setBlock(world, mutablePos, config.stemProvider.getState(random, pos));
        for (int i = 1; i <= 2; i++) {
            this.setBlock(world, mutablePos.relative(Direction.EAST, i), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.WEST, i), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.NORTH, i), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.SOUTH, i), config.stemProvider.getState(random, pos));

            this.setBlock(world, mutablePos.relative(Direction.EAST, 1).relative(Direction.SOUTH, i), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.WEST, 1).relative(Direction.NORTH, i), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.NORTH, 1).relative(Direction.EAST, i), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.SOUTH, 1).relative(Direction.WEST, i), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.SOUTH, 1).relative(Direction.EAST, i), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.NORTH, 1).relative(Direction.WEST, i), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.EAST, 1).relative(Direction.NORTH, i), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.WEST, 1).relative(Direction.SOUTH, i), config.stemProvider.getState(random, pos));
        }
        this.setBlock(world, mutablePos.relative(Direction.EAST, 3), config.stemProvider.getState(random, pos));

        mutablePos.move(Direction.UP);
        this.setBlock(world, mutablePos, config.stemProvider.getState(random, pos));
        for (int i = 1; i <= 2; i++) {
            this.setBlock(world, mutablePos.relative(Direction.EAST, i), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.WEST, i), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.NORTH, i), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.SOUTH, i), config.stemProvider.getState(random, pos));
        }

        this.setBlock(world, mutablePos.relative(Direction.EAST, 1).relative(Direction.SOUTH, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.WEST, 1).relative(Direction.NORTH, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.NORTH, 1).relative(Direction.EAST, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.SOUTH, 1).relative(Direction.WEST, 1), config.stemProvider.getState(random, pos));

        this.setBlock(world, mutablePos.relative(Direction.NORTH, 2).relative(Direction.WEST, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.NORTH, 2).relative(Direction.EAST, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.EAST, 2).relative(Direction.SOUTH, 1), config.stemProvider.getState(random, pos));

        mutablePos.move(Direction.UP);
        this.setBlock(world, mutablePos, config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.EAST, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.WEST, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.NORTH, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.SOUTH, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.EAST, 1).relative(Direction.SOUTH, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.WEST, 1).relative(Direction.NORTH, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.NORTH, 1).relative(Direction.EAST, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.SOUTH, 1).relative(Direction.WEST, 1), config.stemProvider.getState(random, pos));

        this.setBlock(world, mutablePos.relative(Direction.NORTH, 2), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.EAST, 2), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.NORTH, 2).relative(Direction.WEST, 1), config.stemProvider.getState(random, pos));

        mutablePos.move(Direction.UP);
        this.setBlock(world, mutablePos, config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.EAST, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.WEST, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.NORTH, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.SOUTH, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.EAST, 1).relative(Direction.SOUTH, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.WEST, 1).relative(Direction.NORTH, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.NORTH, 1).relative(Direction.EAST, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.SOUTH, 1).relative(Direction.WEST, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.NORTH, 2), config.stemProvider.getState(random, pos));

        mutablePos.move(Direction.UP);
        this.setBlock(world, mutablePos, config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.EAST, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.WEST, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.NORTH, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.SOUTH, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.EAST, 1).relative(Direction.SOUTH, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.WEST, 1).relative(Direction.NORTH, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.NORTH, 1).relative(Direction.EAST, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.SOUTH, 1).relative(Direction.WEST, 1), config.stemProvider.getState(random, pos));

        int bound = random.nextInt(6) + 1;
        for (int i = 0; i <= bound; i++) {
            mutablePos.move(Direction.UP);

            if (i == 0) {
                this.setBlock(world, mutablePos.relative(Direction.EAST, 1).relative(Direction.SOUTH, 1), config.stemProvider.getState(random, pos));
                this.setBlock(world, mutablePos.relative(Direction.WEST, 1).relative(Direction.NORTH, 1), config.stemProvider.getState(random, pos));
            }

            this.setBlock(world, mutablePos, config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.EAST, 1), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.WEST, 1), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.NORTH, 1), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.SOUTH, 1), config.stemProvider.getState(random, pos));

            if (i == bound) {
                this.setBlock(world, mutablePos.relative(Direction.NORTH, 1).relative(Direction.EAST, 1), config.stemProvider.getState(random, pos));
                this.setBlock(world, mutablePos.relative(Direction.SOUTH, 1).relative(Direction.WEST, 1), config.stemProvider.getState(random, pos));
            }
        }

        mutablePos.move(Direction.UP);
        this.setBlock(world, mutablePos, config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.EAST, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.WEST, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.NORTH, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.SOUTH, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.EAST, 1).relative(Direction.SOUTH, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.WEST, 1).relative(Direction.NORTH, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.NORTH, 1).relative(Direction.EAST, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.SOUTH, 1).relative(Direction.WEST, 1), config.stemProvider.getState(random, pos));

        for (int i = 0; i < 2; i++) {
            mutablePos.move(Direction.UP);
            this.setBlock(world, mutablePos, config.stemProvider.getState(random, pos));
            for (int ii = 1; ii <= 2; ii++) {
                this.setBlock(world, mutablePos.relative(Direction.EAST, ii), config.stemProvider.getState(random, pos));
                this.setBlock(world, mutablePos.relative(Direction.WEST, ii), config.stemProvider.getState(random, pos));
                this.setBlock(world, mutablePos.relative(Direction.NORTH, ii), config.stemProvider.getState(random, pos));
                this.setBlock(world, mutablePos.relative(Direction.SOUTH, ii), config.stemProvider.getState(random, pos));
            }

            this.setBlock(world, mutablePos.relative(Direction.EAST, 1).relative(Direction.SOUTH, 1), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.WEST, 1).relative(Direction.NORTH, 1), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.NORTH, 1).relative(Direction.EAST, 1), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.SOUTH, 1).relative(Direction.WEST, 1), config.stemProvider.getState(random, pos));
        }

        mutablePos.move(Direction.UP);
        this.setBlock(world, mutablePos, config.stemProvider.getState(random, pos));
        for (int i = 1; i <= 3; i++) {
            this.setBlock(world, mutablePos.relative(Direction.EAST, i), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.WEST, i), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.NORTH, i), config.stemProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.SOUTH, i), config.stemProvider.getState(random, pos));
        }

        this.setBlock(world, mutablePos.relative(Direction.EAST, 1).relative(Direction.SOUTH, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.WEST, 1).relative(Direction.NORTH, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.NORTH, 1).relative(Direction.EAST, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.SOUTH, 1).relative(Direction.WEST, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.EAST, 1).relative(Direction.SOUTH, 2), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.WEST, 1).relative(Direction.NORTH, 2), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.NORTH, 1).relative(Direction.EAST, 2), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.SOUTH, 1).relative(Direction.WEST, 2), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.EAST, 2).relative(Direction.SOUTH, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.WEST, 2).relative(Direction.NORTH, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.NORTH, 2).relative(Direction.EAST, 1), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.SOUTH, 2).relative(Direction.WEST, 1), config.stemProvider.getState(random, pos));


        for (int i = -3; i <= 3; i++) {
            if (Math.abs(i) == 3) {
                this.setBlock(world, mutablePos.relative(Direction.EAST, 4).relative(Direction.NORTH, i), config.capProvider.getState(random, pos));
                this.setBlock(world, mutablePos.relative(Direction.WEST, 4).relative(Direction.SOUTH, i), config.capProvider.getState(random, pos));
                this.setBlock(world, mutablePos.relative(Direction.NORTH, 4).relative(Direction.WEST, i), config.capProvider.getState(random, pos));
                this.setBlock(world, mutablePos.relative(Direction.SOUTH, 4).relative(Direction.EAST, i), config.capProvider.getState(random, pos));
            } else {
                this.setBlock(world, mutablePos.relative(Direction.EAST, 5).relative(Direction.NORTH, i), config.capProvider.getState(random, pos));
                this.setBlock(world, mutablePos.relative(Direction.WEST, 5).relative(Direction.SOUTH, i), config.capProvider.getState(random, pos));
                this.setBlock(world, mutablePos.relative(Direction.NORTH, 5).relative(Direction.WEST, i), config.capProvider.getState(random, pos));
                this.setBlock(world, mutablePos.relative(Direction.SOUTH, 5).relative(Direction.EAST, i), config.capProvider.getState(random, pos));
            }
        }

        mutablePos.move(Direction.UP);
        for (int i = -2; i <= 3; i++) {
            if (Math.abs(i) == 2) {
                this.setBlock(world, mutablePos.relative(Direction.EAST, 4).relative(Direction.NORTH, i), config.capProvider.getState(random, pos));
                this.setBlock(world, mutablePos.relative(Direction.WEST, 4).relative(Direction.SOUTH, i), config.capProvider.getState(random, pos));
                this.setBlock(world, mutablePos.relative(Direction.NORTH, 4).relative(Direction.WEST, i), config.capProvider.getState(random, pos));
                this.setBlock(world, mutablePos.relative(Direction.SOUTH, 4).relative(Direction.EAST, i), config.capProvider.getState(random, pos));
            } else if (i == 3) {
                this.setBlock(world, mutablePos.relative(Direction.EAST, 3).relative(Direction.NORTH, i), config.capProvider.getState(random, pos));
                this.setBlock(world, mutablePos.relative(Direction.WEST, 3).relative(Direction.SOUTH, i), config.capProvider.getState(random, pos));
                this.setBlock(world, mutablePos.relative(Direction.NORTH, 3).relative(Direction.WEST, i), config.capProvider.getState(random, pos));
                this.setBlock(world, mutablePos.relative(Direction.SOUTH, 3).relative(Direction.EAST, i), config.capProvider.getState(random, pos));
            } else {
                this.setBlock(world, mutablePos.relative(Direction.EAST, 5).relative(Direction.NORTH, i), config.capProvider.getState(random, pos));
                this.setBlock(world, mutablePos.relative(Direction.WEST, 5).relative(Direction.SOUTH, i), config.capProvider.getState(random, pos));
                this.setBlock(world, mutablePos.relative(Direction.NORTH, 5).relative(Direction.WEST, i), config.capProvider.getState(random, pos));
                this.setBlock(world, mutablePos.relative(Direction.SOUTH, 5).relative(Direction.EAST, i), config.capProvider.getState(random, pos));
            }
        }

        this.setBlock(world, mutablePos.relative(Direction.EAST, 4), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.WEST, 4), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.NORTH, 4), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.SOUTH, 4), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.EAST, 2).relative(Direction.NORTH, 2), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.WEST, 2).relative(Direction.SOUTH, 2), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.NORTH, 2).relative(Direction.WEST, 2), config.stemProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.SOUTH, 2).relative(Direction.EAST, 2), config.stemProvider.getState(random, pos));


        mutablePos.move(Direction.UP);
        for (int i = -2; i <= 2; i++) {
            if (Math.abs(i) == 2) {
                this.setBlock(world, mutablePos.relative(Direction.EAST, 3).relative(Direction.NORTH, i), config.capProvider.getState(random, pos));
                this.setBlock(world, mutablePos.relative(Direction.WEST, 3).relative(Direction.SOUTH, i), config.capProvider.getState(random, pos));
                this.setBlock(world, mutablePos.relative(Direction.NORTH, 3).relative(Direction.WEST, i), config.capProvider.getState(random, pos));
                this.setBlock(world, mutablePos.relative(Direction.SOUTH, 3).relative(Direction.EAST, i), config.capProvider.getState(random, pos));
            } else {
                this.setBlock(world, mutablePos.relative(Direction.EAST, 4).relative(Direction.NORTH, i), config.capProvider.getState(random, pos));
                this.setBlock(world, mutablePos.relative(Direction.WEST, 4).relative(Direction.SOUTH, i), config.capProvider.getState(random, pos));
                this.setBlock(world, mutablePos.relative(Direction.NORTH, 4).relative(Direction.WEST, i), config.capProvider.getState(random, pos));
                this.setBlock(world, mutablePos.relative(Direction.SOUTH, 4).relative(Direction.EAST, i), config.capProvider.getState(random, pos));
                this.setBlock(world, mutablePos.relative(Direction.EAST, 3).relative(Direction.NORTH, i), config.capProvider.getState(random, pos));
                this.setBlock(world, mutablePos.relative(Direction.WEST, 3).relative(Direction.SOUTH, i), config.capProvider.getState(random, pos));
                this.setBlock(world, mutablePos.relative(Direction.NORTH, 3).relative(Direction.WEST, i), config.capProvider.getState(random, pos));
                this.setBlock(world, mutablePos.relative(Direction.SOUTH, 3).relative(Direction.EAST, i), config.capProvider.getState(random, pos));
            }
        }

        this.setBlock(world, mutablePos.relative(Direction.EAST, 2).relative(Direction.NORTH, 2), config.capProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.WEST, 2).relative(Direction.SOUTH, 2), config.capProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.NORTH, 2).relative(Direction.WEST, 2), config.capProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.SOUTH, 2).relative(Direction.EAST, 2), config.capProvider.getState(random, pos));

        mutablePos.move(Direction.UP);
        this.setBlock(world, mutablePos, config.capProvider.getState(random, pos));
        for (int i = 1; i <= 3; i++) {
            this.setBlock(world, mutablePos.relative(Direction.EAST, i), config.capProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.WEST, i), config.capProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.NORTH, i), config.capProvider.getState(random, pos));
            this.setBlock(world, mutablePos.relative(Direction.SOUTH, i), config.capProvider.getState(random, pos));
        }

        this.setBlock(world, mutablePos.relative(Direction.EAST, 1).relative(Direction.SOUTH, 1), config.capProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.WEST, 1).relative(Direction.NORTH, 1), config.capProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.NORTH, 1).relative(Direction.EAST, 1), config.capProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.SOUTH, 1).relative(Direction.WEST, 1), config.capProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.EAST, 1).relative(Direction.SOUTH, 2), config.capProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.WEST, 1).relative(Direction.NORTH, 2), config.capProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.NORTH, 1).relative(Direction.EAST, 2), config.capProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.SOUTH, 1).relative(Direction.WEST, 2), config.capProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.EAST, 2).relative(Direction.SOUTH, 1), config.capProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.WEST, 2).relative(Direction.NORTH, 1), config.capProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.NORTH, 2).relative(Direction.EAST, 1), config.capProvider.getState(random, pos));
        this.setBlock(world, mutablePos.relative(Direction.SOUTH, 2).relative(Direction.WEST, 1), config.capProvider.getState(random, pos));

    }

    protected void setBlock(LevelAccessor world, BlockPos pos, BlockState state) {
        if (!world.getBlockState(pos).isSolidRender()) {
            this.setBlock(world, pos, state);
        }

    }

    @Override
    protected int getTreeRadiusForHeight(int i, int j, int capSize, int y) {
        return 0;
    }


}

