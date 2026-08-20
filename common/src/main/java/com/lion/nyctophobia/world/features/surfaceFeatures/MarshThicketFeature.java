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

/// Waist to head high scrub the marsh makes you walk around. Leaf blocks stop movement, so a thicket
/// is terrain rather than decoration: you break through it or you go the long way.
///
/// Mangrove leaves, not oak and not spruce. Spruce and birch carry a fixed colour and would stay the
/// green of another wood; azalea has no tint index at all. Mangrove takes the biome foliage colour, so
/// a thicket turns the same drab olive as the rest of the marsh - and it is the wood the roots in this
/// biome already come from.
///
/// The leaves are set persistent. A leaf block with no log within range decays, and there is no tree
/// here to keep it.
public class MarshThicketFeature extends Feature<DefaultFeatureConfig> {

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

    public MarshThicketFeature(Codec<DefaultFeatureConfig> configCodec) {
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
        int base = groundAt(world, mutable, x, z,
                world.getTopY(Heightmap.Type.OCEAN_FLOOR, x, z) + SCAN_UP,
                world.getTopY(Heightmap.Type.OCEAN_FLOOR, x, z) - SCAN_DOWN);
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
            // Tallest in the middle and ragged at the edge, so it reads as a clump rather than a wall.
            int height = Math.max(1, HEIGHT_MAX - (Math.abs(dx) + Math.abs(dz)) / 2 - random.nextInt(2));
            // Each column starts at its own ground, so a clump standing in a puddle grows out of the
            // water rather than resting on top of it.
            stack(world, mutable, x + dx, z + dz, levels[index(dx, dz)], height);
        }

        return true;
    }

    private static void stack(StructureWorldAccess world, BlockPos.Mutable mutable, int x, int z,
                              int base, int height) {
        for (int i = 0; i < height; ++i) {
            mutable.set(x, base + i, z);
            BlockState existing = world.getBlockState(mutable);
            boolean inWater = existing.isOf(Blocks.WATER);
            if (!existing.isAir() && !inWater) {
                return;
            }

            world.setBlockState(mutable, Blocks.MANGROVE_LEAVES.getDefaultState()
                    .with(Properties.PERSISTENT, true)
                    .with(Properties.WATERLOGGED, inWater), 2);
        }
    }

    /// The marsh surface plus the mud a puddle leaves behind, so a thicket may stand at the water's
    /// edge and grow out of the shallows.
    private static boolean isMarshGround(BlockState state) {
        return state.isOf(Blocks.GRASS_BLOCK)
                || state.isOf(Blocks.DIRT)
                || state.isOf(Blocks.COARSE_DIRT)
                || state.isOf(Blocks.ROOTED_DIRT)
                || state.isOf(Blocks.MOSS_BLOCK)
                || state.isOf(Blocks.MUD);
    }

    private static int groundAt(StructureWorldAccess world, BlockPos.Mutable mutable, int x, int z,
                                int top, int bottom) {
        for (int y = top; y >= bottom; --y) {
            if (world.getBlockState(mutable.set(x, y, z)).blocksMovement()) {
                return y + 1;
            }
        }
        return NO_GROUND;
    }

    private static int index(int dx, int dz) {
        return (dx + REACH) * SPAN + (dz + REACH);
    }
}
