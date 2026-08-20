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

/// A mangrove style root tangle: a knot of roots in the middle, with prop legs that arch away from it
/// and plant a foot where they come down.
///
/// The legs are what make this a root system rather than a stack of blocks. Each one leaves the crown
/// in its own direction and at its own length, loses height as it reaches out, and ends on the ground
/// it happens to find, so no two tangles share an outline.
///
/// A tangle needs open water in reach. That keeps it at the edge of a pool, where a mangrove would
/// stand, instead of scattered over dry marsh.
public class MarshRootsFeature extends Feature<DefaultFeatureConfig> {

    private static final int LEGS_MIN = 3;
    private static final int LEGS_EXTRA = 3;
    private static final int LEG_LENGTH_MIN = 2;
    private static final int LEG_LENGTH_EXTRA = 3;
    private static final int CROWN_MIN = 2;
    private static final int CROWN_EXTRA = 2;
    private static final int WATER_REACH = 4;

    /// All eight compass directions, so a leg can leave the crown diagonally as well.
    private static final int[] STEP_X = {1, 1, 0, -1, -1, -1, 0, 1};
    private static final int[] STEP_Z = {0, 1, 1, 1, 0, -1, -1, -1};

    public MarshRootsFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        int x = origin.getX();
        int z = origin.getZ();
        // OCEAN_FLOOR rather than OCEAN_FLOOR_WG: the WG heightmaps live only as long as a chunk is
        // being generated, so a feature reading them answers -64 to every /place feature and cannot
        // be checked in a running world. This one is filled in before the feature step either way.
        int ground = world.getTopY(Heightmap.Type.OCEAN_FLOOR, x, z);

        if (!waterWithinReach(world, mutable, x, ground, z)) {
            return false;
        }

        // The crown carries the legs, so a blocked column has to lower them with it rather than leave
        // them hanging at the height that was asked for.
        int crownY = ground + CROWN_MIN + random.nextInt(CROWN_EXTRA);
        for (int y = ground; y <= crownY; ++y) {
            if (!placeRoot(world, mutable.set(x, y, z), y == ground)) {
                crownY = y - 1;
                break;
            }
        }

        if (crownY < ground) {
            return false;
        }

        int legs = LEGS_MIN + random.nextInt(LEGS_EXTRA);
        int direction = random.nextInt(STEP_X.length);
        for (int leg = 0; leg < legs; ++leg) {
            // Step around the compass instead of drawing freely, so two legs never take the same way
            // out and the tangle keeps its spread.
            direction = (direction + 1 + random.nextInt(3)) % STEP_X.length;
            growLeg(world, mutable, random, x, z, crownY, direction);
        }

        return true;
    }

    /// One prop leg: full height at the crown, a block lower every second step outwards, and a foot
    /// run down to the ground where it ends. Without that foot a leg reaching over a pool would hang
    /// in the air.
    private static void growLeg(StructureWorldAccess world, BlockPos.Mutable mutable, Random random,
                                int x, int z, int crownY, int direction) {
        int length = LEG_LENGTH_MIN + random.nextInt(LEG_LENGTH_EXTRA);
        int y = crownY;

        for (int step = 1; step <= length; ++step) {
            x += STEP_X[direction];
            z += STEP_Z[direction];
            if (step % 2 == 0) {
                --y;
            }

            int ground = world.getTopY(Heightmap.Type.OCEAN_FLOOR, x, z);
            y = Math.max(y, ground);

            if (!placeRoot(world, mutable.set(x, y, z), y == ground)) {
                return;
            }

            if (step == length) {
                for (int footY = y - 1; footY >= ground; --footY) {
                    if (!placeRoot(world, mutable.set(x, footY, z), footY == ground)) {
                        return;
                    }
                }
            }
        }
    }

    /// Muddy roots only where the tangle rests on dry ground: that block carries no waterlogged state
    /// and would leave a dry pocket anywhere inside the water.
    private static boolean placeRoot(StructureWorldAccess world, BlockPos.Mutable pos, boolean onGround) {
        BlockState existing = world.getBlockState(pos);
        boolean inWater = existing.isOf(Blocks.WATER);

        if (!existing.isAir() && !inWater) {
            return false;
        }

        BlockState root = onGround && !inWater
                ? Blocks.MUDDY_MANGROVE_ROOTS.getDefaultState()
                : Blocks.MANGROVE_ROOTS.getDefaultState().with(Properties.WATERLOGGED, inWater);

        world.setBlockState(pos, root, 2);
        return true;
    }

    private static boolean waterWithinReach(StructureWorldAccess world, BlockPos.Mutable mutable, int x, int y, int z) {
        for (int dx = -WATER_REACH; dx <= WATER_REACH; ++dx) {
            for (int dz = -WATER_REACH; dz <= WATER_REACH; ++dz) {
                for (int dy = -1; dy <= 1; ++dy) {
                    if (world.getBlockState(mutable.set(x + dx, y + dy, z + dz)).isOf(Blocks.WATER)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
