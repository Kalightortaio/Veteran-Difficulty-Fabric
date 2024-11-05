package com.kalightortaio.veterandifficulty.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kalightortaio.veterandifficulty.mob.Creeper;

import net.minecraft.entity.mob.CreeperEntity;

@Mixin(CreeperEntity.class)
public class CreeperEntityMixin {

    @Inject(method = "explode", at = @At("HEAD"))
    private void onExplodeHead(CallbackInfo ci) {
        CreeperEntity creeper = (CreeperEntity) (Object) this;
        if (!creeper.getWorld().isClient()) {
            Creeper.markArea(creeper);
        }
    }
}
