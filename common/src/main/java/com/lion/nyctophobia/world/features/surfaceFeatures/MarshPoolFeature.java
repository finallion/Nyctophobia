package com.lion.nyctophobia.world.features.surfaceFeatures;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Arrays;

/// Small patches of standing water lying in the top layer of the marsh, ringed and floored with mud.
///
/// A surface rule cannot open these: it only exchanges the block that already is the top of the
/// terrain, and the marsh floor sits at y65 while the band it can write in ends at y62.
///
/// **This does not dig.** An earlier version cut a bowl up to three blocks deep and let water run into
/// it, which reads as a crater, and wherever the ground was even it cut a perfect circle. A puddle is
/// one block of water sitting *in* the surface: the top block of the ground becomes water, the block
/// under it becomes mud, and where the ground beside it falls away the gap is **dammed with mud**
/// rather than the puddle dug deeper.
///
/// The outline comes from a random walk over columns that already stand at the water line, so it is
/// ragged by construction and follows whatever the terrain does.
public class MarshPoolFeature extends Feature<DefaultFeatureConfig> {

    private static final int COLUMNS_MIN = 6;
    private static final int COLUMNS_EXTRA = 13;
    /// How far a puddle may spread from where it started.
    private static final int GROW_REACH = 5;
    /// How far the ground may sit off the water line and still be part of the puddle.
    private static final int ROLL = 1;
    /// How much mud may be stacked to dam a drop before the spot counts as a slope.
    private static final int RIM_MAX = 2;
    private static final int SCAN_UP = 6;
    private static final int SCAN_DOWN = 8;

    private static final int REACH = GROW_REACH + 1;
    private static final int SPAN = 2 * REACH + 1;
    private static final int NO_GROUND = Integer.MIN_VALUE;
    private static final int UNKNOWN = Integer.MAX_VALUE;

    private static final int[] STEP_X = {1, -1, 0, 0};
    private static final int[] STEP_Z = {0, 0, 1, -1};

    public MarshPoolFeature(Codec<DefaultFeatureConfig> configCodec) {
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
        int[] ground = new int[SPAN * SPAN];
        Arrays.fill(ground, UNKNOWN);

        // The origin column is always in the chunk being generated, so its heightmap is filled in and
        // can anchor the scan. Every other column is read from its blocks: only the chunk being
        // generated is guaranteed to carry a filled OCEAN_FLOOR, and a puddle reaches into its
        // neighbours. OCEAN_FLOOR rather than OCEAN_FLOOR_WG - the WG heightmaps exist only while a
        // chunk generates and answer -64 to every /place feature.
        int base = world.getTopY(Heightmap.Type.OCEAN_FLOOR, x, z);
        ground[index(0, 0)] = groundAt(world, mutable, x, z, base + SCAN_UP, base - SCAN_DOWN);
        if (ground[index(0, 0)] == NO_GROUND) {
            return false;
        }

        base = ground[index(0, 0)];
        int waterY = base - 1;

        boolean[] puddle = new boolean[SPAN * SPAN];
        int[] members = new int[SPAN * SPAN * 2];
        int count = grow(world, mutable, random, ground, puddle, members, x, z, base);
        if (count < COLUMNS_MIN) {
            return false;
        }

        if (!dam(world, mutable, puddle, members, count, x, z, base, waterY)) {
            return false;
        }

        for (int i = 0; i < count; ++i) {
            fillPuddle(world, mutable, x + members[i * 2], z + members[i * 2 + 1], base, waterY);
        }

        muddyShore(world, mutable, random, puddle, members, count, x, z, base);
        return true;
    }

    /// Random walk outwards from the start over columns that already stand at the water line. Picking
    /// a member at random rather than sweeping a shape is what keeps the outline from reading as a
    /// circle.
    private static int grow(StructureWorldAccess world, BlockPos.Mutable mutable, Random random,
                            int[] ground, boolean[] puddle, int[] members, int x, int z, int base) {
        puddle[index(0, 0)] = true;
        int count = 1;
        int target = COLUMNS_MIN + random.nextInt(COLUMNS_EXTRA);

        for (int attempt = target * 6; count < target && attempt > 0; --attempt) {
            int from = random.nextInt(count) * 2;
            int step = random.nextInt(STEP_X.length);
            int dx = members[from] + STEP_X[step];
            int dz = members[from + 1] + STEP_Z[step];

            if (dx * dx + dz * dz > GROW_REACH * GROW_REACH || puddle[index(dx, dz)]) {
                continue;
            }

            int level = groundOf(world, mutable, ground, x, z, dx, dz, base);
            if (level == NO_GROUND || Math.abs(level - base) > ROLL) {
                continue;
            }

            puddle[index(dx, dz)] = true;
            members[count * 2] = dx;
            members[count * 2 + 1] = dz;
            ++count;
        }

        return count;
    }

