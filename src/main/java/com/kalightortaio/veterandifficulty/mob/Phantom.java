package com.kalightortaio.veterandifficulty.mob;

import com.kalightortaio.veterandifficulty.interfaces.IEntityState;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.server.world.ServerWorld;

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
}
