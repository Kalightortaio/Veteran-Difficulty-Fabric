package com.kalightortaio.veterandifficulty.effect;

import com.kalightortaio.veterandifficulty.interfaces.IEntityState;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.world.ServerWorld;

public class ScaldingEffect extends StatusEffect{
    protected ScaldingEffect() {
		super(StatusEffectCategory.HARMFUL, 0xcf1515);
	}

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier) {
        IEntityState entityState = (IEntityState) entity;

        float currentHealth = entity.getHealth();
        float previousHealth = entityState.getFloatState("previousHealth");

        if (previousHealth == 0) {
            entityState.setFloatState("previousHealth", currentHealth);
            previousHealth = currentHealth;
        }

        if (currentHealth > previousHealth) {
            entity.setHealth(previousHealth);
        } else {
            entityState.setFloatState("previousHealth", currentHealth);
        }

        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
