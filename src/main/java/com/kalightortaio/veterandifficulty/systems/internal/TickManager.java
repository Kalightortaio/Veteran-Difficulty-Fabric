package com.kalightortaio.veterandifficulty.systems.internal;

import com.kalightortaio.veterandifficulty.mob.Drowned;
import com.kalightortaio.veterandifficulty.mob.MagmaCube;
import com.kalightortaio.veterandifficulty.mob.Phantom;
import com.kalightortaio.veterandifficulty.mob.Skeleton;

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
            if (gameTicks % 10 == 0) {
                runEvery10Ticks(world);
            }
            if (gameTicks % 20 == 0) {
                runEvery20Ticks(world);
            }
        }
    }

    private static void runEveryTick(ServerWorld world) {
        Drowned.dynamicSwimmingSpeed(world);
        MagmaCube.updateLavaRemoval();
    }

    private static void runEvery10Ticks(ServerWorld world) {
        Skeleton.updateSkeletonWeapons(world);
    }

    private static void runEvery20Ticks(ServerWorld world) {
        Phantom.despawnPhantoms(world);
    }
}
