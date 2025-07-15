package com.kalightortaio.veterandifficulty.block;

import com.kalightortaio.veterandifficulty.VeteranDifficulty;
import com.kalightortaio.veterandifficulty.block.custom.EnderiumGatewayBlock;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.function.Function;
public class ModBlocks {
    public static final Block SILVER_ORE = registerBlock("silver_ore", ItemGroups.INGREDIENTS, Items.CHARCOAL, AbstractBlock.Settings.copy(Blocks.IRON_ORE), Block::new, new Item.Settings().rarity(Rarity.COMMON));
    public static final Block MITHRIL_ORE = registerBlock("mithril_ore", ItemGroups.INGREDIENTS, Items.CHARCOAL, AbstractBlock.Settings.copy(Blocks.IRON_ORE), Block::new, new Item.Settings().rarity(Rarity.COMMON));
    public static final Block ENDERIUM_ORE = registerBlock("enderium_ore", ItemGroups.INGREDIENTS, Items.CHARCOAL, AbstractBlock.Settings.copy(Blocks.ANCIENT_DEBRIS), Block::new, new Item.Settings().rarity(Rarity.COMMON));
    public static final Block ENDERIUM_BLOCK = registerBlock("enderium_block", ItemGroups.INGREDIENTS, Items.CHARCOAL, AbstractBlock.Settings.copy(Blocks.NETHERITE_BLOCK), Block::new, new Item.Settings().rarity(Rarity.COMMON));
    public static final Block ENDERIUM_GATEWAY = registerBlock("enderium_gateway", ItemGroups.INGREDIENTS, Items.CHARCOAL, AbstractBlock.Settings.copy(Blocks.END_GATEWAY), EnderiumGatewayBlock::new, new Item.Settings().rarity(Rarity.COMMON));
    public static final Block TENEBRIS_ORE = registerBlock("tenebris_ore", ItemGroups.INGREDIENTS, Items.CHARCOAL, AbstractBlock.Settings.copy(Blocks.BEDROCK), Block::new, new Item.Settings().rarity(Rarity.COMMON));

    private static Block registerBlock(String path, RegistryKey<ItemGroup> group, Item location, AbstractBlock.Settings blockSettings, Function<AbstractBlock.Settings, Block> blockFactory, Item.Settings itemSettings) {
        final RegistryKey<Block> blockRegistryKey = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(VeteranDifficulty.MOD_ID, path));
        blockSettings.registryKey(blockRegistryKey);
        Block block = Registry.register(Registries.BLOCK, blockRegistryKey, blockFactory.apply(blockSettings));

        final RegistryKey<Item> itemRegistryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, path));
        itemSettings.registryKey(itemRegistryKey).useBlockPrefixedTranslationKey();
        BlockItem blockItem = new BlockItem(block, itemSettings);
        Registry.register(Registries.ITEM, itemRegistryKey, blockItem);
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.addAfter(location, blockItem));

        return block;
    }

    public static void registerModBlocks() {
        VeteranDifficulty.LOGGER.info("Registering Mod Blocks for " + VeteranDifficulty.MOD_ID);
    }
}
