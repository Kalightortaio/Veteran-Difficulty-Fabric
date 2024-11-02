package com.kalightortaio.veterandifficulty.systems.internal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.kalightortaio.veterandifficulty.interfaces.IEntityState;
import com.kalightortaio.veterandifficulty.mob.Drowned;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class TickManager {

    private static final Map<BlockPos, Long> scheduledLavaRemovals = new HashMap<>();
    private static final Map<BlockPos, ServerWorld> worldReferences = new HashMap<>();

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> onTick(server));
    }

    private static void onTick(MinecraftServer server) {
        for (ServerWorld world : server.getWorlds()) {
            long gameTicks = world.getTime();
            runEveryTick(world, gameTicks);
            if (gameTicks % 20 == 0) {
                runEvery20Ticks(world);
            }
        }
    }

    private static void runEveryTick(ServerWorld world, long currentTime) {
        Drowned.dynamicSwimmingSpeed(world);
        updateLavaRemoval();
    }

    private static void runEvery20Ticks(ServerWorld world) {
        despawnPhantoms(world);
        //TorchMarkers.processMarkers(world);
    }

    public static void despawnPhantoms(ServerWorld world) {
        for (Entity entity : world.getEntitiesByType(EntityType.PHANTOM, phantom -> true)) {
            if (entity instanceof PhantomEntity phantom) {
                if (phantom.hasCustomName()) continue;
                
                IEntityState phantomState = (IEntityState) phantom;
                int newAge = (phantomState.getIntState("Age") + 1);
                phantomState.setIntState("Age", newAge);

                int tenMinutes = 600;
                if (newAge > tenMinutes) {
                    phantom.discard();
                }
            }
        }
    }

    public static void updateLavaRemoval() {
        Iterator<Map.Entry<BlockPos, Long>> iterator = scheduledLavaRemovals.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<BlockPos, Long> entry = iterator.next();
            BlockPos pos = entry.getKey();
            long scheduledTime = entry.getValue();
            ServerWorld currentWorld = worldReferences.get(pos); 
            
            if (currentWorld != null && currentWorld.getTime() >= scheduledTime) {
                if (currentWorld.getBlockState(pos).isOf(Blocks.LAVA)) {
                    currentWorld.setBlockState(pos, Blocks.AIR.getDefaultState());
                }
                iterator.remove();
                worldReferences.remove(pos);
            }
        }
    }

    public static void scheduleLavaRemoval(ServerWorld world, BlockPos pos, long delay) {
        long executeTime = world.getTime() + delay;
        scheduledLavaRemovals.put(pos, executeTime);
        worldReferences.put(pos, world);
    }
}
