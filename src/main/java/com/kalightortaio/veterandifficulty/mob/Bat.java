package com.kalightortaio.veterandifficulty.mob;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kalightortaio.veterandifficulty.interfaces.IEntityState;

import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class Bat {

    private static float biteDistance = 1.9f;

    public static ActionResult handleGlowBerryInteraction(BatEntity bat, ServerPlayerEntity player, ItemStack itemStack, Hand hand, BiteHandler biteHandler) {
            ServerWorld world = player.getServerWorld();
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
                if (world.getClosestPlayer(TargetPredicate.createNonAttackable().setBaseMaxDistance(4.0), bat) != null) {
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
            double smoothX = MathHelper.lerp(0.1, currentVelocity.x, Math.signum(dx) * 0.3);
            double smoothY = MathHelper.lerp(0.1, currentVelocity.y, Math.signum(dy) * 0.5);
            double smoothZ = MathHelper.lerp(0.1, currentVelocity.z, Math.signum(dz) * 0.3);

            ServerPlayerEntity closestPlayer = (ServerPlayerEntity) world.getClosestPlayer(bat, 32.0);
            if (closestPlayer != null) {
                boolean isHoldingGlowBerries = closestPlayer.getMainHandStack().isOf(Items.GLOW_BERRIES) || closestPlayer.getOffHandStack().isOf(Items.GLOW_BERRIES);
                
                if (biteHandler.getBiteCooldown(bat) <= 20) {
                    double playerWeight = isHoldingGlowBerries ? 0.075 : 0.15;
                    double playerDx = closestPlayer.getX() - bat.getX();
                    double playerDy = closestPlayer.getY() + closestPlayer.getStandingEyeHeight() + 0.4 - bat.getY();
                    double playerDz = closestPlayer.getZ() - bat.getZ();
                    Vec3d playerDirection = new Vec3d(playerDx, playerDy, playerDz).normalize();
                    smoothX = MathHelper.lerp(playerWeight, smoothX, playerDirection.x * 0.3);
                    smoothY = MathHelper.lerp(playerWeight, smoothY, playerDirection.y * 0.5);
                    smoothZ = MathHelper.lerp(playerWeight, smoothZ, playerDirection.z * 0.3);

                    if (!isHoldingGlowBerries && bat.squaredDistanceTo(closestPlayer) <= Math.pow(biteDistance, 2)) {
                        biteHandler.tryBite(closestPlayer, bat);
                    }
                }
            }
            Vec3d normalVelocity = new Vec3d(smoothX, smoothY, smoothZ);
            bat.setVelocity(normalVelocity);
            float g = (float)(MathHelper.atan2(normalVelocity.z, normalVelocity.x) * 57.2957763671875) - 90.0F;
            float maxYawChange = 10.0F;
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
                player.damage(player.getServerWorld(), player.getDamageSources().mobAttack(bat), 3.0f);
                if (Math.random() < 0.33) {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 100, 0, false, true, true));
                }
            }
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
        }

        public int getBiteCooldown(BatEntity bat) {
            return ((IEntityState) bat).getIntState(BITE_COOLDOWN_KEY);
        }

        public void setBiteCooldown(BatEntity bat, int cooldown) {
            ((IEntityState) bat).setIntState(BITE_COOLDOWN_KEY, cooldown);
        }
    }
}