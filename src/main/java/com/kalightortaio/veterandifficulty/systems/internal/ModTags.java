package com.kalightortaio.veterandifficulty.systems.internal;

import com.kalightortaio.veterandifficulty.VeteranDifficulty;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    private static final RegistryKey<Registry<Block>> BLOCK = RegistryKeys.BLOCK;
    private static final RegistryKey<Registry<Item>> ITEM = RegistryKeys.ITEM;
    private static final RegistryKey<Registry<EntityType<?>>> ENTITY_TYPE = RegistryKeys.ENTITY_TYPE;

    public static final TagKey<Block> AIR = createTag(BLOCK, "air");
    public static final TagKey<Block> MONUMENT = createTag(BLOCK, "monument");
    public static final TagKey<Block> SEA_FAUNA = createTag(BLOCK, "sea_fauna");
    public static final TagKey<Block> SAPLING_SURFACE = createTag(BLOCK, "sapling_surface");
    public static final TagKey<Block> GLASS_PANES = createTag(BLOCK, "glass_panes");
    public static final TagKey<Block> NEEDS_FLINT_TOOL = createTag(BLOCK, "needs_flint_tool");
    public static final TagKey<Block> NEEDS_WOODEN_TOOL = createTag(BLOCK, "needs_wooden_tool");
    public static final TagKey<Block> NEEDS_COPPER_TOOL = createTag(BLOCK, "needs_copper_tool");
    public static final TagKey<Block> NEEDS_SILVER_TOOL = createTag(BLOCK, "needs_silver_tool");
    public static final TagKey<Block> NEEDS_GOLD_TOOL = createTag(BLOCK, "needs_gold_tool");
    public static final TagKey<Block> NEEDS_MITHRIL_TOOL = createTag(BLOCK, "needs_mithril_tool");
    public static final TagKey<Block> NEEDS_NETHERITE_TOOL = createTag(BLOCK, "needs_netherite_tool");
    public static final TagKey<Block> NEEDS_ENDERIUM_TOOL = createTag(BLOCK, "needs_enderium_tool");
    public static final TagKey<Block> NEEDS_TENEBRIS_TOOL = createTag(BLOCK, "needs_tenebris_tool");
    public static final TagKey<Block> NEEDS_ZENITH_TOOL = createTag(BLOCK, "needs_zenith_tool");
    public static final TagKey<Block> INCORRECT_FOR_NO_TOOL = createTag(BLOCK, "incorrect_for_no_tool");
    public static final TagKey<Block> INCORRECT_FOR_FLINT_TOOL = createTag(BLOCK, "incorrect_for_flint_tool");
    public static final TagKey<Block> INCORRECT_FOR_COPPER_TOOL = createTag(BLOCK, "incorrect_for_copper_tool");
    public static final TagKey<Block> INCORRECT_FOR_SILVER_TOOL = createTag(BLOCK, "incorrect_for_silver_tool");
    public static final TagKey<Block> INCORRECT_FOR_MITHRIL_TOOL = createTag(BLOCK, "incorrect_for_mithril_tool");
    public static final TagKey<Block> INCORRECT_FOR_ENDERIUM_TOOL = createTag(BLOCK, "incorrect_for_enderium_tool");
    public static final TagKey<Block> INCORRECT_FOR_TENEBRIS_TOOL = createTag(BLOCK, "incorrect_for_tenebris_tool");
    public static final TagKey<Block> INCORRECT_FOR_ZENITH_TOOL = createTag(BLOCK, "incorrect_for_zenith_tool");
    public static final TagKey<Item> FLINT_TIER = createTag(ITEM, "flint_tier");
    public static final TagKey<Item> WOODEN_TIER = createTag(ITEM, "wooden_tier");
    public static final TagKey<Item> STONE_TIER = createTag(ITEM, "stone_tier");
    public static final TagKey<Item> COPPER_TIER = createTag(ITEM, "copper_tier");
    public static final TagKey<Item> SILVER_TIER = createTag(ITEM, "silver_tier");
    public static final TagKey<Item> GOLD_TIER = createTag(ITEM, "gold_tier");
    public static final TagKey<Item> IRON_TIER = createTag(ITEM, "iron_tier");
    public static final TagKey<Item> MITHRIL_TIER = createTag(ITEM, "mithril_tier");
    public static final TagKey<Item> DIAMOND_TIER = createTag(ITEM, "diamond_tier");
    public static final TagKey<Item> NETHERITE_TIER = createTag(ITEM, "netherite_tier");
    public static final TagKey<Item> ENDERIUM_TIER = createTag(ITEM, "enderium_tier");
    public static final TagKey<Item> TENEBRIS_TIER = createTag(ITEM, "tenebris_tier");
    public static final TagKey<Item> ZENITH_TIER = createTag(ITEM, "zenith_tier");
    public static final TagKey<Item> FLINT_TOOL_MATERIALS = createTag(ITEM, "flint_tool_materials");
    public static final TagKey<Item> COPPER_TOOL_MATERIALS = createTag(ITEM, "copper_tool_materials");
    public static final TagKey<Item> SILVER_TOOL_MATERIALS = createTag(ITEM, "silver_tool_materials");
    public static final TagKey<Item> MITHRIL_TOOL_MATERIALS = createTag(ITEM, "mithril_tool_materials");
    public static final TagKey<Item> ENDERIUM_TOOL_MATERIALS = createTag(ITEM, "enderium_tool_materials");
    public static final TagKey<Item> TENEBRIS_TOOL_MATERIALS = createTag(ITEM, "tenebris_tool_materials");
    public static final TagKey<Item> ZENITH_TOOL_MATERIALS = createTag(ITEM, "zenith_tool_materials");
    public static final TagKey<Item> REPAIRS_FLINT_ARMOR = createTag(ITEM, "repairs_flint_armor");
    public static final TagKey<Item> REPAIRS_COPPER_ARMOR = createTag(ITEM, "repairs_copper_armor");
    public static final TagKey<Item> REPAIRS_SILVER_ARMOR = createTag(ITEM, "repairs_silver_armor");
    public static final TagKey<Item> REPAIRS_MITHRIL_ARMOR = createTag(ITEM, "repairs_mithril_armor");
    public static final TagKey<Item> REPAIRS_ENDERIUM_ARMOR = createTag(ITEM, "repairs_enderium_armor");
    public static final TagKey<Item> REPAIRS_TENEBRIS_ARMOR = createTag(ITEM, "repairs_tenebris_armor");
    public static final TagKey<Item> REPAIRS_ZENITH_ARMOR = createTag(ITEM, "repairs_zenith_armor");
    public static final TagKey<Item> REGROWTH = createTag(ITEM, "regrowth");
    public static final TagKey<EntityType<?>> ALIVE = createTag(ENTITY_TYPE, "alivemobs");

    private static <T> TagKey<T> createTag(RegistryKey<? extends Registry<T>> type, String name) {
        return TagKey.of(type, Identifier.of(VeteranDifficulty.MOD_ID, name));
    }
}
