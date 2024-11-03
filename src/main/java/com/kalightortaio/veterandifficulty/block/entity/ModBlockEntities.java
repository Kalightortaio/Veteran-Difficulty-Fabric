package com.kalightortaio.veterandifficulty.block.entity;

import com.kalightortaio.veterandifficulty.VeteranDifficulty;
import com.kalightortaio.veterandifficulty.block.ModBlocks;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<VoidGatewayBlockEntity> VOID_GATEWAY_BLOCK_ENTITY = registerBlockEntity("void_gateway_be", VoidGatewayBlockEntity::new, ModBlocks.VOID_GATEWAY);
        
    private static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(String path, FabricBlockEntityTypeBuilder.Factory<T> factory, Block block) {
        BlockEntityType<T> blockEntity = FabricBlockEntityTypeBuilder.create(factory, block).build();
        Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(VeteranDifficulty.MOD_ID, path), blockEntity);
        return blockEntity;
    }
    
    public static void registerModBlockEntities() {
        VeteranDifficulty.LOGGER.info("Registering Mod Block Entities for " + VeteranDifficulty.MOD_ID);
    }
}
