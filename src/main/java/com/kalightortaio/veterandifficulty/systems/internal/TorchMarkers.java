package com.kalightortaio.veterandifficulty.systems.internal;
/*
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class TorchMarkers {

    private static final String MARKER_NAME = "VDStandardTorch";
    private static final String COUNTER_KEY = "VDTickCounter";
    private static final int TICKS_THRESHOLD = 100;

    public static void processMarkers(ServerWorld world) {
        for (Entity marker : world.getEntitiesByType(EntityType.MARKER, entity -> MARKER_NAME.equals(entity.getCustomName().getString()))) {
            int tickCounter = nbtData.contains(COUNTER_KEY) ? nbtData.getInt(COUNTER_KEY) : 0;
            System.out.println(tickCounter);

            tickCounter++;
            nbtData.putInt(COUNTER_KEY, tickCounter);

            marker.readNbt(nbtData);

            if (tickCounter >= TICKS_THRESHOLD) {
                BlockPos pos = marker.getBlockPos();
                world.setBlockState(pos, Blocks.AIR.getDefaultState()); // Todo: Replace with unlit torch later
                marker.kill();
            }
        }
    }
}
*/