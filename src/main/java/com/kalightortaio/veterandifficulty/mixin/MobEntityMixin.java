package com.kalightortaio.veterandifficulty.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kalightortaio.veterandifficulty.interfaces.IBiteHandlerEntity;
import com.kalightortaio.veterandifficulty.mob.Bat;

import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

@Mixin(MobEntity.class)
public abstract class MobEntityMixin {

    @Inject(method = "interactWithItem", at = @At("HEAD"), cancellable = true)
    private void interactWithGlowBerry(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if ((Object) this instanceof BatEntity bat && player instanceof ServerPlayerEntity && this instanceof IBiteHandlerEntity handlerEntity) {
            ItemStack itemStack = player.getStackInHand(hand);
            if (itemStack.isOf(Items.GLOW_BERRIES)) {
                ActionResult result = Bat.handleGlowBerryInteraction(bat, (ServerPlayerEntity) player, itemStack, hand, handlerEntity.getBiteHandler());
                cir.setReturnValue(result);
            }
        }
    }
}