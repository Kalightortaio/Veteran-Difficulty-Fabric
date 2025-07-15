package com.kalightortaio.veterandifficulty.datagen;

import java.util.concurrent.CompletableFuture;

import com.kalightortaio.veterandifficulty.systems.internal.ModTags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(ModTags.NEEDS_NO_TOOL).add(Blocks.GRAVEL).add(Blocks.SAND).add(Blocks.DIRT);
    }
}
