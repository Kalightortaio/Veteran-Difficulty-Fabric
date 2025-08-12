package com.kalightortaio.veterandifficulty.item;

import com.kalightortaio.veterandifficulty.systems.internal.ModTags;
import com.kalightortaio.veterandifficulty.util.Tools;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.TagKey;

public class ModToolMaterials {
    @SuppressWarnings("unchecked")
    private static ToolMaterial make(Tools.Tier tier) {
        try {
            TagKey<Block> incorrect = (TagKey<Block>)
                ModTags.class.getField("INCORRECT_FOR_" + tier.name() + "_TOOL")
                             .get(null);

            TagKey<Item> material = (TagKey<Item>)
                ModTags.class.getField(tier.name() + "_TOOL_MATERIALS")
                             .get(null);

            return new ToolMaterial(incorrect, Tools.Stats.durability(tier), Tools.Stats.miningSpeed(tier), Tools.Stats.attackDamage(tier), Tools.Stats.enchantability(tier), material);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Missing tag constant for tier " + tier, e);
        }
    }

    public static final ToolMaterial FLINT    = make(Tools.Tier.FLINT);
    public static final ToolMaterial COPPER   = make(Tools.Tier.COPPER);
    public static final ToolMaterial SILVER   = make(Tools.Tier.SILVER);
    public static final ToolMaterial MITHRIL  = make(Tools.Tier.MITHRIL);
    public static final ToolMaterial ENDERIUM = make(Tools.Tier.ENDERIUM);
    public static final ToolMaterial TENEBRIS = make(Tools.Tier.TENEBRIS);
    public static final ToolMaterial ZENITH   = make(Tools.Tier.ZENITH);
    
    private ModToolMaterials() {}
}
