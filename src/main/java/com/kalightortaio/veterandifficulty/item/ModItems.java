package com.kalightortaio.veterandifficulty.item;

import com.kalightortaio.veterandifficulty.VeteranDifficulty;
import com.kalightortaio.veterandifficulty.util.ToolStats;
import com.kalightortaio.veterandifficulty.util.ToolTier;
import com.kalightortaio.veterandifficulty.util.ToolType;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.ShovelItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {
    //
    // Food
    //
    public static final Item ACACIA_POD = registerItem("acacia_pod", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "acacia_pod")))
            .food(ModFoods.ACACIA_POD)));

    public static final Item BAMBOO_SHOOT = registerItem("bamboo_shoot", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "bamboo_shoot")))
            .food(ModFoods.BAMBOO_SHOOT)));

    public static final Item BIRCH_BARK = registerItem("birch_bark", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "birch_bark")))
            .food(ModFoods.BIRCH_BARK)));

    public static final Item CACTUS_FRUIT = registerItem("cactus_fruit", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "cactus_fruit")))
            .food(ModFoods.CACTUS_FRUIT)));

    public static final Item CALAMARI = registerItem("calamari", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "calamari")))
            .food(ModFoods.CALAMARI)));

    public static final Item CHERRY = registerItem("cherry", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "cherry")))
            .food(ModFoods.CHERRY)));

    public static final Item COOKED_EGG = registerItem("cooked_egg", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "cooked_egg")))
            .food(ModFoods.COOKED_EGG)));

    public static final Item CREEPER_POD = registerItem("creeper_pod", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "creeper_pod")))
            .food(ModFoods.CREEPER_POD)));

    public static final Item CRIMSON_ROOT = registerItem("crimson_root", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "crimson_root")))
            .food(ModFoods.CRIMSON_ROOT)));

    public static final Item GLOWING_CALAMARI = registerItem("glowing_calamari", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "glowing_calamari")))
            .food(ModFoods.GLOWING_CALAMARI)));

    public static final Item LICHEN_CLUMP = registerItem("lichen_clump", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "lichen_clump")))
            .food(ModFoods.LICHEN_CLUMP)));

    public static final Item MANGO = registerItem("mango", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "mango")))
            .food(ModFoods.MANGO)));

    public static final Item MOSS_CLUMP = registerItem("moss_clump", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "moss_clump")))
            .food(ModFoods.MOSS_CLUMP)));

    public static final Item PUMPKIN_SLICE = registerItem("pumpkin_slice", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "pumpkin_slice")))
            .food(ModFoods.PUMPKIN_SLICE)));

    public static final Item RIPE_SPROUT = registerItem("ripe_sprout", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "ripe_sprout")))
            .food(ModFoods.RIPE_SPROUT)));

    public static final Item RIPE_SUGAR_CANE = registerItem("ripe_sugar_cane", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "ripe_sugar_cane")))
            .food(ModFoods.RIPE_SUGAR_CANE)));

    public static final Item RIPE_WART = registerItem("ripe_wart", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "ripe_wart")))
            .food(ModFoods.RIPE_WART)));

    public static final Item SCULK_GROWTH = registerItem("sculk_growth", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "sculk_growth")))
            .food(ModFoods.SCULK_GROWTH)));

    public static final Item SOUR_CHERRY = registerItem("sour_cherry", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "sour_cherry")))
            .food(ModFoods.SOUR_CHERRY)));

    public static final Item SPRUCE_CONE = registerItem("spruce_cone", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "spruce_cone")))
            .food(ModFoods.SPRUCE_CONE)));

    public static final Item TELEPORTED_MORSEL = registerItem("teleported_morsel", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "teleported_morsel")))
            .food(ModFoods.TELEPORTED_MORSEL)));

    public static final Item WARPED_ROOT = registerItem("warped_root", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "warped_root")))
            .food(ModFoods.WARPED_ROOT)));

    public static final Item WILD_RICE = registerItem("wild_rice", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "wild_rice")))
            .food(ModFoods.WILD_RICE)));

    public static final Item WILD_ROOT = registerItem("wild_root", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "wild_root")))
            .food(ModFoods.WILD_ROOT)));

    public static final Item WORM = registerItem("worm", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "worm")))
            .food(ModFoods.WORM)));
    //
    // Misc
    //
    public static final Item AIR_BLADDER = registerItem("air_bladder", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "air_bladder")))));

    public static final Item PAST_ECHOES = registerItem("past_echoes", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "past_echoes")))));

    public static final Item TOTEM_SHARD = registerItem("totem_shard", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "totem_shard")))));

    public static final Item WAXEN_WINGS = registerItem("waxen_wings", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "waxen_wings")))));
    //
    // Tier 0 (Flint)
    //
    public static final Item FLINT_AND_TINDER = registerItem("flint_and_tinder", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "flint_and_tinder")))));

    public static final Item FLINT_AXE = registerItem("flint_axe", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new AxeItem(ModToolMaterials.FLINT, ToolStats.attackDamage(ToolTier.FLINT, ToolType.AXE), ToolStats.attackSpeed(ToolType.AXE), new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "flint_axe")))));

    public static final Item FLINT_KNIFE = registerItem("flint_knife", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item.Settings().sword(ModToolMaterials.FLINT, 0.0F, ToolStats.attackSpeed(ToolType.SWORD))
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "flint_knife"))));

    public static final Item FLINT_PICKAXE = registerItem("flint_pickaxe", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item.Settings().pickaxe(ModToolMaterials.FLINT, ToolStats.attackDamage(ToolTier.FLINT, ToolType.PICKAXE), ToolStats.attackSpeed(ToolType.PICKAXE))
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "flint_pickaxe"))));
    //
    // Tier 3 (Copper)
    //
    public static final Item COPPER_AXE = registerItem("copper_axe", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new AxeItem(ModToolMaterials.COPPER, ToolStats.attackDamage(ToolTier.COPPER, ToolType.AXE), ToolStats.attackSpeed(ToolType.AXE), new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "copper_axe")))));

    public static final Item COPPER_BOOTS = registerItem("copper_boots", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "copper_boots")))));

    public static final Item COPPER_CHESTPLATE = registerItem("copper_chestplate", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "copper_chestplate")))));

    public static final Item COPPER_HELMET = registerItem("copper_helmet", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "copper_helmet")))));

    public static final Item COPPER_HOE = registerItem("copper_hoe", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new HoeItem(ModToolMaterials.COPPER, ToolStats.attackDamage(ToolTier.COPPER, ToolType.HOE), ToolStats.attackSpeed(ToolType.HOE), new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "copper_hoe")))));

    public static final Item COPPER_LEGGINGS = registerItem("copper_leggings", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "copper_leggings")))));

    public static final Item COPPER_PAN = registerItem("copper_pan", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "copper_pan")))));

    public static final Item COPPER_PICKAXE = registerItem("copper_pickaxe", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item.Settings().pickaxe(ModToolMaterials.COPPER, ToolStats.attackDamage(ToolTier.COPPER, ToolType.PICKAXE), ToolStats.attackSpeed(ToolType.PICKAXE))
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "copper_pickaxe"))));

    public static final Item COPPER_SHOVEL = registerItem("copper_shovel", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new ShovelItem(ModToolMaterials.COPPER, ToolStats.attackDamage(ToolTier.COPPER, ToolType.SHOVEL), ToolStats.attackSpeed(ToolType.SHOVEL), new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "copper_shovel")))));

    public static final Item COPPER_SWORD = registerItem("copper_sword", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item.Settings().sword(ModToolMaterials.COPPER, ToolStats.attackDamage(ToolTier.COPPER, ToolType.SWORD), ToolStats.attackSpeed(ToolType.SWORD))
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "copper_sword"))));
    //
    // Tier 4 (Silver)
    //
    public static final Item SILVER_AXE = registerItem("silver_axe", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new AxeItem(ModToolMaterials.SILVER, ToolStats.attackDamage(ToolTier.SILVER, ToolType.AXE), ToolStats.attackSpeed(ToolType.AXE), new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "silver_axe")))));

    public static final Item SILVER_BOOTS = registerItem("silver_boots", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "silver_boots")))));

    public static final Item SILVER_CHESTPLATE = registerItem("silver_chestplate", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "silver_chestplate")))));

    public static final Item SILVER_HELMET = registerItem("silver_helmet", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "silver_helmet")))));

    public static final Item SILVER_HOE = registerItem("silver_hoe", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new HoeItem(ModToolMaterials.SILVER, ToolStats.attackDamage(ToolTier.SILVER, ToolType.HOE), ToolStats.attackSpeed(ToolType.HOE), new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "silver_hoe")))));

    public static final Item RAW_SILVER = registerItem("raw_silver", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "raw_silver")))));

    public static final Item SILVER_INGOT = registerItem("silver_ingot", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "silver_ingot")))));

    public static final Item SILVER_LEGGINGS = registerItem("silver_leggings", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "silver_leggings")))));

    public static final Item SILVER_PICKAXE = registerItem("silver_pickaxe", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item.Settings().pickaxe(ModToolMaterials.SILVER, ToolStats.attackDamage(ToolTier.SILVER, ToolType.PICKAXE), ToolStats.attackSpeed(ToolType.PICKAXE))
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "silver_pickaxe"))));

    public static final Item SILVER_SHOVEL = registerItem("silver_shovel", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new ShovelItem(ModToolMaterials.SILVER, ToolStats.attackDamage(ToolTier.SILVER, ToolType.SHOVEL), ToolStats.attackSpeed(ToolType.SHOVEL), new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "silver_shovel")))));

    public static final Item SILVER_SWORD = registerItem("silver_sword", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item.Settings().sword(ModToolMaterials.SILVER, ToolStats.attackDamage(ToolTier.SILVER, ToolType.SWORD), ToolStats.attackSpeed(ToolType.SWORD))
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "silver_sword"))));
    //
    // Tier 7 (Mithril)
    //
    public static final Item MITHRIL_AXE = registerItem("mithril_axe", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new AxeItem(ModToolMaterials.MITHRIL, ToolStats.attackDamage(ToolTier.MITHRIL, ToolType.AXE), ToolStats.attackSpeed(ToolType.AXE), new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "mithril_axe")))));

    public static final Item MITHRIL_BOOTS = registerItem("mithril_boots", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "mithril_boots")))));

    public static final Item MITHRIL_CHESTPLATE = registerItem("mithril_chestplate", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "mithril_chestplate")))));

    public static final Item MITHRIL_HELMET = registerItem("mithril_helmet", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "mithril_helmet")))));

    public static final Item MITHRIL_HOE = registerItem("mithril_hoe", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new HoeItem(ModToolMaterials.MITHRIL, ToolStats.attackDamage(ToolTier.MITHRIL, ToolType.HOE), ToolStats.attackSpeed(ToolType.HOE), new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "mithril_hoe")))));

    public static final Item MITHRIL_INGOT = registerItem("mithril_ingot", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "mithril_ingot")))));

    public static final Item MITHRIL_LEGGINGS = registerItem("mithril_leggings", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "mithril_leggings")))));

    public static final Item MITHRIL_PICKAXE = registerItem("mithril_pickaxe", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item.Settings().pickaxe(ModToolMaterials.MITHRIL, ToolStats.attackDamage(ToolTier.MITHRIL, ToolType.PICKAXE), ToolStats.attackSpeed(ToolType.PICKAXE))
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "mithril_pickaxe"))));

    public static final Item MITHRIL_SHOVEL = registerItem("mithril_shovel", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new ShovelItem(ModToolMaterials.MITHRIL, ToolStats.attackDamage(ToolTier.MITHRIL, ToolType.SHOVEL), ToolStats.attackSpeed(ToolType.SHOVEL), new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "mithril_shovel")))));

    public static final Item MITHRIL_SWORD = registerItem("mithril_sword", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item.Settings().sword(ModToolMaterials.MITHRIL, ToolStats.attackDamage(ToolTier.MITHRIL, ToolType.SWORD), ToolStats.attackSpeed(ToolType.SWORD))
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "mithril_sword"))));
    //
    // Tier 8 (Diamond)
    //
    public static final Item RAW_DIAMOND = registerItem("raw_diamond", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "raw_diamond")))));

    public static final Item DIAMOND_ELYTRA = registerItem("diamond_elytra", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "diamond_elytra")))));
    //
    // Tier 9 (Netherite)
    //
    public static final Item NETHERITE_ELYTRA = registerItem("netherite_elytra", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "netherite_elytra")))));
    //
    // Tier 10 (Enderium)
    //
    public static final Item ENDERIUM_AXE = registerItem("enderium_axe", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new AxeItem(ModToolMaterials.ENDERIUM, ToolStats.attackDamage(ToolTier.ENDERIUM, ToolType.AXE), ToolStats.attackSpeed(ToolType.AXE), new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "enderium_axe")))));

    public static final Item ENDERIUM_BOOTS = registerItem("enderium_boots", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "enderium_boots")))));

    public static final Item ENDERIUM_CHESTPLATE = registerItem("enderium_chestplate", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "enderium_chestplate")))));

    public static final Item ENDERIUM_ELYTRA = registerItem("enderium_elytra", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "enderium_elytra")))));

    public static final Item RAW_ENDERIUM = registerItem("raw_enderium", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "raw_enderium")))));

    public static final Item ENDERIUM_GEM = registerItem("enderium_gem", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "enderium_gem")))));

    public static final Item ENDERIUM_HELMET = registerItem("enderium_helmet", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "enderium_helmet")))));

    public static final Item ENDERIUM_HOE = registerItem("enderium_hoe", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new HoeItem(ModToolMaterials.ENDERIUM, ToolStats.attackDamage(ToolTier.ENDERIUM, ToolType.HOE), ToolStats.attackSpeed(ToolType.HOE), new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "enderium_hoe")))));

    public static final Item ENDERIUM_LEGGINGS = registerItem("enderium_leggings", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "enderium_leggings")))));

    public static final Item ENDERIUM_PICKAXE = registerItem("enderium_pickaxe", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item.Settings().pickaxe(ModToolMaterials.ENDERIUM, ToolStats.attackDamage(ToolTier.ENDERIUM, ToolType.PICKAXE), ToolStats.attackSpeed(ToolType.PICKAXE))
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "enderium_pickaxe"))));

    public static final Item ENDERIUM_SHOVEL = registerItem("enderium_shovel", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new ShovelItem(ModToolMaterials.ENDERIUM, ToolStats.attackDamage(ToolTier.ENDERIUM, ToolType.SHOVEL), ToolStats.attackSpeed(ToolType.SHOVEL), new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "enderium_shovel")))));

    public static final Item ENDERIUM_SWORD = registerItem("enderium_sword", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item.Settings().sword(ModToolMaterials.ENDERIUM, ToolStats.attackDamage(ToolTier.ENDERIUM, ToolType.SWORD), ToolStats.attackSpeed(ToolType.SWORD))
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "enderium_sword"))));
    //
    // Tier 11 (Tenebris)
    //
    public static final Item TENEBRIS_AXE = registerItem("tenebris_axe", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new AxeItem(ModToolMaterials.TENEBRIS, ToolStats.attackDamage(ToolTier.TENEBRIS, ToolType.AXE), ToolStats.attackSpeed(ToolType.AXE), new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "tenebris_axe")))));

    public static final Item TENEBRIS_BOOTS = registerItem("tenebris_boots", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "tenebris_boots")))));

    public static final Item TENEBRIS_CHESTPLATE = registerItem("tenebris_chestplate", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "tenebris_chestplate")))));

    public static final Item TENEBRIS_ELYTRA = registerItem("tenebris_elytra", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "tenebris_elytra")))));

    public static final Item TENEBRIS_ESSENCE = registerItem("tenebris_essence", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "tenebris_essence")))));

    public static final Item TENEBRIS_HELMET = registerItem("tenebris_helmet", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "tenebris_helmet")))));

    public static final Item TENEBRIS_HOE = registerItem("tenebris_hoe", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new HoeItem(ModToolMaterials.TENEBRIS, ToolStats.attackDamage(ToolTier.TENEBRIS, ToolType.HOE), ToolStats.attackSpeed(ToolType.HOE), new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "tenebris_hoe")))));

    public static final Item TENEBRIS_INGOT = registerItem("tenebris_ingot", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "tenebris_ingot")))));

    public static final Item TENEBRIS_LEGGINGS = registerItem("tenebris_leggings", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "tenebris_leggings")))));

    public static final Item TENEBRIS_PICKAXE = registerItem("tenebris_pickaxe", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item.Settings().pickaxe(ModToolMaterials.TENEBRIS, ToolStats.attackDamage(ToolTier.TENEBRIS, ToolType.PICKAXE), ToolStats.attackSpeed(ToolType.PICKAXE))
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "tenebris_pickaxe"))));

    public static final Item TENEBRIS_SHOVEL = registerItem("tenebris_shovel", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new ShovelItem(ModToolMaterials.TENEBRIS, ToolStats.attackDamage(ToolTier.TENEBRIS, ToolType.SHOVEL), ToolStats.attackSpeed(ToolType.SHOVEL), new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "tenebris_shovel")))));

    public static final Item TENEBRIS_SWORD = registerItem("tenebris_sword", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item.Settings().sword(ModToolMaterials.TENEBRIS, ToolStats.attackDamage(ToolTier.TENEBRIS, ToolType.SWORD), ToolStats.attackSpeed(ToolType.SWORD))
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "tenebris_sword"))));

    public static final Item TENEBRIS_VOID = registerItem("tenebris_void", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "tenebris_void")))));
    //
    // Tier 12 (Zenith)
    //
    public static final Item ZENITH_AXE = registerItem("zenith_axe", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new AxeItem(ModToolMaterials.ZENITH, ToolStats.attackDamage(ToolTier.ZENITH, ToolType.AXE), ToolStats.attackSpeed(ToolType.AXE), new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "zenith_axe")))));

    public static final Item ZENITH_BOOTS = registerItem("zenith_boots", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "zenith_boots")))));

    public static final Item ZENITH_CHESTPLATE = registerItem("zenith_chestplate", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "zenith_chestplate")))));

    public static final Item ZENITH_ELYTRA = registerItem("zenith_elytra", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "zenith_elytra")))));

    public static final Item ZENITH_HELMET = registerItem("zenith_helmet", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "zenith_helmet")))));

    public static final Item ZENITH_HOE = registerItem("zenith_hoe", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new HoeItem(ModToolMaterials.ZENITH, ToolStats.attackDamage(ToolTier.ZENITH, ToolType.HOE), ToolStats.attackSpeed(ToolType.HOE), new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "zenith_hoe")))));

    public static final Item ZENITH_INGOT = registerItem("zenith_ingot", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "zenith_ingot")))));

    public static final Item ZENITH_LEGGINGS = registerItem("zenith_leggings", ItemGroups.INGREDIENTS, Items.CHARCOAL,
        new Item(new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "zenith_leggings")))));

    public static final Item ZENITH_PICKAXE = registerItem("zenith_pickaxe", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item.Settings().pickaxe(ModToolMaterials.ZENITH, ToolStats.attackDamage(ToolTier.ZENITH, ToolType.PICKAXE), ToolStats.attackSpeed(ToolType.PICKAXE))
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "zenith_pickaxe"))));

    public static final Item ZENITH_SHOVEL = registerItem("zenith_shovel", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new ShovelItem(ModToolMaterials.ZENITH, ToolStats.attackDamage(ToolTier.ZENITH, ToolType.SHOVEL), ToolStats.attackSpeed(ToolType.SHOVEL), new Item.Settings()
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "zenith_shovel")))));

    public static final Item ZENITH_SWORD = registerItem("zenith_sword", ItemGroups.INGREDIENTS, Items.CHARCOAL, 
        new Item.Settings().sword(ModToolMaterials.ZENITH, ToolStats.attackDamage(ToolTier.ZENITH, ToolType.SWORD), ToolStats.attackSpeed(ToolType.SWORD))
            .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "zenith_sword"))));
    
    private static Item registerItem(String path, RegistryKey<ItemGroup> group, Item location, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.addAfter(location, item));
        return Registry.register(Registries.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, path), item);
    }

    private static Item registerItem(String path, RegistryKey<ItemGroup> group, Item location, Item.Settings settings) {
        Item item = new Item(settings);
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.addAfter(location, item));
        return Registry.register(Registries.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, path), item);
    }
    
    public static void registerModItems() {
        VeteranDifficulty.LOGGER.info("Registering Mod Items for " + VeteranDifficulty.MOD_ID);
    }
}
