package com.kalightortaio.veterandifficulty.entity.custom;

import org.jetbrains.annotations.Nullable;

import com.kalightortaio.veterandifficulty.entity.ModEntities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class MagicProjectileEntity extends PersistentProjectileEntity {
    public MagicProjectileEntity(EntityType<? extends PersistentProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public MagicProjectileEntity(World world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom) {
      super(ModEntities.MAGIC_PROJECTILE, owner, world, stack, shotFrom);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(Items.BARRIER);
    }

    @Override
    protected double getGravity() {
        return 0.0;
    }

    @Override
    public void tick() {
        super.tick();
        World world = getWorld();
        if (!world.isClient) {
            Vec3d vec3d = this.getVelocity();
            float f = (1.0F/0.99F);
            if (this.isTouchingWater()) {
                f = (1.0F/this.getDragInWater());
            }
            this.setVelocity(vec3d.multiply((double)f));
        }
        if (world.isClient) {
            Vec3d velocity = this.getVelocity().normalize();
            for (int i = 0; i < 8; i++) {
                double offsetX = (velocity.x + (random.nextDouble() - 0.5));
                double offsetY = (velocity.y + (random.nextDouble() - 0.5));
                double offsetZ = (velocity.z + (random.nextDouble() - 0.5));

                world.addParticleClient(ParticleTypes.OMINOUS_SPAWNING, getX(), getY(), getZ(), -0.2 * offsetX, -0.2 * offsetY, -0.2 * offsetZ);
            }
            world.addParticleClient(ParticleTypes.END_ROD, getX(), getY(), getZ(), 0, 0, 0);
            // todo: implement better texture and particles
        }
        if (this.age > 200) {
            this.discard();
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        World world = getWorld();
        if (!world.isClient) {
            ServerWorld serverWorld = (ServerWorld) world;
            if (hitResult instanceof EntityHitResult entityHitResult) {
                LivingEntity hitEntity = (LivingEntity) entityHitResult.getEntity();
                System.err.println(hitEntity);
                hitEntity.damage(serverWorld, this.getDamageSources().magic(), 2.0F);
            }
            if (hitResult instanceof BlockHitResult blockHitResult) {
                BlockPos blockPos = (BlockPos) blockHitResult.getBlockPos();
                System.err.println(blockPos);
            }
            this.discard();
        }
    }
}
