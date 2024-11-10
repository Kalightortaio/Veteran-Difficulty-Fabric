package com.kalightortaio.veterandifficulty.mob;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.kalightortaio.veterandifficulty.systems.internal.ModTags;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class Vindicator {

    public static void onAttackGolem(ServerWorld world, LivingEntity ironGolem) {
        if (Math.random() < 0.8) return;
        BlockPos golemPos = ironGolem.getBlockPos().up();
        List<BlockPos> availablePositions = generateRandomPositions(golemPos);

        int blocksPlaced = 0;
        for (BlockPos pos : availablePositions) {
            if (blocksPlaced >= 4) break;
            BlockPos groundPos = findGroundPosition(world, pos);

            if (groundPos != null && world.getBlockState(groundPos).isIn(ModTags.AIR)) {
                world.setBlockState(groundPos, Blocks.IRON_BLOCK.getDefaultState());
                blocksPlaced++;
            }
        }

        if (blocksPlaced < 4) {
            for (int i = 0; i < 4 - blocksPlaced; i++) {
                world.spawnEntity(new ItemEntity(world, golemPos.getX(), golemPos.getY(), golemPos.getZ(), new ItemStack(Blocks.IRON_BLOCK)));
            }
        }

        boolean pumpkinPlaced = placePumpkin(world, availablePositions, golemPos);
        if (!pumpkinPlaced) {
            world.spawnEntity(new ItemEntity(world, golemPos.getX(), golemPos.getY(), golemPos.getZ(), new ItemStack(Blocks.CARVED_PUMPKIN)));
        }

        SoundEvent soundEvent = SoundEvents.ENTITY_IRON_GOLEM_DEATH;
        SoundCategory soundCategory = SoundCategory.HOSTILE;
        world.playSound(null, ironGolem.getX(), ironGolem.getY(), ironGolem.getZ(), soundEvent, soundCategory);

        ironGolem.discard();
    }

    private static BlockPos findGroundPosition(ServerWorld world, BlockPos pos) {
        while (world.getBlockState(pos.down()).isIn(ModTags.AIR)) {
            pos = pos.down();
        }
        return pos;
    }

    private static List<BlockPos> generateRandomPositions(BlockPos center) {
        List<BlockPos> positions = new ArrayList<>();
        int range = 3;

        for (int x = -range; x <= range; x++) {
            for (int z = -range; z <= range; z++) {
                BlockPos pos = center.add(x, 0, z);

                if (Math.random() < 0.6 || (x != 0 && z != 0)) {
                    positions.add(pos);
                } else {
                    positions.add(0, pos);
                }
            }
        }

        Collections.shuffle(positions);
        return avoidSquareClustering(positions);
    }

    private static List<BlockPos> avoidSquareClustering(List<BlockPos> positions) {
        List<BlockPos> filteredPositions = new ArrayList<>();

        for (BlockPos pos : positions) {
            if (!wouldCreateSquare(filteredPositions, pos)) {
                filteredPositions.add(pos);
            }
            if (filteredPositions.size() == 4) break;
        }

        return filteredPositions;
    }

    private static boolean wouldCreateSquare(List<BlockPos> existingPositions, BlockPos newPos) {
        for (BlockPos pos : existingPositions) {
            int dx = Math.abs(pos.getX() - newPos.getX());
            int dz = Math.abs(pos.getZ() - newPos.getZ());
            if (dx == 1 && dz == 1) return true;
        }
        return false;
    }

    private static boolean placePumpkin(ServerWorld world, List<BlockPos> ironBlockPositions, BlockPos golemCenter) {
        for (BlockPos ironPos : ironBlockPositions) {
            BlockPos pumpkinPos = findGroundPosition(world, ironPos.up());
            shuntEntities(world, pumpkinPos);

            if (world.getBlockState(pumpkinPos).isIn(ModTags.AIR) && !wouldFormGolem(world, ironPos)) {
                world.setBlockState(pumpkinPos, Blocks.CARVED_PUMPKIN.getDefaultState());
                return true;
            }
        }
        return false;
    }

    private static boolean wouldFormGolem(ServerWorld world, BlockPos ironPos) {
        return world.getBlockState(ironPos.up()).isOf(Blocks.IRON_BLOCK)
            && world.getBlockState(ironPos.up(2)).isOf(Blocks.IRON_BLOCK)
            && world.getBlockState(ironPos.north()).isOf(Blocks.IRON_BLOCK)
            && world.getBlockState(ironPos.south()).isOf(Blocks.IRON_BLOCK);
    }

    private static void shuntEntities(ServerWorld world, BlockPos pos) {
        Box box = new Box(pos).expand(0.5F);
        List<Entity> entities = world.getOtherEntities(null, box);

        for (Entity entity : entities) {
            if (entity instanceof LivingEntity) {
                BlockPos entityPos = entity.getBlockPos();
                BlockPos newPos = findNearestAirSpace(world, entityPos);

                if (newPos != null) {
                    entity.setPosition(Vec3d.ofCenter(newPos));
                }
            }
        }
    }

    private static BlockPos findNearestAirSpace(ServerWorld world, BlockPos origin) {
        int range = 3;

        for (int radius = 1; radius <= range; radius++) {
            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos checkPos = origin.add(x, 0, z);
                    BlockPos abovePos = checkPos.up();

                    if (world.getBlockState(checkPos).isIn(ModTags.AIR) && world.getBlockState(abovePos).isIn(ModTags.AIR)) {
                        return checkPos;
                    }
                }
            }
        }
        return null;
    }
}