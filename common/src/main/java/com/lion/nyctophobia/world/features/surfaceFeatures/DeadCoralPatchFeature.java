package com.lion.nyctophobia.world.features.surfaceFeatures;

import com.lion.nyctophobia.world.features.NPlaceContext;
import com.lion.nyctophobia.world.features.NSimpleFeature;
import com.mojang.datafixers.util.Unit;

import com.lion.nyctophobia.util.NTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class DeadCoralPatchFeature extends NSimpleFeature {


    public DeadCoralPatchFeature() {
        super();
    }

    @Override
    public boolean place(NPlaceContext<Unit> context) {
        WorldGenLevel world = context.level();
        BlockPos blockPos = context.origin();
        RandomSource random = context.random();
        context.config();
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(blockPos);


        for (int i = 61; i < 80; i++) {
            mutable.set(blockPos);
            mutable.move(random.nextInt(3) - random.nextInt(3), 0, random.nextInt(3) - random.nextInt(3));
            mutable.setY(i);

            if (world.getBlockState(mutable).isRedstoneConductor(world, mutable) && world.getBlockState(mutable.above()).isAir() && world.getBiome(mutable).unwrapKey().get().identifier().getPath().contains("ancient_dead_coral_reef")) {
                if (random.nextInt(7) == 0) {
                    BuiltInRegistries.BLOCK.get(NTags.DEAD_CORALS).flatMap((blocks) -> {
                        return blocks.getRandomElement(random);
                    }).map(Holder::value).ifPresent((block) -> {
                        world.setBlock(mutable.above(), block.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, false), 2);
                    });
                    break;
                }
            } else if (world.getBlockState(mutable).isRedstoneConductor(world, mutable) && world.getBlockState(mutable.above()).is(Blocks.WATER) && world.getBiome(mutable).unwrapKey().get().identifier().getPath().contains("ancient_dead_coral_reef")) {
                if (random.nextBoolean()) {
                    BuiltInRegistries.BLOCK.get(NTags.DEAD_CORALS).flatMap((blocks) -> {
                        return blocks.getRandomElement(random);
                    }).map(Holder::value).ifPresent((block) -> {
                        world.setBlock(mutable.above(), block.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true), 2);
                    });
                    break;
                }
            }
        }

        return true;

    }

}

