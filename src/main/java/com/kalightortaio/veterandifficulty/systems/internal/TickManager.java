package com.kalightortaio.veterandifficulty.systems.internal;

import com.kalightortaio.veterandifficulty.mob.Creeper;
import com.kalightortaio.veterandifficulty.mob.Drowned;
import com.kalightortaio.veterandifficulty.mob.ElderGuardian;
import com.kalightortaio.veterandifficulty.mob.Husk;
import com.kalightortaio.veterandifficulty.mob.IronGolem;
import com.kalightortaio.veterandifficulty.mob.MagmaCube;
import com.kalightortaio.veterandifficulty.mob.Phantom;
import com.kalightortaio.veterandifficulty.mob.Skeleton;
import com.kalightortaio.veterandifficulty.mob.Vex;
import com.kalightortaio.veterandifficulty.mob.Wolf;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;

public class TickManager {

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> onTick(server));
    }

    private static void onTick(MinecraftServer server) {
        for (ServerWorld world : server.getWorlds()) {
            long time = world.getTimeOfDay();
            runEveryTick(world, time);
            if (time % 10 == 0) {
                runEvery10Ticks(world, time);
            }
            if (time % 20 == 0) {
                runEvery20Ticks(world, time);
            }
        }
    }

    private static void runEveryTick(ServerWorld world, long time) {
        Drowned.dynamicSwimmingSpeed(world);
        MagmaCube.updateLavaRemoval();
        Vex.updateAnimations();
        Creeper.onTick(world);
        Husk.processAura(world);
    }

    private static void runEvery10Ticks(ServerWorld world, long time) {
        Skeleton.updateSkeletonWeapons(world);
        Wolf.processHunt(world, time);
    }

    private static void runEvery20Ticks(ServerWorld world, long time) {
        Phantom.despawnPhantoms(world);
        ElderGuardian.breakBlocks(world);
        IronGolem.onTick(world);
    }
}
