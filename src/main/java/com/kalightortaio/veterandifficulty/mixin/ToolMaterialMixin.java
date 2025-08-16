package com.kalightortaio.veterandifficulty.mixin;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.kalightortaio.veterandifficulty.util.Tools;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;

@Mixin(ToolMaterial.class)
public class ToolMaterialMixin {

    @Redirect(method = "<clinit>", at = @At(value = "NEW", target = "Lnet/minecraft/item/ToolMaterial;"))
    private static ToolMaterial redirectToolMaterial(TagKey<Block> blockTag, int durability, float speed, float attackDamage, int enchantmentValue, TagKey<Item> itemTag) {
        Map<TagKey<Block>, Tools.Tier> map = Map.of(
            BlockTags.INCORRECT_FOR_WOODEN_TOOL,    Tools.Tier.WOODEN,
            BlockTags.INCORRECT_FOR_STONE_TOOL,     Tools.Tier.STONE,
            BlockTags.INCORRECT_FOR_GOLD_TOOL,      Tools.Tier.GOLD,
            BlockTags.INCORRECT_FOR_IRON_TOOL,      Tools.Tier.IRON,
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL,   Tools.Tier.DIAMOND,
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL, Tools.Tier.NETHERITE
        );
        Tools.Tier tier = map.get(blockTag);
        if (tier == null) {
            return new ToolMaterial(blockTag, durability, speed, attackDamage, enchantmentValue, itemTag);
        }
        return new ToolMaterial(blockTag, Tools.Stats.durability(tier), Tools.Stats.miningSpeed(tier), Tools.Stats.attackDamage(tier), Tools.Stats.enchantability(tier), itemTag);
    }
}
