package com.kalightortaio.veterandifficulty.mob;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class MagmaCube {

    private static final Map<BlockPos, Long> scheduledLavaRemovals = new HashMap<>();
    private static final Map<BlockPos, ServerWorld> worldReferences = new HashMap<>();

    public static void updateLavaRemoval() {
        Iterator<Map.Entry<BlockPos, Long>> iterator = scheduledLavaRemovals.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<BlockPos, Long> entry = iterator.next();
            BlockPos pos = entry.getKey();
            long scheduledTime = entry.getValue();
            ServerWorld currentWorld = worldReferences.get(pos); 
            
            if (currentWorld != null && currentWorld.getTime() >= scheduledTime) {
                if (currentWorld.getBlockState(pos).isOf(Blocks.LAVA)) {
                    currentWorld.setBlockState(pos, Blocks.AIR.getDefaultState());
                }
                iterator.remove();
                worldReferences.remove(pos);
            }
        }
    }

    public static void scheduleLavaRemoval(ServerWorld world, BlockPos pos, long delay) {
        long executeTime = world.getTime() + delay;
        scheduledLavaRemovals.put(pos, executeTime);
        worldReferences.put(pos, world);
    }

    public static void onDeath(DamageSource source, LivingEntity magmaCube, CallbackInfo ci) {
        if (magmaCube.getWorld().isClient()) return;
        ServerWorld world = (ServerWorld) magmaCube.getWorld();
        BlockPos blockPos = magmaCube.getBlockPos();
        world.setBlockState(blockPos, Blocks.LAVA.getDefaultState());
        scheduleLavaRemoval(world, blockPos, 100);
    }
}