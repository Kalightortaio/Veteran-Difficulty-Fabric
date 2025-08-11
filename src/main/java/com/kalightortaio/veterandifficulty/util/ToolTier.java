package com.kalightortaio.veterandifficulty.util;

import com.kalightortaio.veterandifficulty.systems.internal.ModTags;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

public enum ToolTier {
    FLINT     (ModTags.FLINT_TIER,     ModTags.INCORRECT_FOR_FLINT_TOOL),
    WOODEN    (ModTags.WOODEN_TIER,    BlockTags.INCORRECT_FOR_WOODEN_TOOL),
    STONE     (ModTags.STONE_TIER,     BlockTags.INCORRECT_FOR_STONE_TOOL),
    COPPER    (ModTags.COPPER_TIER,    ModTags.INCORRECT_FOR_COPPER_TOOL),
    SILVER    (ModTags.SILVER_TIER,    ModTags.INCORRECT_FOR_SILVER_TOOL),
    GOLD      (ModTags.GOLD_TIER,      BlockTags.INCORRECT_FOR_GOLD_TOOL),
    IRON      (ModTags.IRON_TIER,      BlockTags.INCORRECT_FOR_IRON_TOOL),
    MITHRIL   (ModTags.MITHRIL_TIER,   ModTags.INCORRECT_FOR_MITHRIL_TOOL),
    DIAMOND   (ModTags.DIAMOND_TIER,   BlockTags.INCORRECT_FOR_DIAMOND_TOOL),
    NETHERITE (ModTags.NETHERITE_TIER, BlockTags.INCORRECT_FOR_NETHERITE_TOOL),
    ENDERIUM  (ModTags.ENDERIUM_TIER,  ModTags.INCORRECT_FOR_ENDERIUM_TOOL),
    TENEBRIS  (ModTags.TENEBRIS_TIER,  ModTags.INCORRECT_FOR_TENEBRIS_TOOL),
    ZENITH    (ModTags.ZENITH_TIER,    ModTags.INCORRECT_FOR_ZENITH_TOOL);

    public final TagKey<Item> itemTier;
    public final TagKey<Block> incorrectBlockTag;

    ToolTier(TagKey<Item> itemTier, TagKey<Block> incorrectBlockTag) {
        this.itemTier = itemTier;
        this.incorrectBlockTag = incorrectBlockTag;
    }
}

