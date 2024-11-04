package com.kalightortaio.veterandifficulty.mob;

import com.kalightortaio.veterandifficulty.interfaces.IEntityState;
import com.kalightortaio.veterandifficulty.systems.mechanics.EntityModifiers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Phantom {

    public static void despawnPhantoms(ServerWorld world) {
        for (PhantomEntity phantom : world.getEntitiesByType(EntityType.PHANTOM, phantom -> true)) {
            if (phantom.hasCustomName()) continue;
            
            IEntityState phantomState = (IEntityState) phantom;
            int newAge = (phantomState.getIntState("Age") + 1);
            phantomState.setIntState("Age", newAge);
            
            boolean playerNearby = world.getPlayers().stream().anyMatch(player -> player.squaredDistanceTo(phantom) < 64 * 64);
            if (playerNearby) continue; 

            int fiveMinutes = 300;
            if (newAge > fiveMinutes) {
                phantom.discard();
            }
        }
    }

    public static void canSpawn(ServerWorld world, MinecraftServer server, Entity entity) {
        if (entity instanceof EndermanEntity enderman && world.getRegistryKey() == World.END && !((IEntityState) enderman).getBooleanState(EntityModifiers._KEY)) {
            if (Math.random() < 0.15) {
                BlockPos spawnPos = enderman.getBlockPos().up(32);
                PhantomEntity phantom = new PhantomEntity(EntityType.PHANTOM, world);
                phantom.refreshPositionAndAngles(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), 0.0F, 0.0F);
                world.spawnEntity(phantom);
                ((IEntityState) phantom).setIntState("Age", 0);
            }
            EntityModifiers.tagEntity(enderman, server);
        }
    }
}