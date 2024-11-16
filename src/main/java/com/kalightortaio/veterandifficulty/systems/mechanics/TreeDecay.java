package com.kalightortaio.veterandifficulty.systems.mechanics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import com.kalightortaio.veterandifficulty.interfaces.IEntityState;
import com.kalightortaio.veterandifficulty.systems.internal.ModTags;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Fertilizable;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MarkerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

public class TreeDecay {

    private static final int SCAN_RADIUS = 16;
    private static final int RANDOM_COORDS_COUNT = 64;
    private static final int PLAYER_RADIUS = 300;

    public static void processTrees(ServerWorld world) {

        List<? extends PlayerEntity> players = world.getPlayers();
        if (players.isEmpty()) return;

        List<BlockPos> checkedAreas = new ArrayList<>();

        for (PlayerEntity player : players) {
            BlockPos playerPos = player.getBlockPos();

            boolean isWithinCheckedArea = checkedAreas.stream()
            .anyMatch(checkedPos -> playerPos.isWithinDistance(checkedPos, PLAYER_RADIUS));

            if (isWithinCheckedArea) {
                continue;
            }

            checkedAreas.add(playerPos);

            for (int i = 0; i < RANDOM_COORDS_COUNT; i++) {
                int x = playerPos.getX() + (int)((Math.random() * 2 - 1) * PLAYER_RADIUS);
                int y = -64 + (int)(Math.random() * (318 - (-64) + 1));
                int z = playerPos.getZ() + (int)((Math.random() * 2 - 1) * PLAYER_RADIUS);
                BlockPos randomPos = new BlockPos(x, y, z);

                MarkerEntity marker = findVDTree(world, randomPos, SCAN_RADIUS);
                if (marker != null) {
                    if (!(world.getBlockState(marker.getBlockPos()).isIn(BlockTags.LOGS))) {
                        marker.discard();
                        return;
                    }
                    IEntityState entityState = (IEntityState) marker;
                    long lastModified = entityState.getLongState("lastModified");
                    int treeAge = entityState.getIntState("TreeAge");

                    long currentTime = world.getTime();
                    if (currentTime - lastModified >= 522) {
                        switch (treeAge) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 5:
                            case 7:
                            case 9:
                            case 10:
                            case 11:
                                updateMarker(entityState, treeAge, currentTime);
                                break;
                            case 4:
                                deadLeaves(world, marker);
                                updateMarker(entityState, treeAge, currentTime);
                                break;
                            case 6:
                                fellTree(world, marker);
                                updateMarker(entityState, treeAge, currentTime);
                                break;
                            case 8:
                                growAround(world, marker);
                                updateMarker(entityState, treeAge, currentTime);
                                break;
                            case 12:
                                layToRest(world, marker);
                                break;
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

    private static void updateMarker(IEntityState entityState, int treeAge, long currentTime) {
        entityState.setIntState("TreeAge", treeAge + 1);
        entityState.setLongState("lastModified", currentTime);
    }

    private static void layToRest(ServerWorld world, MarkerEntity marker) {
        BlockPos stumpPos = marker.getBlockPos();
        BlockState logType = world.getBlockState(stumpPos);
        List<BlockPos> stumpPositions = getStumpPositions(world, stumpPos);

        IEntityState entityState = (IEntityState) marker;
        int logsPlaced = entityState.getIntState("logsPlaced");

        Direction fallDirection = Direction.NORTH;
        if (entityState.getBooleanState("fellSouth")) fallDirection = Direction.SOUTH;
        else if (entityState.getBooleanState("fellEast")) fallDirection = Direction.EAST;
        else if (entityState.getBooleanState("fellWest")) fallDirection = Direction.WEST;

        BlockPos baseStump = getBaseStump(stumpPositions, fallDirection);
        List<BlockPos> alignedStumps = getAlignedStumps(stumpPositions, baseStump, fallDirection);

        for (BlockPos stump : stumpPositions) {
            world.setBlockState(stump, Blocks.AIR.getDefaultState(), 3);
        }

        for (BlockPos alignedStump : alignedStumps) {
            List<BlockPos> fallenLogs = getFallenLogs(world, alignedStump, fallDirection, logType, logsPlaced);
            for (BlockPos fallenLog : fallenLogs) {
                world.setBlockState(fallenLog, Blocks.AIR.getDefaultState(), 3);
            }
        }

        marker.discard();
    }

    private static void growAround(ServerWorld world, MarkerEntity marker) {
        BlockPos stumpPos = marker.getBlockPos();
        BlockState logType = world.getBlockState(stumpPos);
        List<BlockPos> stumpPositions = getStumpPositions(world, stumpPos);

        IEntityState entityState = (IEntityState) marker;
        int logsPlaced = entityState.getIntState("logsPlaced");

        Direction fallDirection = Direction.NORTH;
        if (entityState.getBooleanState("fellSouth")) fallDirection = Direction.SOUTH;
        else if (entityState.getBooleanState("fellEast")) fallDirection = Direction.EAST;
        else if (entityState.getBooleanState("fellWest")) fallDirection = Direction.WEST;

        BlockPos baseStump = getBaseStump(stumpPositions, fallDirection);
        List<BlockPos> alignedStumps = getAlignedStumps(stumpPositions, baseStump, fallDirection);

        Box scanBox = new Box(stumpPos).expand(3.0f);
        BlockPos grassPos = BlockPos.stream(scanBox)
        .filter(pos -> pos != null && world.isChunkLoaded(pos.getX() >> 4, pos.getZ() >> 4) && world.getBlockState(pos).isOf(Blocks.GRASS_BLOCK) &&  world.getBlockState(pos.up()).isOf(Blocks.AIR))
        .map(BlockPos::toImmutable)
        .min(Comparator.comparingDouble(pos -> pos.getSquaredDistance(stumpPos)))
        .orElse(null);
        if (grassPos != null) {
            BlockState blockState = world.getBlockState(grassPos);
            Block grassBlock = blockState.getBlock();
            if (grassBlock instanceof Fertilizable fertilizable) {
                if (fertilizable.isFertilizable(world, grassPos, blockState)) {
                    fertilizable.grow((ServerWorld)world, world.random, grassPos, blockState);
                }
            }
        }

        BlockPos mushroomPos = stumpPos.up();
        if (world.getBlockState(mushroomPos).isIn(ModTags.AIR)) {
            BlockState mushroom = Math.random() < 0.5 ? Blocks.RED_MUSHROOM.getDefaultState() : Blocks.BROWN_MUSHROOM.getDefaultState();
            world.setBlockState(mushroomPos, mushroom, 3);
        }

        for (BlockPos alignedStump : alignedStumps) {
            List<BlockPos> fallenLogs = getFallenLogs(world, alignedStump, fallDirection, logType, logsPlaced);
            for (BlockPos fallenLog : fallenLogs) {
                BlockPos mushroomCheckPos = fallenLog.up();
                if (Math.random() < 0.33 && world.getBlockState(mushroomCheckPos).isIn(ModTags.AIR)) {
                    BlockState mushroom = Math.random() < 0.5 ? Blocks.RED_MUSHROOM.getDefaultState() : Blocks.BROWN_MUSHROOM.getDefaultState();
                    world.setBlockState(mushroomCheckPos, mushroom, 3);
                }
            }
        }
    }

    private static void fellTree(ServerWorld world, MarkerEntity marker) {
        List<BlockPos> logPositions = collectLogs(world, marker.getBlockPos());
        BlockPos stumpPos = marker.getBlockPos();
        BlockState logType = world.getBlockState(stumpPos);
        List<BlockPos> stumpPositions = getStumpPositions(world, stumpPos);

        logPositions.sort(Comparator.comparingInt(BlockPos::getY).reversed());
        for (BlockPos logPos : logPositions) {
            if (!stumpPositions.contains(logPos)) {
                BlockPos belowLog = logPos.down();
                if (!(world.getBlockState(belowLog).isIn(BlockTags.DIRT))) {
                    world.setBlockState(logPos, Blocks.AIR.getDefaultState(), 3);
                }
            }
        }

        Direction[] directions = {Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};
        Direction fallDirection = directions[world.getRandom().nextInt(directions.length)];

        BlockPos baseStump = getBaseStump(stumpPositions, fallDirection);
        List<BlockPos> alignedStumps = getAlignedStumps(stumpPositions, baseStump, fallDirection);

        int minY = logPositions.stream()
        .mapToInt(BlockPos::getY)
        .min()
        .orElse(stumpPos.getY());

        int maxY = logPositions.stream()
        .mapToInt(BlockPos::getY)
        .max()
        .orElse(stumpPos.getY());

        int treeHeight = maxY - minY + 1;
        int sidewaysLogCount = Math.max(3, (int) Math.floor(treeHeight / 3)) + (int) (Math.random() * 2);
        int logsPlaced = 0;

        for (BlockPos alignedStump : alignedStumps) {
            BlockPos currentPos = alignedStump.offset(fallDirection, 2);

            while (logsPlaced < sidewaysLogCount) {
                boolean logPlaced = false;

                for (int dy = -2; dy <= 2; dy++) {
                    BlockPos checkPos = currentPos.up(dy);
                    BlockState groundState = world.getBlockState(checkPos.down());

                    if (world.getBlockState(checkPos).isIn(ModTags.AIR) && !groundState.isIn(ModTags.AIR)) {
                        BlockState sidewaysLog = logType.with(Properties.AXIS, fallDirection.getAxis());
                        world.setBlockState(checkPos, sidewaysLog, 3);

                        currentPos = currentPos.offset(fallDirection);
                        logsPlaced++;
                        logPlaced = true;
                        break;
                    }
                }

                if (!logPlaced) break;
            }
        }
        IEntityState entityState = (IEntityState) marker;
        entityState.setBooleanState("fellNorth", fallDirection == Direction.NORTH);
        entityState.setBooleanState("fellSouth", fallDirection == Direction.SOUTH);
        entityState.setBooleanState("fellEast", fallDirection == Direction.EAST);
        entityState.setBooleanState("fellWest", fallDirection == Direction.WEST);
        entityState.setIntState("logsPlaced", logsPlaced);
    }

    private static void deadLeaves(ServerWorld world, MarkerEntity marker) {
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
            world.breakBlock(leaf, true);
        }
    }

    private static List<BlockPos> collectLogs(ServerWorld world, BlockPos basePos) {
        List<BlockPos> logPositions = new ArrayList<>();
        Set<BlockPos> visited = new HashSet<>();
        Queue<BlockPos> queue = new LinkedList<>();
        queue.add(basePos);

        while (!queue.isEmpty() && logPositions.size() <= 400) {
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
        PriorityQueue<BlockPos> queue = new PriorityQueue<>(
            Comparator.comparingDouble(pos -> logPositions.stream()
                .mapToDouble(log -> log.getSquaredDistance(pos))
                .min()
                .orElse(Double.MAX_VALUE))
        );
        queue.addAll(logPositions);

        while (!queue.isEmpty()) {
            BlockPos current = queue.poll();
            if (!visited.contains(current)) {
                visited.add(current);

                for (int dx = -1; dx <= 1; dx++) {
                    for (int dy = -1; dy <= 1; dy++) {
                        for (int dz = -1; dz <= 1; dz++) {
                            BlockPos neighbor = current.add(dx, dy, dz);
                            if (!visited.contains(neighbor) &&
                                logPositions.stream().anyMatch(log -> log.getManhattanDistance(neighbor) <= 7) &&
                                world.getBlockState(neighbor).isIn(BlockTags.LEAVES)) {

                                leafPositions.add(neighbor);
                                queue.add(neighbor);
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
        PriorityQueue<BlockPos> queue = new PriorityQueue<>(
            Comparator.comparingDouble(pos -> logPositions.stream()
                .mapToDouble(log -> log.getSquaredDistance(pos))
                .min()
                .orElse(Double.MAX_VALUE))
        );
        queue.addAll(leafPositions);

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
                                    .mapToDouble(log -> log.getManhattanDistance(neighbor))
                                    .min()
                                    .orElse(Double.MAX_VALUE);

                                if (distanceToLogs <= 10) {
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

    private static List<BlockPos> getStumpPositions(ServerWorld world, BlockPos stumpPos) {
        List<BlockPos> stumpPositions = new ArrayList<>();
        stumpPositions.add(stumpPos);

        for (int dx = 0; dx <= 1; dx++) {
            for (int dz = 0; dz <= 1; dz++) {
                if (dx != 0 || dz != 0) {
                    BlockPos neighbor = stumpPos.add(dx, 0, dz);
                    BlockState neighborState = world.getBlockState(neighbor);
                    if (neighborState.isIn(BlockTags.LOGS) && neighborState.get(Properties.AXIS) == Direction.Axis.Y) {
                        stumpPositions.add(neighbor);
                    }
                }
            }
        }
        return stumpPositions;
    }

    private static BlockPos getBaseStump(List<BlockPos> stumpPositions, Direction fallDirection) {
        if (fallDirection == Direction.NORTH) {
            return stumpPositions.stream()
                .min(Comparator.comparingInt(pos -> pos.getZ()))
                .orElse(stumpPositions.get(0));
        } else if (fallDirection == Direction.SOUTH) {
            return stumpPositions.stream()
                .max(Comparator.comparingInt(pos -> pos.getZ()))
                .orElse(stumpPositions.get(0));
        } else if (fallDirection == Direction.WEST) {
            return stumpPositions.stream()
                .min(Comparator.comparingInt(pos -> pos.getX()))
                .orElse(stumpPositions.get(0));
        } else if (fallDirection == Direction.EAST) {
            return stumpPositions.stream()
                .max(Comparator.comparingInt(pos -> pos.getX()))
                .orElse(stumpPositions.get(0));
        }
        return stumpPositions.get(0);
    }

    private static List<BlockPos> getAlignedStumps(List<BlockPos> stumpPositions, BlockPos baseStump, Direction fallDirection) {
        List<BlockPos> alignedStumps = new ArrayList<>();
        for (BlockPos stump : stumpPositions) {
            if ((fallDirection == Direction.NORTH || fallDirection == Direction.SOUTH) && stump.getZ() == baseStump.getZ()) {
                alignedStumps.add(stump);
            } else if ((fallDirection == Direction.EAST || fallDirection == Direction.WEST) && stump.getX() == baseStump.getX()) {
                alignedStumps.add(stump);
            }
        }
        return alignedStumps;
    }

    private static List<BlockPos> getFallenLogs(ServerWorld world, BlockPos alignedStump, Direction fallDirection, BlockState logType, int logsPlaced) {
        List<BlockPos> fallenLogs = new ArrayList<>();
        BlockPos currentPos = alignedStump.offset(fallDirection, 2);

        while (logsPlaced > 0) {
            boolean logFound = false;

            for (int dy = -2; dy <= 2; dy++) {
                BlockPos checkPos = currentPos.up(dy);
                BlockState currentState = world.getBlockState(checkPos);

                if (currentState.isOf(logType.getBlock()) && currentState.get(Properties.AXIS) == fallDirection.getAxis()) {
                    fallenLogs.add(checkPos);
                    currentPos = checkPos.offset(fallDirection);
                    logsPlaced--;
                    logFound = true;
                    break;
                }
            }

            if (!logFound) break;
        }

        return fallenLogs;
    }
}
