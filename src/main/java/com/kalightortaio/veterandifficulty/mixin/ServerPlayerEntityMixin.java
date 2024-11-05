package com.kalightortaio.veterandifficulty.mixin;

import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;

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

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void onWriteCustomDataToNbt(NbtCompound nbt, CallbackInfo info) {
        NutritionStats stats = PlayerNutrition.getStats(asPlayer());
        NbtCompound nutritionData = new NbtCompound();
        nutritionData.putInt("VDFruit", stats.VDFruit);
        nutritionData.putInt("VDProtein", stats.VDProtein);
        nutritionData.putInt("VDVegetables", stats.VDVegetables);
        nutritionData.putInt("VDSweets", stats.VDSweets);
        nutritionData.putInt("VDGrains", stats.VDGrains);
        nutritionData.putInt("VDWater", stats.VDWater);
        nbt.put("PlayerNutrition", nutritionData);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void onReadCustomDataFromNbt(NbtCompound nbt, CallbackInfo info) {
        if (nbt.contains("PlayerNutrition", 10)) {
            NbtCompound nutritionData = nbt.getCompound("PlayerNutrition");
            NutritionStats stats = PlayerNutrition.getStats(asPlayer());
            stats.VDFruit = nutritionData.getInt("VDFruit");
            stats.VDProtein = nutritionData.getInt("VDProtein");
            stats.VDVegetables = nutritionData.getInt("VDVegetables");
            stats.VDSweets = nutritionData.getInt("VDSweets");
            stats.VDGrains = nutritionData.getInt("VDGrains");
            stats.VDWater = nutritionData.getInt("VDWater");
        }
    }

    @Inject(method = "consumeItem", at = @At("HEAD"))
    private void consumeItem(CallbackInfo ci) {
        Item consumedItem = asPlayer().getActiveItem().getItem();
        PlayerNutrition.processEating(asPlayer(), consumedItem);
    }
}
