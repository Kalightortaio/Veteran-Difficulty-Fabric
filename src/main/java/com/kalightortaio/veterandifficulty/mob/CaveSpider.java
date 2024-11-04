package com.kalightortaio.veterandifficulty.mob;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.CaveSpiderEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class CaveSpider {

    public static void onDeath(LivingEntity caveSpider) {
        if (caveSpider.getWorld().isClient()) return;
        ServerWorld world = (ServerWorld) caveSpider.getWorld();
        BlockPos blockPos = caveSpider.getBlockPos();

        while (true) {
            BlockState blockState = world.getBlockState(blockPos);
            BlockState blockBelow = world.getBlockState(blockPos.down());
            if ((blockState.isOf(Blocks.AIR) || blockState.isOf(Blocks.CAVE_AIR)) && blockBelow.isOf(Blocks.AIR)) {
                boolean hasAdjacentNonAir = 
                    !world.getBlockState(blockPos.north()).isAir() ||
                    !world.getBlockState(blockPos.south()).isAir() ||
                    !world.getBlockState(blockPos.east()).isAir() ||
                    !world.getBlockState(blockPos.west()).isAir() ||
                    !world.getBlockState(blockPos.up()).isAir();
                if (hasAdjacentNonAir) {
                    world.setBlockState(blockPos, Blocks.COBWEB.getDefaultState());
                    break;
                }
                blockPos = blockPos.down();
            } else {
                world.setBlockState(blockPos, Blocks.COBWEB.getDefaultState());
                break;
            }
        }

        if (caveSpider.getAttributeInstance(EntityAttributes.SCALE).getBaseValue() == 0.5f) return;
        for (int i = 0; i < (2 + Math.random() * 4); i++) {
            CaveSpiderEntity childSpider = EntityType.CAVE_SPIDER.create(world, null, blockPos, SpawnReason.TRIGGERED, false, false);
            if (childSpider != null) {
                childSpider.getAttributeInstance(EntityAttributes.SCALE).setBaseValue(0.5f);
                world.spawnEntity(childSpider);
            }
        }
    }
}