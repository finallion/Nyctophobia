package com.lion.nyctophobia.world.biomes;

import com.lion.nyctophobia.Nyctophobia;
import com.lion.nyctophobia.config.BiomeConfigEntry;
import com.lion.nyctophobia.init.NBiomes;
import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;


public class NBiomeProvider extends Region {

    public NBiomeProvider(Identifier name, int overworldWeight) {
        super(name, RegionType.OVERWORLD, overworldWeight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
        Map<String, RegistryKey<Biome>> vanillaByPath = keysByPath(registry);

        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            NBiomes.byPath().forEach((path, biome) -> {
                BiomeConfigEntry entry = Nyctophobia.config.biomeConfigEntries.get(path);
                if (entry == null || !entry.canGenerate) {
                    return;
                }

                for (String host : entry.replaceableBiomes) {
                    RegistryKey<Biome> hostKey = vanillaByPath.get(host);
                    if (hostKey == null) {
                        // Skipping keeps one typo from taking the whole region down inside TerraBlender,
                        // far away from the config line that caused it.
                        Nyctophobia.LOGGER.error("Unknown biome \"" + host + "\" configured for " + path + ", skipping it.");
                        continue;
                    }
                    builder.replaceBiome(hostKey, biome);
                }
            });
        });
    }

    /// Resolved once per region build. The previous lookup walked the whole biome registry for every
    /// single config entry.
    private static Map<String, RegistryKey<Biome>> keysByPath(Registry<Biome> registry) {
        Map<String, RegistryKey<Biome>> byPath = new HashMap<>();
        for (Map.Entry<RegistryKey<Biome>, Biome> entry : registry.getEntrySet()) {
            byPath.put(entry.getKey().getValue().getPath(), entry.getKey());
        }
        return byPath;
    }
}
