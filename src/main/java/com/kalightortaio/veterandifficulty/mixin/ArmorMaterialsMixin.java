package com.kalightortaio.veterandifficulty.mixin;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.kalightortaio.veterandifficulty.util.Armors;

import net.minecraft.item.Item;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.ArmorMaterials;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvent;

@Mixin(ArmorMaterials.class)
public interface ArmorMaterialsMixin {
 
    @Redirect(method = "<clinit>", at = @At(value = "NEW", target = "Lnet/minecraft/item/equipment/ArmorMaterial;"))
    private static ArmorMaterial redirectArmorMaterial(int durability, Map<EquipmentType, Integer> defenseMap, int enchantValue, RegistryEntry<SoundEvent> equipSound, float toughness, float kBResist, TagKey<Item> repairItem, RegistryKey<EquipmentAsset> id) {
        Map<RegistryKey<EquipmentAsset>, Armors.Tier> map = Map.of(
            EquipmentAssetKeys.LEATHER,   Armors.Tier.LEATHER,
            EquipmentAssetKeys.CHAINMAIL, Armors.Tier.CHAINMAIL,
            EquipmentAssetKeys.IRON,      Armors.Tier.IRON,
            EquipmentAssetKeys.GOLD,      Armors.Tier.GOLD,
            EquipmentAssetKeys.DIAMOND,   Armors.Tier.DIAMOND,
            EquipmentAssetKeys.NETHERITE, Armors.Tier.NETHERITE
        );

        Armors.Tier tier = map.get(id);
        if (tier == null) {
            return new ArmorMaterial(durability, defenseMap, enchantValue, equipSound, toughness, kBResist, repairItem, id);
        }
        return new ArmorMaterial(Armors.Stats.durability(tier), Armors.Stats.defenseMap(tier), Armors.Stats.enchantValue(tier), Armors.Stats.equipSound(tier), Armors.Stats.toughness(tier), Armors.Stats.kBResist(tier), Armors.Stats.repairItem(tier), Armors.Stats.id(tier));
    }
}
