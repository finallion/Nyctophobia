# Nyctophobia

Spooky, foggy, creepy and dark biomes for your Minecraft world. Formerly known as
*The Graveyard Biomes*.

Nyctophobia adds surface biomes built entirely from vanilla blocks - no new blocks, items or
mobs. The biomes use their own trees, features, surface rules and fog.

## Biomes

- **Haunted Forest** - dark spruce forest with fog, cobwebs and fallen trees
- **Eroded Haunted Forest** - thinned-out forest with skeletons and wither skeletons
- **Haunted Lakes** - foggy swamp with blood red water and candles over lily pads
- **Deep Dark Forest** - dark oak forest with giant mushrooms
- **Burnt Forest** - sparse stripped spruces, fallen logs and lichen
- **Marshlands** - treeless wetland with pools, mud banks, mangrove roots and thickets
- **Dead Coral Reef** - bleached coral reef on the coast; bone meal grows coral there like in a warm ocean

## Versions

This branch targets **Minecraft 1.21.8** and runs on **Fabric** and **NeoForge**.

| Branch | Minecraft | Loaders |
| --- | --- | --- |
| `1.20.1` | 1.20.1 | Fabric, Forge |
| `1.21.1` | 1.21.1 | Fabric, NeoForge |
| `1.21.8` | 1.21.8 | Fabric, NeoForge |
| `1.21.11` | 1.21.11 | Fabric, NeoForge |
| `26.1` | 26.1.2 | Fabric, NeoForge |
| `26.2` | 26.2 | Fabric, NeoForge |
| `26.3` | 26.3 | Fabric, NeoForge |

- [TerraBlender](https://modrinth.com/mod/terrablender) (required on every loader - all
  biome injection runs through it)
- [Fabric API](https://modrinth.com/mod/fabric-api) (Fabric only)

## Configuration

The config file `config/nyctophobia_config.json5` controls the replaced vanilla biomes for
every Nyctophobia biome, as well as the fog density and its height range. For each biome it
also sets whether it generates, and `nyctophobiaBiomeWeight` sets the weight of all of them.

The fog can also be toggled in-game with the client command `/nyctophobia fog on|off`.

## Building

The project is an Architectury multiloader build with a shared `common` module:

```
gradlew :fabric:build :neoforge:build
```

Data generation runs through Fabric via `gradlew :fabric:runDatagenClient`; the output under
`common/src/generated/resources` is committed.

## License

Licensed under the [GNU General Public License v3.0](LICENSE).
