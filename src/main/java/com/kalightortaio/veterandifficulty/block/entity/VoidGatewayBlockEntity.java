package com.kalightortaio.veterandifficulty.block.entity;

import com.kalightortaio.veterandifficulty.block.custom.VoidGatewayBlock;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class VoidGatewayBlockEntity extends BlockEntity {
    private int tickCounter = 0;

    public VoidGatewayBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.VOID_GATEWAY_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, VoidGatewayBlockEntity be) {
        if (world.isClient() || be.tickCounter < 10) {
            be.tickCounter++;
            return;
        }

        if (be.tickCounter >= 10) {
            be.tickCounter = 0;

            boolean isValid = world.getBlockState(pos.down()).isOf(Blocks.COBBLED_DEEPSLATE);
            if (world.getBlockState(pos).get(VoidGatewayBlock.VALID_VOID_GATEWAY) != isValid) {
                world.setBlockState(pos, state.with(VoidGatewayBlock.VALID_VOID_GATEWAY, isValid));
            }
        }
    }
}