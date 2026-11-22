package com.lion.nyctophobia.world.features.surfaceFeatures;

import com.lion.nyctophobia.world.features.NPlaceContext;
import com.lion.nyctophobia.world.features.NSimpleFeature;
import com.mojang.datafixers.util.Unit;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.Heightmap;

/// Mangrove leaves take the biome foliage colour.
/// The leaves are persistent, because no log keeps them alive.
public class MarshThicketFeature extends NSimpleFeature {

    private static final int COLUMNS_MIN = 6;
    private static final int COLUMNS_EXTRA = 9;
    private static final int GROW_REACH = 3;
    private static final int HEIGHT_MAX = 3;
    private static final int ROLL = 1;
    private static final int SCAN_UP = 4;
    private static final int SCAN_DOWN = 6;

    private static final int REACH = GROW_REACH;
    private static final int SPAN = 2 * REACH + 1;
    private static final int NO_GROUND = Integer.MIN_VALUE;

    private static final int[] STEP_X = {1, -1, 0, 0};
    private static final int[] STEP_Z = {0, 0, 1, -1};

    public MarshThicketFeature() {
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
        int base = groundAt(
                world,
                mutable,
                x,
                z,
                world.getHeight(Heightmap.Types.OCEAN_FLOOR, x, z) + SCAN_UP,
                world.getHeight(Heightmap.Types.OCEAN_FLOOR, x, z) - SCAN_DOWN
        );

        if (base == NO_GROUND || !isMarshGround(world.getBlockState(mutable.set(x, base - 1, z)))) {
            return false;
        }

        boolean[] thicket = new boolean[SPAN * SPAN];
        int[] levels = new int[SPAN * SPAN];
        int[] members = new int[SPAN * SPAN * 2];
        thicket[index(0, 0)] = true;
        levels[index(0, 0)] = base;
        int count = 1;
        int target = COLUMNS_MIN + random.nextInt(COLUMNS_EXTRA);

        for (int attempt = target * 6; count < target && attempt > 0; --attempt) {
            int from = random.nextInt(count) * 2;
            int step = random.nextInt(STEP_X.length);
            int dx = members[from] + STEP_X[step];
            int dz = members[from + 1] + STEP_Z[step];

            if (dx * dx + dz * dz > GROW_REACH * GROW_REACH || thicket[index(dx, dz)]) {
                continue;
            }

            int level = groundAt(world, mutable, x + dx, z + dz, base + SCAN_UP, base - SCAN_DOWN);

            if (level == NO_GROUND || Math.abs(level - base) > ROLL
                    || !isMarshGround(world.getBlockState(mutable.set(x + dx, level - 1, z + dz)))) {
                continue;
            }

            thicket[index(dx, dz)] = true;
            levels[index(dx, dz)] = level;
            members[count * 2] = dx;
            members[count * 2 + 1] = dz;
            ++count;
        }

        if (count < COLUMNS_MIN) {
            return false;
        }

        for (int i = 0; i < count; ++i) {
            int dx = members[i * 2];
            int dz = members[i * 2 + 1];
            int height = Math.max(1, HEIGHT_MAX - (Math.abs(dx) + Math.abs(dz)) / 2 - random.nextInt(2));
            stack(world, mutable, x + dx, z + dz, levels[index(dx, dz)], height);
        }

        return true;
    }

    private static void stack(
            WorldGenLevel world,
            BlockPos.MutableBlockPos mutable,
            int x,
            int z,
            int base,
            int height
    ) {
        for (int i = 0; i < height; ++i) {
            mutable.set(x, base + i, z);
            BlockState existing = world.getBlockState(mutable);
            boolean inWater = existing.is(Blocks.WATER);

            if (!existing.isAir() && !inWater) {
                return;
            }

            world.setBlock(
                    mutable,
                    Blocks.MANGROVE_LEAVES.defaultBlockState()
                            .setValue(BlockStateProperties.PERSISTENT, true)
                            .setValue(BlockStateProperties.WATERLOGGED, inWater),
                    2
            );
        }
    }

    private static boolean isMarshGround(BlockState state) {
        return state.is(Blocks.GRASS_BLOCK)
                || state.is(Blocks.DIRT)
                || state.is(Blocks.COARSE_DIRT)
                || state.is(Blocks.ROOTED_DIRT)
                || state.is(Blocks.MOSS_BLOCK)
                || state.is(Blocks.MUD);
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
            if (FeatureHelper.blocksMotion(world.getBlockState(mutable.set(x, y, z)))) {
                return y + 1;
            }
        }
        return NO_GROUND;
    }

    private static int index(int dx, int dz) {
        return (dx + REACH) * SPAN + (dz + REACH);
    }
}
