package com.kalightortaio.veterandifficulty.item;

import com.kalightortaio.veterandifficulty.systems.internal.ModTags;
import com.kalightortaio.veterandifficulty.util.ToolStats;
import com.kalightortaio.veterandifficulty.util.ToolTier;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.TagKey;

public class ModToolMaterials {
    @SuppressWarnings("unchecked")
    private static ToolMaterial make(ToolTier tier) {
        try {
            TagKey<Block> incorrect = (TagKey<Block>)
                ModTags.class.getField("INCORRECT_FOR_" + tier.name() + "_TOOL")
                             .get(null);

            TagKey<Item> material = (TagKey<Item>)
                ModTags.class.getField(tier.name() + "_TOOL_MATERIALS")
                             .get(null);

            return new ToolMaterial(incorrect, ToolStats.durability(tier), ToolStats.miningSpeed(tier), ToolStats.attackDamage(tier), ToolStats.enchantability(tier), material);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Missing tag constant for tier " + tier, e);
        }
    }

    public static final ToolMaterial FLINT    = make(ToolTier.FLINT);
    public static final ToolMaterial COPPER   = make(ToolTier.COPPER);
    public static final ToolMaterial SILVER   = make(ToolTier.SILVER);
    public static final ToolMaterial MITHRIL  = make(ToolTier.MITHRIL);
    public static final ToolMaterial ENDERIUM = make(ToolTier.ENDERIUM);
    public static final ToolMaterial TENEBRIS = make(ToolTier.TENEBRIS);
    public static final ToolMaterial ZENITH   = make(ToolTier.ZENITH);
    
    private ModToolMaterials() {}
}
