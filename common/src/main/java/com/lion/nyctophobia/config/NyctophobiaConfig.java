package com.lion.nyctophobia.config;


import com.lion.nyctophobia.config.annotations.Description;
import net.minecraft.util.Identifier;


import java.util.*;

public class NyctophobiaConfig implements Config {
    @Description("""
             Welcome to the Nyctophobia Config!
             
             Configure the weight of the biomes by changing "nyctophobiaBiomeWeight".
             Configure which minecraft biomes can be replaced by Nyctophobia Biomes.
             A full list of all the biomes can be found here https:minecraft.fandom.com/wiki/Biome#Biome_IDs.
             Configure fog density and fog height.
             
            """)


    public final Map<String, BiomeConfigEntry> biomeConfigEntries = new HashMap<>();
    public final Map<String, BiomeFogConfigEntry> biomeFogConfigEntries = new HashMap<>();
    public final Map<String, Boolean> miscConfigEntries = new HashMap<>();

    public final Map<String, Integer> biomeGeneration = new HashMap<>();

    @Override
    public String getName() {
        return "nyctophobia_config";
    }

    @Override
    public String getExtension() {
        return "json5";
    }

    // generation booleans
    public boolean biomeFogSpawn(Identifier id) {
        return getFog(id).enabled;
    }


    // biome spawn config
    public BiomeConfigEntry getBiome(Identifier id) {
        for (Map.Entry<String, BiomeConfigEntry> entry : biomeConfigEntries.entrySet()) {
            if (entry.getKey().equals(id.getPath())) {
                return entry.getValue();
            }
        }
        throw new NullPointerException("Tried BiomeConfigEntry with id: " + id + ", but it was null!");
    }


    // fog spawn config
    public BiomeFogConfigEntry getFog(Identifier id) {
        for (Map.Entry<String, BiomeFogConfigEntry> entry : biomeFogConfigEntries.entrySet()) {
            if (entry.getKey().equals(id.getPath())) {
                return entry.getValue();
            }
        }
        throw new NullPointerException("Tried BiomeFogConfigEntry with id: " + id + ", but it was null!");
    }

    @Override
    public void save() {
        biomeGeneration.putIfAbsent("nyctophobiaBiomeWeight", 1);
        miscConfigEntries.putIfAbsent("useSoulLanternsInHauntedLakes", false);

        // A biome is only as common as the vanilla biomes it replaces. windswept_forest alone is rare
        // enough that the haunted forest could sit outside /locate range; taiga hosts it just as well,
        // since the biome is built entirely from spruce.
        // Two biomes must not claim the same host: replaceBiome maps one vanilla biome to one
        // replacement, so the second call would simply overwrite the first.
        biomeConfigEntries.putIfAbsent("haunted_forest", BiomeConfigEntry.of(Arrays.asList("windswept_forest", "taiga")));
        biomeConfigEntries.putIfAbsent("haunted_lakes", BiomeConfigEntry.of(Arrays.asList("swamp")));
        biomeConfigEntries.putIfAbsent("eroded_haunted_forest", BiomeConfigEntry.of(Arrays.asList("meadow")));
        biomeConfigEntries.putIfAbsent("ancient_dead_coral_reef", BiomeConfigEntry.of(Arrays.asList("beach")));
        biomeConfigEntries.putIfAbsent("deep_dark_forest", BiomeConfigEntry.of(Arrays.asList("dark_forest")));
        biomeConfigEntries.putIfAbsent("marshlands", BiomeConfigEntry.of(Arrays.asList("mangrove_swamp")));
        // The two old growth taigas alone are too rare to be found; windswept_hills carries the biome
        // often enough, and a fire on an exposed hillside is no stretch. old_growth_spruce_taiga moves
        // here from the haunted forest, which keeps taiga and stays common enough on its own.
        biomeConfigEntries.putIfAbsent("burnt_forest", BiomeConfigEntry.of(Arrays.asList("old_growth_pine_taiga", "old_growth_spruce_taiga", "windswept_hills")));

        biomeFogConfigEntries.putIfAbsent("haunted_forest_fog", BiomeFogConfigEntry.of(0.35F, 63, 148));
        biomeFogConfigEntries.putIfAbsent("haunted_lakes_fog", BiomeFogConfigEntry.of(0.3F, 63, 128));
        biomeFogConfigEntries.putIfAbsent("eroded_haunted_forest_fog", BiomeFogConfigEntry.of(0.35F, 93, 160));
        biomeFogConfigEntries.putIfAbsent("marshlands_fog", BiomeFogConfigEntry.of(0.25F, 55, 110));

        Config.super.save();
    }

}


