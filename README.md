# Nyctophobia

Spooky, foggy, creepy and dark biomes for the Minecraft Overworld. Formerly known as
*The Graveyard Biomes*.

Nyctophobia adds atmospheric surface biomes built entirely from vanilla blocks - no new
blocks, items or mobs. Each biome comes with its own trees, vegetation, surface rules and
a dense volumetric fog.

## Biomes

- **Haunted Forest** - a dark, misty oak forest
- **Eroded Haunted Forest** - its weathered, broken counterpart
- **Haunted Lakes** - murky water between dead trees
- **Deep Dark Forest** - a dense dark oak forest with a closed canopy
- **Burnt Forest** - a thin stand of charred spruce
- **Marshlands** - an open wetland with pools, thickets and crooked trees
- **Ancient Dead Coral Reef** - a bleached reef ocean; bone meal revives the dead coral

## Requirements

This version targets **Minecraft 1.21.8** and runs on **Fabric** and **NeoForge**.

- [TerraBlender](https://modrinth.com/mod/terrablender) (required on both loaders - all
  biome injection runs through it)
- [Fabric API](https://modrinth.com/mod/fabric-api) (Fabric only)

## Configuration

The config file `config/nyctophobia_config.json5` controls the generation weight and the
replaced vanilla biome for every Nyctophobia biome, as well as the fog density and its
height range. Setting a biome's weight to zero disables it.

The fog can also be toggled in-game with the client command `/nyctophobia fog on|off`.

## Building

The project is an Architectury multiloader build with a shared `common` module:

```
gradlew :fabric:build :neoforge:build
```

Data generation runs through Fabric via `gradlew :fabric:runDatagen`; the output under
`common/src/generated/resources` is committed.

## License

Licensed under the [GNU General Public License v3.0](LICENSE).
