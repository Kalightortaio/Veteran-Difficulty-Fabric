package com.kalightortaio.veterandifficulty.systems.mechanics;

import com.kalightortaio.veterandifficulty.systems.internal.ModTags;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class Regrowth {

    public static void processItem(ItemEntity itemEntity) {
        ServerWorld world = (ServerWorld) itemEntity.getWorld();
        Item saplingOrCrop = itemEntity.getStack().getItem();
        BlockPos pos = itemEntity.getBlockPos();

        if (tryPlant(world, pos, saplingOrCrop)) {
            itemEntity.discard();
        }
    }

    private static boolean tryPlant(ServerWorld world, BlockPos pos, Item item) {
        BlockState itemBlockPos = world.getBlockState(pos);
        BlockState groundBlockPos = world.getBlockState(pos.down());
        BlockState airBlockPos = world.getBlockState(pos.up());
        boolean isInAir = itemBlockPos.isIn(ModTags.AIR) && !itemBlockPos.isIn(BlockTags.SAPLINGS);
        boolean isOnDirt = groundBlockPos.isIn(ModTags.SAPLING_SURFACE);
        boolean isOnSand = groundBlockPos.isIn(BlockTags.SAND);
        boolean validWart = itemBlockPos.isOf(Blocks.SOUL_SAND) && airBlockPos.isIn(ModTags.AIR);
        boolean validCrop = itemBlockPos.isOf(Blocks.FARMLAND) && airBlockPos.isIn(ModTags.AIR);
        boolean validMushroom = isInAir && (isOnDirt || groundBlockPos.isIn(BlockTags.BASE_STONE_OVERWORLD) || groundBlockPos.isIn(BlockTags.BASE_STONE_NETHER));
        boolean validSapling = isInAir && isOnDirt;

        if (item == Items.OAK_SAPLING && validSapling) {
            world.setBlockState(pos, Blocks.OAK_SAPLING.getDefaultState());
            return true;
        } else if (item == Items.SPRUCE_SAPLING && validSapling) {
            world.setBlockState(pos, Blocks.SPRUCE_SAPLING.getDefaultState());
            return true;
        } else if (item == Items.BIRCH_SAPLING && validSapling) {
            world.setBlockState(pos, Blocks.BIRCH_SAPLING.getDefaultState());
            return true;
        } else if (item == Items.JUNGLE_SAPLING && validSapling) {
            world.setBlockState(pos, Blocks.JUNGLE_SAPLING.getDefaultState());
            return true;
        } else if (item == Items.ACACIA_SAPLING && validSapling) {
            world.setBlockState(pos, Blocks.ACACIA_SAPLING.getDefaultState());
            return true;
        } else if (item == Items.DARK_OAK_SAPLING && validSapling) {
            world.setBlockState(pos, Blocks.DARK_OAK_SAPLING.getDefaultState());
            return true;
        } else if (item == Items.CHERRY_SAPLING && validSapling) {
            world.setBlockState(pos, Blocks.CHERRY_SAPLING.getDefaultState());
            return true;
        } else if (item == Items.PALE_OAK_SAPLING && validSapling) {
            world.setBlockState(pos, Blocks.PALE_OAK_SAPLING.getDefaultState());
            return true;
        } else if (item == Items.MANGROVE_PROPAGULE && validSapling) {
            world.setBlockState(pos, Blocks.MANGROVE_PROPAGULE.getDefaultState());
            return true;
        } else if (item == Items.BROWN_MUSHROOM && validMushroom) {
            world.setBlockState(pos, Blocks.BROWN_MUSHROOM.getDefaultState());
            return true;
        } else if (item == Items.RED_MUSHROOM && validMushroom) {
            world.setBlockState(pos, Blocks.RED_MUSHROOM.getDefaultState());
            return true;
        } else if (item == Items.CRIMSON_FUNGUS && isInAir && (isOnDirt || groundBlockPos.isOf(Blocks.CRIMSON_NYLIUM))) {
            world.setBlockState(pos, Blocks.CRIMSON_FUNGUS.getDefaultState());
            return true;
        } else if (item == Items.WARPED_FUNGUS && isInAir && (isOnDirt || groundBlockPos.isOf(Blocks.WARPED_NYLIUM))) {
            world.setBlockState(pos, Blocks.WARPED_FUNGUS.getDefaultState());
            return true;
        } else if (item == Items.NETHER_WART && validWart) {
            world.setBlockState(pos.up(), Blocks.NETHER_WART.getDefaultState());
            return true;
        } else if (item == Items.WHEAT_SEEDS && validCrop) {
            world.setBlockState(pos.up(), Blocks.WHEAT.getDefaultState());
            return true;
        } else if (item == Items.BEETROOT_SEEDS && validCrop) {
            world.setBlockState(pos.up(), Blocks.BEETROOTS.getDefaultState());
            return true;
        } else if (item == Items.PUMPKIN_SEEDS && validCrop) {
            world.setBlockState(pos.up(), Blocks.PUMPKIN_STEM.getDefaultState());
            return true;
        } else if (item == Items.MELON_SEEDS && validCrop) {
            world.setBlockState(pos.up(), Blocks.MELON_STEM.getDefaultState());
            return true;
        } else if (item == Items.CARROT && validCrop) {
            world.setBlockState(pos.up(), Blocks.CARROTS.getDefaultState());
            return true;
        } else if (item == Items.POTATO && validCrop) {
            world.setBlockState(pos.up(), Blocks.POTATOES.getDefaultState());
            return true; 
        } else if (item == Items.TORCHFLOWER_SEEDS && validCrop) {
            world.setBlockState(pos.up(), Blocks.TORCHFLOWER_CROP.getDefaultState());
            return true;
        } else if (item == Items.PITCHER_POD && validCrop) {
            world.setBlockState(pos.up(), Blocks.PITCHER_CROP.getDefaultState());
            return true;
        } else if (item == Items.BAMBOO && isInAir && (isOnDirt || groundBlockPos.isIn(BlockTags.BAMBOO_PLANTABLE_ON))) {
            world.setBlockState(pos, Blocks.BAMBOO_SAPLING.getDefaultState());
            return true;
        } else if (item == Items.SUGAR_CANE && validSapling) {
            world.setBlockState(pos, Blocks.SUGAR_CANE.getDefaultState());
            return true;
        } else if (item == Items.SWEET_BERRIES && validSapling) {
            world.setBlockState(pos, Blocks.SWEET_BERRY_BUSH.getDefaultState());
            return true;
        } else if (item == Items.CHORUS_FLOWER && isInAir && groundBlockPos.isOf(Blocks.END_STONE)) {
            world.setBlockState(pos, Blocks.CHORUS_FLOWER.getDefaultState());
            return true;
        } else if (item == Items.CACTUS && isInAir && isOnSand) {
            world.setBlockState(pos, Blocks.CACTUS.getDefaultState());
            return true;
        }
        return false;
    }
}
