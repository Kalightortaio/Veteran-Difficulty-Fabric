package com.kalightortaio.veterandifficulty.item;

import com.kalightortaio.veterandifficulty.systems.internal.ModTags;

import net.minecraft.item.ToolMaterial;

public class ModToolMaterials {
    //               // (Inverse Block List, Durability, Speed, Damage, Enchantment, Repair Materials)
    //               // Commented out code are modified vanilla tiers for balancing reference
    public static final ToolMaterial FLINT = new ToolMaterial(ModTags.INCORRECT_FOR_FLINT_TOOL, 64, 0.0F, 0.0F, 6, ModTags.FLINT_TOOL_MATERIALS);
    //               // ToolMaterial WOOD = new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 106, 1.0F, 0.5F, 8, ItemTags.WOODEN_TOOL_MATERIALS);
    //               // ToolMaterial STONE = new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 176, 2.0F, 1.0F, 10, ItemTags.STONE_TOOL_MATERIALS);
    public static final ToolMaterial COPPER = new ToolMaterial(ModTags.INCORRECT_FOR_COPPER_TOOL, 293, 3.0F, 1.5F, 12, ModTags.COPPER_TOOL_MATERIALS);
    public static final ToolMaterial SILVER = new ToolMaterial(ModTags.INCORRECT_FOR_SILVER_TOOL, 486, 4.0F, 2.0F, 14, ModTags.SILVER_TOOL_MATERIALS);
    //               // ToolMaterial GOLD = new ToolMaterial(BlockTags.INCORRECT_FOR_GOLD_TOOL, 807, 5.0F, 2.5F, 16, ItemTags.GOLD_TOOL_MATERIALS);
    //               // ToolMaterial IRON = new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 1339, 6.0F, 3.0F, 18, ItemTags.IRON_TOOL_MATERIALS);
    public static final ToolMaterial MITHRIL = new ToolMaterial(ModTags.INCORRECT_FOR_MITHRIL_TOOL, 2223, 7.0F, 3.5F, 20, ModTags.MITHRIL_TOOL_MATERIALS);
    //               // ToolMaterial DIAMOND = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3690, 8.0F, 4.0F, 22, ItemTags.DIAMOND_TOOL_MATERIALS);
    //               // ToolMaterial NETHERITE = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 6126, 9.0F, 4.5F, 24, ItemTags.NETHERITE_TOOL_MATERIALS);
    public static final ToolMaterial ENDERIUM = new ToolMaterial(ModTags.INCORRECT_FOR_ENDERIUM_TOOL, 10168, 10.0F, 5.0F, 26, ModTags.ENDERIUM_TOOL_MATERIALS);
    public static final ToolMaterial TENEBRIS = new ToolMaterial(ModTags.INCORRECT_FOR_TENEBRIS_TOOL, 16384, 11.0F, 5.5F, 28, ModTags.TENEBRIS_TOOL_MATERIALS);
    public static final ToolMaterial ZENITH = new ToolMaterial(ModTags.INCORRECT_FOR_ZENITH_TOOL, 32768, 12.0F, 6.0F, 30, ModTags.ZENITH_TOOL_MATERIALS);
}
