package com.kalightortaio.veterandifficulty.item;

import com.kalightortaio.veterandifficulty.VeteranDifficulty;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItems {
    // Food
    public static final Item ACACIA_POD = registerItem("acacia_pod", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, new Item.Settings().food(ModFoods.ACACIA_POD));
    public static final Item BAMBOO_SHOOT = registerItem("bamboo_shoot", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, new Item.Settings().food(ModFoods.BAMBOO_SHOOT));
    public static final Item BIRCH_BARK = registerItem("birch_bark", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, new Item.Settings().food(ModFoods.BIRCH_BARK));
    public static final Item CACTUS_FRUIT = registerItem("cactus_fruit", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, new Item.Settings().food(ModFoods.CACTUS_FRUIT));
    public static final Item CALAMARI = registerItem("calamari", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, new Item.Settings().food(ModFoods.CALAMARI));
    public static final Item CHERRY = registerItem("cherry", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, new Item.Settings().food(ModFoods.CHERRY));
    public static final Item COOKED_EGG = registerItem("cooked_egg", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, new Item.Settings().food(ModFoods.COOKED_EGG));
    public static final Item CREEPER_POD = registerItem("creeper_pod", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, new Item.Settings().food(ModFoods.CREEPER_POD));
    public static final Item CRIMSON_ROOT = registerItem("crimson_root", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, new Item.Settings().food(ModFoods.CRIMSON_ROOT));
    public static final Item GLOWING_CALAMARI = registerItem("glowing_calamari", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, new Item.Settings().food(ModFoods.GLOWING_CALAMARI));
    public static final Item LICHEN_CLUMP = registerItem("lichen_clump", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, new Item.Settings().food(ModFoods.LICHEN_CLUMP));
    public static final Item MANGO = registerItem("mango", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, new Item.Settings().food(ModFoods.MANGO));
    public static final Item MOSS_CLUMP = registerItem("moss_clump", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, new Item.Settings().food(ModFoods.MOSS_CLUMP));
    public static final Item PUMPKIN_SLICE = registerItem("pumpkin_slice", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, new Item.Settings().food(ModFoods.PUMPKIN_SLICE));
    public static final Item RIPE_SPROUT = registerItem("ripe_sprout", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, new Item.Settings().food(ModFoods.RIPE_SPROUT));
    public static final Item RIPE_SUGAR_CANE = registerItem("ripe_sugar_cane", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, new Item.Settings().food(ModFoods.RIPE_SUGAR_CANE));
    public static final Item RIPE_WART = registerItem("ripe_wart", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, new Item.Settings().food(ModFoods.RIPE_WART));
    public static final Item SCULK_GROWTH = registerItem("sculk_growth", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, new Item.Settings().food(ModFoods.SCULK_GROWTH));
    public static final Item SOUR_CHERRY = registerItem("sour_cherry", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, new Item.Settings().food(ModFoods.SOUR_CHERRY));
    public static final Item SPRUCE_CONE = registerItem("spruce_cone", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, new Item.Settings().food(ModFoods.SPRUCE_CONE));
    public static final Item TELEPORTED_MORSEL = registerItem("teleported_morsel", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, new Item.Settings().food(ModFoods.TELEPORTED_MORSEL));
    public static final Item WARPED_ROOT = registerItem("warped_root", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, new Item.Settings().food(ModFoods.WARPED_ROOT));
    public static final Item WILD_RICE = registerItem("wild_rice", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, new Item.Settings().food(ModFoods.WILD_RICE));
    public static final Item WILD_ROOT = registerItem("wild_root", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, new Item.Settings().food(ModFoods.WILD_ROOT));
    public static final Item WORM = registerItem("worm", ItemGroups.FOOD_AND_DRINK, Items.PUMPKIN_PIE, new Item.Settings().food(ModFoods.WORM));
    // Misc
    public static final Item AIR_BLADDER = registerItem("air_bladder", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item PAST_ECHOES = registerItem("past_echoes", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item TOTEM_SHARD = registerItem("totem_shard", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item WAXEN_WINGS = registerItem("waxen_wings", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    // Tier 0 (Flint)
    public static final Item FLINT_AXE = registerItem("flint_axe", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item FLINT_KNIFE = registerItem("flint_knife", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item FLINT_AND_TINDER = registerItem("flint_and_tinder", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    // Tier 3 (Copper)
    public static final Item COPPER_BOOTS = registerItem("copper_boots", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item COPPER_CHESTPLATE = registerItem("copper_chestplate", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item COPPER_HELMET = registerItem("copper_helmet", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item COPPER_HOE = registerItem("copper_hoe", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item COPPER_LEGGINGS = registerItem("copper_leggings", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item COPPER_PAN = registerItem("copper_pan", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item COPPER_PICKAXE = registerItem("copper_pickaxe", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item COPPER_SHOVEL = registerItem("copper_shovel", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item COPPER_SWORD = registerItem("copper_sword", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    // Tier 4 (Silver)
    public static final Item SILVER_AXE = registerItem("silver_axe", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item SILVER_BOOTS = registerItem("silver_boots", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item SILVER_CHESTPLATE = registerItem("silver_chestplate", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item SILVER_HELMET = registerItem("silver_helmet", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item SILVER_HOE = registerItem("silver_hoe", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item SILVER_LEGGINGS = registerItem("silver_leggings", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item SILVER_PICKAXE = registerItem("silver_pickaxe", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item SILVER_SHOVEL = registerItem("silver_shovel", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item SILVER_SWORD = registerItem("silver_sword", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    // Tier 7 (Mithril)
    public static final Item MITHRIL_AXE = registerItem("mithril_axe", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item MITHRIL_BOOTS = registerItem("mithril_boots", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item MITHRIL_CHESTPLATE = registerItem("mithril_chestplate", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item MITHRIL_HELMET = registerItem("mithril_helmet", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item MITHRIL_HOE = registerItem("mithril_hoe", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item MITHRIL_LEGGINGS = registerItem("mithril_leggings", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item MITHRIL_PICKAXE = registerItem("mithril_pickaxe", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item MITHRIL_SHOVEL = registerItem("mithril_shovel", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item MITHRIL_SWORD = registerItem("mithril_sword", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    // Tier 8 (Diamond)
    public static final Item RAW_DIAMOND = registerItem("raw_diamond", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item DIAMOND_ELYTRA = registerItem("diamond_elytra", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    // Tier 9 (Netherite)
    public static final Item NETHERITE_ELYTRA = registerItem("netherite_elytra", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    // Tier 10 (Enderium)
    public static final Item ENDERIUM_AXE = registerItem("enderium_axe", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item ENDERIUM_BOOTS = registerItem("enderium_boots", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item ENDERIUM_CHESTPLATE = registerItem("enderium_chestplate", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item ENDERIUM_ELYTRA = registerItem("enderium_elytra", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item ENDERIUM_GEM = registerItem("enderium_gem", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item ENDERIUM_HELMET = registerItem("enderium_helmet", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item ENDERIUM_HOE = registerItem("enderium_hoe", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item ENDERIUM_LEGGINGS = registerItem("enderium_leggings", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item ENDERIUM_PICKAXE = registerItem("enderium_pickaxe", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item ENDERIUM_SHOVEL = registerItem("enderium_shovel", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item ENDERIUM_SWORD = registerItem("enderium_sword", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    // Tier 11 (Tenebris)
    public static final Item TENEBRIS_AXE = registerItem("tenebris_axe", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item TENEBRIS_BOOTS = registerItem("tenebris_boots", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item TENEBRIS_CHESTPLATE = registerItem("tenebris_chestplate", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item TENEBRIS_ELYTRA = registerItem("tenebris_elytra", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item TENEBRIS_ESSENCE = registerItem("tenebris_essence", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item TENEBRIS_HELMET = registerItem("tenebris_helmet", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item TENEBRIS_HOE = registerItem("tenebris_hoe", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item TENEBRIS_INGOT = registerItem("tenebris_ingot", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item TENEBRIS_LEGGINGS = registerItem("tenebris_leggings", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item TENEBRIS_PICKAXE = registerItem("tenebris_pickaxe", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item TENEBRIS_SHOVEL = registerItem("tenebris_shovel", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item TENEBRIS_SWORD = registerItem("tenebris_sword", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item TENEBRIS_VOID = registerItem("tenebris_void", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    // Tier 12 (Zenith)
    public static final Item ZENITH_AXE = registerItem("zenith_axe", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item ZENITH_BOOTS = registerItem("zenith_boots", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item ZENITH_CHESTPLATE = registerItem("zenith_chestplate", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item ZENITH_ELYTRA = registerItem("zenith_elytra", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item ZENITH_HELMET = registerItem("zenith_helmet", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item ZENITH_HOE = registerItem("zenith_hoe", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item ZENITH_LEGGINGS = registerItem("zenith_leggings", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item ZENITH_PICKAXE = registerItem("zenith_pickaxe", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item ZENITH_SHOVEL = registerItem("zenith_shovel", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    public static final Item ZENITH_SWORD = registerItem("zenith_sword", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item.Settings());
    
    public static Item registerItem(String path, RegistryKey<ItemGroup> group, Item location, Item.Settings settings) {
        final RegistryKey<Item> registryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, path));
        Item item = Items.register(registryKey, Item::new, settings);
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.addAfter(location, item));
        return item;
    }
    
    public static void registerModItems() {
        VeteranDifficulty.LOGGER.info("Registering Mod Items for " + VeteranDifficulty.MOD_ID);
    }
}
