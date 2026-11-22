package com.lion.nyctophobia.world.features.surfaceFeatures;

import com.lion.nyctophobia.world.features.NPlaceContext;
import com.lion.nyctophobia.world.features.NSimpleFeature;
import com.mojang.datafixers.util.Unit;

import java.util.Arrays;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;

public class MarshPoolFeature extends NSimpleFeature {

    private static final int COLUMNS_MIN = 6;
    private static final int COLUMNS_EXTRA = 13;
    private static final int GROW_REACH = 5;
    private static final int ROLL = 1;
    private static final int RIM_MAX = 2;
    private static final int SCAN_UP = 6;
    private static final int SCAN_DOWN = 8;

    private static final int REACH = GROW_REACH + 1;
    private static final int SPAN = 2 * REACH + 1;
    private static final int NO_GROUND = Integer.MIN_VALUE;
    private static final int UNKNOWN = Integer.MAX_VALUE;

    private static final int[] STEP_X = {1, -1, 0, 0};
    private static final int[] STEP_Z = {0, 0, 1, -1};

    public MarshPoolFeature() {
        super();
    }

    @Override
    public boolean place(NPlaceContext<Unit> context) {
        WorldGenLevel world = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

        int x = origin.getX();
        int z = origin.getZ();
        int[] ground = new int[SPAN * SPAN];
        Arrays.fill(ground, UNKNOWN);

        // Only the origin column has a filled OCEAN_FLOOR. Read other columns from blocks.
        int base = world.getHeight(Heightmap.Types.OCEAN_FLOOR, x, z);
        ground[index(0, 0)] = groundAt(world, mutable, x, z, base + SCAN_UP, base - SCAN_DOWN);

        if (ground[index(0, 0)] == NO_GROUND
                || !isMarshSurface(world.getBlockState(mutable.set(x, ground[index(0, 0)] - 1, z)))) {
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

    private static int grow(
            WorldGenLevel world,
            BlockPos.MutableBlockPos mutable,
            RandomSource random,
            int[] ground,
            boolean[] puddle,
            int[] members,
            int x,
            int z,
            int base
    ) {
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

            if (level == NO_GROUND || Math.abs(level - base) > ROLL
                    || !isMarshSurface(world.getBlockState(mutable.set(x + dx, level - 1, z + dz)))) {
                continue;
            }

            puddle[index(dx, dz)] = true;
            members[count * 2] = dx;
            members[count * 2 + 1] = dz;
            ++count;
        }

        return count;
    }

    private static boolean dam(
            WorldGenLevel world,
            BlockPos.MutableBlockPos mutable,
            boolean[] puddle,
            int[] members,
            int count,
            int x,
            int z,
            int base,
            int waterY
    ) {
        for (int i = 0; i < count; ++i) {
            for (int step = 0; step < STEP_X.length; ++step) {
                int dx = members[i * 2] + STEP_X[step];
                int dz = members[i * 2 + 1] + STEP_Z[step];

                if (puddle[index(dx, dz)]) {
                    continue;
                }

                // Read the column again. An earlier puddle may have changed it.
                int level = groundAt(world, mutable, x + dx, z + dz, base + SCAN_UP, base - SCAN_DOWN);

                if (level == NO_GROUND || waterY - level + 1 > RIM_MAX) {
                    return false;
                }

                for (int y = level; y <= waterY; ++y) {
                    world.setBlock(mutable.set(x + dx, y, z + dz), Blocks.MUD.defaultBlockState(), 2);
                }
            }
        }

        return true;
    }

    private static void fillPuddle(
            WorldGenLevel world,
            BlockPos.MutableBlockPos mutable,
            int x,
            int z,
            int base,
            int waterY
    ) {
        for (int y = waterY + 1; y <= base + ROLL; ++y) {
            mutable.set(x, y, z);

            if (!world.getBlockState(mutable).isAir()) {
                world.setBlock(mutable, Blocks.AIR.defaultBlockState(), 2);
            }
        }

        world.setBlock(mutable.set(x, waterY, z), Blocks.WATER.defaultBlockState(), 2);
        world.setBlock(mutable.set(x, waterY - 1, z), Blocks.MUD.defaultBlockState(), 2);
    }

    private static void muddyShore(
            WorldGenLevel world,
            BlockPos.MutableBlockPos mutable,
            RandomSource random,
            boolean[] puddle,
            int[] members,
            int count,
            int x,
            int z,
            int base
    ) {
        for (int i = 0; i < count; ++i) {
            for (int nx = members[i * 2] - 1; nx <= members[i * 2] + 1; ++nx) {
                for (int nz = members[i * 2 + 1] - 1; nz <= members[i * 2 + 1] + 1; ++nz) {
                    if (Math.abs(nx) > REACH || Math.abs(nz) > REACH || puddle[index(nx, nz)] || random.nextInt(4) == 0) {
                        continue;
                    }

                    int level = groundAt(world, mutable, x + nx, z + nz, base + SCAN_UP, base - SCAN_DOWN);

                    if (level == NO_GROUND || Math.abs(level - base) > 1) {
                        continue;
                    }

                    mutable.set(x + nx, level - 1, z + nz);

                    if (isMarshSurface(world.getBlockState(mutable))) {
                        world.setBlock(mutable, Blocks.MUD.defaultBlockState(), 2);
                    }
                }
            }
        }
    }

    private static int groundOf(
            WorldGenLevel world,
            BlockPos.MutableBlockPos mutable,
            int[] ground,
            int x,
            int z,
            int dx,
            int dz,
            int base
    ) {
        int at = index(dx, dz);

        if (ground[at] == UNKNOWN) {
            ground[at] = groundAt(world, mutable, x + dx, z + dz, base + SCAN_UP, base - SCAN_DOWN);
        }

        return ground[at];
    }

    private static int groundAt(
            WorldGenLevel world,
            BlockPos.MutableBlockPos mutable,
            int x,
            int z,
            int top,
            int bottom
    ) {
        for (int y = top; y >= bottom; --y) {
            BlockState state = world.getBlockState(mutable.set(x, y, z));

            if (state.is(BlockTags.LEAVES)) {
                continue;
            }

            if (state.is(BlockTags.LOGS)) {
                return NO_GROUND;
            }

            if (FeatureHelper.blocksMotion(state)) {
                return y + 1;
            }
        }

        return NO_GROUND;
    }

    private static boolean isMarshSurface(BlockState state) {
        return state.is(Blocks.GRASS_BLOCK)
                || state.is(Blocks.DIRT)
                || state.is(Blocks.COARSE_DIRT)
                || state.is(Blocks.ROOTED_DIRT)
                || state.is(Blocks.MOSS_BLOCK)
                || state.is(Blocks.MUD);
    }

    private static int index(int dx, int dz) {
        return (dx + REACH) * SPAN + (dz + REACH);
    }
}
