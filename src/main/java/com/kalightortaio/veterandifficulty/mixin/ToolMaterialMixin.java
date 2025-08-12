package com.kalightortaio.veterandifficulty.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.kalightortaio.veterandifficulty.util.Tools;

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
            return new ToolMaterial(
                BlockTags.INCORRECT_FOR_WOODEN_TOOL, 
                Tools.Stats.durability(Tools.Tier.WOODEN), 
                Tools.Stats.miningSpeed(Tools.Tier.WOODEN), 
                Tools.Stats.attackDamage(Tools.Tier.WOODEN), 
                Tools.Stats.enchantability(Tools.Tier.WOODEN), 
                ItemTags.WOODEN_TOOL_MATERIALS
            );
        } else if (blockTag.equals(BlockTags.INCORRECT_FOR_STONE_TOOL)) {
            return new ToolMaterial(
                BlockTags.INCORRECT_FOR_STONE_TOOL, 
                Tools.Stats.durability(Tools.Tier.STONE), 
                Tools.Stats.miningSpeed(Tools.Tier.STONE), 
                Tools.Stats.attackDamage(Tools.Tier.STONE), 
                Tools.Stats.enchantability(Tools.Tier.STONE), 
                ItemTags.STONE_TOOL_MATERIALS
            );
        } else if (blockTag.equals(BlockTags.INCORRECT_FOR_GOLD_TOOL)) {
            return new ToolMaterial(
                BlockTags.INCORRECT_FOR_GOLD_TOOL, 
                Tools.Stats.durability(Tools.Tier.GOLD), 
                Tools.Stats.miningSpeed(Tools.Tier.GOLD), 
                Tools.Stats.attackDamage(Tools.Tier.GOLD), 
                Tools.Stats.enchantability(Tools.Tier.GOLD), 
                ItemTags.GOLD_TOOL_MATERIALS
            );
        } else if (blockTag.equals(BlockTags.INCORRECT_FOR_IRON_TOOL)) {
            return new ToolMaterial(
                BlockTags.INCORRECT_FOR_IRON_TOOL, 
                Tools.Stats.durability(Tools.Tier.IRON), 
                Tools.Stats.miningSpeed(Tools.Tier.IRON), 
                Tools.Stats.attackDamage(Tools.Tier.IRON), 
                Tools.Stats.enchantability(Tools.Tier.IRON), 
                ItemTags.IRON_TOOL_MATERIALS
            );
        } else if (blockTag.equals(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)) {
            return new ToolMaterial(
                BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 
                Tools.Stats.durability(Tools.Tier.DIAMOND), 
                Tools.Stats.miningSpeed(Tools.Tier.DIAMOND), 
                Tools.Stats.attackDamage(Tools.Tier.DIAMOND), 
                Tools.Stats.enchantability(Tools.Tier.DIAMOND), 
                ItemTags.DIAMOND_TOOL_MATERIALS
            );
        } else if (blockTag.equals(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)) {
            return new ToolMaterial(
                BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 
                Tools.Stats.durability(Tools.Tier.NETHERITE), 
                Tools.Stats.miningSpeed(Tools.Tier.NETHERITE), 
                Tools.Stats.attackDamage(Tools.Tier.NETHERITE), 
                Tools.Stats.enchantability(Tools.Tier.NETHERITE), 
                ItemTags.NETHERITE_TOOL_MATERIALS
            );
        }
        return new ToolMaterial(blockTag, durability, speed, attackDamage, enchantmentValue, itemTag);
    }
}
