package com.kalightortaio.veterandifficulty.datagen;

import com.kalightortaio.veterandifficulty.block.ModBlocks;
import com.kalightortaio.veterandifficulty.block.custom.EnderiumGatewayBlock;
import com.kalightortaio.veterandifficulty.item.ModItems;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.util.Identifier;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.BlockStateVariantMap;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import net.minecraft.client.data.TextureMap;
import net.minecraft.client.data.VariantsBlockModelDefinitionCreator;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator gen) {
        gen.registerSimpleCubeAll(ModBlocks.SILVER_ORE);
        gen.registerSimpleCubeAll(ModBlocks.MITHRIL_ORE);
        gen.registerSimpleCubeAll(ModBlocks.ENDERIUM_ORE);
        gen.registerSimpleCubeAll(ModBlocks.ENDERIUM_BLOCK);
        gen.registerSimpleCubeAll(ModBlocks.TENEBRIS_ORE);
        registerEnderiumGateway(gen);
    }

    @Override
    public void generateItemModels(ItemModelGenerator gen) {
        // Misc
        gen.register(ModItems.AIR_BLADDER, Models.GENERATED);
        gen.register(ModItems.PAST_ECHOES, Models.GENERATED);
        gen.register(ModItems.TOTEM_SHARD, Models.GENERATED);
        // Food
        gen.register(ModItems.ACACIA_POD, Models.GENERATED);
        gen.register(ModItems.BAMBOO_SHOOT, Models.GENERATED);
        gen.register(ModItems.BIRCH_BARK, Models.GENERATED);
        gen.register(ModItems.CACTUS_FRUIT, Models.GENERATED);
        gen.register(ModItems.CALAMARI, Models.GENERATED);
        gen.register(ModItems.CHERRY, Models.GENERATED);
        gen.register(ModItems.COOKED_EGG, Models.GENERATED);
        gen.register(ModItems.CREEPER_POD, Models.GENERATED);
        gen.register(ModItems.CRIMSON_ROOT, Models.GENERATED);
        gen.register(ModItems.GLOWING_CALAMARI, Models.GENERATED);
        gen.register(ModItems.LICHEN_CLUMP, Models.GENERATED);
        gen.register(ModItems.MANGO, Models.GENERATED);
        gen.register(ModItems.MOSS_CLUMP, Models.GENERATED);
        gen.register(ModItems.PUMPKIN_SLICE, Models.GENERATED);
        gen.register(ModItems.RIPE_SPROUT, Models.GENERATED);
        gen.register(ModItems.RIPE_SUGAR_CANE, Models.GENERATED);
        gen.register(ModItems.RIPE_WART, Models.GENERATED);
        gen.register(ModItems.SCULK_GROWTH, Models.GENERATED);
        gen.register(ModItems.SOUR_CHERRY, Models.GENERATED);
        gen.register(ModItems.SPRUCE_CONE, Models.GENERATED);
        gen.register(ModItems.TELEPORTED_MORSEL, Models.GENERATED);
        gen.register(ModItems.WARPED_ROOT, Models.GENERATED);
        gen.register(ModItems.WILD_RICE, Models.GENERATED);
        gen.register(ModItems.WILD_ROOT, Models.GENERATED);
        gen.register(ModItems.WORM, Models.GENERATED);
        // ToolTiers / Ores
        gen.register(ModItems.COPPER_AXE, Models.GENERATED);
        gen.register(ModItems.COPPER_BOOTS, Models.GENERATED);
        gen.register(ModItems.COPPER_CHESTPLATE, Models.GENERATED);
        gen.register(ModItems.COPPER_HELMET, Models.GENERATED);
        gen.register(ModItems.COPPER_HOE, Models.GENERATED);
        gen.register(ModItems.COPPER_LEGGINGS, Models.GENERATED);
        gen.register(ModItems.COPPER_PICKAXE, Models.GENERATED);
        gen.register(ModItems.COPPER_SWORD, Models.GENERATED);
        gen.register(ModItems.COPPER_SHOVEL, Models.GENERATED);
        gen.register(ModItems.RAW_SILVER, Models.GENERATED);
        gen.register(ModItems.SILVER_INGOT, Models.GENERATED);
        gen.register(ModItems.RAW_DIAMOND, Models.GENERATED);
        gen.register(ModItems.RAW_ENDERIUM, Models.GENERATED);
        gen.register(ModItems.ENDERIUM_GEM, Models.GENERATED);
    }

    private void registerEnderiumGateway(BlockStateModelGenerator gen) {
        Identifier activeModel = gen.createSubModel(ModBlocks.ENDERIUM_GATEWAY, "_active", Models.CUBE_ALL, id -> 
            TextureMap.all(Identifier.of("minecraft", "block/respawn_anchor_top"))
        );

        Identifier inactiveModel = gen.createSubModel(ModBlocks.ENDERIUM_GATEWAY, "_inactive", Models.CUBE_ALL, id -> 
            TextureMap.all(Identifier.of("minecraft", "block/respawn_anchor_top_off"))
        );
        gen.blockStateCollector.accept(VariantsBlockModelDefinitionCreator
            .of(ModBlocks.ENDERIUM_GATEWAY)
            .with(BlockStateVariantMap.models(EnderiumGatewayBlock.VALID_ENDERIUM_GATEWAY)
            .register(true , BlockStateModelGenerator.createWeightedVariant(activeModel))
            .register(false, BlockStateModelGenerator.createWeightedVariant(inactiveModel)))
            .apply(BlockStateModelGenerator.UV_LOCK)
        );
        gen.registerParentedItemModel(ModBlocks.ENDERIUM_GATEWAY, inactiveModel);
    }
}
