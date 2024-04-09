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
        foodEffects.put(Items.APPLE, (player, stats) -> stats.modifyStat("VDFruit", 20));
        foodEffects.put(Items.GOLDEN_APPLE, (player, stats) -> stats.modifyStat("VDFruit", 50));
        foodEffects.put(Items.ENCHANTED_GOLDEN_APPLE, (player, stats) -> stats.modifyStat("VDFruit", 60));
        foodEffects.put(Items.CHORUS_FRUIT, (player, stats) -> stats.modifyStat("VDFruit", 20));
        foodEffects.put(Items.MELON_SLICE, (player, stats) -> {
            stats.modifyStat("VDFruit", 10);
            stats.modifyStat("VDWater", 10);
        });
        foodEffects.put(Items.SWEET_BERRIES, (player, stats) -> stats.modifyStat("VDFruit", 10));
        foodEffects.put(Items.GLOW_BERRIES, (player, stats) -> {
            stats.modifyStat("VDFruit", 20);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 1200, 0, false, true));
        });
        foodEffects.put(Items.BEEF, (player, stats) -> stats.modifyStat("VDProtein", 10));
        foodEffects.put(Items.CHICKEN, (player, stats) -> stats.modifyStat("VDProtein", 10));
        foodEffects.put(Items.MUTTON, (player, stats) -> stats.modifyStat("VDProtein", 10));
        foodEffects.put(Items.PORKCHOP, (player, stats) -> stats.modifyStat("VDProtein", 10));
        foodEffects.put(Items.RABBIT, (player, stats) -> stats.modifyStat("VDProtein", 10));
        foodEffects.put(Items.SALMON, (player, stats) -> stats.modifyStat("VDProtein", 10));
        foodEffects.put(Items.COD, (player, stats) -> stats.modifyStat("VDProtein", 10));
        foodEffects.put(Items.COOKED_BEEF, (player, stats) -> stats.modifyStat("VDProtein", 30));
        foodEffects.put(Items.COOKED_CHICKEN, (player, stats) -> stats.modifyStat("VDProtein", 30));
        foodEffects.put(Items.COOKED_MUTTON, (player, stats) -> stats.modifyStat("VDProtein", 30));
        foodEffects.put(Items.COOKED_PORKCHOP, (player, stats) -> stats.modifyStat("VDProtein", 30));
        foodEffects.put(Items.COOKED_RABBIT, (player, stats) -> stats.modifyStat("VDProtein", 30));
        foodEffects.put(Items.COOKED_SALMON, (player, stats) -> stats.modifyStat("VDProtein", 20));
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
        foodEffects.put(Items.ROTTEN_FLESH, (player, stats) -> stats.modifyStat("VDAll", -50));
        foodEffects.put(Items.SPIDER_EYE, (player, stats) -> {
            stats.modifyStat("VDAll", -100);
        });
        foodEffects.put(Items.MILK_BUCKET, (player, stats) -> {
            stats.modifyStat("VDProtein", 10);
            stats.modifyStat("VDWater", 10);
        });
        foodEffects.put(Items.BEETROOT, (player, stats) -> stats.modifyStat("VDVegetables", 10));
        foodEffects.put(Items.BEETROOT_SOUP, (player, stats) -> {
            stats.modifyStat("VDVegetables", 10);
            stats.modifyStat("VDWater", 10);
        });
        foodEffects.put(Items.CARROT, (player, stats) -> stats.modifyStat("VDVegetables", 10));
        foodEffects.put(Items.GOLDEN_CARROT, (player, stats) -> stats.modifyStat("VDVegetables", 30));
        foodEffects.put(Items.DRIED_KELP, (player, stats) -> stats.modifyStat("VDVegetables", 10));
        foodEffects.put(Items.POTATO, (player, stats) -> stats.modifyStat("VDVegetables", 10));
        foodEffects.put(Items.BREAD, (player, stats) -> stats.modifyStat("VDGrains", 10));
        foodEffects.put(Items.BAKED_POTATO, (player, stats) -> {
            stats.modifyStat("VDVegetables", 20);
            stats.modifyStat("VDGrains", 10);
        });
        foodEffects.put(Items.POISONOUS_POTATO, (player, stats) -> {
            stats.modifyStat("VDVegetables", -10);
            stats.modifyStat("VDGrains", -10);
        });
        foodEffects.put(Items.PUMPKIN_PIE, (player, stats) -> {
            stats.modifyStat("VDSweets", 30);
            stats.modifyStat("VDGrains", 10);
        });
        foodEffects.put(Items.COOKIE, (player, stats) -> {
            stats.modifyStat("VDSweets", 10);
            stats.modifyStat("VDGrains", 10);
        });
        foodEffects.put(Items.CAKE, (player, stats) -> {
            stats.modifyStat("VDSweets", 40);
            stats.modifyStat("VDGrains", 20);
        });
        foodEffects.put(Items.HONEY_BOTTLE, (player, stats) -> {
            stats.modifyStat("VDSweets", 10);
            stats.modifyStat("VDWater", 10);
        });
        foodEffects.put(Items.POTION, (player, stats) -> {
            stats.modifyStat("VDWater", 50);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 20, 100, false, false));
        });
        foodEffects.put(ModItems.COOKED_EGG, (player, stats) -> stats.modifyStat("VDProtein", 30));
        foodEffects.put(ModItems.TELEPORTED_MORSEL, (player, stats) -> {
            stats.modifyStat("VDProtein", 10);
            if (Math.random() < 0.6) player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 100, 1, false, false));
        });
        foodEffects.put(ModItems.ACACIA_POD, (player, stats) -> stats.modifyStat("VDGrains", 10));
        foodEffects.put(ModItems.BAMBOO_SHOOT, (player, stats) -> stats.modifyStat("VDVegetables", 10));
        foodEffects.put(ModItems.BIRCH_BARK, (player, stats) -> {
            stats.modifyStat("VDGrains", 10);
            stats.modifyStat("VDWater", -30);
        });
        foodEffects.put(ModItems.CACTUS_FRUIT, (player, stats) -> stats.modifyStat("VDFruit", 20));
        foodEffects.put(ModItems.CALAMARI, (player, stats) -> stats.modifyStat("VDProtein", 20));
        foodEffects.put(ModItems.CHERRY, (player, stats) -> stats.modifyStat("VDFruit", 20));
        foodEffects.put(ModItems.CREEPER_POD, (player, stats) -> {
            stats.modifyStat("VDGrains", 30);
            //implement creeper spawning #todo
        });
        foodEffects.put(ModItems.CRIMSON_ROOT, (player, stats) -> stats.modifyStat("VDGrains", 10));
        foodEffects.put(ModItems.GLOWING_CALAMARI, (player, stats) -> {
            stats.modifyStat("VDProtein", 20);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 1200, 0, false, true));
        });
        foodEffects.put(ModItems.LICHEN_CLUMP, (player, stats) -> stats.modifyStat("VDVegetables", 10));
        foodEffects.put(ModItems.MANGO, (player, stats) -> stats.modifyStat("VDFruit", 20));
        foodEffects.put(ModItems.MOSS_CLUMP, (player, stats) -> stats.modifyStat("VDVegetables", 10));
        foodEffects.put(ModItems.PUMPKIN_SLICE, (player, stats) -> stats.modifyStat("VDVegetables", 10));
        foodEffects.put(ModItems.RIPE_SPROUT, (player, stats) -> stats.modifyStat("VDVegetables", 10));
        foodEffects.put(ModItems.RIPE_SUGAR_CANE, (player, stats) -> stats.modifyStat("VDSweets", 20));
        foodEffects.put(ModItems.RIPE_WART, (player, stats) -> {
            stats.modifyStat("VDVegetables", 10);
            //implement some effect here #todo
        });
        foodEffects.put(ModItems.SCULK_GROWTH, (player, stats) -> {
            stats.modifyStat("VDAll", 30);
            if (Math.random() < 0.6) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 1200, 0, false, false));
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 1200, 0, false, false));
            }
        });
        foodEffects.put(ModItems.SOUR_CHERRY, (player, stats) -> {
            stats.modifyStat("VDFruit", 20);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 300, 0, false, true));
        });
        foodEffects.put(ModItems.SPRUCE_CONE, (player, stats) -> stats.modifyStat("VDGrains", 10));
        foodEffects.put(ModItems.WARPED_ROOT, (player, stats) -> stats.modifyStat("VDGrains", 10));
        foodEffects.put(ModItems.WILD_RICE, (player, stats) -> stats.modifyStat("VDGrains", 10));
        foodEffects.put(ModItems.WILD_ROOT, (player, stats) -> stats.modifyStat("VDGrains", 10));
        foodEffects.put(ModItems.WORM, (player, stats) -> stats.modifyStat("VDProtein", 10));
    }

    public static BiConsumer<ServerPlayerEntity, PlayerNutrition.NutritionStats> getFoodEffect(Item consumedItem) {
        return foodEffects.getOrDefault(consumedItem, (player, stats) -> {
            VeteranDifficulty.LOGGER.warn("Food item {} not found in nutrition table. Applying default nutrition effect.", consumedItem);
            stats.modifyStat("VDAll", 5);
        });
    }
}
