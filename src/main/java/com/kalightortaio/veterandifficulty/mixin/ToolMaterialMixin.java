package com.kalightortaio.veterandifficulty.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.kalightortaio.veterandifficulty.util.ToolTier;
import com.kalightortaio.veterandifficulty.util.ToolStats;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

@Mixin(ToolMaterial.class)
public class ToolMaterialMixin {

    @Redirect(method = "<clinit>", at = @At(value = "NEW", target = "Lnet/minecraft/item/ToolMaterial;"))
    private static ToolMaterial redirectToolMaterial(TagKey<Block> blockTag, int durability, float speed, float attackDamage, int enchantmentValue, TagKey<Item> itemTag) {
        if (blockTag.equals(BlockTags.INCORRECT_FOR_WOODEN_TOOL)) {
            return new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, ToolStats.durability(ToolTier.WOODEN), ToolStats.miningSpeed(ToolTier.WOODEN), ToolStats.attackDamage(ToolTier.WOODEN), ToolStats.enchantability(ToolTier.WOODEN), ItemTags.WOODEN_TOOL_MATERIALS);
        } else if (blockTag.equals(BlockTags.INCORRECT_FOR_STONE_TOOL)) {
            return new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, ToolStats.durability(ToolTier.STONE), ToolStats.miningSpeed(ToolTier.STONE), ToolStats.attackDamage(ToolTier.STONE), ToolStats.enchantability(ToolTier.STONE), ItemTags.STONE_TOOL_MATERIALS);
        } else if (blockTag.equals(BlockTags.INCORRECT_FOR_GOLD_TOOL)) {
            return new ToolMaterial(BlockTags.INCORRECT_FOR_GOLD_TOOL, ToolStats.durability(ToolTier.GOLD), ToolStats.miningSpeed(ToolTier.GOLD), ToolStats.attackDamage(ToolTier.GOLD), ToolStats.enchantability(ToolTier.GOLD), ItemTags.GOLD_TOOL_MATERIALS);
        } else if (blockTag.equals(BlockTags.INCORRECT_FOR_IRON_TOOL)) {
            return new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, ToolStats.durability(ToolTier.IRON), ToolStats.miningSpeed(ToolTier.IRON), ToolStats.attackDamage(ToolTier.IRON), ToolStats.enchantability(ToolTier.IRON), ItemTags.IRON_TOOL_MATERIALS);
        } else if (blockTag.equals(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)) {
            return new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, ToolStats.durability(ToolTier.DIAMOND), ToolStats.miningSpeed(ToolTier.DIAMOND), ToolStats.attackDamage(ToolTier.DIAMOND), ToolStats.enchantability(ToolTier.DIAMOND), ItemTags.DIAMOND_TOOL_MATERIALS);
        } else if (blockTag.equals(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)) {
            return new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, ToolStats.durability(ToolTier.NETHERITE), ToolStats.miningSpeed(ToolTier.NETHERITE), ToolStats.attackDamage(ToolTier.NETHERITE), ToolStats.enchantability(ToolTier.NETHERITE), ItemTags.NETHERITE_TOOL_MATERIALS);
        }
        return new ToolMaterial(blockTag, durability, speed, attackDamage, enchantmentValue, itemTag);
    }
}
