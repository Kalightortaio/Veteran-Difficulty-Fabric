package com.kalightortaio.veterandifficulty.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kalightortaio.veterandifficulty.effect.ModEffects;
import com.kalightortaio.veterandifficulty.interfaces.IEntityState;
import com.kalightortaio.veterandifficulty.mob.MagmaCube;

import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.event.GameEvent.Emitter;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "removeStatusEffect", at = @At("HEAD"))
    private void onRemoveStatusEffect(StatusEffect effect, CallbackInfoReturnable<Boolean> cir) {
        if (!(effect == ModEffects.SCALDING_EFFECT)) return;
        LivingEntity entity = (LivingEntity) (Object) this;
        ((IEntityState) entity).setFloatState("previousHealth", 0);
    }

    @Inject(method = "damage", at = @At("TAIL"), cancellable = true)
    private void makeGhastsCry(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!((Object) this instanceof GhastEntity)) return;
        LivingEntity hurEntity = (LivingEntity) (Object) this;
        int tearsToDrop = 1 + (int) (Math.random() * 3);
        
        for (int i = 0; i < tearsToDrop; i++) {
            ItemEntity ghastTear = new ItemEntity(world, hurEntity.getX(), (hurEntity.getY() + hurEntity.getEyeHeight(hurEntity.getPose())), hurEntity.getZ(), new ItemStack(Items.GHAST_TEAR));
            world.spawnEntity(ghastTear);
        }
    }

    @Inject(method = "onDeath", at = @At("TAIL"))
    private void magmaCubeDeathRattle(DamageSource source, CallbackInfo ci) {
        if (!((Object) this instanceof MagmaCubeEntity)) return;
        LivingEntity hurtEntity = (LivingEntity) (Object) this;
        if (!hurtEntity.getWorld().isClient()) {
            ServerWorld world = (ServerWorld) hurtEntity.getWorld();
            BlockPos blockPos = hurtEntity.getBlockPos();
            world.setBlockState(blockPos, Blocks.LAVA.getDefaultState());
            MagmaCube.scheduleLavaRemoval(world, blockPos, 100);
        }
    }

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void preventDrownedTridentDamage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!((Object) this instanceof DrownedEntity)) return;
        if (source.getSource() instanceof TridentEntity trident) {
            if (trident.getOwner() instanceof DrownedEntity) {
                cir.setReturnValue(false);
            }
        }
    }

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void onEndermanAttack(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) { 
        if (source.getSource() instanceof EndermanEntity && Math.random() < 0.2f) {
            LivingEntity hurtEntity = (LivingEntity) (Object) this;

            int currentTime = (int) world.getTime();
            int lastTeleportTime = ((IEntityState) hurtEntity).getIntState("lastTeleportTime");
            
            if (currentTime - lastTeleportTime < 200) {
                return;
            }
            
            double teleportDiameter = 32.0;
            for (int i = 0; i < 16; ++i) { 
                double x = hurtEntity.getX() + (hurtEntity.getRandom().nextDouble() - 0.5) * teleportDiameter;
                double y = MathHelper.clamp(hurtEntity.getY() + (hurtEntity.getRandom().nextDouble() - 0.5) * teleportDiameter, (double)world.getBottomY(), (double)(world.getBottomY() + ((ServerWorld)world).getLogicalHeight() - 1));
                double z = hurtEntity.getZ() + (hurtEntity.getRandom().nextDouble() - 0.5) * teleportDiameter;

                if (hurtEntity.hasVehicle()) {
                    hurtEntity.stopRiding(); 
                }

                Vec3d vec3d = hurtEntity.getPos();
                if (hurtEntity.teleport(x, y, z, true)) {
                    world.emitGameEvent(GameEvent.TELEPORT, vec3d, Emitter.of(hurtEntity));

                    SoundCategory soundCategory;
                    SoundEvent soundEvent;
                    soundEvent = SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                    soundCategory = SoundCategory.PLAYERS;
                    world.playSound(null, hurtEntity.getX(), hurtEntity.getY(), hurtEntity.getZ(), soundEvent, soundCategory);

                    hurtEntity.onLanding();
                    ((IEntityState) hurtEntity).setIntState("lastTeleportTime", (currentTime));
                    break;
                }
            }
        }
    }
}