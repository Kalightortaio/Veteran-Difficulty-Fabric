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
    public static final TagKey<Block> INCORRECT_FOR_FLINT_TOOL = createTag(BLOCK, "incorrect_for_flint_tool");
    public static final TagKey<Block> INCORRECT_FOR_COPPER_TOOL = createTag(BLOCK, "incorrect_for_copper_tool");
    public static final TagKey<Block> INCORRECT_FOR_SILVER_TOOL = createTag(BLOCK, "incorrect_for_silver_tool");
    public static final TagKey<Block> INCORRECT_FOR_MITHRIL_TOOL = createTag(BLOCK, "incorrect_for_mithril_tool");
    public static final TagKey<Block> INCORRECT_FOR_ENDERIUM_TOOL = createTag(BLOCK, "incorrect_for_enderium_tool");
    public static final TagKey<Block> INCORRECT_FOR_TENEBRIS_TOOL = createTag(BLOCK, "incorrect_for_tenebris_tool");
    public static final TagKey<Block> INCORRECT_FOR_ZENITH_TOOL = createTag(BLOCK, "incorrect_for_zenith_tool");
    public static final TagKey<Item> FLINT_TOOL_MATERIALS = createTag(ITEM, "flint_tool_materials");
    public static final TagKey<Item> COPPER_TOOL_MATERIALS = createTag(ITEM, "copper_tool_materials");
    public static final TagKey<Item> SILVER_TOOL_MATERIALS = createTag(ITEM, "silver_tool_materials");
    public static final TagKey<Item> MITHRIL_TOOL_MATERIALS = createTag(ITEM, "mithril_tool_materials");
    public static final TagKey<Item> ENDERIUM_TOOL_MATERIALS = createTag(ITEM, "enderium_tool_materials");
    public static final TagKey<Item> TENEBRIS_TOOL_MATERIALS = createTag(ITEM, "tenebris_tool_materials");
    public static final TagKey<Item> ZENITH_TOOL_MATERIALS = createTag(ITEM, "zenith_tool_materials");
    public static final TagKey<Item> REGROWTH = createTag(ITEM, "regrowth");
    public static final TagKey<EntityType<?>> ALIVE = createTag(ENTITY_TYPE, "alivemobs");

    private static <T> TagKey<T> createTag(RegistryKey<? extends Registry<T>> type, String name) {
        return TagKey.of(type, Identifier.of(VeteranDifficulty.MOD_ID, name));
    }
}
