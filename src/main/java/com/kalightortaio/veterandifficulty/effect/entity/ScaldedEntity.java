package com.kalightortaio.veterandifficulty.effect.entity;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.LivingEntity;

public class ScaldedEntity {

    public static void onHeal(LivingEntity scalded, float health, CallbackInfo ci) {
        if (health > scalded.getHealth()) {
            ci.cancel();
        }
    }
}
