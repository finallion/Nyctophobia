package com.lion.nyctophobia.world.trunk;

import com.lion.nyctophobia.init.NTrunkPlacer;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class DeepDarkOakTrunkPlacer extends TrunkPlacer {
    public static final Codec<DeepDarkOakTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) -> {
        return fillTrunkPlacerFields(instance).apply(instance, DeepDarkOakTrunkPlacer::new);
    });

    private static final int[][] ROOT_OFFSETS = {
            {-1, 0}, {-1, 1}, {0, -1}, {1, -1}, {2, 0}, {2, 1}, {0, 2}, {1, 2}
    };

    private static final int[][] BRANCH_CORNERS = {{1, 0}, {0, 1}, {1, 1}, {0, 0}};
    private static final Direction[][] BRANCH_DRIFTS = {
            {Direction.NORTH, Direction.EAST},
            {Direction.SOUTH, Direction.WEST},
            {Direction.EAST, Direction.SOUTH},
            {Direction.WEST, Direction.NORTH}
    };

    private static final int MAX_ROOT_DROP = 4;

    private static final int LOWER_BRANCHES = 8;
    private static final int UPPER_BRANCHES = 4;
    private static final int LOWER_BRANCH_LENGTH = 5;
    private static final int UPPER_BRANCH_LENGTH = 6;

    private static final int ROOT_MIN_HEIGHT = 5;
    private static final int ROOT_EXTRA_HEIGHT = 6;

    public DeepDarkOakTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
    }

    protected TrunkPlacerType<?> getType() {
        return NTrunkPlacer.DEEP_DARK_OAK_TRUNK_PLACER.get();
    }

    public List<FoliagePlacer.TreeNode> generate(
            TestableWorld world,
            BiConsumer<BlockPos, BlockState> replacer,
            Random random,
            int height,
            BlockPos startPos,
            TreeFeatureConfig config
    ) {
        List<FoliagePlacer.TreeNode> nodes = Lists.newArrayList();
        int x = startPos.getX();
        int y = startPos.getY();
        int z = startPos.getZ();

        for (int dx = 0; dx <= 1; ++dx) {
            for (int dz = 0; dz <= 1; ++dz) {
                setToDirt(world, replacer, random, new BlockPos(x + dx, y - 1, z + dz), config);
            }
        }
        placeRoots(world, replacer, random, config, x, y, z);

        int reached = placeTrunk(world, replacer, random, config, height, x, y, z);

        if (reached == 0) {
            return nodes;
        }

        int crownY = y + reached - 1;
        for (int i = 0; i < LOWER_BRANCHES; ++i) {
            growBranch(world, replacer, random, config, nodes, x, crownY, z, i / 2, LOWER_BRANCH_LENGTH, true, i % 2 == 1);
        }
        for (int i = 0; i < UPPER_BRANCHES; ++i) {
            growBranch(world, replacer, random, config, nodes, x, crownY, z, i, UPPER_BRANCH_LENGTH, false, false);
        }

        return nodes;
    }

    private void placeRoots(
            TestableWorld world,
            BiConsumer<BlockPos, BlockState> replacer,
            Random random,
            TreeFeatureConfig config,
            int x,
            int y,
            int z
    ) {
        for (int[] offset : ROOT_OFFSETS) {
            int rootX = x + offset[0];
            int rootZ = z + offset[1];
            int top = y + ROOT_MIN_HEIGHT + random.nextInt(ROOT_EXTRA_HEIGHT);
            int base = groundLevel(world, rootX, y, rootZ);

            setToDirt(world, replacer, random, new BlockPos(rootX, base - 1, rootZ), config);
            for (int rootY = base; rootY < top; ++rootY) {
                this.getAndSetState(world, replacer, random, new BlockPos(rootX, rootY, rootZ), config);
            }
        }
    }

    private static int groundLevel(TestableWorld world, int x, int baseY, int z) {
        for (int y = baseY; y >= baseY - MAX_ROOT_DROP; --y) {
            if (!TreeFeature.canReplace(world, new BlockPos(x, y - 1, z))) {
                return y;
            }
        }
        return baseY - MAX_ROOT_DROP;
    }

    private int placeTrunk(
            TestableWorld world,
            BiConsumer<BlockPos, BlockState> replacer,
            Random random,
            TreeFeatureConfig config,
            int height,
            int x,
            int y,
            int z
    ) {
        int placed = 0;
        for (int dy = 0; dy < height; ++dy) {
            BlockPos pos = new BlockPos(x, y + dy, z);

            if (!TreeFeature.isAirOrLeaves(world, pos)) {
                break;
            }

            this.getAndSetState(world, replacer, random, pos, config);
            this.getAndSetState(world, replacer, random, pos.east(), config);
            this.getAndSetState(world, replacer, random, pos.south(), config);
            this.getAndSetState(world, replacer, random, pos.east().south(), config);
            placed = dy + 1;
        }
        return placed;
    }

    /// east() returns a new position. Only move() mutates it.
    private void growBranch(
            TestableWorld world,
            BiConsumer<BlockPos, BlockState> replacer,
            Random random,
            TreeFeatureConfig config,
            List<FoliagePlacer.TreeNode> nodes,
            int x,
            int crownY,
            int z,
            int corner,
            int length,
            boolean driftEveryStep,
            boolean driftFixed
    ) {
        int[] start = BRANCH_CORNERS[corner];
        Direction[] drifts = BRANCH_DRIFTS[corner];
        BlockPos.Mutable pos = new BlockPos.Mutable(x + start[0], crownY, z + start[1]);

        for (int step = 0; step < length; ++step) {
            pos.move(Direction.UP);

            if (driftEveryStep) {
                pos.move(driftFixed ? drifts[0] : drifts[random.nextInt(2)]);
            } else if (random.nextInt(2) == 0) {
                pos.move(drifts[random.nextInt(2)]);
            }

            if (TreeFeature.canReplace(world, pos)) {
                this.getAndSetState(world, replacer, random, pos, config);
            }
        }

        nodes.add(new FoliagePlacer.TreeNode(pos.up(), 0, true));
    }
}
