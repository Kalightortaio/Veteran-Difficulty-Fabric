package com.kalightortaio.veterandifficulty.item;

import com.kalightortaio.veterandifficulty.VeteranDifficulty;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    public static final Item AIR_BLADDER = registerItem("air_bladder", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item COPPER_AXE = registerItem("copper_axe", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item COPPER_BOOTS = registerItem("copper_boots", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item COPPER_CHESTPLATE = registerItem("copper_chestplate", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item COPPER_HELMET = registerItem("copper_helmet", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item COPPER_HOE = registerItem("copper_hoe", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item COPPER_LEGGINGS = registerItem("copper_leggings", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item COPPER_PAN = registerItem("copper_pan", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item COPPER_PICKAXE = registerItem("copper_pickaxe", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item COPPER_SHOVEL = registerItem("copper_shovel", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item COPPER_SWORD = registerItem("copper_sword", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item FLINT_AND_TINDER = registerItem("flint_and_tinder", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item NETHERITE_ELYTRA = registerItem("netherite_elytra", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item PAST_ECHOES = registerItem("past_echoes", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item RAW_DIAMOND = registerItem("raw_diamond", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item TOTEM_SHARD = registerItem("totem_shard", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item VOID = registerItem("the_void", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item VOID_AXE = registerItem("void_axe", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item VOID_BOOTS = registerItem("void_boots", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item VOID_CHESTPLATE = registerItem("void_chestplate", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item VOID_ELYTRA = registerItem("void_elytra", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item VOID_ESSENCE = registerItem("void_essence", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item VOID_GATEWAY = registerItem("void_gateway", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item VOID_HELMET = registerItem("void_helmet", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item VOID_HOE = registerItem("void_hoe", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item VOID_INGOT = registerItem("void_ingot", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item VOID_LEGGINGS = registerItem("void_leggings", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item VOID_PICKAXE = registerItem("void_pickaxe", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item VOID_SHOVEL = registerItem("void_shovel", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item VOID_SWORD = registerItem("void_sword", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item WAXEN_WINGS = registerItem("waxen_wings", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item ACACIA_POD = registerItem("acacia_pod", ItemGroups.FOOD_AND_DRINK, Items.COOKED_CHICKEN, new Item(new FabricItemSettings().food(ModFoods.ACACIA_POD)));
    public static final Item BAMBOO_SHOOT = registerItem("bamboo_shoot", ItemGroups.FOOD_AND_DRINK, Items.COOKED_CHICKEN, new Item(new FabricItemSettings().food(ModFoods.BAMBOO_SHOOT)));
    public static final Item BIRCH_BARK = registerItem("birch_bark", ItemGroups.FOOD_AND_DRINK, Items.COOKED_CHICKEN, new Item(new FabricItemSettings().food(ModFoods.BIRCH_BARK)));
    public static final Item CACTUS_FRUIT = registerItem("cactus_fruit", ItemGroups.FOOD_AND_DRINK, Items.COOKED_CHICKEN, new Item(new FabricItemSettings().food(ModFoods.CACTUS_FRUIT)));
    public static final Item CALAMARI = registerItem("calamari", ItemGroups.FOOD_AND_DRINK, Items.COOKED_CHICKEN, new Item(new FabricItemSettings().food(ModFoods.CALAMARI)));
    public static final Item CHERRY = registerItem("cherry", ItemGroups.FOOD_AND_DRINK, Items.COOKED_CHICKEN, new Item(new FabricItemSettings().food(ModFoods.CHERRY)));
    public static final Item COOKED_EGG = registerItem("cooked_egg", ItemGroups.FOOD_AND_DRINK, Items.COOKED_CHICKEN, new Item(new FabricItemSettings().food(ModFoods.COOKED_EGG)));
    public static final Item CREEPER_POD = registerItem("creeper_pod", ItemGroups.FOOD_AND_DRINK, Items.COOKED_CHICKEN, new Item(new FabricItemSettings().food(ModFoods.CREEPER_POD)));
    public static final Item CRIMSON_ROOT = registerItem("crimson_root", ItemGroups.FOOD_AND_DRINK, Items.COOKED_CHICKEN, new Item(new FabricItemSettings().food(ModFoods.CRIMSON_ROOT)));
    public static final Item GLOWING_CALAMARI = registerItem("glowing_calamari", ItemGroups.FOOD_AND_DRINK, Items.COOKED_CHICKEN, new Item(new FabricItemSettings().food(ModFoods.GLOWING_CALAMARI)));
    public static final Item LICHEN_CLUMP = registerItem("lichen_clump", ItemGroups.FOOD_AND_DRINK, Items.COOKED_CHICKEN, new Item(new FabricItemSettings().food(ModFoods.LICHEN_CLUMP)));
    public static final Item MANGO = registerItem("mango", ItemGroups.FOOD_AND_DRINK, Items.COOKED_CHICKEN, new Item(new FabricItemSettings().food(ModFoods.MANGO)));
    public static final Item MOSS_CLUMP = registerItem("moss_clump", ItemGroups.FOOD_AND_DRINK, Items.COOKED_CHICKEN, new Item(new FabricItemSettings().food(ModFoods.MOSS_CLUMP)));
    public static final Item PUMPKIN_SLICE = registerItem("pumpkin_slice", ItemGroups.FOOD_AND_DRINK, Items.COOKED_CHICKEN, new Item(new FabricItemSettings().food(ModFoods.PUMPKIN_SLICE)));
    public static final Item RIPE_SPROUT = registerItem("ripe_sprout", ItemGroups.FOOD_AND_DRINK, Items.COOKED_CHICKEN, new Item(new FabricItemSettings().food(ModFoods.RIPE_SPROUT)));
    public static final Item RIPE_SUGAR_CANE = registerItem("ripe_sugar_cane", ItemGroups.FOOD_AND_DRINK, Items.COOKED_CHICKEN, new Item(new FabricItemSettings().food(ModFoods.RIPE_SUGAR_CANE)));
    public static final Item RIPE_WART = registerItem("ripe_wart", ItemGroups.FOOD_AND_DRINK, Items.COOKED_CHICKEN, new Item(new FabricItemSettings().food(ModFoods.RIPE_WART)));
    public static final Item SCULK_GROWTH = registerItem("sculk_growth", ItemGroups.FOOD_AND_DRINK, Items.COOKED_CHICKEN, new Item(new FabricItemSettings().food(ModFoods.SCULK_GROWTH)));
    public static final Item SOUR_CHERRY = registerItem("sour_cherry", ItemGroups.FOOD_AND_DRINK, Items.COOKED_CHICKEN, new Item(new FabricItemSettings().food(ModFoods.SOUR_CHERRY)));
    public static final Item SPRUCE_CONE = registerItem("spruce_cone", ItemGroups.FOOD_AND_DRINK, Items.COOKED_CHICKEN, new Item(new FabricItemSettings().food(ModFoods.SPRUCE_CONE)));
    public static final Item TELEPORTED_MORSEL = registerItem("teleported_morsel", ItemGroups.FOOD_AND_DRINK, Items.COOKED_CHICKEN, new Item(new FabricItemSettings().food(ModFoods.TELEPORTED_MORSEL)));
    public static final Item WARPED_ROOT = registerItem("warped_root", ItemGroups.FOOD_AND_DRINK, Items.COOKED_CHICKEN, new Item(new FabricItemSettings().food(ModFoods.WARPED_ROOT)));
    public static final Item WILD_RICE = registerItem("wild_rice", ItemGroups.FOOD_AND_DRINK, Items.COOKED_CHICKEN, new Item(new FabricItemSettings().food(ModFoods.WILD_RICE)));
    public static final Item WILD_ROOT = registerItem("wild_root", ItemGroups.FOOD_AND_DRINK, Items.COOKED_CHICKEN, new Item(new FabricItemSettings().food(ModFoods.WILD_ROOT)));
    public static final Item WORM = registerItem("worm", ItemGroups.FOOD_AND_DRINK, Items.COOKED_CHICKEN, new Item(new FabricItemSettings().food(ModFoods.WORM)));
    private static Item registerItem(String name, RegistryKey<ItemGroup> group, Item location, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.addAfter(location, item));
        return Registry.register(Registries.ITEM, new Identifier(VeteranDifficulty.MOD_ID, name), item);
    }
    
    public static void registerModItems() {
        VeteranDifficulty.LOGGER.info("Registering Mod Items for " + VeteranDifficulty.MOD_ID);
    }
}
