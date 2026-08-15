package com.lion.nyctophobia.forge;

import com.lion.nyctophobia.Nyctophobia;
import com.lion.nyctophobia.platform.forge.RegistryHelperImpl;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Nyctophobia.MOD_ID)
public class NyctophobiaForge {

    public NyctophobiaForge() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Must run before the DeferredRegisters are attached: it triggers the static initialisers in
        // the init classes, and those are what fill the registers.
        Nyctophobia.init();

        RegistryHelperImpl.FEATURES.register(modBus);
        RegistryHelperImpl.TRUNK_PLACER_TYPES.register(modBus);
        RegistryHelperImpl.STRUCTURE_TYPES.register(modBus);

        modBus.addListener(this::commonSetup);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(Nyctophobia::onTerraBlenderInit);
    }
}
