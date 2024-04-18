package com.kalightortaio.veterandifficulty.block.custom;

import org.jetbrains.annotations.Nullable;

import com.kalightortaio.veterandifficulty.block.entity.ModBlockEntities;
import com.kalightortaio.veterandifficulty.block.entity.VoidGatewayBlockEntity;
import com.mojang.serialization.MapCodec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class VoidGatewayBlock extends BlockWithEntity {
    public static final BooleanProperty VALID_VOID_GATEWAY = BooleanProperty.of("valid_void_gateway");
    public static final MapCodec<VoidGatewayBlock> CODEC = VoidGatewayBlock.createCodec(VoidGatewayBlock::new);

    public VoidGatewayBlock(Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)this.getDefaultState().with(VALID_VOID_GATEWAY, false));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
      builder.add(new Property[]{VALID_VOID_GATEWAY});
   }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new VoidGatewayBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.VOID_GATEWAY_BLOCK_ENTITY, (world1, pos, state1, be) -> VoidGatewayBlockEntity.tick(world1, pos, state1, be));
    }
}