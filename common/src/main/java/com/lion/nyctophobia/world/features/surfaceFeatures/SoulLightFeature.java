package com.lion.nyctophobia.world.features.surfaceFeatures;

import com.lion.nyctophobia.Nyctophobia;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class SoulLightFeature extends Feature<NoneFeatureConfiguration> {


    public SoulLightFeature(Codec<NoneFeatureConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos blockPos = context.origin();
        RandomSource random = context.random();
        context.config();
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().set(blockPos);

        mutable.set(blockPos);
        mutable.move(random.nextInt(10) - random.nextInt(10), 0, random.nextInt(10) - random.nextInt(10));
        mutable.setY(63);


        if (world.getBlockState(mutable).is(Blocks.LILY_PAD) && world.getBlockState(mutable.above()).isAir() && world.getBlockState(mutable.above(2)).isAir() && FeatureHelper.isCorrectBiome(world.getBiome(mutable).unwrapKey().get())) {

            if (!Nyctophobia.config.miscConfigEntries.get("useSoulLanternsInHauntedLakes")) {
                world.setBlock(
                        mutable.move(0, random.nextInt(2) + 1, 0),
                        Blocks.BLACK_CANDLE.defaultBlockState()
                                .setValue(BlockStateProperties.CANDLES, random.nextInt(3) + 2)
                                .setValue(BlockStateProperties.LIT, true),
                        2
                );
            } else {
                world.setBlock(mutable.move(0, random.nextInt(2) + 1, 0), Blocks.SOUL_LANTERN.defaultBlockState(), 2);
            }

            return true;
        }




        return false;
    }
}
