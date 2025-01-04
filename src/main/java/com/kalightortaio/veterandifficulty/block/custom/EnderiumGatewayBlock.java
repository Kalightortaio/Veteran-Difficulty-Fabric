package com.kalightortaio.veterandifficulty.block.custom;

import org.jetbrains.annotations.Nullable;

import com.kalightortaio.veterandifficulty.block.entity.ModBlockEntities;
import com.kalightortaio.veterandifficulty.block.entity.EnderiumGatewayBlockEntity;
import com.mojang.serialization.MapCodec;

import net.minecraft.block.AbstractBlock;
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

public class EnderiumGatewayBlock extends BlockWithEntity {
    public static final BooleanProperty VALID_ENDERIUM_GATEWAY = BooleanProperty.of("valid_enderium_gateway");
    public static final MapCodec<EnderiumGatewayBlock> CODEC = EnderiumGatewayBlock.createCodec(EnderiumGatewayBlock::new);

    public EnderiumGatewayBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)this.getDefaultState().with(VALID_ENDERIUM_GATEWAY, false));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
      builder.add(new Property[]{VALID_ENDERIUM_GATEWAY});
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
        return new EnderiumGatewayBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.ENDERIUM_GATEWAY_BLOCK_ENTITY, (world1, pos, state1, be) -> EnderiumGatewayBlockEntity.tick(world1, pos, state1, be));
    }
}