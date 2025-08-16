package com.kalightortaio.veterandifficulty.util;

import java.util.Map;

import com.google.common.collect.Maps;
import com.kalightortaio.veterandifficulty.VeteranDifficulty;
import com.kalightortaio.veterandifficulty.systems.internal.ModTags;

import net.minecraft.item.Item;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class Armors {
    public enum Type { HELMET, CHESTPLATE, LEGGINGS, BOOTS, BODY }

    public enum Tier {
        FLINT,
        LEATHER,
        CHAINMAIL,
        COPPER,
        SILVER,
        GOLD,
        IRON,
        MITHRIL,
        DIAMOND,
        NETHERITE,
        ENDERIUM,
        TENEBRIS,
        ZENITH;
    }

    public static final class Stats {
        private record Row(int durability, Map<EquipmentType, Integer> defenseMap, int enchantValue, RegistryEntry<SoundEvent> equipSound, float toughness, float kBResist, TagKey<Item> repairItem, RegistryKey<EquipmentAsset> id) {}
    
        private static final Map<Tier, Row> CORE = Map.ofEntries(
            Map.entry(Tier.FLINT,     new Row(2,    createDefenseMap(1, 1, 1, 1, 4),  6,  SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,   0.0F, 0.0F,   ModTags.REPAIRS_FLINT_ARMOR,      RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(VeteranDifficulty.MOD_ID, "flint")))),
            Map.entry(Tier.LEATHER,   new Row(4,    createDefenseMap(1, 1, 1, 1, 4),  8,  SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,   0.0F, 0.0F,   ItemTags.REPAIRS_LEATHER_ARMOR,   EquipmentAssetKeys.LEATHER)),
            Map.entry(Tier.CHAINMAIL, new Row(7,    createDefenseMap(1, 1, 1, 1, 4),  10, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN,     0.0F, 0.0F,   ItemTags.REPAIRS_CHAIN_ARMOR,     EquipmentAssetKeys.CHAINMAIL)),
            Map.entry(Tier.COPPER,    new Row(10,   createDefenseMap(2, 2, 2, 2, 8),  12, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN,     0.0F, 0.0F,   ModTags.REPAIRS_COPPER_ARMOR,     RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(VeteranDifficulty.MOD_ID, "copper")))),
            Map.entry(Tier.SILVER,    new Row(16,   createDefenseMap(2, 2, 2, 2, 8),  14, SoundEvents.ITEM_ARMOR_EQUIP_GOLD,      0.0F, 0.0F,   ModTags.REPAIRS_SILVER_ARMOR,     RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(VeteranDifficulty.MOD_ID, "silver")))),
            Map.entry(Tier.GOLD,      new Row(33,   createDefenseMap(2, 2, 2, 2, 8),  16, SoundEvents.ITEM_ARMOR_EQUIP_GOLD,      0.2F, 0.0F,   ItemTags.REPAIRS_GOLD_ARMOR,      EquipmentAssetKeys.GOLD)),
            Map.entry(Tier.IRON,      new Row(55,   createDefenseMap(3, 3, 4, 3, 13), 18, SoundEvents.ITEM_ARMOR_EQUIP_IRON,      0.4F, 0.0F,   ItemTags.REPAIRS_IRON_ARMOR,      EquipmentAssetKeys.IRON)),
            Map.entry(Tier.MITHRIL,   new Row(89,   createDefenseMap(3, 3, 4, 3, 13), 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,   0.6F, 0.025F, ModTags.REPAIRS_MITHRIL_ARMOR,    RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(VeteranDifficulty.MOD_ID, "mithril")))),
            Map.entry(Tier.DIAMOND,   new Row(153,  createDefenseMap(3, 3, 4, 3, 13), 22, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,   0.8F, 0.05F,  ItemTags.REPAIRS_DIAMOND_ARMOR,   EquipmentAssetKeys.DIAMOND)),
            Map.entry(Tier.NETHERITE, new Row(255,  createDefenseMap(4, 4, 6, 4, 18), 24, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 1.0F, 0.075F, ItemTags.REPAIRS_NETHERITE_ARMOR, EquipmentAssetKeys.NETHERITE)),
            Map.entry(Tier.ENDERIUM,  new Row(300,  createDefenseMap(4, 4, 6, 4, 18), 26, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 2.0F, 0.10F,  ModTags.REPAIRS_ENDERIUM_ARMOR,   RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(VeteranDifficulty.MOD_ID, "enderium")))),
            Map.entry(Tier.TENEBRIS,  new Row(444,  createDefenseMap(5, 5, 8, 5, 23), 28, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.0F, 0.125F, ModTags.REPAIRS_TENEBRIS_ARMOR,   RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(VeteranDifficulty.MOD_ID, "tenebris")))),
            Map.entry(Tier.ZENITH,    new Row(4166, createDefenseMap(5, 5, 8, 5, 23), 30, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 4.0F, 0.15F,  ModTags.REPAIRS_ZENITH_ARMOR,     RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of(VeteranDifficulty.MOD_ID, "zenith"))))
        );

        public static int durability(Tier tier)                         { return CORE.get(tier).durability(); }
        public static Map<EquipmentType, Integer> defenseMap(Tier tier) { return CORE.get(tier).defenseMap(); }
        public static int enchantValue(Tier tier)                       { return CORE.get(tier).enchantValue(); }
        public static RegistryEntry<SoundEvent> equipSound(Tier tier)   { return CORE.get(tier).equipSound(); }
        public static float toughness(Tier tier)                        { return CORE.get(tier).toughness(); }
        public static float kBResist(Tier tier)                         { return CORE.get(tier).kBResist(); }
        public static TagKey<Item> repairItem(Tier tier)                { return CORE.get(tier).repairItem(); }
        public static RegistryKey<EquipmentAsset> id(Tier tier)         { return CORE.get(tier).id(); }

        private static Map<EquipmentType, Integer> createDefenseMap(int bootsDefense, int leggingsDefense, int chestplateDefense, int helmetDefense, int bodyDefense) {
            return Maps.newEnumMap(
                Map.of(
                    EquipmentType.BOOTS,
                    bootsDefense,
                    EquipmentType.LEGGINGS,
                    leggingsDefense,
                    EquipmentType.CHESTPLATE,
                    chestplateDefense,
                    EquipmentType.HELMET,
                    helmetDefense,
                    EquipmentType.BODY,
                    bodyDefense
                )
            );
        }

        private Stats() {}
    }

    private Armors() {}
}
