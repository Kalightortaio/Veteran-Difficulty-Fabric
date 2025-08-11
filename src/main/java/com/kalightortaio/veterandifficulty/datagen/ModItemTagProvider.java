package com.kalightortaio.veterandifficulty.datagen;

import java.util.concurrent.CompletableFuture;

import com.kalightortaio.veterandifficulty.item.ModItems;
import com.kalightortaio.veterandifficulty.systems.internal.ModTags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(ItemTags.PICKAXES).add(
            ModItems.FLINT_PICKAXE,
            ModItems.COPPER_PICKAXE,
            ModItems.SILVER_PICKAXE,
            ModItems.MITHRIL_PICKAXE,
            ModItems.ENDERIUM_PICKAXE,
            ModItems.TENEBRIS_PICKAXE,
            ModItems.ZENITH_PICKAXE
        );

        valueLookupBuilder(ItemTags.AXES).add(
            ModItems.FLINT_AXE,
            ModItems.COPPER_AXE,
            ModItems.SILVER_AXE,
            ModItems.MITHRIL_AXE,
            ModItems.ENDERIUM_AXE,
            ModItems.TENEBRIS_AXE,
            ModItems.ZENITH_AXE
        );

        valueLookupBuilder(ItemTags.SHOVELS).add(
            ModItems.COPPER_SHOVEL,
            ModItems.SILVER_SHOVEL,
            ModItems.MITHRIL_SHOVEL,
            ModItems.ENDERIUM_SHOVEL,
            ModItems.TENEBRIS_SHOVEL,
            ModItems.ZENITH_SHOVEL
        );

        valueLookupBuilder(ItemTags.SWORDS).add(
            ModItems.FLINT_KNIFE,
            ModItems.COPPER_SWORD,
            ModItems.SILVER_SWORD,
            ModItems.MITHRIL_SWORD,
            ModItems.ENDERIUM_SWORD,
            ModItems.TENEBRIS_SWORD,
            ModItems.ZENITH_SWORD
        );

        valueLookupBuilder(ItemTags.HOES).add(
            ModItems.COPPER_HOE,
            ModItems.SILVER_HOE,
            ModItems.MITHRIL_HOE,
            ModItems.ENDERIUM_HOE,
            ModItems.TENEBRIS_HOE,
            ModItems.ZENITH_HOE
        );

        valueLookupBuilder(ModTags.FLINT_TOOL_MATERIALS).add(Items.FLINT);
        valueLookupBuilder(ModTags.COPPER_TOOL_MATERIALS).add(Items.COPPER_INGOT);
        valueLookupBuilder(ModTags.SILVER_TOOL_MATERIALS).add(ModItems.SILVER_INGOT);
        valueLookupBuilder(ModTags.MITHRIL_TOOL_MATERIALS).add(ModItems.MITHRIL_INGOT);
        valueLookupBuilder(ModTags.ENDERIUM_TOOL_MATERIALS).add(ModItems.ENDERIUM_GEM);
        valueLookupBuilder(ModTags.TENEBRIS_TOOL_MATERIALS).add(ModItems.TENEBRIS_INGOT);
        valueLookupBuilder(ModTags.ZENITH_TOOL_MATERIALS).add(ModItems.ZENITH_INGOT);

        valueLookupBuilder(ModTags.FLINT_TIER).add(
            ModItems.FLINT_AXE, ModItems.FLINT_KNIFE, ModItems.FLINT_PICKAXE
        );
        valueLookupBuilder(ModTags.WOODEN_TIER).add(
            Items.WOODEN_AXE, Items.WOODEN_HOE, Items.WOODEN_PICKAXE, Items.WOODEN_SHOVEL, Items.WOODEN_SWORD
        );
        valueLookupBuilder(ModTags.STONE_TIER).add(
            Items.STONE_AXE, Items.STONE_HOE, Items.STONE_PICKAXE, Items.STONE_SHOVEL, Items.STONE_SWORD
        );
        valueLookupBuilder(ModTags.COPPER_TIER).add(
            ModItems.COPPER_AXE, ModItems.COPPER_HOE, ModItems.COPPER_PICKAXE, ModItems.COPPER_SHOVEL, ModItems.COPPER_SWORD
        );
        valueLookupBuilder(ModTags.SILVER_TIER).add(
            ModItems.SILVER_AXE, ModItems.SILVER_HOE, ModItems.SILVER_PICKAXE, ModItems.SILVER_SHOVEL, ModItems.SILVER_SWORD
        );
        valueLookupBuilder(ModTags.GOLD_TIER).add(
            Items.GOLDEN_AXE, Items.GOLDEN_HOE, Items.GOLDEN_PICKAXE, Items.GOLDEN_SHOVEL, Items.GOLDEN_SWORD
        );
        valueLookupBuilder(ModTags.IRON_TIER).add(
            Items.IRON_AXE, Items.IRON_HOE, Items.IRON_PICKAXE, Items.IRON_SHOVEL, Items.IRON_SWORD
        );
        valueLookupBuilder(ModTags.MITHRIL_TIER).add(
            ModItems.MITHRIL_AXE, ModItems.MITHRIL_HOE, ModItems.MITHRIL_PICKAXE, ModItems.MITHRIL_SHOVEL, ModItems.MITHRIL_SWORD
        );
        valueLookupBuilder(ModTags.DIAMOND_TIER).add(
            Items.DIAMOND_AXE, Items.DIAMOND_HOE, Items.DIAMOND_PICKAXE, Items.DIAMOND_SHOVEL, Items.DIAMOND_SWORD
        );
        valueLookupBuilder(ModTags.NETHERITE_TIER).add(
            Items.NETHERITE_AXE, Items.NETHERITE_HOE, Items.NETHERITE_PICKAXE, Items.NETHERITE_SHOVEL, Items.NETHERITE_SWORD
        );
        valueLookupBuilder(ModTags.ENDERIUM_TIER).add(
            ModItems.ENDERIUM_AXE, ModItems.ENDERIUM_HOE, ModItems.ENDERIUM_PICKAXE, ModItems.ENDERIUM_SHOVEL, ModItems.ENDERIUM_SWORD
        );
        valueLookupBuilder(ModTags.TENEBRIS_TIER).add(
            ModItems.TENEBRIS_AXE, ModItems.TENEBRIS_HOE, ModItems.TENEBRIS_PICKAXE, ModItems.TENEBRIS_SHOVEL, ModItems.TENEBRIS_SWORD
        );
        valueLookupBuilder(ModTags.ZENITH_TIER).add(
            ModItems.ZENITH_AXE, ModItems.ZENITH_HOE, ModItems.ZENITH_PICKAXE, ModItems.ZENITH_SHOVEL, ModItems.ZENITH_SWORD
        );
    }
}
