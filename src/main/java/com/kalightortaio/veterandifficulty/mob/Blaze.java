package com.kalightortaio.veterandifficulty.mob;

import com.kalightortaio.veterandifficulty.effect.ModEffects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;

public class Blaze {

    public static void onAttack(LivingEntity scalded) {
        scalded.addStatusEffect(new StatusEffectInstance(ModEffects.SCALDING, 300, 0, false, true, true));
    }
}