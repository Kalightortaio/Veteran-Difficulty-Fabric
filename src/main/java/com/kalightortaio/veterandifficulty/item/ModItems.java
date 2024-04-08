package com.kalightortaio.veterandifficulty.item;

import com.kalightortaio.veterandifficulty.VeteranDifficulty;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItems {
    public static final Item RAW_DIAMOND = registerItem("raw_diamond", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Item(new FabricItemSettings().rarity(Rarity.COMMON)));
    public static final Item COOKED_EGG = registerItem("cooked_egg", ItemGroups.FOOD_AND_DRINK, Items.COOKED_CHICKEN, new Item(new FabricItemSettings().food(ModFoods.COOKED_EGG)));

    private static Item registerItem(String name, RegistryKey<ItemGroup> group, Item location, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.addAfter(location, item));
        return Registry.register(Registries.ITEM, new Identifier(VeteranDifficulty.MOD_ID, name), item);
    }

    public static void registerModItems() {
        VeteranDifficulty.LOGGER.info("Registering Mod Items for " + VeteranDifficulty.MOD_ID);
    }
}
