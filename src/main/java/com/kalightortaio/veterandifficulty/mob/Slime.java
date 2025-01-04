package com.kalightortaio.veterandifficulty.mob;

import com.kalightortaio.veterandifficulty.interfaces.IEntityState;
import com.kalightortaio.veterandifficulty.systems.internal.EntityModifiers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.server.MinecraftServer;

public class Slime {

    public static void onLoad(MinecraftServer server, Entity entity) {
        if (entity instanceof SlimeEntity slime && slime instanceof IEntityState slimeStates && !slimeStates.getBooleanState(EntityModifiers._KEY)) {
            slime.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, Integer.MAX_VALUE, 1, false, false, false));
            slime.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, Integer.MAX_VALUE, 5, false, false, false));
            EntityModifiers.tagEntity(slime, server);
        }
    }
}