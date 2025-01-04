package com.kalightortaio.veterandifficulty.mob;

import java.util.EnumSet;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kalightortaio.veterandifficulty.interfaces.IEntityState;
import com.kalightortaio.veterandifficulty.mixin.accessor.MobEntityAccessor;
import com.kalightortaio.veterandifficulty.systems.internal.EntityModifiers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class Spider {

    public static void onLoad(MinecraftServer server, Entity entity) {
        if (entity instanceof SpiderEntity spider && spider instanceof IEntityState spiderStates && !spiderStates.getBooleanState(EntityModifiers._KEY)) {
            GoalSelector goalSelector = ((MobEntityAccessor) spider).getGoalSelector();
            goalSelector.add(1, new ModMountTargetGoal(spider));
        }
    }

    public static void onDamage(ServerWorld world, LivingEntity entity) {
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 40, 3, false, false, false));
        if (Math.random() < 0.4) {
            unmountSpider(entity);
        }
    }

    public static void onDeath(DamageSource source, LivingEntity entity, CallbackInfo ci) {
        unmountSpider(entity);
    }

    private static void unmountSpider(Entity entity) {
        if (entity.getWorld().isClient()) return;
        if (entity instanceof IEntityState spiderStates) {
            if (spiderStates.getBooleanState("SpiderRider")) {
                spiderStates.setBooleanState("SpiderRider", false);
            }
        } else {
            System.err.println("Failed to apply IEntityState to: " + entity);
        }
    }

    private static class ModMountTargetGoal extends Goal {
        private SpiderEntity spider;
        @Nullable
        private ServerPlayerEntity target;
        private static final float mountRadius = 2.0f;
        private int mountCooldown;
        private int biteCooldown;
        private boolean shouldTick;
        private boolean shouldStop;

        public ModMountTargetGoal(SpiderEntity spider) {
            this.spider = spider;
            this.target = null;
            this.mountCooldown = 0;
            this.biteCooldown = 20;
            this.shouldTick = false;
            this.shouldStop = false;
            setControls(EnumSet.of(Control.MOVE));
        }

        public boolean canStart() {
            if (shouldTick) return true;
            if (Math.random() < 0.2) return false;
            if (spider.getTarget() == null) return false;
            if (!(spider.getTarget() instanceof ServerPlayerEntity)) return false;
            ServerPlayerEntity player = (ServerPlayerEntity) spider.getTarget();
            if (spider.squaredDistanceTo(player) > Math.pow(mountRadius, 2)) return false;
            if (mountCooldown > 0) {
                mountCooldown--;
                return false;
            }
            if (player instanceof IEntityState playerStates && playerStates.getBooleanState("hasSpiderRider")) {
                boolean anotherSpiderRiding = spider.getWorld().getEntitiesByClass(SpiderEntity.class, spider.getBoundingBox().expand(Math.pow(mountRadius, 2)), otherSpider -> (otherSpider instanceof IEntityState otherSpiderStates) && otherSpiderStates.getBooleanState("SpiderRider")).size() > 0;
                if (anotherSpiderRiding) return false;
            }
            this.target = player;
            return true; 
        }

        public void start() {
            spider.refreshPositionAndAngles(target.getX(), target.getY() + target.getStandingEyeHeight() - 0.5, target.getZ(), target.getYaw(), target.getPitch());
            if (!(spider.hasNoGravity())) spider.setNoGravity(true);
            if (!(spider.noClip)) spider.noClip = true;
            if (target instanceof IEntityState targetStates) {
                targetStates.setBooleanState("hasSpiderRider", true);
            } else {
                System.err.println("Failed to apply IEntityState to: " + target);
                return;
            }
            EntityModifiers.tagEntity(spider, spider.getServer(), "SpiderRider");
            this.shouldStop = false;
            this.shouldTick = true;
        }

        public void stop() {
            if (shouldStop) {
                this.mountCooldown = 40;
                if (spider.hasNoGravity()) spider.setNoGravity(false);
                if (spider.noClip) spider.noClip = false;
                if (spider instanceof IEntityState spiderStates) {
                    spiderStates.setBooleanState("SpiderRider", false);
                } else {
                    return;
                }
                if (target instanceof IEntityState targetStates) {
                    targetStates.setBooleanState("hasSpiderRider", false);
                } else {
                    System.err.println("Failed to apply IEntityState to: " + target);
                    return;
                }
                this.target = null;
                this.shouldStop = false;
                this.shouldTick = false;
            }
        }

        public boolean shouldRunEveryTick() {
            return shouldTick;
        }

        public void tick() {
            if (spider instanceof IEntityState spiderStates) {
                if (spiderStates.getBooleanState("SpiderRider")) {
                    spider.refreshPositionAndAngles(target.getX(), target.getY() + target.getStandingEyeHeight(), target.getZ(), target.getYaw(), target.getPitch());
                    target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 30, 2, false, false, true));
                    if (biteCooldown > 0) {
                        biteCooldown--;
                    } else {
                        biteCooldown = 20;
                        target.damage(target.getServerWorld(), target.getDamageSources().magic(), 3.0f);
                        spider.heal(8.0f);
                    }
                } else {
                    this.shouldStop = true;
                    this.stop();
                }
            } else {
                System.err.println("Failed to apply IEntityState to: " + spider);
                return;
            }
        }
    }
}
