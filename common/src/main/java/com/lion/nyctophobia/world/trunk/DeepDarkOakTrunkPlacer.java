package com.lion.nyctophobia.world.trunk;

import com.lion.nyctophobia.init.NTrunkPlacer;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import java.util.function.BiConsumer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class DeepDarkOakTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<DeepDarkOakTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
        return trunkPlacerParts(instance).apply(instance, DeepDarkOakTrunkPlacer::new);
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

    protected TrunkPlacerType<?> type() {
        return NTrunkPlacer.DEEP_DARK_OAK_TRUNK_PLACER.get();
    }

    public List<FoliagePlacer.FoliageAttachment> placeTrunk(
            LevelSimulatedReader world,
            BiConsumer<BlockPos, BlockState> replacer,
            RandomSource random,
            int height,
            BlockPos startPos,
            TreeConfiguration config
    ) {
        List<FoliagePlacer.FoliageAttachment> nodes = Lists.newArrayList();
        int x = startPos.getX();
        int y = startPos.getY();
        int z = startPos.getZ();

        for (int dx = 0; dx <= 1; ++dx) {
            for (int dz = 0; dz <= 1; ++dz) {
                setDirtAt(world, replacer, random, new BlockPos(x + dx, y - 1, z + dz), config);
            }
        }
        placeRoots(world, replacer, random, config, x, y, z);

        int reached = placeTrunk(world, replacer, random, config, height, x, y, z);

        if (reached == 0) {
            return nodes;
        }

        // Branches start at the height the trunk reached, so the crown never floats.
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
            LevelSimulatedReader world,
            BiConsumer<BlockPos, BlockState> replacer,
            RandomSource random,
            TreeConfiguration config,
            int x,
            int y,
            int z
    ) {
        for (int[] offset : ROOT_OFFSETS) {
            int rootX = x + offset[0];
            int rootZ = z + offset[1];
            int top = y + ROOT_MIN_HEIGHT + random.nextInt(ROOT_EXTRA_HEIGHT);
            int base = groundLevel(world, rootX, y, rootZ);

            setDirtAt(world, replacer, random, new BlockPos(rootX, base - 1, rootZ), config);
            for (int rootY = base; rootY < top; ++rootY) {
                this.placeLog(world, replacer, random, new BlockPos(rootX, rootY, rootZ), config);
            }
        }
    }

    private static int groundLevel(LevelSimulatedReader world, int x, int baseY, int z) {
        for (int y = baseY; y >= baseY - MAX_ROOT_DROP; --y) {
            if (!TreeFeature.validTreePos(world, new BlockPos(x, y - 1, z))) {
                return y;
            }
        }
        return baseY - MAX_ROOT_DROP;
    }

    private int placeTrunk(
            LevelSimulatedReader world,
            BiConsumer<BlockPos, BlockState> replacer,
            RandomSource random,
            TreeConfiguration config,
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

            this.placeLog(world, replacer, random, pos, config);
            this.placeLog(world, replacer, random, pos.east(), config);
            this.placeLog(world, replacer, random, pos.south(), config);
            this.placeLog(world, replacer, random, pos.east().south(), config);
            placed = dy + 1;
        }
        return placed;
    }

    private void growBranch(
            LevelSimulatedReader world,
            BiConsumer<BlockPos, BlockState> replacer,
            RandomSource random,
            TreeConfiguration config,
            List<FoliagePlacer.FoliageAttachment> nodes,
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
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(x + start[0], crownY, z + start[1]);

        for (int step = 0; step < length; ++step) {
            pos.move(Direction.UP);

            if (driftEveryStep) {
                pos.move(driftFixed ? drifts[0] : drifts[random.nextInt(2)]);
            } else if (random.nextInt(2) == 0) {
                pos.move(drifts[random.nextInt(2)]);
            }

            if (TreeFeature.validTreePos(world, pos)) {
                this.placeLog(world, replacer, random, pos, config);
            }
        }

        nodes.add(new FoliagePlacer.FoliageAttachment(pos.above(), 0, true));
    }
}
