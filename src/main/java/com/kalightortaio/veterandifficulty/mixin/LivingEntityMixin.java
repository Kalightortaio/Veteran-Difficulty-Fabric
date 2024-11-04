package com.kalightortaio.veterandifficulty.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kalightortaio.veterandifficulty.effect.ModEffects;
import com.kalightortaio.veterandifficulty.interfaces.IEntityState;
import com.kalightortaio.veterandifficulty.mob.MagmaCube;
import com.kalightortaio.veterandifficulty.mob.Vex;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.CaveSpiderEntity;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.EvokerEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
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
    

    private LivingEntity asLivingEntity() {
        return (LivingEntity) (Object) this;
    }

    private ServerPlayerEntity asPlayer() {
        return (ServerPlayerEntity) (Object) this;
    }

    @Inject(method = "damage", at = @At("TAIL"), cancellable = true)
    private void evokerSummon(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!((Object) this instanceof EvokerEntity)) return;
        LivingEntity evoker = asLivingEntity();
        if (amount >= 8.0f && amount < 16.0f && Math.random() < 0.5) {
            VexEntity vex = new VexEntity(EntityType.VEX, world);
            vex.refreshPositionAndAngles(evoker.getX(), evoker.getY(), evoker.getZ(), 0.0F, 0.0F);
            world.spawnEntity(vex);
        } else if (amount >= 16.0f) {
            evoker.heal(4.0f);
            VexEntity vex = new VexEntity(EntityType.VEX, world);
            vex.refreshPositionAndAngles(evoker.getX(), evoker.getY(), evoker.getZ(), 0.0F, 0.0F);
            world.spawnEntity(vex);
        }
        if (!(source.getAttacker() instanceof LivingEntity)) return;
        LivingEntity attacker = (LivingEntity) source.getAttacker();
        if (evoker.squaredDistanceTo(attacker) >= 100) {
            evoker.heal(8.0f);
            if (attacker.hasVehicle()) {
                attacker.stopRiding(); 
            }
            Vec3d attackerPos = attacker.getPos();
            if (attacker.teleport(evoker.getX(), evoker.getY(), evoker.getZ(), true)) {
                world.emitGameEvent(GameEvent.TELEPORT, attackerPos, Emitter.of(attacker));

                SoundCategory soundCategory;
                SoundEvent soundEvent;
                soundEvent = SoundEvents.ENTITY_EVOKER_CAST_SPELL;
                soundCategory = SoundCategory.PLAYERS;
                world.playSound(null, attacker.getX(), attacker.getY(), attacker.getZ(), soundEvent, soundCategory);

                attacker.onLanding();
            }
        }
    }

    @Inject(method = "damage", at = @At("TAIL"), cancellable = true)
    private void evokerSlow(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!(source.getAttacker() instanceof EvokerEntity)) return;
        if (!((Object) this instanceof ServerPlayerEntity)) return;
        ServerPlayerEntity player = asPlayer();
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 0, false, true, true));
    }

    @Inject(method = "damage", at = @At("TAIL"), cancellable = true)
    private void vexHeal(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!((Object) this instanceof ServerPlayerEntity)) return;
        Entity vex = source.getAttacker();
        if (!(vex instanceof VexEntity)) return;
        EvokerEntity evoker = (EvokerEntity) ((VexEntity) vex).getOwner();
        if (evoker == null || evoker.isRemoved() || evoker.isDead()) return;
        Vex.addAnimation(world, vex.getPos(), evoker.getPos(), 20);
        evoker.heal(10.0f);
    }

    @Inject(method = "onDeath", at = @At("TAIL"))
    private void caveSpiderDeathRattle(DamageSource source, CallbackInfo ci) {
        if (!((Object) this instanceof CaveSpiderEntity)) return;
        LivingEntity caveSpider = asLivingEntity();
        if (caveSpider.getWorld().isClient()) return;
        ServerWorld world = (ServerWorld) caveSpider.getWorld();
        BlockPos blockPos = caveSpider.getBlockPos();
        while (true) {
            BlockState blockState = world.getBlockState(blockPos);
            BlockState blockBelow = world.getBlockState(blockPos.down());
            if ((blockState.isOf(Blocks.AIR) || blockState.isOf(Blocks.CAVE_AIR)) && blockBelow.isOf(Blocks.AIR)) {
                boolean hasAdjacentNonAir = 
                    !world.getBlockState(blockPos.north()).isAir() ||
                    !world.getBlockState(blockPos.south()).isAir() ||
                    !world.getBlockState(blockPos.east()).isAir() ||
                    !world.getBlockState(blockPos.west()).isAir() ||
                    !world.getBlockState(blockPos.up()).isAir();
                if (hasAdjacentNonAir) {
                    world.setBlockState(blockPos, Blocks.COBWEB.getDefaultState());
                    break;
                }
                blockPos = blockPos.down();
            } else {
                world.setBlockState(blockPos, Blocks.COBWEB.getDefaultState());
                break;
            }
        }
        if (caveSpider.getAttributeInstance(EntityAttributes.SCALE).getBaseValue() == 0.5f) return;
        for (int i = 0; i < (2 + Math.random() * 4); i++) {
            CaveSpiderEntity childSpider = EntityType.CAVE_SPIDER.create(world, null, blockPos, SpawnReason.TRIGGERED, false, false);
            if (childSpider != null) {
                childSpider.getAttributeInstance(EntityAttributes.SCALE).setBaseValue(0.5f);
                world.spawnEntity(childSpider);
            }
        }
    }

    @Inject(method = "damage", at = @At("TAIL"), cancellable = true)
    private void scaldingBlazes(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!(source.getAttacker() instanceof BlazeEntity)) return;
        LivingEntity hurtEntity = asLivingEntity();
        hurtEntity.addStatusEffect(new StatusEffectInstance(ModEffects.SCALDING, 300, 0, false, true, true));
    }

    @Inject(method = "setHealth", at = @At("HEAD"), cancellable = true)
    private void preventHealthIncrease(float health, CallbackInfo ci) {
        LivingEntity scalded = asLivingEntity();
        if (ModEffects.SCALDING != null && scalded.hasStatusEffect(ModEffects.SCALDING)) {
            if (health > scalded.getHealth()) {
                ci.cancel();
            }
        }
    }

    @Inject(method = "damage", at = @At("TAIL"), cancellable = true)
    private void makeGhastsCry(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!((Object) this instanceof GhastEntity)) return;
        LivingEntity ghast = asLivingEntity();
        int tearsToDrop = 1 + (int) (Math.random() * 3);
        
        for (int i = 0; i < tearsToDrop; i++) {
            ItemEntity ghastTear = new ItemEntity(world, ghast.getX(), (ghast.getY() + ghast.getEyeHeight(ghast.getPose())), ghast.getZ(), new ItemStack(Items.GHAST_TEAR));
            world.spawnEntity(ghastTear);
        }
    }

    @Inject(method = "onDeath", at = @At("TAIL"))
    private void magmaCubeDeathRattle(DamageSource source, CallbackInfo ci) {
        if (!((Object) this instanceof MagmaCubeEntity)) return;
        LivingEntity magmaCube = asLivingEntity();
        if (magmaCube.getWorld().isClient()) return;
        ServerWorld world = (ServerWorld) magmaCube.getWorld();
        BlockPos blockPos = magmaCube.getBlockPos();
        world.setBlockState(blockPos, Blocks.LAVA.getDefaultState());
        MagmaCube.scheduleLavaRemoval(world, blockPos, 100);
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
            LivingEntity teleTarget = asLivingEntity();

            int currentTime = (int) world.getTime();
            int lastTeleportTime = ((IEntityState) teleTarget).getIntState("lastTeleportTime");
            
            if (currentTime - lastTeleportTime < 200) {
                return;
            }
            
            double teleportDiameter = 32.0;
            for (int i = 0; i < 16; ++i) { 
                double x = teleTarget.getX() + (teleTarget.getRandom().nextDouble() - 0.5) * teleportDiameter;
                double y = MathHelper.clamp(teleTarget.getY() + (teleTarget.getRandom().nextDouble() - 0.5) * teleportDiameter, (double)world.getBottomY(), (double)(world.getBottomY() + ((ServerWorld)world).getLogicalHeight() - 1));
                double z = teleTarget.getZ() + (teleTarget.getRandom().nextDouble() - 0.5) * teleportDiameter;

                if (teleTarget.hasVehicle()) {
                    teleTarget.stopRiding(); 
                }

                Vec3d teleTargetPos = teleTarget.getPos();
                if (teleTarget.teleport(x, y, z, true)) {
                    world.emitGameEvent(GameEvent.TELEPORT, teleTargetPos, Emitter.of(teleTarget));

                    SoundCategory soundCategory;
                    SoundEvent soundEvent;
                    soundEvent = SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                    soundCategory = SoundCategory.PLAYERS;
                    world.playSound(null, teleTarget.getX(), teleTarget.getY(), teleTarget.getZ(), soundEvent, soundCategory);

                    teleTarget.onLanding();
                    ((IEntityState) teleTarget).setIntState("lastTeleportTime", (currentTime));
                    break;
                }
            }
        }
    }
}