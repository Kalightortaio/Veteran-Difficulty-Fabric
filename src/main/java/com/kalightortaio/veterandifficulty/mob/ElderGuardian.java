package com.kalightortaio.veterandifficulty.mob;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.joml.Math;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kalightortaio.veterandifficulty.effect.ModEffects;
import com.kalightortaio.veterandifficulty.interfaces.IEntityState;
import com.kalightortaio.veterandifficulty.systems.internal.EntityModifiers;
import com.kalightortaio.veterandifficulty.systems.internal.ModTags;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.ElderGuardianEntity;
import net.minecraft.entity.mob.GuardianEntity;
import net.minecraft.entity.projectile.ShulkerBulletEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ElderGuardian {

    public static void onLoad(MinecraftServer server, Entity entity) {
        if (entity instanceof ElderGuardianEntity elder && !((IEntityState) elder).getBooleanState(EntityModifiers._KEY)) {
            elder.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(120.0f);
            elder.setHealth(120.0f);

            EntityModifiers.tagEntity(elder, server);
        }
    }

    public static void onAttack(LivingEntity entity, DamageSource source) {
        if (source.isOf(DamageTypes.INDIRECT_MAGIC)) {
            entity.addStatusEffect(new StatusEffectInstance(ModEffects.SCALDING, 120, 0, false, true, true));
        }
    }

    public static void onDamage(LivingEntity entity, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source.getAttacker() instanceof ElderGuardianEntity) {
            cir.setReturnValue(false);
            return;
        }
        if (!(entity instanceof ElderGuardianEntity)) return;
        World world = entity.getWorld();
        double maxHealth = entity.getAttributeInstance(EntityAttributes.MAX_HEALTH).getBaseValue();
        float currentHealth = entity.getHealth();
        int phaseHealthOld = (4 - (int) Math.ceil((currentHealth / maxHealth) * 4));
        int phaseHealthNew = (4 - (int) Math.ceil(((currentHealth - amount) / maxHealth) * 4));
        if (phaseHealthNew > phaseHealthOld) {
            entity.getAttributeInstance(EntityAttributes.SCALE).setBaseValue(1.0F - (phaseHealthNew * 0.2F));
            for (int i = 0; i < (1 + Math.random() * 3); i++) {
                GuardianEntity guardian = new GuardianEntity(EntityType.GUARDIAN, world);
                guardian.refreshPositionAndAngles(entity.getX(), entity.getY(), entity.getZ(), entity.getYaw(), 0.0F);
                world.spawnEntity(guardian);
            }
        } else {
            for (int i = 0; i < (2 + Math.random() * 4); i++) {
                ElderSpike revengeSpike = new ElderSpike(world, entity, source.getAttacker(), entity.getFacing().getAxis());
                world.spawnEntity(revengeSpike);
            }
        }
    }

    public static void breakBlocks(ServerWorld world) {
        world.getEntitiesByType(EntityType.ELDER_GUARDIAN, elder -> true).stream()
        .filter(elder -> {
            BlockPos origin = elder.getBlockPos();
            BlockPos startPos = getStartPos(origin, 4);
            BlockPos endPos = getEndPos(origin, 4);
            boolean isInMonument = BlockPos.stream(startPos, endPos)
                    .anyMatch(pos -> world.getBlockState(pos).isIn(ModTags.MONUMENT));
            return isInMonument;
        })
        .forEach(elder -> {
            BlockPos origin = elder.getBlockPos();
            BlockPos startPos = getStartPos(origin, 3);
            BlockPos endPos = getEndPos(origin, 3);
            BlockPos.stream(startPos, endPos).forEach(pos -> {
                BlockState getWorldBlock = world.getBlockState(pos);
                if (!getWorldBlock.isAir() && !getWorldBlock.isIn(BlockTags.WITHER_IMMUNE) && !getWorldBlock.isIn(ModTags.MONUMENT) && !getWorldBlock.isIn(ModTags.SEA_FAUNA)) {
                    world.breakBlock(pos, false, elder);
                    world.setBlockState(pos, Blocks.WATER.getDefaultState());
                }
            });
        });
    }

    private static BlockPos getStartPos(BlockPos origin, int radius) {
        return origin.add(radius, radius, radius);
    }

    private static BlockPos getEndPos(BlockPos origin, int radius) {
        radius = (radius * -1);
        return origin.add(radius, radius, radius);
    }

    public static class ElderSpike extends ShulkerBulletEntity {

        public ElderSpike(EntityType<? extends ShulkerBulletEntity> entityType, World world) {
            super(entityType, world);
            super.noClip = true;
        }
                
        public ElderSpike(World world, LivingEntity owner, Entity target, Direction.Axis axis) {
            super(EntityType.SHULKER_BULLET, world);
            super.setOwner(owner);
            Vec3d vec3d = owner.getBoundingBox().getCenter();
            super.refreshPositionAndAngles(vec3d.x, vec3d.y, vec3d.z, this.getYaw(), this.getPitch());
            try {
                Field targetField = ShulkerBulletEntity.class.getDeclaredField("target");
                targetField.setAccessible(true);
                targetField.set(this, target);

                Field directionField = ShulkerBulletEntity.class.getDeclaredField("direction");
                directionField.setAccessible(true);
                directionField.set(this, Direction.UP);

                Method changeTargetDirectionMethod = ShulkerBulletEntity.class.getDeclaredMethod("changeTargetDirection", Direction.Axis.class);
                changeTargetDirectionMethod.setAccessible(true);
                changeTargetDirectionMethod.invoke(this, axis);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onEntityHit(EntityHitResult entityHitResult) {
            super.onEntityHit(entityHitResult);
            Entity entity = entityHitResult.getEntity();
            if (entity instanceof LivingEntity) {
                LivingEntity livingEntity = (LivingEntity) entity;
                livingEntity.removeStatusEffect(StatusEffects.LEVITATION);
                livingEntity.addStatusEffect(new StatusEffectInstance(ModEffects.ANCHORED, 600, 0, false, true, true));
            }
        }
    }
}
