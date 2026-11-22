package com.lion.nyctophobia.world.features.surfaceFeatures;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class BushFeature extends Feature<NoneFeatureConfiguration> {
    private LeavesBlock leavesBlock;

    public BushFeature(Codec<NoneFeatureConfiguration> configCodec, LeavesBlock block) {
        super(configCodec);
        this.leavesBlock = block;
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos blockPos = context.origin();
        RandomSource random = context.random();
        context.config();
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(blockPos);


        for (int i = 64; i < world.getHeight(); i++) {
            mutable.set(blockPos);
            mutable.move(random.nextInt(3) - random.nextInt(3), 0, random.nextInt(3) - random.nextInt(3));
            mutable.setY(i);

            if (FeatureHelper.canBePlaced(world.getBlockState(mutable)) && world.getBlockState(mutable.above()).isAir() && FeatureHelper.isCorrectBiome(world.getBiome(mutable).unwrapKey().get())) {
                if (random.nextInt(7) == 0) {
                    world.setBlock(mutable.above(), leavesBlock.defaultBlockState().setValue(BlockStateProperties.PERSISTENT, true), 2);
                    break;
                }
            }
        }

        return true;

    }

}

