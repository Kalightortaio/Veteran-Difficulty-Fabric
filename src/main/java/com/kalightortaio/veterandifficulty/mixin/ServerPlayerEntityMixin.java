package com.kalightortaio.veterandifficulty.mixin;

import net.minecraft.item.Item;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kalightortaio.veterandifficulty.systems.nutrition.PlayerNutrition;
import com.kalightortaio.veterandifficulty.systems.nutrition.PlayerNutrition.NutritionStats;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {

    private ServerPlayerEntity asPlayer() {
        return (ServerPlayerEntity) (Object) this;
    }

    @Inject(method = "writeCustomData", at = @At("TAIL"))
    private void onWriteCustomData(WriteView view, CallbackInfo ci) {
        NutritionStats stats = PlayerNutrition.getStats(asPlayer());
        WriteView nutritionData = view.get("PlayerNutrition");
        nutritionData.putInt("VDFruit", stats.VDFruit);
        nutritionData.putInt("VDProtein", stats.VDProtein);
        nutritionData.putInt("VDVegetables", stats.VDVegetables);
        nutritionData.putInt("VDSweets", stats.VDSweets);
        nutritionData.putInt("VDGrains", stats.VDGrains);
        nutritionData.putInt("VDWater", stats.VDWater);
    }

    @Inject(method = "readCustomData", at = @At("TAIL"))
    private void onReadCustomData(ReadView view, CallbackInfo ci) {
        view.getOptionalReadView("PlayerNutrition").ifPresent(child -> {
            NutritionStats stats = PlayerNutrition.getStats(asPlayer());
            stats.VDFruit  = child.getInt("VDFruit", stats.VDFruit);
            stats.VDProtein = child.getInt("VDProtein", stats.VDProtein);
            stats.VDVegetables = child.getInt("VDVegetables", stats.VDVegetables);
            stats.VDSweets  = child.getInt("VDSweets", stats.VDSweets);
            stats.VDGrains = child.getInt("VDGrains", stats.VDGrains);
            stats.VDWater = child.getInt("VDWater", stats.VDWater);
        });
    }

    @Inject(method = "consumeItem", at = @At("HEAD"))
    private void consumeItem(CallbackInfo ci) {
        Item consumedItem = asPlayer().getActiveItem().getItem();
        PlayerNutrition.processEating(asPlayer(), consumedItem);
    }
}
