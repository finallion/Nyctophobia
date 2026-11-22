package com.lion.nyctophobia.util;

import com.lion.nyctophobia.Nyctophobia;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;


public class NTags {
    public static void init() {}

    public static final TagKey<Block> DEAD_CORAL_BLOCKS = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Nyctophobia.MOD_ID, "dead_coral_blocks"));
    public static final TagKey<Block> DEAD_CORAL_PLANTS = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Nyctophobia.MOD_ID, "dead_coral_plants"));
    public static final TagKey<Block> DEAD_CORALS = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Nyctophobia.MOD_ID, "dead_corals"));
    public static final TagKey<Block> DEAD_WALL_CORALS = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(Nyctophobia.MOD_ID, "dead_wall_corals"));
    public static final TagKey<Biome> MUSHROOM_BIOMES = TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(Nyctophobia.MOD_ID, "has_structure/mushroom_biomes"));

}
