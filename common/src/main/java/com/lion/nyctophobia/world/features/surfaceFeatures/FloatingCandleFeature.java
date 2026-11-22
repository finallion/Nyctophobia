package com.lion.nyctophobia.world.features.surfaceFeatures;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

/// Flag 2 skips the neighbour update that breaks a candle.
public class FloatingCandleFeature extends Feature<DefaultFeatureConfig> {

    private static final int CLUSTER_MIN = 2;
    private static final int CLUSTER_EXTRA = 4;
    private static final int SPREAD = 6;
    private static final int HOVER_MIN = 2;
    private static final int HOVER_EXTRA = 4;

    public FloatingCandleFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        boolean placedAny = false;
        int flames = CLUSTER_MIN + random.nextInt(CLUSTER_EXTRA);

        for (int i = 0; i < flames; ++i) {
            int x = origin.getX() + random.nextInt(SPREAD) - random.nextInt(SPREAD);
            int z = origin.getZ() + random.nextInt(SPREAD) - random.nextInt(SPREAD);
            int surface = world.getTopY(Heightmap.Type.WORLD_SURFACE_WG, x, z);
            mutable.set(x, surface + HOVER_MIN + random.nextInt(HOVER_EXTRA), z);

            if (!world.getBlockState(mutable).isAir() || !world.getBlockState(mutable.down()).isAir()) {
                continue;
            }

            world.setBlockState(
                    mutable,
                    Blocks.CANDLE.getDefaultState()
                            .with(Properties.CANDLES, candleCount(random))
                            .with(Properties.LIT, true),
                    2
            );
            placedAny = true;
        }

        return placedAny;
    }

    private static int candleCount(Random random) {
        int roll = random.nextInt(15);

        if (roll < 8) {
            return 1;
        }

        if (roll < 12) {
            return 2;
        }

        return roll < 14 ? 3 : 4;
    }
}
