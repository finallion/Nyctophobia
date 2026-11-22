package com.lion.nyctophobia.world.features.surfaceFeatures;

import com.lion.nyctophobia.world.features.NPlaceContext;
import com.lion.nyctophobia.world.features.NSimpleFeature;
import com.mojang.datafixers.util.Unit;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.Heightmap;

public class MarshRootsFeature extends NSimpleFeature {

    private static final int LEGS_MIN = 3;
    private static final int LEGS_EXTRA = 3;
    private static final int LEG_LENGTH_MIN = 2;
    private static final int LEG_LENGTH_EXTRA = 3;
    private static final int CROWN_MIN = 2;
    private static final int CROWN_EXTRA = 2;
    private static final int WATER_REACH = 4;
    private static final int SCAN_DOWN = 8;
    private static final int NO_GROUND = Integer.MIN_VALUE;

    private static final int[] STEP_X = {1, 1, 0, -1, -1, -1, 0, 1};
    private static final int[] STEP_Z = {0, 1, 1, 1, 0, -1, -1, -1};

    public MarshRootsFeature() {
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
        int ground = groundAt(world, mutable, x, z);

        if (ground == NO_GROUND || !waterWithinReach(world, mutable, x, ground, z)) {
            return false;
        }

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
            direction = (direction + 1 + random.nextInt(3)) % STEP_X.length;
            growLeg(world, mutable, random, x, z, crownY, direction);
        }

        return true;
    }

    private static void growLeg(
            WorldGenLevel world,
            BlockPos.MutableBlockPos mutable,
            RandomSource random,
            int x,
            int z,
            int crownY,
            int direction
    ) {
        int length = LEG_LENGTH_MIN + random.nextInt(LEG_LENGTH_EXTRA);
        int y = crownY;

        for (int step = 1; step <= length; ++step) {
            x += STEP_X[direction];
            z += STEP_Z[direction];

            if (step % 2 == 0) {
                --y;
            }

            int ground = groundAt(world, mutable, x, z);

            if (ground == NO_GROUND) {
                return;
            }

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

    /// Muddy roots only on dry ground. That block has no waterlogged state.
    private static boolean placeRoot(WorldGenLevel world, BlockPos.MutableBlockPos pos, boolean onGround) {
        BlockState existing = world.getBlockState(pos);
        boolean inWater = existing.is(Blocks.WATER);

        if (!existing.isAir() && !inWater) {
            return false;
        }

        BlockState root = onGround && !inWater
                ? Blocks.MUDDY_MANGROVE_ROOTS.defaultBlockState()
                : Blocks.MANGROVE_ROOTS.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, inWater);

        world.setBlock(pos, root, 2);
        return true;
    }

    private static int groundAt(WorldGenLevel world, BlockPos.MutableBlockPos mutable, int x, int z) {
        int top = world.getHeight(Heightmap.Types.OCEAN_FLOOR, x, z);

        for (int y = top - 1; y >= top - SCAN_DOWN; --y) {
            BlockState state = world.getBlockState(mutable.set(x, y, z));

            if (state.is(BlockTags.LOGS)) {
                return NO_GROUND;
            }

            if (state.is(BlockTags.LEAVES) || !FeatureHelper.blocksMotion(state)) {
                continue;
            }

            return isMarshGround(state) ? y + 1 : NO_GROUND;
        }

        return NO_GROUND;
    }

    private static boolean isMarshGround(BlockState state) {
        return state.is(Blocks.GRASS_BLOCK)
                || state.is(Blocks.DIRT)
                || state.is(Blocks.COARSE_DIRT)
                || state.is(Blocks.ROOTED_DIRT)
                || state.is(Blocks.MOSS_BLOCK)
                || state.is(Blocks.MUD);
    }

    private static boolean waterWithinReach(
            WorldGenLevel world,
            BlockPos.MutableBlockPos mutable,
            int x,
            int y,
            int z
    ) {
        for (int dx = -WATER_REACH; dx <= WATER_REACH; ++dx) {
            for (int dz = -WATER_REACH; dz <= WATER_REACH; ++dz) {
                for (int dy = -1; dy <= 1; ++dy) {
                    if (world.getBlockState(mutable.set(x + dx, y + dy, z + dz)).is(Blocks.WATER)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
