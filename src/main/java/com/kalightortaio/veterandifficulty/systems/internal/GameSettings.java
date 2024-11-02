package com.kalightortaio.veterandifficulty.systems.internal;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameRules;

public class GameSettings {
    public static void register() {
        ServerWorldEvents.LOAD.register((MinecraftServer server, ServerWorld world) -> {
            world.getGameRules().get(GameRules.DO_INSOMNIA).set(false, server);
        });
    }
}
