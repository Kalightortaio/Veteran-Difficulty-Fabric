package com.kalightortaio.veterandifficulty.systems.mechanics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import com.kalightortaio.veterandifficulty.interfaces.IEntityState;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MarkerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

public class TreeDecay {

    private static final int SCAN_RADIUS = 16;
    private static final int RANDOM_COORDS_COUNT = 64;
    private static final int PLAYER_RADIUS = 300;

    public static void processTrees(ServerWorld world) {

        List<? extends PlayerEntity> players = world.getPlayers();
        if (players.isEmpty()) return;

        for (PlayerEntity player : players) {
            BlockPos playerPos = player.getBlockPos();

            for (int i = 0; i < RANDOM_COORDS_COUNT; i++) {
                int x = playerPos.getX() + (int)((Math.random() * 2 - 1) * PLAYER_RADIUS);
                int y = -64 + (int)(Math.random() * (318 - (-64) + 1));
                int z = playerPos.getZ() + (int)((Math.random() * 2 - 1) * PLAYER_RADIUS);
                BlockPos randomPos = new BlockPos(x, y, z);

                MarkerEntity marker = findVDTree(world, randomPos, SCAN_RADIUS);
                if (marker != null) {
                    IEntityState entityState = (IEntityState) marker;
                    long lastModified = entityState.getLongState("lastModified");
                    int treeAge = entityState.getIntState("TreeAge");

                    long currentTime = world.getTime();
                    if (currentTime - lastModified >= 50) {
                        if (treeAge > 1) {
                            List<BlockPos> logPositions = collectLogs(world, marker.getBlockPos());
                            List<BlockPos> leafPositions = collectLeaves(world, logPositions);
                            List<BlockPos> otherLogs = collectOtherLogs(world, logPositions, leafPositions);
                            List<BlockPos> leavesToDestroy = new ArrayList<>();

                            for (BlockPos leaf : leafPositions) {
                                double distanceToOurLogs = logPositions.stream()
                                .mapToDouble(log -> log.getSquaredDistance(leaf))
                                .min()
                                .orElse(Double.MAX_VALUE);
                                double distanceToOtherLogs = otherLogs.stream()
                                .mapToDouble(log -> log.getSquaredDistance(leaf))
                                .min()
                                .orElse(Double.MAX_VALUE);
                                if (distanceToOurLogs <= distanceToOtherLogs) {
                                    leavesToDestroy.add(leaf);
                                }
                            }
                            for (BlockPos leaf : leavesToDestroy) {
                                world.breakBlock(leaf, false);
                            }
                            entityState.setLongState("lastModified", currentTime);
                            marker.discard();
                        } else {
                            entityState.setIntState("TreeAge", treeAge + 1);
                        }
                    }
                }
            }
        }
    }

    private static MarkerEntity findVDTree(ServerWorld world, BlockPos center, int radius) {
        Box scanBox = new Box(center).expand(radius);

        return world.getEntitiesByType(EntityType.MARKER, scanBox, entity -> 
        entity instanceof MarkerEntity && "VDTree".equals(entity.getCustomName().getString()))
        .stream()
        .min(Comparator.comparingDouble(entity -> entity.squaredDistanceTo(Vec3d.ofCenter(center))))
        .orElse(null);
    }

    private static List<BlockPos> collectLogs(ServerWorld world, BlockPos basePos) {
        List<BlockPos> logPositions = new ArrayList<>();
        Set<BlockPos> visited = new HashSet<>();
        Queue<BlockPos> queue = new LinkedList<>();
        queue.add(basePos);

        while (!queue.isEmpty() && logPositions.size() <= 150) {
            BlockPos current = queue.poll();
            if (!visited.contains(current)) {
                visited.add(current);
                if (world.getBlockState(current).isIn(BlockTags.LOGS)) {
                    logPositions.add(current);
                    for (int dx = -1; dx <= 1; dx++) {
                        for (int dy = -1; dy <= 1; dy++) {
                            for (int dz = -1; dz <= 1; dz++) {
                                queue.add(current.add(dx, dy, dz));
                            }
                        }
                    }
                }
            }
        }
        return logPositions;
    }

    private static List<BlockPos> collectLeaves(ServerWorld world, List<BlockPos> logPositions) {
        List<BlockPos> leafPositions = new ArrayList<>();
        Set<BlockPos> visited = new HashSet<>();
        Queue<BlockPos> queue = new LinkedList<>(logPositions);

        while (!queue.isEmpty()) {
            BlockPos current = queue.poll();
            if (!visited.contains(current)) {
                visited.add(current);
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        for (int dz = -1; dz <= 1; dz++) {
                            BlockPos neighbor = current.add(dx, dy, dz);
                            if (!visited.contains(neighbor)) {
                                if (world.getBlockState(neighbor).isIn(BlockTags.LEAVES)) {
                                    leafPositions.add(neighbor);
                                    queue.add(neighbor);
                                }
                            }
                        }
                    }
                }
            }
        }
        return leafPositions;
    }

    private static List<BlockPos> collectOtherLogs(ServerWorld world, List<BlockPos> logPositions, List<BlockPos> leafPositions) {
        List<BlockPos> otherLogs = new ArrayList<>();
        Set<BlockPos> visited = new HashSet<>();
        Queue<BlockPos> queue = new LinkedList<>(leafPositions);

        while (!queue.isEmpty()) {
            BlockPos current = queue.poll();
            if (!visited.contains(current)) {
                visited.add(current);
                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        for (int dz = -1; dz <= 1; dz++) {
                            BlockPos neighbor = current.add(dx, dy, dz);
                            if (!visited.contains(neighbor)) {
                                double distanceToLogs = logPositions.stream()
                                .mapToDouble(log -> log.getSquaredDistance(neighbor))
                                .min()
                                .orElse(Double.MAX_VALUE);
                                if (distanceToLogs <= Math.pow(10, 2)) {
                                    if (world.getBlockState(neighbor).isIn(BlockTags.LOGS) && !logPositions.contains(neighbor)) {
                                        otherLogs.add(neighbor);
                                    } else if (world.getBlockState(neighbor).isIn(BlockTags.LEAVES)) {
                                        queue.add(neighbor);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return otherLogs;
    }
}
