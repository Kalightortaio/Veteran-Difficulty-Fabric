package com.kalightortaio.veterandifficulty.block;

import com.kalightortaio.veterandifficulty.VeteranDifficulty;
import com.kalightortaio.veterandifficulty.block.custom.VoidGatewayBlock;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModBlocks {
    public static final Block VOID_BLOCK = registerBlock("void_block", ItemGroups.INGREDIENTS, Items.CHARCOAL, new Block(FabricBlockSettings.copyOf(Blocks.NETHERITE_BLOCK)), new FabricItemSettings().rarity(Rarity.COMMON));
    public static final Block VOID_GATEWAY = registerBlock("void_gateway", ItemGroups.INGREDIENTS, Items.CHARCOAL, new VoidGatewayBlock(FabricBlockSettings.copyOf(Blocks.END_GATEWAY)), new FabricItemSettings().rarity(Rarity.COMMON));

    private static Block registerBlock(String name, RegistryKey<ItemGroup> group, Item location, Block block, Settings settings) {
        registerBlockItem(name, group, location, block, settings);
        return Registry.register(Registries.BLOCK, new Identifier(VeteranDifficulty.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, RegistryKey<ItemGroup> group, Item location, Block block, Settings settings) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.addAfter(location, block));
        return Registry.register(Registries.ITEM, new Identifier(VeteranDifficulty.MOD_ID, name), new BlockItem(block, settings));
    }

    public static void registerModBlocks() {
        VeteranDifficulty.LOGGER.info("Registering Mod Blocks for " + VeteranDifficulty.MOD_ID);
    }
}
