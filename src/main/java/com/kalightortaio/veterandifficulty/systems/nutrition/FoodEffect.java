package com.kalightortaio.veterandifficulty.systems.nutrition;

import com.kalightortaio.veterandifficulty.systems.nutrition.PlayerNutrition.NutritionStats;

@FunctionalInterface
public interface FoodEffect {
    void apply(NutritionStats stats);
}
