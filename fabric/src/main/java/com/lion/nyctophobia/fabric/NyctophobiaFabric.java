package com.lion.nyctophobia.fabric;

import com.lion.nyctophobia.Nyctophobia;
import net.fabricmc.api.ModInitializer;
import terrablender.api.TerraBlenderApi;

public class NyctophobiaFabric implements ModInitializer, TerraBlenderApi {

    @Override
    public void onInitialize() {
        Nyctophobia.init();
    }

    @Override
    public void onTerraBlenderInitialized() {
        Nyctophobia.onTerraBlenderInit();
    }
}
