package com.kalightortaio.veterandifficulty.systems.internal;

import com.kalightortaio.veterandifficulty.mob.Drowned;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

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

    public static void scheduleLavaRemoval(ServerWorld world, BlockPos pos, long delay) {
        long executeTime = world.getTime() + delay;

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            if (world.getTime() >= executeTime && world.getBlockState(pos).isOf(Blocks.LAVA)) {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        });
        // TODO: Handle edgecase for scheduleLavaRemoval getting lost on world unload.
    }
}
