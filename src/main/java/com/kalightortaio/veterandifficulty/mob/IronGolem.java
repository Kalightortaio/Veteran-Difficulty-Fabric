package com.kalightortaio.veterandifficulty.mob;

import com.kalightortaio.veterandifficulty.interfaces.IEntityState;
import com.kalightortaio.veterandifficulty.systems.internal.EntityModifiers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;

public class IronGolem {

    public static void onLoad(MinecraftServer server, Entity entity) {
        if (entity instanceof IronGolemEntity golem && !((IEntityState) golem).getBooleanState(EntityModifiers._KEY)) {
            golem.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.3f);
            EntityModifiers.tagEntity(golem, server);
        }
    }

    public static void onTick(ServerWorld world) {
        world.getEntitiesByType(EntityType.IRON_GOLEM, golem -> true).stream().forEach(golem -> {
            golem.heal(3.2F);
        });
    }
}
