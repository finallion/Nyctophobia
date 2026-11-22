package com.lion.nyctophobia.init;


import com.lion.nyctophobia.Nyctophobia;
import com.lion.nyctophobia.world.biomes.AncientDeadCoralReef;
import com.lion.nyctophobia.world.biomes.BurntForest;
import com.lion.nyctophobia.world.biomes.DeepDarkForest;
import com.lion.nyctophobia.world.biomes.HauntedForestBiomes;
import com.lion.nyctophobia.world.biomes.Marshlands;
import com.lion.nyctophobia.data.NRegistryContext;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class NBiomes {
    // Keep insertion order, so generated output stays stable.
    private static final Map<String, RegistryKey<Biome>> BY_PATH = new LinkedHashMap<>();

    public static final RegistryKey<Biome> ERODED_HAUNTED_FOREST_KEY = registerBiomeKeys("eroded_haunted_forest");
    public static final RegistryKey<Biome> HAUNTED_LAKES_KEY = registerBiomeKeys("haunted_lakes");
    public static final RegistryKey<Biome> HAUNTED_FOREST_KEY = registerBiomeKeys("haunted_forest");
    public static final RegistryKey<Biome> ANCIENT_DEAD_CORAL_REEF_KEY = registerBiomeKeys("ancient_dead_coral_reef");
    public static final RegistryKey<Biome> DEEP_DARK_FOREST = registerBiomeKeys("deep_dark_forest");
    public static final RegistryKey<Biome> MARSHLANDS_KEY = registerBiomeKeys("marshlands");
    public static final RegistryKey<Biome> BURNT_FOREST_KEY = registerBiomeKeys("burnt_forest");

    private static RegistryKey<Biome> registerBiomeKeys(String name) {
        RegistryKey<Biome> key = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(Nyctophobia.MOD_ID, name));
        BY_PATH.put(name, key);
        return key;
    }

    public static Map<String, RegistryKey<Biome>> byPath() {
        return Collections.unmodifiableMap(BY_PATH);
    }

    public static void init() {}


    public static void populate(NRegistryContext<Biome> entries) {
        entries.add(HAUNTED_FOREST_KEY, HauntedForestBiomes.createHauntedForest(entries));
        entries.add(ERODED_HAUNTED_FOREST_KEY, HauntedForestBiomes.createErodedHauntedForest(entries));
        entries.add(HAUNTED_LAKES_KEY, HauntedForestBiomes.createHauntedLakes(entries));
        entries.add(ANCIENT_DEAD_CORAL_REEF_KEY, AncientDeadCoralReef.createAncientDeadCoralReef(entries));
        entries.add(DEEP_DARK_FOREST, DeepDarkForest.createDeepDarkForest(entries));
        entries.add(MARSHLANDS_KEY, Marshlands.createMarshlands(entries));
        entries.add(BURNT_FOREST_KEY, BurntForest.createBurntForest(entries));

    }
}
