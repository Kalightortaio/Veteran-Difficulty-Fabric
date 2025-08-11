package com.kalightortaio.veterandifficulty.block;

import com.kalightortaio.veterandifficulty.VeteranDifficulty;
import com.kalightortaio.veterandifficulty.block.custom.EnderiumGatewayBlock;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.intprovider.ConstantIntProvider;

import java.util.function.Function;
public class ModBlocks {
    public static final Block FLINT_BLOCK = registerBlock(
        "flint_block", 
        Block::new, 
		AbstractBlock.Settings.create()
            .mapColor(MapColor.STONE_GRAY)
            .instrument(NoteBlockInstrument.SNARE)
            .strength(0.6F)
            .sounds(BlockSoundGroup.GRAVEL),
        new Item.Settings().rarity(Rarity.COMMON)
    );
    public static final Block SILVER_ORE = registerBlock(
        "silver_ore", 
        settings -> new ExperienceDroppingBlock(ConstantIntProvider.create(0), settings), 
        AbstractBlock.Settings.create()
            .mapColor(MapColor.STONE_GRAY)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .requiresTool()
            .strength(3.0F, 3.0F),
        new Item.Settings().rarity(Rarity.COMMON)
    );
    public static final Block SILVER_BLOCK = registerBlock(
        "silver_block", 
        Block::new, 
        AbstractBlock.Settings.create()
			.mapColor(MapColor.IRON_GRAY)
			.instrument(NoteBlockInstrument.IRON_XYLOPHONE)
			.requiresTool()
			.strength(4.0F, 5.0F)
			.sounds(BlockSoundGroup.IRON),  
        new Item.Settings().rarity(Rarity.COMMON)
    );
    public static final Block RAW_SILVER_BLOCK = registerBlock(
        "raw_silver_block", 
        Block::new, 
        AbstractBlock.Settings.create()
			.mapColor(MapColor.IRON_GRAY)
			.instrument(NoteBlockInstrument.IRON_XYLOPHONE)
			.requiresTool()
			.strength(4.0F, 5.0F)
			.sounds(BlockSoundGroup.IRON),  
        new Item.Settings().rarity(Rarity.COMMON)
    );
    public static final Block DEEPSLATE_MITHRIL_ORE = registerBlock(
        "deepslate_mithril_ore", 
        settings -> new ExperienceDroppingBlock(ConstantIntProvider.create(0), settings), 
        AbstractBlock.Settings.create()
            .mapColor(MapColor.DEEPSLATE_GRAY)
            .requiresTool()
            .strength(4.5F, 3.0F)
            .sounds(BlockSoundGroup.DEEPSLATE),
        new Item.Settings().rarity(Rarity.COMMON)
    );
    public static final Block RAW_MITHRIL_BLOCK = registerBlock(
        "raw_mithril_block", 
        Block::new, 
        AbstractBlock.Settings.create()
			.mapColor(MapColor.IRON_GRAY)
			.instrument(NoteBlockInstrument.IRON_XYLOPHONE)
			.requiresTool()
			.strength(5.0F, 6.0F)
			.sounds(BlockSoundGroup.IRON), 
        new Item.Settings().rarity(Rarity.COMMON)
    );
    public static final Block MITHRIL_BLOCK = registerBlock(
        "mithril_block", 
        Block::new, 
        AbstractBlock.Settings.create()
			.mapColor(MapColor.IRON_GRAY)
			.instrument(NoteBlockInstrument.IRON_XYLOPHONE)
			.requiresTool()
			.strength(5.0F, 6.0F)
			.sounds(BlockSoundGroup.IRON), 
        new Item.Settings().rarity(Rarity.COMMON)
    );
    public static final Block ENDERIUM_ORE = registerBlock(
        "enderium_ore", 
        settings -> new ExperienceDroppingBlock(ConstantIntProvider.create(0), settings), 
        AbstractBlock.Settings.create()
            .mapColor(MapColor.BLACK)
            .requiresTool()
            .strength(30.0F, 1200.0F)
            .sounds(BlockSoundGroup.ANCIENT_DEBRIS), 
        new Item.Settings().rarity(Rarity.COMMON)
    );
    public static final Block RAW_ENDERIUM_BLOCK = registerBlock(
        "raw_enderium_block", 
        Block::new, 
        AbstractBlock.Settings.create()
            .mapColor(MapColor.BLACK)
            .requiresTool()
            .strength(50.0F, 1200.0F)
            .sounds(BlockSoundGroup.NETHERITE), 
        new Item.Settings().rarity(Rarity.COMMON)
    );
    public static final Block ENDERIUM_BLOCK = registerBlock(
        "enderium_block", 
        Block::new, 
        AbstractBlock.Settings.create()
            .mapColor(MapColor.BLACK)
            .requiresTool()
            .strength(50.0F, 1200.0F)
            .sounds(BlockSoundGroup.NETHERITE), 
        new Item.Settings().rarity(Rarity.COMMON)
    );
    public static final Block ENDERIUM_GATEWAY = registerBlock(
        "enderium_gateway", 
        EnderiumGatewayBlock::new, 
        AbstractBlock.Settings.create()
			.mapColor(MapColor.BLACK)
			.noCollision()
			.luminance(state -> 15)
			.strength(1.0F, 1200.0F)
			.pistonBehavior(PistonBehavior.BLOCK), 
        new Item.Settings().rarity(Rarity.COMMON)
    );
    public static final Block TENEBRIS_ORE = registerBlock(
        "tenebris_ore", 
        settings -> new ExperienceDroppingBlock(ConstantIntProvider.create(0), settings), 
        AbstractBlock.Settings.create()
            .mapColor(MapColor.STONE_GRAY)
            .instrument(NoteBlockInstrument.WITHER_SKELETON)
            .requiresTool()
            .strength(500.0F, 1200.0F), 
        new Item.Settings().rarity(Rarity.COMMON)
    );
    public static final Block RAW_TENEBRIS_BLOCK = registerBlock(
        "raw_tenebris_block", 
        Block::new, 
        AbstractBlock.Settings.create()
			.mapColor(MapColor.BLACK)
			.instrument(NoteBlockInstrument.WITHER_SKELETON)
			.requiresTool()
			.strength(500.0F, 1200.0F)
			.sounds(BlockSoundGroup.NETHERITE), 
        new Item.Settings().rarity(Rarity.COMMON)
    );
    public static final Block TENEBRIS_BLOCK = registerBlock(
        "tenebris_block", 
        Block::new, 
        AbstractBlock.Settings.create()
			.mapColor(MapColor.BLACK)
			.instrument(NoteBlockInstrument.WITHER_SKELETON)
			.requiresTool()
			.strength(500.0F, 1200.0F)
			.sounds(BlockSoundGroup.NETHERITE), 
        new Item.Settings().rarity(Rarity.COMMON)
    );
    public static final Block RAW_ZENITH_BLOCK = registerBlock(
        "raw_zenith_block", 
        Block::new, 
        AbstractBlock.Settings.create()
			.mapColor(MapColor.BLACK)
			.instrument(NoteBlockInstrument.WITHER_SKELETON)
			.requiresTool()
			.strength(500.0F, 1200.0F)
			.sounds(BlockSoundGroup.NETHERITE), 
        new Item.Settings().rarity(Rarity.COMMON)
    );
    public static final Block ZENITH_BLOCK = registerBlock(
        "zenith_block", 
        Block::new, 
        AbstractBlock.Settings.create()
			.mapColor(MapColor.BLACK)
			.instrument(NoteBlockInstrument.WITHER_SKELETON)
			.requiresTool()
			.strength(500.0F, 1200.0F)
			.sounds(BlockSoundGroup.NETHERITE), 
        new Item.Settings().rarity(Rarity.COMMON)
    );

    private static Block registerBlock(String id, Function<AbstractBlock.Settings, Block> blockFactory, AbstractBlock.Settings blockSettings, Item.Settings itemSettings ) {
        final RegistryKey<Block> blockRegistryKey = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(VeteranDifficulty.MOD_ID, id));
        blockSettings.registryKey(blockRegistryKey);
        Block block = Registry.register(Registries.BLOCK, blockRegistryKey, blockFactory.apply(blockSettings));

        final RegistryKey<Item> itemRegistryKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(VeteranDifficulty.MOD_ID, id));
        itemSettings.registryKey(itemRegistryKey).useBlockPrefixedTranslationKey();
        BlockItem blockItem = new BlockItem(block, itemSettings);
        Registry.register(Registries.ITEM, itemRegistryKey, blockItem);

        return block;
    }

    public static void registerModBlocks() {
        VeteranDifficulty.LOGGER.info("Registering Mod Blocks for " + VeteranDifficulty.MOD_ID);
    }
}
