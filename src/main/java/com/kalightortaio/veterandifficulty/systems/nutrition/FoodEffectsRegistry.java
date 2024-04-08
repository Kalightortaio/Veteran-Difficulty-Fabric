package com.kalightortaio.veterandifficulty.systems.nutrition;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import com.kalightortaio.veterandifficulty.VeteranDifficulty;
import com.kalightortaio.veterandifficulty.item.ModItems;

public class FoodEffectsRegistry {
    private static final Map<Item, BiConsumer<ServerPlayerEntity, PlayerNutrition.NutritionStats>> foodEffects = new HashMap<>();

    static {
        foodEffects.put(Items.BEEF, (player, stats) -> stats.modifyStat("VDProtein", 10));
        foodEffects.put(Items.COOKED_BEEF, (player, stats) -> stats.modifyStat("VDProtein", 30));
        foodEffects.put(Items.CHICKEN, (player, stats) -> stats.modifyStat("VDProtein", 10));
        foodEffects.put(Items.COOKED_CHICKEN, (player, stats) -> stats.modifyStat("VDProtein", 30));
        foodEffects.put(Items.MUTTON, (player, stats) -> stats.modifyStat("VDProtein", 10));
        foodEffects.put(Items.COOKED_MUTTON, (player, stats) -> stats.modifyStat("VDProtein", 30));
        foodEffects.put(Items.PORKCHOP, (player, stats) -> stats.modifyStat("VDProtein", 10));
        foodEffects.put(Items.COOKED_PORKCHOP, (player, stats) -> stats.modifyStat("VDProtein", 30));
        foodEffects.put(Items.RABBIT, (player, stats) -> stats.modifyStat("VDProtein", 10));
        foodEffects.put(Items.COOKED_RABBIT, (player, stats) -> stats.modifyStat("VDProtein", 30));
        foodEffects.put(Items.SALMON, (player, stats) -> stats.modifyStat("VDProtein", 10));
        foodEffects.put(Items.COOKED_SALMON, (player, stats) -> stats.modifyStat("VDProtein", 20));
        foodEffects.put(Items.COD, (player, stats) -> stats.modifyStat("VDProtein", 10));
        foodEffects.put(Items.COOKED_COD, (player, stats) -> stats.modifyStat("VDProtein", 20));
        foodEffects.put(Items.MUSHROOM_STEW, (player, stats) -> {
            stats.modifyStat("VDProtein", 20);
            stats.modifyStat("VDVegetables", 20);
        });
        foodEffects.put(Items.SUSPICIOUS_STEW, (player, stats) -> {
            stats.modifyStat("VDProtein", 20);
            stats.modifyStat("VDVegetables", 20);
            stats.modifyStat("VDWater", 20);

        });
        foodEffects.put(Items.RABBIT_STEW, (player, stats) -> {
            stats.modifyStat("VDProtein", 40);
            stats.modifyStat("VDVegetables", 40);
            stats.modifyStat("VDWater", 40);
        });
        foodEffects.put(Items.TROPICAL_FISH, (player, stats) -> stats.modifyStat("VDProtein", 10));
        foodEffects.put(Items.ROTTEN_FLESH, (player, stats) -> stats.modifyStat("VDProtein", -20));
        foodEffects.put(Items.MILK_BUCKET, (player, stats) -> {
            stats.modifyStat("VDProtein", 10);
            stats.modifyStat("VDWater", 10);
        });
        foodEffects.put(Items.GLOW_BERRIES, (player, stats) -> {
            stats.modifyStat("VDFruit", 20);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 1200, 0, false, true));
        });
        foodEffects.put(ModItems.COOKED_EGG, (player, stats) -> stats.modifyStat("VDProtein", 30));

    }

    public static BiConsumer<ServerPlayerEntity, PlayerNutrition.NutritionStats> getFoodEffect(Item consumedItem) {
        return foodEffects.getOrDefault(consumedItem, (player, stats) -> {
            VeteranDifficulty.LOGGER.error("Food item {} not found in nutrition table. Applying default nutrition effect.", consumedItem);
            
            stats.modifyStat("VDFruit", 3);
            stats.modifyStat("VDProtein", 3);
            stats.modifyStat("VDVegetables", 3);
            stats.modifyStat("VDSweets", 3);
            stats.modifyStat("VDGrains", 3);
            stats.modifyStat("VDWater", 3);
        });
    }
}
