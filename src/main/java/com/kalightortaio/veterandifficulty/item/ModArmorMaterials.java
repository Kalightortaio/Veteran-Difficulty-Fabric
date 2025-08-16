package com.kalightortaio.veterandifficulty.item;

import com.kalightortaio.veterandifficulty.util.Armors;

import net.minecraft.item.equipment.ArmorMaterial;

public class ModArmorMaterials {

    private static ArmorMaterial make(Armors.Tier tier) {
        return new ArmorMaterial(Armors.Stats.durability(tier), Armors.Stats.defenseMap(tier), Armors.Stats.enchantValue(tier), Armors.Stats.equipSound(tier), Armors.Stats.toughness(tier), Armors.Stats.kBResist(tier), Armors.Stats.repairItem(tier), Armors.Stats.id(tier));
    }

    public static final ArmorMaterial FLINT    = make(Armors.Tier.FLINT);
    public static final ArmorMaterial COPPER   = make(Armors.Tier.COPPER);
    public static final ArmorMaterial SILVER   = make(Armors.Tier.SILVER);
    public static final ArmorMaterial MITHRIL  = make(Armors.Tier.MITHRIL);
    public static final ArmorMaterial ENDERIUM = make(Armors.Tier.ENDERIUM);
    public static final ArmorMaterial TENEBRIS = make(Armors.Tier.TENEBRIS);
    public static final ArmorMaterial ZENITH   = make(Armors.Tier.ZENITH);

    public static void registerModArmorMaterials() {};
}