    /// Raises the ground beside the puddle to the water line wherever it falls away. This is the whole
    /// trick: a marsh puddle is held by mud that is added, not by a hole that is cut.
    private static boolean dam(StructureWorldAccess world, BlockPos.Mutable mutable,
                               boolean[] puddle, int[] members, int count, int x, int z,
                               int base, int waterY) {
        for (int i = 0; i < count; ++i) {
            for (int step = 0; step < STEP_X.length; ++step) {
                int dx = members[i * 2] + STEP_X[step];
                int dz = members[i * 2 + 1] + STEP_Z[step];
                if (puddle[index(dx, dz)]) {
                    continue;
                }

                // Read the column again rather than trust the cache: an earlier puddle in this chunk
                // may already have raised or opened it, and a stale height leaves mud in the air.
                int level = groundAt(world, mutable, x + dx, z + dz, base + SCAN_UP, base - SCAN_DOWN);
                if (level == NO_GROUND || waterY - level + 1 > RIM_MAX) {
                    return false;
                }
                for (int y = level; y <= waterY; ++y) {
                    world.setBlockState(mutable.set(x + dx, y, z + dz), Blocks.MUD.getDefaultState(), 2);
                }
            }
        }
        return true;
    }

    /// One block of water where the ground was, one block of mud under it, and nothing left standing
    /// over the water line.
    private static void fillPuddle(StructureWorldAccess world, BlockPos.Mutable mutable, int x, int z,
                                   int base, int waterY) {
        for (int y = waterY + 1; y <= base + ROLL; ++y) {
            mutable.set(x, y, z);
            if (!world.getBlockState(mutable).isAir()) {
                world.setBlockState(mutable, Blocks.AIR.getDefaultState(), 2);
            }
        }
        world.setBlockState(mutable.set(x, waterY, z), Blocks.WATER.getDefaultState(), 2);
        world.setBlockState(mutable.set(x, waterY - 1, z), Blocks.MUD.getDefaultState(), 2);
    }

    /// Mud on most of the surrounding surface, so a puddle sits in a wet bank instead of in the grass.
    /// Not all of it: an unbroken mud ring would give the puddle an outline again.
    private static void muddyShore(StructureWorldAccess world, BlockPos.Mutable mutable, Random random,
                                   boolean[] puddle, int[] members, int count,
                                   int x, int z, int base) {
        for (int i = 0; i < count; ++i) {
            for (int nx = members[i * 2] - 1; nx <= members[i * 2] + 1; ++nx) {
                for (int nz = members[i * 2 + 1] - 1; nz <= members[i * 2 + 1] + 1; ++nz) {
                    if (Math.abs(nx) > REACH || Math.abs(nz) > REACH || puddle[index(nx, nz)] || random.nextInt(4) == 0) {
                        continue;
                    }

                    int level = groundAt(world, mutable, x + nx, z + nz, base + SCAN_UP, base - SCAN_DOWN);
                    // Only at the water line: mud two blocks up a bank reads as a smear, not a shore.
                    if (level == NO_GROUND || Math.abs(level - base) > 1) {
                        continue;
                    }

                    // Exchange the surface block, never add one on top of it.
                    mutable.set(x + nx, level - 1, z + nz);
                    if (isMarshSurface(world.getBlockState(mutable))) {
                        world.setBlockState(mutable, Blocks.MUD.getDefaultState(), 2);
                    }
                }
            }
        }
    }

    private static int groundOf(StructureWorldAccess world, BlockPos.Mutable mutable, int[] ground,
                                int x, int z, int dx, int dz, int base) {
        int at = index(dx, dz);
        if (ground[at] == UNKNOWN) {
            ground[at] = groundAt(world, mutable, x + dx, z + dz, base + SCAN_UP, base - SCAN_DOWN);
        }
        return ground[at];
    }

    /// First free block over the ground in this column, or NO_GROUND if the column holds nothing solid
    /// in the window - a cliff or a hole, either of which would let the puddle run out.
    ///
    /// The test is whether a block stops you walking, not whether it is air: grass, ferns and dead
    /// bushes are neither, and counting them as ground puts the mud a block too high, standing on top
    /// of the turf. Neighbouring chunks are often already planted by the time this one digs.
    private static int groundAt(StructureWorldAccess world, BlockPos.Mutable mutable, int x, int z,
                                int top, int bottom) {
        for (int y = top; y >= bottom; --y) {
            if (world.getBlockState(mutable.set(x, y, z)).blocksMovement()) {
                return y + 1;
            }
        }
        return NO_GROUND;
    }

    /// What the marsh surface rules write. Mud replaces one of these or nothing at all, so the feature
    /// can never drop a block onto turf, a plant or thin air.
    private static boolean isMarshSurface(BlockState state) {
        return state.isOf(Blocks.GRASS_BLOCK)
                || state.isOf(Blocks.DIRT)
                || state.isOf(Blocks.COARSE_DIRT)
                || state.isOf(Blocks.ROOTED_DIRT)
                || state.isOf(Blocks.MOSS_BLOCK)
                || state.isOf(Blocks.MUD);
    }

    private static int index(int dx, int dz) {
        return (dx + REACH) * SPAN + (dz + REACH);
    }
}
