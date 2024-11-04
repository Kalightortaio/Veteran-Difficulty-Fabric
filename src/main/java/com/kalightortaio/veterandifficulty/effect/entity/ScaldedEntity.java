package com.kalightortaio.veterandifficulty.effect.entity;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kalightortaio.veterandifficulty.effect.ModEffects;

import net.minecraft.entity.LivingEntity;

public class ScaldedEntity {

    public static void onHeal(LivingEntity scalded, float health, CallbackInfo ci) {
        if (ModEffects.SCALDING != null && scalded.hasStatusEffect(ModEffects.SCALDING)) {
            if (health > scalded.getHealth()) {
                ci.cancel();
            }
        }
    }

}
