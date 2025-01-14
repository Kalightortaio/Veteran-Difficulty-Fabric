package com.kalightortaio.veterandifficulty.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

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
            return new ToolMaterial(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 106, 1.0F, 0.5F, 8, ItemTags.WOODEN_TOOL_MATERIALS);
        } else if (blockTag.equals(BlockTags.INCORRECT_FOR_STONE_TOOL)) {
            return new ToolMaterial(BlockTags.INCORRECT_FOR_STONE_TOOL, 176, 2.0F, 1.0F, 10, ItemTags.STONE_TOOL_MATERIALS);
        } else if (blockTag.equals(BlockTags.INCORRECT_FOR_GOLD_TOOL)) {
            return new ToolMaterial(BlockTags.INCORRECT_FOR_GOLD_TOOL, 807, 5.0F, 2.5F, 16, ItemTags.GOLD_TOOL_MATERIALS);
        } else if (blockTag.equals(BlockTags.INCORRECT_FOR_IRON_TOOL)) {
            return new ToolMaterial(BlockTags.INCORRECT_FOR_IRON_TOOL, 1339, 6.0F, 3.0F, 18, ItemTags.IRON_TOOL_MATERIALS);
        } else if (blockTag.equals(BlockTags.INCORRECT_FOR_DIAMOND_TOOL)) {
            return new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 3690, 8.0F, 4.0F, 22, ItemTags.DIAMOND_TOOL_MATERIALS);
        } else if (blockTag.equals(BlockTags.INCORRECT_FOR_NETHERITE_TOOL)) {
            return new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 6126, 9.0F, 4.5F, 24, ItemTags.NETHERITE_TOOL_MATERIALS);
        }
        return new ToolMaterial(blockTag, durability, speed, attackDamage, enchantmentValue, itemTag);
    }
}
