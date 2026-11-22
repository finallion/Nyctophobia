package com.lion.nyctophobia.world.features.surfaceFeatures;

import com.lion.nyctophobia.world.features.NPlaceContext;
import com.lion.nyctophobia.world.features.NSimpleFeature;
import com.mojang.datafixers.util.Unit;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.Heightmap;

/// Flag 2 skips the neighbour update that breaks a candle.
public class FloatingCandleFeature extends NSimpleFeature {

    private static final int CLUSTER_MIN = 2;
    private static final int CLUSTER_EXTRA = 4;
    private static final int SPREAD = 6;
    private static final int HOVER_MIN = 2;
    private static final int HOVER_EXTRA = 4;

    public FloatingCandleFeature() {
        super();
    }

    @Override
    public boolean place(NPlaceContext<Unit> context) {
        WorldGenLevel world = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        boolean placedAny = false;
        int flames = CLUSTER_MIN + random.nextInt(CLUSTER_EXTRA);

        for (int i = 0; i < flames; ++i) {
            int x = origin.getX() + random.nextInt(SPREAD) - random.nextInt(SPREAD);
            int z = origin.getZ() + random.nextInt(SPREAD) - random.nextInt(SPREAD);
            int surface = world.getHeight(Heightmap.Types.WORLD_SURFACE_WG, x, z);
            mutable.set(x, surface + HOVER_MIN + random.nextInt(HOVER_EXTRA), z);

            if (!world.getBlockState(mutable).isAir() || !world.getBlockState(mutable.below()).isAir()) {
                continue;
            }

            world.setBlock(
                    mutable,
                    Blocks.CANDLE.defaultBlockState()
                            .setValue(BlockStateProperties.CANDLES, candleCount(random))
                            .setValue(BlockStateProperties.LIT, true),
                    2
            );
            placedAny = true;
        }

        return placedAny;
    }

    private static int candleCount(RandomSource random) {
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
