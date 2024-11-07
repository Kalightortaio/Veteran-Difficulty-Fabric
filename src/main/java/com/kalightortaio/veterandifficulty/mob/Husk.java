package com.kalightortaio.veterandifficulty.mob;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.world.ServerWorld;

public class Husk {

    public static void onAttack(LivingEntity entity) {
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 260, 80, false, false, true));
    }

    public static void processAura(ServerWorld world) {
        world.getPlayers().forEach(player -> {
        boolean nearHusk = !world.getEntitiesByType(EntityType.HUSK, player.getBoundingBox().expand(8), husk -> true).isEmpty();
        
        if (nearHusk) {
            player.setSprinting(false);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 10, 1, false, false, true));
        }
    });
    }
}
