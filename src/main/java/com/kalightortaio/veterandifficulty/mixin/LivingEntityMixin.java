package com.kalightortaio.veterandifficulty.mixin;

import java.util.HashMap;
import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kalightortaio.veterandifficulty.interfaces.IEntityState;
import com.kalightortaio.veterandifficulty.systems.internal.TickManager;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.nbt.NbtCompound;
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
public abstract class LivingEntityMixin implements IEntityState {
    
    @Unique
    private final Map<String, Boolean> booleanStates = new HashMap<>();
    @Unique
    private final Map<String, Integer> intStates = new HashMap<>();

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        saveStatesToNbt(nbt);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        loadStatesFromNbt(nbt);
    }

    private void saveStatesToNbt(NbtCompound nbt) {
        for (Map.Entry<String, Boolean> entry : booleanStates.entrySet()) {
            nbt.putBoolean(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, Integer> entry : intStates.entrySet()) {
            nbt.putInt(entry.getKey(), entry.getValue());
        }
    }

    private void loadStatesFromNbt(NbtCompound nbt) {
        for (String key : booleanStates.keySet()) {
            if (nbt.contains(key)) {
                booleanStates.put(key, nbt.getBoolean(key));
            }
        }
        for (String key : intStates.keySet()) {
            if (nbt.contains(key)) {
                intStates.put(key, nbt.getInt(key));
            }
        }
    }

    @Override
    public boolean getBooleanState(String stateName) {
        return booleanStates.getOrDefault(stateName, false);
    }

    @Override
    public void setBooleanState(String stateName, boolean value) {
        booleanStates.put(stateName, value);
    }

    @Override
    public int getIntState(String stateName) {
        return intStates.getOrDefault(stateName, 0);
    }

    @Override
    public void setIntState(String stateName, int value) {
        intStates.put(stateName, value);
    }

    @Inject(method = "onDeath", at = @At("TAIL"))
    private void magmaCubeDeathRattle(DamageSource source, CallbackInfo ci) {
        if (!((Object) this instanceof MagmaCubeEntity)) return;
        LivingEntity hurtEntity = (LivingEntity) (Object) this;
        if (!hurtEntity.getWorld().isClient()) {
            ServerWorld world = (ServerWorld) hurtEntity.getWorld();
            BlockPos blockPos = hurtEntity.getBlockPos();
            world.setBlockState(blockPos, Blocks.LAVA.getDefaultState());
            TickManager.scheduleLavaRemoval(world, blockPos, 100);
        }
    }

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void preventDrownedTridentDamage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if ((Object) this instanceof DrownedEntity) {
            if (source.getSource() instanceof TridentEntity trident) {
                if (trident.getOwner() instanceof DrownedEntity) {
                    cir.setReturnValue(false);
                }
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