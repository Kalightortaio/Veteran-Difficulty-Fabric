package com.kalightortaio.veterandifficulty.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kalightortaio.veterandifficulty.systems.nutrition.PlayerNutrition;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @Inject(method = "eatFood", at = @At("HEAD"))
    private void onEatFood(net.minecraft.world.World world, net.minecraft.item.ItemStack stack, CallbackInfoReturnable<net.minecraft.item.ItemStack> cir) {
        Item consumedItem = stack.getItem();
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (player instanceof ServerPlayerEntity) {
            PlayerNutrition.processEating((ServerPlayerEntity) player, consumedItem);
        }
    }
}
