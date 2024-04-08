package com.kalightortaio.veterandifficulty.mixin;

import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kalightortaio.veterandifficulty.systems.nutrition.PlayerNutrition;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {

    @Inject(method = "onDisconnected", at = @At("HEAD"))
    private void onPlayerDisconnect(CallbackInfo info) {
        ServerPlayerEntity player = ((ServerPlayNetworkHandler)(Object)this).player;
        PlayerNutrition.removePlayer(player);
    }
}
