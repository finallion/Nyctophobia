package com.lion.nyctophobia.world.features.surfaceFeatures;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

/// Low tangles of mangrove roots breaking the surface of the marsh, the way they do around a mangrove
/// trunk - a few short arms rather than anything with a canopy, so the horizon stays open.
///
/// Arms start on the ocean floor rather than the world surface, so a tangle standing in a pool grows
/// out of the water instead of on top of it, and the submerged part is waterlogged.
public class MarshRootsFeature extends Feature<DefaultFeatureConfig> {

    private static final int ARMS_MIN = 3;
    private static final int ARMS_EXTRA = 5;
    private static final int SPREAD = 3;
    private static final int HEIGHT_MIN = 1;
    private static final int HEIGHT_EXTRA = 3;

    public MarshRootsFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        boolean placedAny = false;
        int arms = ARMS_MIN + random.nextInt(ARMS_EXTRA);

        for (int arm = 0; arm < arms; ++arm) {
            int x = origin.getX() + random.nextInt(SPREAD) - random.nextInt(SPREAD);
            int z = origin.getZ() + random.nextInt(SPREAD) - random.nextInt(SPREAD);
            int floor = world.getTopY(Heightmap.Type.OCEAN_FLOOR_WG, x, z);
            int height = HEIGHT_MIN + random.nextInt(HEIGHT_EXTRA);

            for (int i = 0; i < height; ++i) {
                mutable.set(x, floor + i, z);
                BlockState existing = world.getBlockState(mutable);
                boolean inWater = existing.isOf(Blocks.WATER);

                if (!existing.isAir() && !inWater) {
                    break;
                }

                // Muddy roots only at a dry base: that block carries no waterlogged property and would
                // leave a dry hole in the middle of a pool.
                BlockState root = i == 0 && !inWater
                        ? Blocks.MUDDY_MANGROVE_ROOTS.getDefaultState()
                        : Blocks.MANGROVE_ROOTS.getDefaultState().with(Properties.WATERLOGGED, inWater);

                world.setBlockState(mutable, root, 2);
                placedAny = true;
            }
        }

        return placedAny;
    }
}
