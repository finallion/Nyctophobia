package com.lion.nyctophobia.config;


import com.lion.nyctophobia.config.annotations.Description;
import java.util.*;
import net.minecraft.resources.Identifier;

public class NyctophobiaConfig implements Config {

    private static final int CONFIG_VERSION = 2;

    @Description("""
             Bookkeeping, not a setting. It records which defaults this file has already been brought
             up to date with. A file written before this existed reads back as 0.
            
            """)
    public int configVersion = 0;

    @Description("""
             Welcome to the Nyctophobia Config!
             
             Configure the weight of the biomes by changing "nyctophobiaBiomeWeight".
             Configure which minecraft biomes can be replaced by Nyctophobia Biomes.
             A full list of all the biomes can be found here https:minecraft.fandom.com/wiki/Biome#Biome_IDs.
             A biome from another mod needs its full id, for example "biomesoplenty:bayou".
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

    public boolean biomeFogSpawn(Identifier id) {
        return getFog(id).enabled;
    }


    public BiomeConfigEntry getBiome(Identifier id) {
        for (Map.Entry<String, BiomeConfigEntry> entry : biomeConfigEntries.entrySet()) {
            if (entry.getKey().equals(id.getPath())) {
                return entry.getValue();
            }
        }
        throw new NullPointerException("Tried BiomeConfigEntry with id: " + id + ", but it was null!");
    }


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

        biomeConfigEntries.putIfAbsent("haunted_forest", BiomeConfigEntry.of(Arrays.asList("windswept_forest", "taiga")));
        biomeConfigEntries.putIfAbsent("haunted_lakes", BiomeConfigEntry.of(Arrays.asList("swamp")));
        biomeConfigEntries.putIfAbsent("eroded_haunted_forest", BiomeConfigEntry.of(Arrays.asList("meadow")));
        biomeConfigEntries.putIfAbsent("ancient_dead_coral_reef", BiomeConfigEntry.of(Arrays.asList("beach")));
        biomeConfigEntries.putIfAbsent("deep_dark_forest", BiomeConfigEntry.of(Arrays.asList("dark_forest")));
        biomeConfigEntries.putIfAbsent("marshlands", BiomeConfigEntry.of(Arrays.asList("mangrove_swamp", "birch_forest")));
        biomeConfigEntries.putIfAbsent("burnt_forest", BiomeConfigEntry.of(Arrays.asList("old_growth_pine_taiga", "old_growth_spruce_taiga", "windswept_hills")));

        biomeFogConfigEntries.putIfAbsent("haunted_forest_fog", BiomeFogConfigEntry.of(0.35F, 63, 148));
        biomeFogConfigEntries.putIfAbsent("haunted_lakes_fog", BiomeFogConfigEntry.of(0.3F, 63, 128));
        biomeFogConfigEntries.putIfAbsent("eroded_haunted_forest_fog", BiomeFogConfigEntry.of(0.35F, 93, 160));
        biomeFogConfigEntries.putIfAbsent("marshlands_fog", BiomeFogConfigEntry.of(0.25F, 55, 110));

        migrate();

        Config.super.save();
    }

    /// putIfAbsent never changes an entry, so each step adds a missing default once.
    private void migrate() {
        if (configVersion < 1) {
            addHost("haunted_forest", "taiga");
        }

        if (configVersion < 2) {
            addHost("marshlands", "birch_forest");
        }

        configVersion = CONFIG_VERSION;
    }

    private void addHost(String biome, String host) {
        BiomeConfigEntry entry = biomeConfigEntries.get(biome);

        if (entry == null || entry.replaceableBiomes.contains(host)) {
            return;
        }

        // A default list has a fixed size. Copy it before the add.
        List<String> hosts = new ArrayList<>(entry.replaceableBiomes);
        hosts.add(host);
        biomeConfigEntries.put(biome, BiomeConfigEntry.of(entry.canGenerate, hosts));
    }

}
