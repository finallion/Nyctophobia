package com.lion.nyctophobia.init;


import com.lion.nyctophobia.Nyctophobia;
import com.lion.nyctophobia.world.biomes.AncientDeadCoralReef;
import com.lion.nyctophobia.world.biomes.DeepDarkForest;
import com.lion.nyctophobia.world.biomes.HauntedForestBiomes;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class NBiomes {
    /// Keyed by path so config entries, surface rules and the biome provider can all look a biome up
    /// by the same string the config file uses. Insertion ordered to keep generated output stable.
    private static final Map<String, RegistryKey<Biome>> BY_PATH = new LinkedHashMap<>();

    public static final RegistryKey<Biome> ERODED_HAUNTED_FOREST_KEY = registerBiomeKeys("eroded_haunted_forest");
    public static final RegistryKey<Biome> HAUNTED_LAKES_KEY = registerBiomeKeys("haunted_lakes");
    public static final RegistryKey<Biome> HAUNTED_FOREST_KEY = registerBiomeKeys("haunted_forest");
    public static final RegistryKey<Biome> ANCIENT_DEAD_CORAL_REEF_KEY = registerBiomeKeys("ancient_dead_coral_reef");
    public static final RegistryKey<Biome> DEEP_DARK_FOREST = registerBiomeKeys("deep_dark_forest");

    private static RegistryKey<Biome> registerBiomeKeys(String name) {
        RegistryKey<Biome> key = RegistryKey.of(RegistryKeys.BIOME, new Identifier(Nyctophobia.MOD_ID, name));
        BY_PATH.put(name, key);
        return key;
    }

    /// Every biome this mod adds, by the path its config entry uses.
    public static Map<String, RegistryKey<Biome>> byPath() {
        return Collections.unmodifiableMap(BY_PATH);
    }

    public static void init() {}


    public static void populate(FabricDynamicRegistryProvider.Entries entries) {
        entries.add(HAUNTED_FOREST_KEY, HauntedForestBiomes.createHauntedForest(entries));
        entries.add(ERODED_HAUNTED_FOREST_KEY, HauntedForestBiomes.createErodedHauntedForest(entries));
        entries.add(HAUNTED_LAKES_KEY, HauntedForestBiomes.createHauntedLakes(entries));
        entries.add(ANCIENT_DEAD_CORAL_REEF_KEY, AncientDeadCoralReef.createAncientDeadCoralReef(entries));
        entries.add(DEEP_DARK_FOREST, DeepDarkForest.createDeepDarkForest(entries));

    }
}
