package com.kalightortaio.veterandifficulty.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kalightortaio.veterandifficulty.mixin.accessor.ItemEntityAccessor;
import com.kalightortaio.veterandifficulty.systems.internal.ModTags;
import com.kalightortaio.veterandifficulty.systems.mechanics.Regrowth;

import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {

    private ItemEntity asItemEntity() {
        return (ItemEntity) (Object) this;
    }

    @Inject(method = "setStack", at = @At("HEAD"))
    private void applyFireImmunityAfterStackSet(ItemStack stack, CallbackInfo ci) {
        if (!(stack.isOf(Items.MAGMA_CREAM))) return;
        ((ItemEntityAccessor) asItemEntity()).setHealth(20);
        asItemEntity().extinguish();
    }

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void preventMagmaCreamBurning(CallbackInfo ci) {
        if (!(asItemEntity().getStack().getItem() == Items.MAGMA_CREAM)) return;
        if ((asItemEntity().isInLava() || asItemEntity().isOnFire())) {
            ((ItemEntityAccessor) asItemEntity()).setHealth(20);
            asItemEntity().extinguish();
        }
    }

    @Inject(method = "shouldPlayBurnSoundInLava", at = @At("HEAD"), cancellable = true)
    private void disableBurnSoundInLava(CallbackInfoReturnable<Boolean> cir) {
        if (!(asItemEntity().getStack().getItem() == Items.MAGMA_CREAM)) return;
        cir.setReturnValue(false);
    }

    @Inject(method = "isFireImmune", at = @At("HEAD"), cancellable = true)
    private void disableBurnEffectInLava(CallbackInfoReturnable<Boolean> cir) {
        if (!(asItemEntity().getStack().getItem() == Items.MAGMA_CREAM)) return;
        cir.setReturnValue(true);
    }

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void regrowPlants(CallbackInfo ci) {
        if (!(asItemEntity().isOnGround())) return;
        if (!(asItemEntity().getItemAge() == 600)) return;
        if (!(asItemEntity().getStack().isIn(ModTags.REGROWTH))) return;
        Regrowth.processItem(asItemEntity());
    }
}
