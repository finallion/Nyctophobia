package com.lion.nyctophobia.neoforge;

import com.lion.nyctophobia.Nyctophobia;
import com.lion.nyctophobia.platform.neoforge.RegistryHelperImpl;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(Nyctophobia.MOD_ID)
public class NyctophobiaNeoForge {

    public NyctophobiaNeoForge(IEventBus modBus) {
        // Run this first. It triggers the static initialisers that fill the registers.
        Nyctophobia.init();

        RegistryHelperImpl.FEATURE_TYPES.register(modBus);
        RegistryHelperImpl.TRUNK_PLACER_TYPES.register(modBus);
        RegistryHelperImpl.STRUCTURE_TYPES.register(modBus);

        modBus.addListener(this::commonSetup);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(Nyctophobia::onTerraBlenderInit);
    }
}
