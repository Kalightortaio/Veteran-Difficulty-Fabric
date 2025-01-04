package com.kalightortaio.veterandifficulty.block.entity;

import com.kalightortaio.veterandifficulty.block.custom.EnderiumGatewayBlock;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EnderiumGatewayBlockEntity extends BlockEntity {
    private int tickCounter = 0;

    public EnderiumGatewayBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ENDERIUM_GATEWAY_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, EnderiumGatewayBlockEntity be) {
        if (world.isClient() || be.tickCounter < 10) {
            be.tickCounter++;
            return;
        }

        if (be.tickCounter >= 10) {
            be.tickCounter = 0;

            boolean isValid = world.getBlockState(pos.down()).isOf(Blocks.COBBLED_DEEPSLATE);
            if (world.getBlockState(pos).get(EnderiumGatewayBlock.VALID_ENDERIUM_GATEWAY) != isValid) {
                world.setBlockState(pos, state.with(EnderiumGatewayBlock.VALID_ENDERIUM_GATEWAY, isValid));
            }
        }
    }
}