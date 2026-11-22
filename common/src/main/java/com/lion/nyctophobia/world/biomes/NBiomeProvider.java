package com.lion.nyctophobia.world.biomes;

import com.lion.nyctophobia.Nyctophobia;
import com.lion.nyctophobia.config.BiomeConfigEntry;
import com.lion.nyctophobia.init.NBiomes;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;


public class NBiomeProvider extends Region {

    public NBiomeProvider(Identifier name, int overworldWeight) {
        super(name, RegionType.OVERWORLD, overworldWeight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            NBiomes.byPath().forEach((path, biome) -> {
                BiomeConfigEntry entry = Nyctophobia.config.biomeConfigEntries.get(path);

                if (entry == null || !entry.canGenerate) {
                    return;
                }

                for (String host : entry.replaceableBiomes) {
                    Identifier hostId = hostId(host);

                    if (hostId == null) {
                        Nyctophobia.LOGGER.error("Malformed biome id \"" + host + "\" configured for " + path + ", skipping it.");
                        continue;
                    }

                    ResourceKey<Biome> hostKey = ResourceKey.create(Registries.BIOME, hostId);

                    if (!registry.containsKey(hostKey)) {
                        Nyctophobia.LOGGER.error("Unknown biome \"" + hostId + "\" configured for " + path + ", skipping it.");
                        continue;
                    }

                    builder.replaceBiome(hostKey, biome);
                }
            });
        });
    }

    /// A bare path means a vanilla biome. A full id reaches a biome from another mod.
    private static Identifier hostId(String host) {
        return Identifier.tryParse(host.indexOf(':') < 0 ? Identifier.DEFAULT_NAMESPACE + ":" + host : host);
    }
}
