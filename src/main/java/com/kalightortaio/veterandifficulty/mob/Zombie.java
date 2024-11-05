package com.kalightortaio.veterandifficulty.mob;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.jetbrains.annotations.Nullable;

import com.kalightortaio.veterandifficulty.interfaces.IEntityState;
import com.kalightortaio.veterandifficulty.mixin.accessor.MobEntityAccessor;
import com.kalightortaio.veterandifficulty.systems.mechanics.EntityModifiers;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

public class Zombie {

    public static void onLoad(MinecraftServer server, Entity entity) {
        if (entity instanceof ZombieEntity zombie && !((IEntityState) zombie).getBooleanState(EntityModifiers._KEY)) {
            zombie.getAttributeInstance(EntityAttributes.SPAWN_REINFORCEMENTS).setBaseValue(0.4f);
            zombie.getAttributeInstance(EntityAttributes.FOLLOW_RANGE).setBaseValue(64.0f);
            zombie.getAttributeInstance(EntityAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.25f);
            zombie.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(30.0f);
            zombie.setHealth(30.0f);
            if (zombie.isBaby()) {
                zombie.getAttributeInstance(EntityAttributes.SCALE).setBaseValue(1.25f);
                zombie.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(10.0f);
                zombie.setHealth(10.0f);
            }
            
            GoalSelector goalSelector = ((MobEntityAccessor) zombie).getGoalSelector();
            goalSelector.add(1, new ModZombieTorchBreak(zombie));

            EntityModifiers.tagEntity(zombie, server);
        }
    }
    
    private static class ModZombieTorchBreak extends Goal {
        private ZombieEntity zombie;
        @Nullable
        private BlockPos target;
        private final Set<BlockPos> torchBlacklist = new HashSet<>();
        private int attemptTicks = 0;
        private static final float searchRadius = 1.5f;
        private static final float breakDistance = 2.5f;
        private static final int maxAttemptTicks = 20;

        public ModZombieTorchBreak(ZombieEntity zombie) {
            this.zombie = zombie;
            this.setControls(EnumSet.of(Control.MOVE));
        }

        public boolean canStart() {
            BlockPos zombiePos = zombie.getBlockPos();
            ServerWorld world = (ServerWorld) zombie.getWorld();
            if (world == null || zombiePos == null) {
                return false;
            }

            Box scanBox = new Box(zombiePos).expand(searchRadius);

            BlockPos closestTorch = BlockPos.stream(scanBox)
                .filter(pos -> pos != null && world.isChunkLoaded(pos.getX() >> 4, pos.getZ() >> 4) && isTorch(world.getBlockState(pos).getBlock()))
                .map(BlockPos::toImmutable)
                .min(Comparator.comparingDouble(pos -> pos.getSquaredDistance(zombiePos)))
                .orElse(null);

            if (closestTorch != null) {
                this.target = closestTorch;
                attemptTicks = 0;
                return true;
            }
            return false;
        }

        public void start() {
            if (target != null) {
                this.zombie.getNavigation().startMovingTo(target.getX(), target.getY(), target.getZ(), 1.1);
            }
        }

        public void stop() {
            this.target = null;
            this.attemptTicks = 0;
        }

        public boolean shouldRunEveryTick() {
            return true;
        }

        public void tick() {
            if (target != null) {
                if (zombie.getBlockPos().isWithinDistance(target, breakDistance)) {
                    ServerWorld world = (ServerWorld) zombie.getWorld();
                    world.breakBlock(target, false);
                    this.stop();
                } else if (zombie.getNavigation().isIdle()) {
                    this.zombie.getNavigation().startMovingTo(target.getX(), target.getY(), target.getZ(), 1.0);
                    attemptTicks++;
                }

                if (attemptTicks > maxAttemptTicks && zombie.getBlockPos().isWithinDistance(target, 1.0)) {
                    torchBlacklist.add(target);
                    this.stop();
                }
            }
        }

        private boolean isTorch(Block block) {
            return block == Blocks.TORCH || block == Blocks.WALL_TORCH;
        }
    }
}
