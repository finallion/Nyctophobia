package com.lion.nyctophobia.world.features.surfaceFeatures.coralFeatures;


import com.lion.nyctophobia.world.features.surfaceFeatures.DeadCoralFeature;
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;

public class DeadCoralClawFeature extends DeadCoralFeature {
    public DeadCoralClawFeature() {
        super();
    }

    protected boolean generateCoral(LevelAccessor world, RandomSource random, BlockPos pos, BlockState state) {
        if (!this.generateCoralPiece(world, random, pos, state)) {
            return false;
        } else {
            Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(world.getRandom());
            int i = random.nextInt(2) + 2;
            List<Direction> list = Lists.newArrayList(new Direction[]{direction, direction.getClockWise(), direction.getCounterClockWise()});
            Collections.shuffle(list, new java.util.Random());
            List<Direction> list2 = list.subList(0, i);
            Iterator var9 = list2.iterator();

            while(var9.hasNext()) {
                Direction direction2 = (Direction)var9.next();
                BlockPos.MutableBlockPos mutable = pos.mutable();
                int j = random.nextInt(2) + 1;
                mutable.move(direction2);
                int k;
                Direction direction3;

                if (direction2 == direction) {
                    direction3 = direction;
                    k = random.nextInt(3) + 2;
                } else {
                    mutable.move(Direction.UP);
                    Direction[] directions = new Direction[]{direction2, Direction.UP};
                    direction3 = (Direction) Util.getRandom(directions, world.getRandom());
                    k = random.nextInt(3) + 3;
                }

                int directions;
                for(directions = 0; directions < j && this.generateCoralPiece(world, random, mutable, state); ++directions) {
                    mutable.move(direction3);
                }

                mutable.move(direction3.getOpposite());
                mutable.move(Direction.UP);

                for(directions = 0; directions < k; ++directions) {
                    mutable.move(direction);

                    if (!this.generateCoralPiece(world, random, mutable, state)) {
                        break;
                    }

                    if (random.nextFloat() < 0.25F) {
                        mutable.move(Direction.UP);
                    }
                }
            }

            return true;
        }
    }
}
