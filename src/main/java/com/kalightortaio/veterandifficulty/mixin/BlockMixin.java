package com.kalightortaio.veterandifficulty.mixin;

/*
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MarkerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class BlockMixin {

    @Inject(method = "onPlaced", at = @At("HEAD"))
    public void onBlockPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack, CallbackInfo ci) {
        
        if (!world.isClient && (state.isOf(Blocks.TORCH) || state.isOf(Blocks.WALL_TORCH))) {
            ServerWorld serverWorld = (ServerWorld) world;
            MarkerEntity marker = new MarkerEntity(EntityType.MARKER, serverWorld);
            marker.setPosition(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
            marker.setCustomName(Text.literal("VDStandardTorch"));
            serverWorld.spawnEntity(marker);
        }
    }
}
*/