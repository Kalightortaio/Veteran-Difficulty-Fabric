package com.kalightortaio.veterandifficulty.datagen;

import com.kalightortaio.veterandifficulty.item.ModItems;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }
    
    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        // Food
        itemModelGenerator.register(ModItems.ACACIA_POD, Models.GENERATED);
        itemModelGenerator.register(ModItems.BAMBOO_SHOOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.BIRCH_BARK, Models.GENERATED);
        itemModelGenerator.register(ModItems.CACTUS_FRUIT, Models.GENERATED);
        itemModelGenerator.register(ModItems.CALAMARI, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHERRY, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_EGG, Models.GENERATED);
        itemModelGenerator.register(ModItems.CREEPER_POD, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRIMSON_ROOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.GLOWING_CALAMARI, Models.GENERATED);
        itemModelGenerator.register(ModItems.LICHEN_CLUMP, Models.GENERATED);
        itemModelGenerator.register(ModItems.MANGO, Models.GENERATED);
        itemModelGenerator.register(ModItems.MOSS_CLUMP, Models.GENERATED);
        itemModelGenerator.register(ModItems.PUMPKIN_SLICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RIPE_SPROUT, Models.GENERATED);
        itemModelGenerator.register(ModItems.RIPE_SUGAR_CANE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RIPE_WART, Models.GENERATED);
        itemModelGenerator.register(ModItems.SCULK_GROWTH, Models.GENERATED);
        itemModelGenerator.register(ModItems.SOUR_CHERRY, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPRUCE_CONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.TELEPORTED_MORSEL, Models.GENERATED);
        itemModelGenerator.register(ModItems.WARPED_ROOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.WILD_RICE, Models.GENERATED);
        itemModelGenerator.register(ModItems.WILD_ROOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.WORM, Models.GENERATED);
        // Misc
        itemModelGenerator.register(ModItems.AIR_BLADDER, Models.GENERATED);
        itemModelGenerator.register(ModItems.PAST_ECHOES, Models.GENERATED);
        itemModelGenerator.register(ModItems.TOTEM_SHARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.WAXEN_WINGS, Models.GENERATED);
    }
}
