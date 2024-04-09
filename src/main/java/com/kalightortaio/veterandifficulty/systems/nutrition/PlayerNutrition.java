package com.kalightortaio.veterandifficulty.systems.nutrition;

import net.minecraft.item.Item;
import net.minecraft.server.network.ServerPlayerEntity;
import java.util.HashMap;
import java.util.Map;

public class PlayerNutrition {
    private static final Map<ServerPlayerEntity, NutritionStats> nutritionStats = new HashMap<>();

    public static NutritionStats getStats(ServerPlayerEntity player) {
        return nutritionStats.computeIfAbsent(player, k -> new NutritionStats());
    }

    public static void removePlayer(ServerPlayerEntity player) {
        nutritionStats.remove(player);
    }

    public static void processEating(ServerPlayerEntity player, Item consumedItem) {
        NutritionStats stats = getStats(player);
        FoodEffectsRegistry.getFoodEffect(consumedItem).accept(player, stats);
    }

    public static class NutritionStats {
        public int VDFruit = 500;
        public int VDProtein = 500;
        public int VDVegetables = 500;
        public int VDSweets = 500;
        public int VDGrains = 500;
        public int VDWater = 500;

        public void modifyStat(String category, int amount) {
            switch (category) {
                case "VDFruit":
                    VDFruit = Math.max(0, VDFruit + amount);
                    break;
                case "VDProtein":
                    VDProtein = Math.max(0, VDProtein + amount);
                    break;
                case "VDVegetables":
                    VDVegetables = Math.max(0, VDVegetables + amount);
                    break;
                case "VDSweets":
                    VDSweets = Math.max(0, VDSweets + amount);
                    break;
                case "VDGrains":
                    VDGrains = Math.max(0, VDGrains + amount);
                    break;
                case "VDWater":
                    VDWater = Math.max(0, VDWater + amount);
                    break;
                case "VDAll":
                    VDFruit = Math.max(0, VDFruit + amount);
                    VDProtein = Math.max(0, VDProtein + amount);
                    VDVegetables = Math.max(0, VDVegetables + amount);
                    VDSweets = Math.max(0, VDSweets + amount);
                    VDGrains = Math.max(0, VDGrains + amount);
                    VDWater = Math.max(0, VDWater + amount);
                    break;
                default:
                    System.out.println("Unknown nutrition category: " + category);
            }
        }
    }
}
