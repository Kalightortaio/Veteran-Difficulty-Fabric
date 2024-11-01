package com.kalightortaio.veterandifficulty.systems.internal;

import com.kalightortaio.veterandifficulty.mob.Drowned;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;

public class TickManager {

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> onTick(server));
    }

    private static void onTick(MinecraftServer server) {
        for (ServerWorld world : server.getWorlds()) {
            long gameTicks = world.getTime();

            runEveryTick(world);

            if (gameTicks % 20 == 0) {
                runEvery20Ticks(world);
            }
        }
    }

    private static void runEveryTick(ServerWorld world) {
        Drowned.dynamicSwimmingSpeed(world);
    }

    private static void runEvery20Ticks(ServerWorld world) {
        //TorchMarkers.processMarkers(world);
    }
}
