package com.lion.nyctophobia.fabric;

import com.lion.nyctophobia.Nyctophobia;
import com.lion.nyctophobia.client.NFog;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

public class NyctophobiaFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
                dispatcher.register(ClientCommands.literal(Nyctophobia.MOD_ID)
                        .then(ClientCommands.literal("fog")
                                .then(ClientCommands.literal("on").executes(context -> set(context.getSource(), true)))
                                .then(ClientCommands.literal("off").executes(context -> set(context.getSource(), false)))
                                .executes(context -> {
                                    NFog.toggle();
                                    return report(context.getSource());
                                }))));
    }

    private static int set(FabricClientCommandSource source, boolean value) {
        NFog.setEnabled(value);
        return report(source);
    }

    private static int report(FabricClientCommandSource source) {
        source.sendFeedback(NFog.feedback());
        return 1;
    }
}
