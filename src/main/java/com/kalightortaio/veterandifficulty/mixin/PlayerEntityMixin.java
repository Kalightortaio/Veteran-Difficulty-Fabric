package com.kalightortaio.veterandifficulty.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kalightortaio.veterandifficulty.systems.nutrition.PlayerNutrition;

@Mixin(LivingEntity.class)
public class PlayerEntityMixin {

    @Inject(method = "consumeItem", at = @At("HEAD"))
    private void consumeItem(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (player instanceof ServerPlayerEntity serverPlayer) {
            Item consumedItem = player.getActiveItem().getItem();
            PlayerNutrition.processEating(serverPlayer, consumedItem);
        }
    }
}
