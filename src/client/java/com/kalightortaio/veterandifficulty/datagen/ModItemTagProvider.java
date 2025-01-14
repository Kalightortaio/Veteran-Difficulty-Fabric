package com.kalightortaio.veterandifficulty.datagen;

import java.util.concurrent.CompletableFuture;

import com.kalightortaio.veterandifficulty.item.ModItems;
import com.kalightortaio.veterandifficulty.systems.internal.ModTags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.FLINT_TOOL_MATERIALS)
            .add(Items.FLINT);
        getOrCreateTagBuilder(ModTags.COPPER_TOOL_MATERIALS)
            .add(Items.COPPER_INGOT);
        getOrCreateTagBuilder(ModTags.SILVER_TOOL_MATERIALS)
            .add(ModItems.SILVER_INGOT);
        getOrCreateTagBuilder(ModTags.MITHRIL_TOOL_MATERIALS)
            .add(ModItems.MITHRIL_INGOT);
        getOrCreateTagBuilder(ModTags.ENDERIUM_TOOL_MATERIALS)
            .add(ModItems.ENDERIUM_GEM);
        getOrCreateTagBuilder(ModTags.TENEBRIS_TOOL_MATERIALS)
            .add(ModItems.TENEBRIS_INGOT);
        getOrCreateTagBuilder(ModTags.ZENITH_TOOL_MATERIALS)
            .add(ModItems.ZENITH_INGOT);
    }
}
