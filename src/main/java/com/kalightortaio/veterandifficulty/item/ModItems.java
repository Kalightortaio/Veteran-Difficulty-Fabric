package com.kalightortaio.veterandifficulty.item;

import com.kalightortaio.veterandifficulty.VeteranDifficulty;
import com.kalightortaio.veterandifficulty.util.Armors;
import com.kalightortaio.veterandifficulty.util.Tools;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItems {
    //
    // Food
    //
    public static final Item ACACIA_POD = registerItem("acacia_pod", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "acacia_pod")))
        .food(ModFoods.ACACIA_POD)));

    public static final Item BAMBOO_SHOOT = registerItem("bamboo_shoot", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "bamboo_shoot")))
        .food(ModFoods.BAMBOO_SHOOT)));

    public static final Item CACTUS_FRUIT = registerItem("cactus_fruit", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "cactus_fruit")))
        .food(ModFoods.CACTUS_FRUIT)));

    public static final Item CALAMARI = registerItem("calamari", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "calamari")))
        .food(ModFoods.CALAMARI)));

    public static final Item CHERRY = registerItem("cherry", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "cherry")))
        .food(ModFoods.CHERRY)));

    public static final Item COOKED_EGG = registerItem("cooked_egg", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "cooked_egg")))
        .food(ModFoods.COOKED_EGG)));

    public static final Item CREEPER_POD = registerItem("creeper_pod", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "creeper_pod")))
        .food(ModFoods.CREEPER_POD)));

    public static final Item CRIMSON_ROOT = registerItem("crimson_root", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "crimson_root")))
        .food(ModFoods.CRIMSON_ROOT)));

    public static final Item GLOWING_CALAMARI = registerItem("glowing_calamari", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "glowing_calamari")))
        .food(ModFoods.GLOWING_CALAMARI)));

    public static final Item LICHEN_CLUMP = registerItem("lichen_clump", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "lichen_clump")))
        .food(ModFoods.LICHEN_CLUMP)));

    public static final Item MANGO = registerItem("mango", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "mango")))
        .food(ModFoods.MANGO)));

    public static final Item MOSS_CLUMP = registerItem("moss_clump", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "moss_clump")))
        .food(ModFoods.MOSS_CLUMP)));

    public static final Item PUMPKIN_SLICE = registerItem("pumpkin_slice", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "pumpkin_slice")))
        .food(ModFoods.PUMPKIN_SLICE)));

    public static final Item RIPE_SPROUT = registerItem("ripe_sprout", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "ripe_sprout")))
        .food(ModFoods.RIPE_SPROUT)));

    public static final Item RIPE_SUGAR_CANE = registerItem("ripe_sugar_cane", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "ripe_sugar_cane")))
        .food(ModFoods.RIPE_SUGAR_CANE)));

    public static final Item RIPE_WART = registerItem("ripe_wart", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "ripe_wart")))
        .food(ModFoods.RIPE_WART)));

    public static final Item SCULK_GROWTH = registerItem("sculk_growth", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "sculk_growth")))
        .food(ModFoods.SCULK_GROWTH)));

    public static final Item SOUR_CHERRY = registerItem("sour_cherry", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "sour_cherry")))
        .food(ModFoods.SOUR_CHERRY)));

    public static final Item SPRUCE_CONE = registerItem("spruce_cone", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "spruce_cone")))
        .food(ModFoods.SPRUCE_CONE)));

    public static final Item TELEPORTED_MORSEL = registerItem("teleported_morsel", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "teleported_morsel")))
        .food(ModFoods.TELEPORTED_MORSEL)));

    public static final Item WARPED_ROOT = registerItem("warped_root", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "warped_root")))
        .food(ModFoods.WARPED_ROOT)));

    public static final Item WILD_RICE = registerItem("wild_rice", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "wild_rice")))
        .food(ModFoods.WILD_RICE)));

    public static final Item WILD_ROOT = registerItem("wild_root", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "wild_root")))
        .food(ModFoods.WILD_ROOT)));

    public static final Item WORM = registerItem("worm", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "worm")))
        .food(ModFoods.WORM)));
    //
    // Bark
    //
    public static final Item OAK_BARK = registerItem("oak_bark", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "oak_bark")))
        .food(ModFoods.BARK)));

    public static final Item SPRUCE_BARK = registerItem("spruce_bark", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "spruce_bark")))
        .food(ModFoods.BARK)));
    
    public static final Item BIRCH_BARK = registerItem("birch_bark", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "birch_bark")))
        .food(ModFoods.BARK)));

    public static final Item ACACIA_BARK = registerItem("acacia_bark", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "acacia_bark")))
        .food(ModFoods.BARK)));

    public static final Item CHERRY_BARK = registerItem("cherry_bark", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "cherry_bark")))
        .food(ModFoods.BARK)));

    public static final Item CRIMSON_BARK = registerItem("crimson_bark", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "crimson_bark")))
        .food(ModFoods.BARK)));

    public static final Item DARK_OAK_BARK = registerItem("dark_oak_bark", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "dark_oak_bark")))
        .food(ModFoods.BARK)));

    public static final Item JUNGLE_BARK = registerItem("jungle_bark", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "jungle_bark")))
        .food(ModFoods.BARK)));

    public static final Item MANGROVE_BARK = registerItem("mangrove_bark", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "mangrove_bark")))
        .food(ModFoods.BARK)));
    
    public static final Item WARPED_BARK = registerItem("warped_bark", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "warped_bark")))
        .food(ModFoods.BARK)));

    public static final Item PALE_OAK_BARK = registerItem("pale_oak_bark", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "pale_oak_bark")))
        .food(ModFoods.BARK)));
    //
    // Misc
    //
    public static final Item AIR_BLADDER = registerItem("air_bladder", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "air_bladder")))));

    public static final Item PAST_ECHOES = registerItem("past_echoes", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "past_echoes")))));

    public static final Item TOTEM_SHARD = registerItem("totem_shard", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "totem_shard")))));

    public static final Item TWINE = registerItem("twine", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "twine")))));
        
    public static final Item WAXEN_WINGS = registerItem("waxen_wings", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "waxen_wings")))));

    //
    // Tier 0 (Flint)
    //
    public static final Item FLINT_AND_TINDER = registerItem("flint_and_tinder", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "flint_and_tinder")))));

    public static final Item FLINT_AXE = registerItem("flint_axe", new AxeItem(ModToolMaterials.FLINT, Tools.Stats.baseDamage(Tools.Type.AXE), Tools.Stats.attackSpeed(Tools.Type.AXE), new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "flint_axe")))));

    public static final Item FLINT_BOOTS = registerItem("flint_boots", new Item(new Item.Settings().armor(ModArmorMaterials.FLINT, EquipmentType.BOOTS).maxDamage(EquipmentType.BOOTS.getMaxDamage(Armors.Stats.durability(Armors.Tier.FLINT)))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "flint_boots")))));

    public static final Item FLINT_CHESTPLATE = registerItem("flint_chestplate", new Item(new Item.Settings().armor(ModArmorMaterials.FLINT, EquipmentType.CHESTPLATE).maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(Armors.Stats.durability(Armors.Tier.FLINT)))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "flint_chestplate")))));

    public static final Item FLINT_HELMET = registerItem("flint_helmet", new Item(new Item.Settings().armor(ModArmorMaterials.FLINT, EquipmentType.HELMET).maxDamage(EquipmentType.HELMET.getMaxDamage(Armors.Stats.durability(Armors.Tier.FLINT)))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "flint_helmet")))));

    public static final Item FLINT_LEGGINGS = registerItem("flint_leggings", new Item(new Item.Settings().armor(ModArmorMaterials.FLINT, EquipmentType.LEGGINGS).maxDamage(EquipmentType.LEGGINGS.getMaxDamage(Armors.Stats.durability(Armors.Tier.FLINT)))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "flint_leggings")))));

    public static final Item FLINT_KNIFE = registerItem("flint_knife", new Item.Settings().sword(ModToolMaterials.FLINT, Tools.Stats.baseDamage(Tools.Type.SWORD), Tools.Stats.attackSpeed(Tools.Type.SWORD))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "flint_knife"))));

    public static final Item FLINT_PICKAXE = registerItem("flint_pickaxe", new Item.Settings().pickaxe(ModToolMaterials.FLINT, Tools.Stats.baseDamage(Tools.Type.PICKAXE), Tools.Stats.attackSpeed(Tools.Type.PICKAXE))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "flint_pickaxe"))));
    //
    // Tier 3 (Copper)
    //
    public static final Item COPPER_AXE = registerItem("copper_axe", new AxeItem(ModToolMaterials.COPPER, Tools.Stats.baseDamage(Tools.Type.AXE), Tools.Stats.attackSpeed(Tools.Type.AXE), new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "copper_axe")))));

    public static final Item COPPER_BOOTS = registerItem("copper_boots", new Item(new Item.Settings().armor(ModArmorMaterials.COPPER, EquipmentType.BOOTS).maxDamage(EquipmentType.BOOTS.getMaxDamage(Armors.Stats.durability(Armors.Tier.COPPER)))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "copper_boots")))));

    public static final Item COPPER_CHESTPLATE = registerItem("copper_chestplate", new Item(new Item.Settings().armor(ModArmorMaterials.COPPER, EquipmentType.CHESTPLATE).maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(Armors.Stats.durability(Armors.Tier.COPPER)))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "copper_chestplate")))));

    public static final Item COPPER_HELMET = registerItem("copper_helmet", new Item(new Item.Settings().armor(ModArmorMaterials.COPPER, EquipmentType.HELMET).maxDamage(EquipmentType.HELMET.getMaxDamage(Armors.Stats.durability(Armors.Tier.COPPER)))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "copper_helmet")))));

    public static final Item COPPER_HOE = registerItem("copper_hoe", new HoeItem(ModToolMaterials.COPPER, Tools.Stats.baseDamage(Tools.Type.HOE), Tools.Stats.attackSpeed(Tools.Type.HOE), new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "copper_hoe")))));

    public static final Item COPPER_LEGGINGS = registerItem("copper_leggings", new Item(new Item.Settings().armor(ModArmorMaterials.COPPER, EquipmentType.LEGGINGS).maxDamage(EquipmentType.LEGGINGS.getMaxDamage(Armors.Stats.durability(Armors.Tier.COPPER)))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "copper_leggings")))));

    public static final Item COPPER_PAN = registerItem("copper_pan", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "copper_pan")))));

    public static final Item COPPER_PICKAXE = registerItem("copper_pickaxe", new Item.Settings().pickaxe(ModToolMaterials.COPPER, Tools.Stats.baseDamage(Tools.Type.PICKAXE), Tools.Stats.attackSpeed(Tools.Type.PICKAXE))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "copper_pickaxe"))));

    public static final Item COPPER_SHOVEL = registerItem("copper_shovel", new ShovelItem(ModToolMaterials.COPPER, Tools.Stats.baseDamage(Tools.Type.SHOVEL), Tools.Stats.attackSpeed(Tools.Type.SHOVEL), new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "copper_shovel")))));

    public static final Item COPPER_SWORD = registerItem("copper_sword", new Item.Settings().sword(ModToolMaterials.COPPER, Tools.Stats.baseDamage(Tools.Type.SWORD), Tools.Stats.attackSpeed(Tools.Type.SWORD))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "copper_sword"))));
    //
    // Tier 4 (Silver)
    //
    public static final Item SILVER_AXE = registerItem("silver_axe", new AxeItem(ModToolMaterials.SILVER, Tools.Stats.baseDamage(Tools.Type.AXE), Tools.Stats.attackSpeed(Tools.Type.AXE), new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "silver_axe")))));

    public static final Item SILVER_BOOTS = registerItem("silver_boots", new Item(new Item.Settings().armor(ModArmorMaterials.SILVER, EquipmentType.BOOTS).maxDamage(EquipmentType.BOOTS.getMaxDamage(Armors.Stats.durability(Armors.Tier.SILVER)))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "silver_boots")))));

    public static final Item SILVER_CHESTPLATE = registerItem("silver_chestplate", new Item(new Item.Settings().armor(ModArmorMaterials.SILVER, EquipmentType.CHESTPLATE).maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(Armors.Stats.durability(Armors.Tier.SILVER)))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "silver_chestplate")))));

    public static final Item SILVER_HELMET = registerItem("silver_helmet", new Item(new Item.Settings().armor(ModArmorMaterials.SILVER, EquipmentType.HELMET).maxDamage(EquipmentType.HELMET.getMaxDamage(Armors.Stats.durability(Armors.Tier.SILVER)))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "silver_helmet")))));

    public static final Item SILVER_HOE = registerItem("silver_hoe", new HoeItem(ModToolMaterials.SILVER, Tools.Stats.baseDamage(Tools.Type.HOE), Tools.Stats.attackSpeed(Tools.Type.HOE), new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "silver_hoe")))));

    public static final Item RAW_SILVER = registerItem("raw_silver", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "raw_silver")))));

    public static final Item SILVER_INGOT = registerItem("silver_ingot", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "silver_ingot")))));

    public static final Item SILVER_LEGGINGS = registerItem("silver_leggings", new Item(new Item.Settings().armor(ModArmorMaterials.SILVER, EquipmentType.LEGGINGS).maxDamage(EquipmentType.LEGGINGS.getMaxDamage(Armors.Stats.durability(Armors.Tier.SILVER)))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "silver_leggings")))));

    public static final Item SILVER_PICKAXE = registerItem("silver_pickaxe", new Item.Settings().pickaxe(ModToolMaterials.SILVER, Tools.Stats.baseDamage(Tools.Type.PICKAXE), Tools.Stats.attackSpeed(Tools.Type.PICKAXE))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "silver_pickaxe"))));

    public static final Item SILVER_SHOVEL = registerItem("silver_shovel", new ShovelItem(ModToolMaterials.SILVER, Tools.Stats.baseDamage(Tools.Type.SHOVEL), Tools.Stats.attackSpeed(Tools.Type.SHOVEL), new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "silver_shovel")))));

    public static final Item SILVER_SWORD = registerItem("silver_sword", new Item.Settings().sword(ModToolMaterials.SILVER, Tools.Stats.baseDamage(Tools.Type.SWORD), Tools.Stats.attackSpeed(Tools.Type.SWORD))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "silver_sword"))));
    //
    // Tier 7 (Mithril)
    //
    public static final Item MITHRIL_AXE = registerItem("mithril_axe", new AxeItem(ModToolMaterials.MITHRIL, Tools.Stats.baseDamage(Tools.Type.AXE), Tools.Stats.attackSpeed(Tools.Type.AXE), new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "mithril_axe")))));

    public static final Item MITHRIL_BOOTS = registerItem("mithril_boots", new Item(new Item.Settings().armor(ModArmorMaterials.MITHRIL, EquipmentType.BOOTS).maxDamage(EquipmentType.BOOTS.getMaxDamage(Armors.Stats.durability(Armors.Tier.MITHRIL)))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "mithril_boots")))));

    public static final Item MITHRIL_CHESTPLATE = registerItem("mithril_chestplate", new Item(new Item.Settings().armor(ModArmorMaterials.MITHRIL, EquipmentType.CHESTPLATE).maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(Armors.Stats.durability(Armors.Tier.MITHRIL)))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "mithril_chestplate")))));

    public static final Item MITHRIL_HELMET = registerItem("mithril_helmet", new Item(new Item.Settings().armor(ModArmorMaterials.MITHRIL, EquipmentType.HELMET).maxDamage(EquipmentType.HELMET.getMaxDamage(Armors.Stats.durability(Armors.Tier.MITHRIL)))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "mithril_helmet")))));

    public static final Item MITHRIL_HOE = registerItem("mithril_hoe", new HoeItem(ModToolMaterials.MITHRIL, Tools.Stats.baseDamage(Tools.Type.HOE), Tools.Stats.attackSpeed(Tools.Type.HOE), new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "mithril_hoe")))));

    public static final Item RAW_MITHRIL = registerItem("raw_mithril", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "raw_mithril")))));

    public static final Item MITHRIL_INGOT = registerItem("mithril_ingot", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "mithril_ingot")))));

    public static final Item MITHRIL_LEGGINGS = registerItem("mithril_leggings", new Item(new Item.Settings().armor(ModArmorMaterials.MITHRIL, EquipmentType.LEGGINGS).maxDamage(EquipmentType.LEGGINGS.getMaxDamage(Armors.Stats.durability(Armors.Tier.MITHRIL)))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "mithril_leggings")))));

    public static final Item MITHRIL_PICKAXE = registerItem("mithril_pickaxe", new Item.Settings().pickaxe(ModToolMaterials.MITHRIL, Tools.Stats.baseDamage(Tools.Type.PICKAXE), Tools.Stats.attackSpeed(Tools.Type.PICKAXE))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "mithril_pickaxe"))));

    public static final Item MITHRIL_SHOVEL = registerItem("mithril_shovel", new ShovelItem(ModToolMaterials.MITHRIL, Tools.Stats.baseDamage(Tools.Type.SHOVEL), Tools.Stats.attackSpeed(Tools.Type.SHOVEL), new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "mithril_shovel")))));

    public static final Item MITHRIL_SWORD = registerItem("mithril_sword", new Item.Settings().sword(ModToolMaterials.MITHRIL, Tools.Stats.baseDamage(Tools.Type.SWORD), Tools.Stats.attackSpeed(Tools.Type.SWORD))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "mithril_sword"))));
    //
    // Tier 8 (Diamond)
    //
    public static final Item RAW_DIAMOND = registerItem("raw_diamond", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "raw_diamond")))));

    public static final Item DIAMOND_ELYTRA = registerItem("diamond_elytra", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "diamond_elytra")))));
    //
    // Tier 9 (Netherite)
    //
    public static final Item NETHERITE_ELYTRA = registerItem("netherite_elytra", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "netherite_elytra")))));
    //
    // Tier 10 (Enderium)
    //
    public static final Item ENDERIUM_AXE = registerItem("enderium_axe", new AxeItem(ModToolMaterials.ENDERIUM, Tools.Stats.baseDamage(Tools.Type.AXE), Tools.Stats.attackSpeed(Tools.Type.AXE), new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "enderium_axe")))));

    public static final Item ENDERIUM_BOOTS = registerItem("enderium_boots", new Item(new Item.Settings().armor(ModArmorMaterials.ENDERIUM, EquipmentType.BOOTS).maxDamage(EquipmentType.BOOTS.getMaxDamage(Armors.Stats.durability(Armors.Tier.ENDERIUM)))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "enderium_boots")))));

    public static final Item ENDERIUM_CHESTPLATE = registerItem("enderium_chestplate", new Item(new Item.Settings().armor(ModArmorMaterials.ENDERIUM, EquipmentType.CHESTPLATE).maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(Armors.Stats.durability(Armors.Tier.ENDERIUM)))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "enderium_chestplate")))));

    public static final Item ENDERIUM_ELYTRA = registerItem("enderium_elytra", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "enderium_elytra")))));

    public static final Item RAW_ENDERIUM = registerItem("raw_enderium", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "raw_enderium")))));

    public static final Item ENDERIUM_GEM = registerItem("enderium_gem", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "enderium_gem")))));

    public static final Item ENDERIUM_HELMET = registerItem("enderium_helmet", new Item(new Item.Settings().armor(ModArmorMaterials.ENDERIUM, EquipmentType.HELMET).maxDamage(EquipmentType.HELMET.getMaxDamage(Armors.Stats.durability(Armors.Tier.ENDERIUM)))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "enderium_helmet")))));

    public static final Item ENDERIUM_HOE = registerItem("enderium_hoe", new HoeItem(ModToolMaterials.ENDERIUM, Tools.Stats.baseDamage(Tools.Type.HOE), Tools.Stats.attackSpeed(Tools.Type.HOE), new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "enderium_hoe")))));

    public static final Item ENDERIUM_LEGGINGS = registerItem("enderium_leggings", new Item(new Item.Settings().armor(ModArmorMaterials.ENDERIUM, EquipmentType.LEGGINGS).maxDamage(EquipmentType.LEGGINGS.getMaxDamage(Armors.Stats.durability(Armors.Tier.ENDERIUM)))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "enderium_leggings")))));

    public static final Item ENDERIUM_PICKAXE = registerItem("enderium_pickaxe", new Item.Settings().pickaxe(ModToolMaterials.ENDERIUM, Tools.Stats.baseDamage(Tools.Type.PICKAXE), Tools.Stats.attackSpeed(Tools.Type.PICKAXE))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "enderium_pickaxe"))));

    public static final Item ENDERIUM_SHOVEL = registerItem("enderium_shovel", new ShovelItem(ModToolMaterials.ENDERIUM, Tools.Stats.baseDamage(Tools.Type.SHOVEL), Tools.Stats.attackSpeed(Tools.Type.SHOVEL), new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "enderium_shovel")))));

    public static final Item ENDERIUM_SWORD = registerItem("enderium_sword", new Item.Settings().sword(ModToolMaterials.ENDERIUM, Tools.Stats.baseDamage(Tools.Type.SWORD), Tools.Stats.attackSpeed(Tools.Type.SWORD))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "enderium_sword"))));
    //
    // Tier 11 (Tenebris)
    //
    public static final Item TENEBRIS_AXE = registerItem("tenebris_axe", new AxeItem(ModToolMaterials.TENEBRIS, Tools.Stats.baseDamage(Tools.Type.AXE), Tools.Stats.attackSpeed(Tools.Type.AXE), new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "tenebris_axe")))));

    public static final Item TENEBRIS_BOOTS = registerItem("tenebris_boots", new Item(new Item.Settings().armor(ModArmorMaterials.TENEBRIS, EquipmentType.BOOTS).maxDamage(EquipmentType.BOOTS.getMaxDamage(Armors.Stats.durability(Armors.Tier.TENEBRIS)))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "tenebris_boots")))));

    public static final Item TENEBRIS_CHESTPLATE = registerItem("tenebris_chestplate", new Item(new Item.Settings().armor(ModArmorMaterials.TENEBRIS, EquipmentType.CHESTPLATE).maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(Armors.Stats.durability(Armors.Tier.TENEBRIS)))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "tenebris_chestplate")))));

    public static final Item TENEBRIS_ELYTRA = registerItem("tenebris_elytra", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "tenebris_elytra")))));

    public static final Item RAW_TENEBRIS = registerItem("raw_tenebris", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "raw_tenebris")))));

    public static final Item TENEBRIS_HELMET = registerItem("tenebris_helmet", new Item(new Item.Settings().armor(ModArmorMaterials.TENEBRIS, EquipmentType.HELMET).maxDamage(EquipmentType.HELMET.getMaxDamage(Armors.Stats.durability(Armors.Tier.TENEBRIS)))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "tenebris_helmet")))));

    public static final Item TENEBRIS_HOE = registerItem("tenebris_hoe", new HoeItem(ModToolMaterials.TENEBRIS, Tools.Stats.baseDamage(Tools.Type.HOE), Tools.Stats.attackSpeed(Tools.Type.HOE), new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "tenebris_hoe")))));

    public static final Item TENEBRIS_INGOT = registerItem("tenebris_ingot", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "tenebris_ingot")))));

    public static final Item TENEBRIS_LEGGINGS = registerItem("tenebris_leggings", new Item(new Item.Settings().armor(ModArmorMaterials.TENEBRIS, EquipmentType.LEGGINGS).maxDamage(EquipmentType.LEGGINGS.getMaxDamage(Armors.Stats.durability(Armors.Tier.TENEBRIS)))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "tenebris_leggings")))));

    public static final Item TENEBRIS_PICKAXE = registerItem("tenebris_pickaxe", new Item.Settings().pickaxe(ModToolMaterials.TENEBRIS, Tools.Stats.baseDamage(Tools.Type.PICKAXE), Tools.Stats.attackSpeed(Tools.Type.PICKAXE))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "tenebris_pickaxe"))));

    public static final Item TENEBRIS_SHOVEL = registerItem("tenebris_shovel", new ShovelItem(ModToolMaterials.TENEBRIS, Tools.Stats.baseDamage(Tools.Type.SHOVEL), Tools.Stats.attackSpeed(Tools.Type.SHOVEL), new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "tenebris_shovel")))));

    public static final Item TENEBRIS_SWORD = registerItem("tenebris_sword", new Item.Settings().sword(ModToolMaterials.TENEBRIS, Tools.Stats.baseDamage(Tools.Type.SWORD), Tools.Stats.attackSpeed(Tools.Type.SWORD))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "tenebris_sword"))));

    public static final Item TENEBRIS_VOID = registerItem("tenebris_void", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "tenebris_void")))));
    //
    // Tier 12 (Zenith)
    //
    public static final Item ZENITH_AXE = registerItem("zenith_axe", new AxeItem(ModToolMaterials.ZENITH, Tools.Stats.baseDamage(Tools.Type.AXE), Tools.Stats.attackSpeed(Tools.Type.AXE), new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "zenith_axe")))));

    public static final Item ZENITH_BOOTS = registerItem("zenith_boots", new Item(new Item.Settings().armor(ModArmorMaterials.ZENITH, EquipmentType.BOOTS).maxDamage(EquipmentType.BOOTS.getMaxDamage(Armors.Stats.durability(Armors.Tier.ZENITH)))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "zenith_boots")))));

    public static final Item ZENITH_CHESTPLATE = registerItem("zenith_chestplate", new Item(new Item.Settings().armor(ModArmorMaterials.ZENITH, EquipmentType.CHESTPLATE).maxDamage(EquipmentType.CHESTPLATE.getMaxDamage(Armors.Stats.durability(Armors.Tier.ZENITH)))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "zenith_chestplate")))));

    public static final Item ZENITH_ELYTRA = registerItem("zenith_elytra", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "zenith_elytra")))));

    public static final Item ZENITH_HELMET = registerItem("zenith_helmet", new Item(new Item.Settings().armor(ModArmorMaterials.ZENITH, EquipmentType.HELMET).maxDamage(EquipmentType.HELMET.getMaxDamage(Armors.Stats.durability(Armors.Tier.ZENITH)))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "zenith_helmet")))));

    public static final Item ZENITH_HOE = registerItem("zenith_hoe", new HoeItem(ModToolMaterials.ZENITH, Tools.Stats.baseDamage(Tools.Type.HOE), Tools.Stats.attackSpeed(Tools.Type.HOE), new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "zenith_hoe")))));

    public static final Item ZENITH_INGOT = registerItem("zenith_ingot", new Item(new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "zenith_ingot")))));

    public static final Item ZENITH_LEGGINGS = registerItem("zenith_leggings", new Item(new Item.Settings().armor(ModArmorMaterials.ZENITH, EquipmentType.LEGGINGS).maxDamage(EquipmentType.LEGGINGS.getMaxDamage(Armors.Stats.durability(Armors.Tier.ZENITH)))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "zenith_leggings")))));

    public static final Item ZENITH_PICKAXE = registerItem("zenith_pickaxe", new Item.Settings().pickaxe(ModToolMaterials.ZENITH, Tools.Stats.baseDamage(Tools.Type.PICKAXE), Tools.Stats.attackSpeed(Tools.Type.PICKAXE))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "zenith_pickaxe"))));

    public static final Item ZENITH_SHOVEL = registerItem("zenith_shovel", new ShovelItem(ModToolMaterials.ZENITH, Tools.Stats.baseDamage(Tools.Type.SHOVEL), Tools.Stats.attackSpeed(Tools.Type.SHOVEL), new Item.Settings()
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "zenith_shovel")))));

    public static final Item ZENITH_SWORD = registerItem("zenith_sword", new Item.Settings().sword(ModToolMaterials.ZENITH, Tools.Stats.baseDamage(Tools.Type.SWORD), Tools.Stats.attackSpeed(Tools.Type.SWORD))
        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, "zenith_sword"))));

    private static Item registerItem(String path, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, path), item);
    }

    private static Item registerItem(String path, Item.Settings settings) {
        Item item = new Item(settings);
        return Registry.register(Registries.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, path), item);
    }

    public static final RegistryKey<ItemGroup> VETERAN_GROUP_KEY =
        RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(VeteranDifficulty.MOD_ID, "main"));

    public static void registerItemGroup() {
        Registry.register(Registries.ITEM_GROUP, VETERAN_GROUP_KEY,
            FabricItemGroup.builder()
                .displayName(Text.translatable("itemGroup." + VeteranDifficulty.MOD_ID + ".main"))
                .icon(() -> new ItemStack(ModItems.ZENITH_SWORD))
                .entries((ctx, entries) -> {
                    Registries.ITEM.stream()
                        .filter(i -> Registries.ITEM.getId(i).getNamespace().equals(VeteranDifficulty.MOD_ID))
                        .forEach(entries::add);
                })
                .build()
        );
    }
    
    public static void registerModItems() {
        VeteranDifficulty.LOGGER.info("Registering Mod Items for " + VeteranDifficulty.MOD_ID);
    }
}
