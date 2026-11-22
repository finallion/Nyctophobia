package com.lion.nyctophobia.world.features.surfaceFeatures.coralFeatures;

import com.lion.nyctophobia.world.features.surfaceFeatures.DeadCoralFeature;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class DeadCoralTreeFeature extends DeadCoralFeature {
    public DeadCoralTreeFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    protected boolean generateCoral(LevelAccessor world, RandomSource random, BlockPos pos, BlockState state) {
        BlockPos.MutableBlockPos mutable = pos.mutable();
        int i = random.nextInt(3) + 1;

        for(int j = 0; j < i; ++j) {
            if (!this.generateCoralPiece(world, random, mutable, state)) {
                return true;
            }

            mutable.move(Direction.UP);
        }

        BlockPos j = mutable.immutable();
        int k = random.nextInt(3) + 2;
        List<Direction> list = Lists.newArrayList(Direction.Plane.HORIZONTAL);
        Collections.shuffle(list, new java.util.Random());
        List<Direction> list2 = list.subList(0, k);
        Iterator var11 = list2.iterator();

        while(var11.hasNext()) {
            Direction direction = (Direction)var11.next();
            mutable.set(j);
            mutable.move(direction);
            int l = random.nextInt(5) + 2;
            int m = 0;

            for(int n = 0; n < l && this.generateCoralPiece(world, random, mutable, state); ++n) {
                ++m;
                mutable.move(Direction.UP);

                if (n == 0 || m >= 2 && random.nextFloat() < 0.25F) {
                    mutable.move(direction);
                    m = 0;
                }
            }
        }

        return true;
    }
}