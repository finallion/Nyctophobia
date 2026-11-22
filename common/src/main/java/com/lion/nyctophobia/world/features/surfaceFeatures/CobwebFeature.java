package com.lion.nyctophobia.world.features.surfaceFeatures;

import com.lion.nyctophobia.world.features.NPlaceContext;
import com.lion.nyctophobia.world.features.NSimpleFeature;
import com.mojang.datafixers.util.Unit;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;

public class CobwebFeature extends NSimpleFeature {


    public CobwebFeature() {
        super();
    }

    @Override
    public boolean place(NPlaceContext<Unit> context) {
        WorldGenLevel world = context.level();
        BlockPos blockPos = context.origin();
        RandomSource random = context.random();
        context.config();
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(blockPos);

        for (int i = 64; i < world.getHeight(); i++) {
            mutable.set(blockPos);
            mutable.move(random.nextInt(10) - random.nextInt(10), 0, random.nextInt(10) - random.nextInt(10));
            mutable.setY(i);

            if (world.getBlockState(mutable).is(Blocks.SPRUCE_LEAVES) && world.getBlockState(mutable.below()).isAir() && random.nextBoolean() && FeatureHelper.isCorrectBiome(world.getBiome(mutable).unwrapKey().get())) {
                world.setBlock(mutable.below(), Blocks.COBWEB.defaultBlockState(), 2);
                break;
            }
        }

        return true;
    }
}