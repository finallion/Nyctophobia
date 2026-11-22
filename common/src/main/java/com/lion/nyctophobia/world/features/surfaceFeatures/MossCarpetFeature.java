package com.lion.nyctophobia.world.features.surfaceFeatures;

import com.lion.nyctophobia.world.features.NPlaceContext;
import com.lion.nyctophobia.world.features.NSimpleFeature;
import com.mojang.datafixers.util.Unit;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;

public class MossCarpetFeature extends NSimpleFeature {


    public MossCarpetFeature() {
        super();
    }

    @Override
    public boolean place(NPlaceContext<Unit> context) {
        WorldGenLevel world = context.level();
        BlockPos blockPos = context.origin();
        RandomSource random = context.random();
        context.config();
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(blockPos);

        for (int i = 62; i < 128; i++) {
            mutable.set(blockPos);
            mutable.move(random.nextInt(10) - random.nextInt(10), 0, random.nextInt(10) - random.nextInt(10));
            mutable.setY(i);

            if (world.getBlockState(mutable).getBlock() instanceof LeavesBlock && world.getBlockState(mutable.above()).isAir() && FeatureHelper.isCorrectBiome(world.getBiome(mutable).unwrapKey().get())) {
                world.setBlock(mutable.above(), Blocks.MOSS_CARPET.defaultBlockState(), 2);
                break;

            } else if (world.getBlockState(mutable).is(Blocks.MOSS_BLOCK) && world.getBlockState(mutable.above()).isAir()) {
                if (random.nextInt(5) == 0) {
                    world.setBlock(mutable.above(), Blocks.MOSS_CARPET.defaultBlockState(), 2);
                    break;
                }
            }

        }

        return true;

    }

}
