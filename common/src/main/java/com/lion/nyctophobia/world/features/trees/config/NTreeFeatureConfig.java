package com.lion.nyctophobia.world.features.trees.config;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class NTreeFeatureConfig {
    public static final MapCodec<NTreeFeatureConfig> MAP_CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
            BlockState.CODEC.fieldOf("wood_state").forGetter((config) -> config.woodState),
            BlockState.CODEC.fieldOf("leaf_state").forGetter((config) -> config.leafState)
    ).apply(instance, NTreeFeatureConfig::new));

    public final BlockState woodState;
    public final BlockState leafState;

    public NTreeFeatureConfig(BlockState woodState, BlockState leafState) {
        // Fallen trees need the axis property.
        if (!woodState.getProperties().contains(BlockStateProperties.AXIS)) {
            throw new IllegalArgumentException("Tree wood state " + woodState + " has no axis property");
        }

        this.woodState = woodState;

        if (leafState.getProperties().contains(BlockStateProperties.DISTANCE)) {
            this.leafState = leafState.setValue(BlockStateProperties.DISTANCE, 1);
        } else {
            this.leafState = leafState;
        }
    }
}
