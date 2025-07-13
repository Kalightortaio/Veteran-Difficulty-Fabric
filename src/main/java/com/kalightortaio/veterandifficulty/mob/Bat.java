package com.kalightortaio.veterandifficulty.mob;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kalightortaio.veterandifficulty.interfaces.IEntityState;
import com.kalightortaio.veterandifficulty.systems.internal.EntityModifiers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class Bat {

    private static float biteDistance = 1.9f;

    public static void onLoad(ServerWorld world, MinecraftServer server, Entity entity) {
        if (entity instanceof BatEntity bat && !((IEntityState) bat).getBooleanState(EntityModifiers._KEY)) {
            for (int i = 0; i < (int) (1 + Math.random() * 2); i++) {
                BatEntity extraBat = EntityType.BAT.create(world, null, bat.getBlockPos(), SpawnReason.TRIGGERED, false, false);
                if (extraBat != null) {
                    EntityModifiers.tagEntity(extraBat, server);
                    world.spawnEntity(extraBat);
                }
            }
            EntityModifiers.tagEntity(bat, server);
        }
    }

    public static ActionResult handleGlowBerryInteraction(BatEntity bat, ServerPlayerEntity player, ItemStack itemStack, Hand hand, BiteHandler biteHandler) {
            ServerWorld world = player.getWorld();
            itemStack.decrement(1);
            world.spawnParticles(ParticleTypes.HEART, bat.getX(), bat.getY() + 0.5, bat.getZ(), 5, 0.2, 0.2, 0.2, 0.0);
            
            if (Math.random() < 0.2 && biteHandler.getBiteCooldown(bat) > 20) {
                bat.dropStack(world, new ItemStack(getRandomSeed()));
            }

            biteHandler.setBiteCooldown(bat, 6020);
            return ActionResult.SUCCESS;
    }

    private static Item getRandomSeed() {
        switch ((int) (Math.random() * 5)) {
            case 0: return Items.WHEAT_SEEDS;
            case 1: return Items.PUMPKIN_SEEDS;
            case 2: return Items.MELON_SEEDS;
            case 3: return Items.BEETROOT_SEEDS;
            case 4: return Items.COCOA_BEANS;
            default: return Items.WHEAT_SEEDS;
        }
    }
            
    public static void onTick(ServerWorld world, BatEntity bat, BlockPos hangingPosition, BiteHandler biteHandler, CallbackInfo info) {
        BlockPos batPos = bat.getBlockPos();
        BlockPos batPosUp = batPos.up();

        if (bat.isRoosting()) {
            if (world.getBlockState(batPosUp).isSolidBlock(world, batPos)) {
                if (((int)(Math.random() * 200)) == 0) {
                    bat.headYaw = (float) (Math.random() * 360);
                }
                if (world.getClosestPlayer(TargetPredicate.createNonAttackable().setBaseMaxDistance(16.0), bat) != null) {
                    bat.setRoosting(false);
                    world.syncWorldEvent((PlayerEntity)null, 1025, batPos, 0);
                }
            } else {
                bat.setRoosting(false);
                world.syncWorldEvent((PlayerEntity)null, 1025, batPos, 0);
            }
        } else {
            if (hangingPosition != null && (!world.isAir(hangingPosition) || hangingPosition.getY() <= world.getBottomY())) {
                hangingPosition = null;
            }
            if (hangingPosition == null || (int)(Math.random() * 30) == 0 || hangingPosition.isWithinDistance(bat.getPos(), 2.0)) {
                hangingPosition = BlockPos.ofFloored(
                    bat.getX() + (double)(Math.random() * 7) - (double)(Math.random() * 7),
                    bat.getY() + (double)(Math.random() * 6) - 2.0,
                    bat.getZ() + (double)(Math.random() * 7) - (double)(Math.random() * 7)
                );
            }

            double dx = (double)hangingPosition.getX() + 0.5 - bat.getX();
            double dy = (double)hangingPosition.getY() + 0.5 - bat.getY();
            double dz = (double)hangingPosition.getZ() + 0.5 - bat.getZ();
            Vec3d currentVelocity = bat.getVelocity();
            double smoothX = MathHelper.lerp(0.15, currentVelocity.x, Math.signum(dx) * 0.35);
            double smoothY = MathHelper.lerp(0.15, currentVelocity.y, Math.signum(dy) * 0.55);
            double smoothZ = MathHelper.lerp(0.15, currentVelocity.z, Math.signum(dz) * 0.35);
            Vec3d normalVelocity = new Vec3d(smoothX, smoothY, smoothZ);

            TargetPredicate.EntityPredicate entityPredicate = (LivingEntity entity, ServerWorld world2) -> {
                return entity instanceof PlayerEntity player && !player.isSpectator() && !player.isCreative();
            };

            TargetPredicate validPlayerPredicate = TargetPredicate.createNonAttackable()
                .ignoreVisibility()
                .setBaseMaxDistance(32.0)
                .setPredicate(entityPredicate);

            ServerPlayerEntity closestPlayer = (ServerPlayerEntity) world.getClosestPlayer(validPlayerPredicate, bat.getX(), bat.getY(), bat.getZ());
            if (closestPlayer != null) {
                boolean isHoldingGlowBerries = closestPlayer.getMainHandStack().isOf(Items.GLOW_BERRIES) || closestPlayer.getOffHandStack().isOf(Items.GLOW_BERRIES);
                
                if (biteHandler.getBiteCooldown(bat) <= 20) {
                    double playerDx = closestPlayer.getX() - bat.getX() - 0.3;
                    if (Math.random() < 0.5) playerDx += 0.6;
                    double playerDy = closestPlayer.getY() + closestPlayer.getStandingEyeHeight() + 0.3 - bat.getY();
                    if (Math.random() < 0.5) playerDy += 0.2;
                    double playerDz = closestPlayer.getZ() - bat.getZ() - 0.3;
                    if (Math.random() < 0.5) playerDz += 0.6;
                    Vec3d playerDirection = new Vec3d(playerDx, playerDy, playerDz).normalize();

                    if (Math.random() < 0.5) {
                        Vec3d playerVelocity = playerDirection.multiply(0.25F);
                        bat.setVelocity(playerVelocity);
                    } else {
                        bat.setVelocity(normalVelocity);
                    }

                    if (!isHoldingGlowBerries && bat.squaredDistanceTo(closestPlayer) <= Math.pow(biteDistance, 2)) {
                        biteHandler.tryBite(closestPlayer, bat);
                    }
                }
            } else {
                bat.setVelocity(normalVelocity);
            }
            float g = (float)(MathHelper.atan2(normalVelocity.z, normalVelocity.x) * 57.2957763671875) - 90.0F;
            float maxYawChange = 20.0F;
            float yawDifference = MathHelper.wrapDegrees(g - bat.getYaw());
            float clampedYawChange = MathHelper.clamp(yawDifference, -maxYawChange, maxYawChange);
            bat.forwardSpeed = 0.5F;
            bat.setYaw(bat.getYaw() + clampedYawChange);
            
            if (((int)(Math.random() * 7)) == 0 && world.getBlockState(batPosUp).isSolidBlock(world, batPosUp)) {
                bat.setRoosting(true);
            }
            info.cancel();
        }
        biteHandler.decrementCooldown(world, bat);
    }

    public static class BiteHandler {
        private static final String BITE_COOLDOWN_KEY = "BiteCooldown";

        public BiteHandler(BatEntity bat) {
            this.setBiteCooldown(bat, 20);
        }

        public void tryBite(ServerPlayerEntity player, BatEntity bat) {
            IEntityState batState = (IEntityState) bat;
            int biteCooldown = batState.getIntState(BITE_COOLDOWN_KEY);
            if (biteCooldown <= 0) {
                biteCooldown = 20;
                player.damage(player.getWorld(), player.getDamageSources().mobAttack(bat), 1.0f);
                if (Math.random() < 0.33) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 80, 0, false, true, true));
                }
            }
            batState.setIntState(BITE_COOLDOWN_KEY, biteCooldown);
        }

        public void decrementCooldown(ServerWorld world, BatEntity bat) {
            IEntityState batState = (IEntityState) bat;
            int biteCooldown = batState.getIntState(BITE_COOLDOWN_KEY);
            if (biteCooldown > 0) {
                biteCooldown--;
            }
            if (biteCooldown > 20 && biteCooldown % 20 == 0 && Math.random() < 0.4) {
                world.spawnParticles(ParticleTypes.HEART, bat.getX(), bat.getY() + 0.5, bat.getZ(), 2, 0.2, 0.2, 0.2, 0.0);
            }
            batState.setIntState(BITE_COOLDOWN_KEY, biteCooldown);
        }

        public int getBiteCooldown(BatEntity bat) {
            return ((IEntityState) bat).getIntState(BITE_COOLDOWN_KEY);
        }

        public void setBiteCooldown(BatEntity bat, int cooldown) {
            ((IEntityState) bat).setIntState(BITE_COOLDOWN_KEY, cooldown);
        }
    }
}