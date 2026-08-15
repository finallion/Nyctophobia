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

/// Candles hanging in the air over the marsh rather than standing on it.
///
/// A candle needs a block under it and breaks itself the moment a neighbour update reaches it, so
/// these are written with flag 2, which notifies listeners without triggering that update. Generated
/// terrain never updates on its own, so they stay put; a player breaking a block right beside one
/// will still knock it down.
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

            // Both the target and the block under it have to be clear, otherwise the flame reads as
            // sitting on something rather than hanging.
            if (!world.getBlockState(mutable).isAir() || !world.getBlockState(mutable.down()).isAir()) {
                continue;
            }

            world.setBlockState(mutable, Blocks.CANDLE.getDefaultState()
                    .with(Properties.CANDLES, candleCount(random))
                    .with(Properties.LIT, true), 2);
            placedAny = true;
        }

        return placedAny;
    }

    /// Single flames far outnumber clusters of four, so most of what you see is one point of light.
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
