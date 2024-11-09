package com.kalightortaio.veterandifficulty.effect.entity;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;

public class AnchoredEntity {

    public static void onSwim(LivingEntity anchored, CallbackInfo ci) {
        if (anchored.isTouchingWater()) anchored.setSwimming(false);
    }

    public static void onMoveInFluid(LivingEntity anchored, Vec3d motion, CallbackInfoReturnable<Vec3d> cir) {
        cir.setReturnValue(new Vec3d(motion.x, -0.2, motion.z));
    }
}
