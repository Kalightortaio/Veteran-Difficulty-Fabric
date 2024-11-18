package com.kalightortaio.veterandifficulty.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkGenerator.class)
public abstract class ChunkGeneratorMixin {

    @Inject(method = "generateFeatures", at = @At("TAIL"))
    private void onGenerateFeatures(StructureWorldAccess world, Chunk chunk, StructureAccessor structureAccessor, CallbackInfo ci) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = chunk.getBottomY(); y < chunk.getTopYInclusive(); y++) {
                    BlockPos blockPos = new BlockPos(chunk.getPos().getStartX() + x, y, chunk.getPos().getStartZ() + z);
                    BlockState blockState = world.getBlockState(blockPos);

                    if (blockState.isOf(Blocks.TORCH) || blockState.isOf(Blocks.WALL_TORCH)) {
                        world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), 3);
                    }

                    if ((blockState.isOf(Blocks.SHORT_GRASS) || blockState.isOf(Blocks.FERN)) && Math.random() < 0.0002) {
                        Box searchBox = new Box(blockPos).expand(4.0F);

                        BlockPos.stream(searchBox)
                        .filter(pos -> pos != null && world.isChunkLoaded(pos.getX() >> 4, pos.getZ() >> 4))
                        .filter(pos -> {
                            BlockState state = world.getBlockState(pos);
                            return state.isOf(Blocks.SHORT_GRASS) || state.isOf(Blocks.FERN);
                        })
                        .forEach(pos -> {
                            if (Math.random() < 0.55) {
                                switch ((int) (Math.random() * 5)) {
                                    case 0:
                                    case 1:
                                        world.setBlockState(pos, Blocks.WHEAT.getDefaultState().with(Properties.AGE_7, 7), 3);
                                        break;
                                    case 2:
                                        world.setBlockState(pos, Blocks.CARROTS.getDefaultState().with(Properties.AGE_7, 7), 3);
                                        break;
                                    case 3:
                                        world.setBlockState(pos, Blocks.POTATOES.getDefaultState().with(Properties.AGE_7, 7), 3);
                                        break;
                                    case 4:
                                        world.setBlockState(pos, Blocks.BEETROOTS.getDefaultState().with(Properties.AGE_3, 3), 3);
                                        break;
                                }
                            }
                        });
                    }
                }
            }
        }
    }
}


