package com.lion.nyctophobia.world.features.trees;

import com.lion.nyctophobia.world.features.trees.config.NTreeFeatureConfig;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

/// A tree that gave up. The trunk staggers sideways as it climbs and carries a handful of leaves
/// rather than a crown, so it breaks the horizon without closing it - the marsh is meant to stay open.
///
/// Every sideways step lays a second log beside the one below it. Without that the trunk would only
/// touch itself corner to corner and read as a stack of loose blocks.
public class CrookedMarshTree extends Feature<NTreeFeatureConfig> {

    private static final int HEIGHT_MIN = 5;
    private static final int HEIGHT_EXTRA = 5;
    private static final int TRUNK_MIN = 4;
    private static final int BEND_CHANCE = 3;
    private static final int TURN_CHANCE = 4;
    private static final int CLUMPS_MIN = 2;
    private static final int CLUMPS_EXTRA = 3;

    private static final int[] STEP_X = {1, -1, 0, 0};
    private static final int[] STEP_Z = {0, 0, 1, -1};

    public CrookedMarshTree(Codec<NTreeFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<NTreeFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();
        BlockState wood = context.getConfig().woodState;
        BlockState leaf = context.getConfig().leafState;
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        if (!standsOnMarshGround(world.getBlockState(origin.down()))) {
            return false;
        }

        int height = HEIGHT_MIN + random.nextInt(HEIGHT_EXTRA);
        int x = origin.getX();
        int z = origin.getZ();
        int lean = random.nextInt(STEP_X.length);

        int[] trunk = new int[height * 3];
        int placed = 0;

        for (int i = 0; i < height; ++i) {
            int y = origin.getY() + i;

            if (i > 0 && random.nextInt(BEND_CHANCE) == 0) {
                // Mostly keep leaning the same way, so the tree looks pushed over rather than shaken.
                if (random.nextInt(TURN_CHANCE) == 0) {
                    lean = random.nextInt(STEP_X.length);
                }
                x += STEP_X[lean];
                z += STEP_Z[lean];
                if (!place(world, mutable.set(x, y - 1, z), wood)) {
                    break;
                }
            }

            if (!place(world, mutable.set(x, y, z), wood)) {
                break;
            }
            trunk[placed * 3] = x;
            trunk[placed * 3 + 1] = y;
            trunk[placed * 3 + 2] = z;
            ++placed;
        }

        if (placed < TRUNK_MIN) {
            return false;
        }

        growLeaves(world, mutable, random, leaf, trunk, placed);
        return true;
    }

    /// A few clumps hanging off the upper half, never a closed canopy: two to four spots, each with
    /// three or four leaves around one log.
    private static void growLeaves(StructureWorldAccess world, BlockPos.Mutable mutable, Random random,
                                   BlockState leaf, int[] trunk, int placed) {
        int half = placed / 2;
        int clumps = CLUMPS_MIN + random.nextInt(CLUMPS_EXTRA);

        for (int clump = 0; clump < clumps; ++clump) {
            int at = half + random.nextInt(placed - half);
            int x = trunk[at * 3];
            int y = trunk[at * 3 + 1];
            int z = trunk[at * 3 + 2];

            for (int step = 0; step < STEP_X.length; ++step) {
                if (random.nextInt(4) == 0) {
                    continue;
                }
                place(world, mutable.set(x + STEP_X[step], y, z + STEP_Z[step]), leaf);
            }
            if (at == placed - 1) {
                place(world, mutable.set(x, y + 1, z), leaf);
            }
        }
    }

    private static boolean place(StructureWorldAccess world, BlockPos.Mutable pos, BlockState state) {
        if (!world.getBlockState(pos).isAir()) {
            return false;
        }
        world.setBlockState(pos, state, 2);
        return true;
    }

    /// The marsh surface, plus the mud a puddle leaves behind: a tree at the water's edge is the point
    /// of this one. FeatureHelper.canBePlaced knows neither mud nor dirt.
    private static boolean standsOnMarshGround(BlockState state) {
        return state.isOf(Blocks.GRASS_BLOCK)
                || state.isOf(Blocks.DIRT)
                || state.isOf(Blocks.COARSE_DIRT)
                || state.isOf(Blocks.ROOTED_DIRT)
                || state.isOf(Blocks.MOSS_BLOCK)
                || state.isOf(Blocks.MUD);
    }
}
