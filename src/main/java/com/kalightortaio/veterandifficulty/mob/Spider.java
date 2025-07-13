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
        if (entity instanceof SpiderEntity spider && !((IEntityState) spider).getBooleanState(EntityModifiers._KEY)) {
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
        IEntityState spiderState = (IEntityState) (SpiderEntity) entity;
        if (spiderState.getBooleanState("SpiderRider")) {
            spiderState.setBooleanState("SpiderRider", false);
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
            if (((IEntityState) player).getBooleanState("hasSpiderRider")) {
                boolean anotherSpiderRiding = spider.getWorld().getEntitiesByClass(SpiderEntity.class, spider.getBoundingBox().expand(Math.pow(mountRadius, 2)), otherSpider -> ((IEntityState) otherSpider).getBooleanState("SpiderRider")).size() > 0;
                if (anotherSpiderRiding) return false;
            }
            this.target = player;
            return true; 
        }

        public void start() {
            spider.refreshPositionAndAngles(target.getX(), target.getY() + target.getStandingEyeHeight() - 0.5, target.getZ(), target.getYaw(), target.getPitch());
            if (!(spider.hasNoGravity())) spider.setNoGravity(true);
            if (!(spider.noClip)) spider.noClip = true;
            ((IEntityState) target).setBooleanState("hasSpiderRider", true);
            EntityModifiers.tagEntity(spider, spider.getServer(), "SpiderRider");
            this.shouldStop = false;
            this.shouldTick = true;
        }

        public void stop() {
            if (shouldStop) {
                this.mountCooldown = 40;
                if (spider.hasNoGravity()) spider.setNoGravity(false);
                if (spider.noClip) spider.noClip = false;
                ((IEntityState) spider).setBooleanState("SpiderRider", false);
                ((IEntityState) target).setBooleanState("hasSpiderRider", false);
                this.target = null;
                this.shouldStop = false;
                this.shouldTick = false;
            }
        }

        public boolean shouldRunEveryTick() {
            return shouldTick;
        }

        public void tick() {
            if (((IEntityState) spider).getBooleanState("SpiderRider")) {
                spider.refreshPositionAndAngles(target.getX(), target.getY() + target.getStandingEyeHeight(), target.getZ(), target.getYaw(), target.getPitch());
                target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 30, 2, false, false, true));
                if (biteCooldown > 0) {
                    biteCooldown--;
                } else {
                    biteCooldown = 20;
                    target.damage(target.getWorld(), target.getDamageSources().magic(), 3.0f);
                    spider.heal(8.0f);
                }
            } else {
                this.shouldStop = true;
                this.stop();
            }
        }
    }
}
