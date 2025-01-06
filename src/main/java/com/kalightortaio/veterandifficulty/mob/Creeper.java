package com.kalightortaio.veterandifficulty.mob;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.jetbrains.annotations.Nullable;

import com.kalightortaio.veterandifficulty.interfaces.IEntityState;
import com.kalightortaio.veterandifficulty.mixin.accessor.MobEntityAccessor;
import com.kalightortaio.veterandifficulty.systems.internal.EntityModifiers;
import com.kalightortaio.veterandifficulty.systems.internal.ModTags;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.CreeperIgniteGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class Creeper {

    public static void onLoad(MinecraftServer server, Entity entity) {
        if (entity instanceof CreeperEntity creeper && !((IEntityState) creeper).getBooleanState(EntityModifiers._KEY)) {
            creeper.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.35f);

            GoalSelector goalSelector = ((MobEntityAccessor) creeper).getGoalSelector();
            GoalSelector targetSelector = ((MobEntityAccessor) creeper).getTargetSelector();

            goalSelector.getGoals().forEach(prioritizedGoal -> {
                if (prioritizedGoal != null && prioritizedGoal.getGoal() instanceof CreeperIgniteGoal) {
                    goalSelector.remove(prioritizedGoal.getGoal());
                }
            });
            goalSelector.add(2, new ModCreeperIgniteGoal(creeper));
            
            targetSelector.getGoals().forEach(prioritizedGoal -> {
                if (prioritizedGoal != null && prioritizedGoal.getGoal() instanceof ActiveTargetGoal) {
                    targetSelector.remove(prioritizedGoal.getGoal());
                }
            });
            targetSelector.add(1, new ActiveTargetGoal<PlayerEntity>(creeper, PlayerEntity.class, false));
            EntityModifiers.tagEntity(creeper, server);
        }
    }

    public static void onTick(ServerWorld world) {
        ExplosionHandler.update(world);
        for (CreeperEntity creeper : world.getEntitiesByType(EntityType.CREEPER, creeper -> true)) {
            world.getPlayers().stream()
            .filter(player -> !player.isSpectator() && !player.isCreative())
            .sorted(Comparator.comparingDouble(player -> creeper.squaredDistanceTo(player)))
            .filter(player -> creeper.squaredDistanceTo(player) <= 256)
            .findFirst()
            .ifPresent(player -> {
                if (!player.equals(creeper.getTarget())) {
                    creeper.setTarget(player);
                }
            });
        }
    }

    public static void markArea(CreeperEntity creeper) {
        ExplosionHandler.markArea(creeper);
    }

    private static class ModCreeperIgniteGoal extends Goal {
        private CreeperEntity creeper;
        @Nullable
        private LivingEntity target;

        public ModCreeperIgniteGoal(CreeperEntity creeper) {
            this.creeper = creeper;
            this.setControls(EnumSet.of(Control.MOVE));
        }

        public boolean canStart() {
            LivingEntity livingEntity = this.creeper.getTarget();
            if (livingEntity instanceof PlayerEntity player && (player.isSpectator() || player.isCreative())) {
                this.target = null;
                return false;
            }
            return this.creeper.getFuseSpeed() > 0 || livingEntity != null && this.creeper.squaredDistanceTo(livingEntity) < 16.0f;
        }

        public void start() {
            this.creeper.getNavigation().stop();
            this.target = this.creeper.getTarget();
        }

        public void stop() {
            this.target = null;
        }

        public boolean shouldRunEveryTick() {
            return true;
        }

        public void tick() {
            if (this.target == null) {
                this.creeper.setFuseSpeed(-1);
            } else if (this.creeper.squaredDistanceTo(this.target) > 49.0) {
                this.creeper.setFuseSpeed(-1);
            } else {
                this.creeper.setFuseSpeed(1);
            }
        }
    }

    public static class ExplosionHandler {
        
        private static Set<BlockPos> blocksBeforeExplosion = new HashSet<>();

        private static void markArea(CreeperEntity creeper) {
            BlockPos creeperPos = creeper.getBlockPos();
            World world = creeper.getWorld();
            blocksBeforeExplosion.clear();
            int radius = 5;
            double radiusSquared = Math.pow(radius, 2);

            Box scanBox = new Box(
                creeperPos.getX() - radius, creeperPos.getY() - radius, creeperPos.getZ() - radius,
                creeperPos.getX() + radius, creeperPos.getY() + radius, creeperPos.getZ() + radius
            );

            BlockPos.stream(scanBox)
            .filter(pos -> creeperPos.getSquaredDistance(pos) <= radiusSquared)
            .filter(pos -> !world.getBlockState(pos).isIn(ModTags.AIR))
            .map(BlockPos::toImmutable)
            .forEach(blocksBeforeExplosion::add);
        }

        private static void update(ServerWorld world) {
            if (!blocksBeforeExplosion.isEmpty()) {
                process(world);
                blocksBeforeExplosion.clear();
            }
        }

        private static void process(ServerWorld world) {
            for (BlockPos pos : blocksBeforeExplosion) {
                if (!world.getBlockState(pos).isIn(ModTags.AIR)) continue;
                for (BlockPos neighborPos : new BlockPos[] {
                    pos.down(), pos.up(), pos.north(), pos.south(), pos.east(), pos.west() }) {
                    if (world.getBlockState(neighborPos).isIn(BlockTags.MOSS_REPLACEABLE)) {
                        world.setBlockState(neighborPos, Blocks.MOSS_BLOCK.getDefaultState());
                    }
                }
            }
        }
    }
}
