package com.kalightortaio.veterandifficulty.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kalightortaio.veterandifficulty.mixin.accessor.ItemEntityAccessor;

import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {

    @Inject(method = "setStack", at = @At("HEAD"))
    private void applyFireImmunityAfterStackSet(ItemStack stack, CallbackInfo ci) {
        if (!(stack.isOf(Items.MAGMA_CREAM))) return;
        ItemEntity itemEntity = (ItemEntity) (Object) this;
        ((ItemEntityAccessor) itemEntity).setHealth(20);
        itemEntity.extinguish();
    }

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void preventMagmaCreamBurning(CallbackInfo ci) {
        ItemEntity itemEntity = (ItemEntity) (Object) this;
        if (!(itemEntity.getStack().getItem() == Items.MAGMA_CREAM)) return;
        if ((itemEntity.isInLava() || itemEntity.isOnFire())) {
            ((ItemEntityAccessor) itemEntity).setHealth(20);
            itemEntity.extinguish();
        }
    }

    @Inject(method = "shouldPlayBurnSoundInLava", at = @At("HEAD"), cancellable = true)
    private void disableBurnSoundInLava(CallbackInfoReturnable<Boolean> cir) {
        ItemEntity itemEntity = (ItemEntity) (Object) this;
        if (!(itemEntity.getStack().getItem() == Items.MAGMA_CREAM)) return;
        cir.setReturnValue(false);
    }

    @Inject(method = "isFireImmune", at = @At("HEAD"), cancellable = true)
    private void disableBurnEffectInLava(CallbackInfoReturnable<Boolean> cir) {
        ItemEntity itemEntity = (ItemEntity) (Object) this;
        if (!(itemEntity.getStack().getItem() == Items.MAGMA_CREAM)) return;
        cir.setReturnValue(true);
    }
}
