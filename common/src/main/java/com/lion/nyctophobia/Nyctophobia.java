package com.lion.nyctophobia;

import com.lion.nyctophobia.config.NyctophobiaConfig;
import com.lion.nyctophobia.config.OmegaConfig;
import com.lion.nyctophobia.init.*;
import com.lion.nyctophobia.util.NTags;
import com.lion.nyctophobia.world.biomes.NBiomeProvider;
import com.lion.nyctophobia.world.surfacerules.NSurfaceRules;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

public class Nyctophobia {
    public static final String MOD_ID = "nyctophobia";
    public static final NyctophobiaConfig config = OmegaConfig.register(NyctophobiaConfig.class);
    public static final Logger LOGGER = LogManager.getLogger();

    public static void init() {
        NTrunkPlacer.init();
        NFeatures.init();
        NTags.init();
        NConfiguredFeatures.init();
        NPlacedFeatures.init();
        NBiomes.init();
        NStructures.registerStructureFeatures();
    }

    /// Called from each loader once TerraBlender is ready: through the terrablender entrypoint on
    /// Fabric, from FMLCommonSetupEvent on Forge.
    public static void onTerraBlenderInit() {
        Regions.register(new NBiomeProvider(new Identifier(MOD_ID, "biome_provider"),
                config.biomeGeneration.get("nyctophobiaBiomeWeight")));
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, NSurfaceRules.makeRules());
    }
}
