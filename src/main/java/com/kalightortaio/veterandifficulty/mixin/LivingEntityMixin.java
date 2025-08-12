package com.kalightortaio.veterandifficulty.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kalightortaio.veterandifficulty.effect.ModEffects;
import com.kalightortaio.veterandifficulty.effect.entity.AnchoredEntity;
import com.kalightortaio.veterandifficulty.effect.entity.ScaldedEntity;
import com.kalightortaio.veterandifficulty.mob.Blaze;
import com.kalightortaio.veterandifficulty.mob.CaveSpider;
import com.kalightortaio.veterandifficulty.mob.Drowned;
import com.kalightortaio.veterandifficulty.mob.ElderGuardian;
import com.kalightortaio.veterandifficulty.mob.Enderman;
import com.kalightortaio.veterandifficulty.mob.Evoker;
import com.kalightortaio.veterandifficulty.mob.Ghast;
import com.kalightortaio.veterandifficulty.mob.Husk;
import com.kalightortaio.veterandifficulty.mob.MagmaCube;
import com.kalightortaio.veterandifficulty.mob.Spider;
import com.kalightortaio.veterandifficulty.mob.Vex;
import com.kalightortaio.veterandifficulty.mob.Vindicator;
import com.kalightortaio.veterandifficulty.systems.internal.ModTags;
import com.kalightortaio.veterandifficulty.util.Tools;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.CaveSpiderEntity;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.entity.mob.ElderGuardianEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.EvokerEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.mob.GuardianEntity;
import net.minecraft.entity.mob.HuskEntity;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.entity.mob.VindicatorEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    private LivingEntity asLivingEntity() {
        return (LivingEntity) (Object) this;
    }

    private ServerPlayerEntity asPlayer() {
        return (ServerPlayerEntity) (Object) this;
    }

    // On Damage Triggers
    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void reduceFistDamage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source.getAttacker() instanceof ServerPlayerEntity player && !(amount == 0.0f)) {
            ItemStack heldItem = player.getMainHandStack();
            if (!Tools.Util.isValidWeaponOrTool(heldItem)) {
                asLivingEntity().damage(world, source, 0.0f);
                cir.setReturnValue(false);
            }
        }
    }
    
    @Inject(method = "damage", at = @At("TAIL"), cancellable = true)
    private void vindicatorChopChop(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!(source.getAttacker() instanceof VindicatorEntity && asLivingEntity() instanceof IronGolemEntity)) return;
        Vindicator.onAttackGolem(world, asLivingEntity());
    }

    @Inject(method = "damage", at = @At("TAIL"), cancellable = true)
    private void guardianEyeBeam(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!(source.getAttacker() instanceof ElderGuardianEntity || source.getAttacker() instanceof GuardianEntity)) return;
        ElderGuardian.onAttack(asLivingEntity(), source);
    }

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void elderRevenge(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!((asLivingEntity() instanceof GuardianEntity || asLivingEntity() instanceof ElderGuardianEntity) && source.getAttacker() instanceof LivingEntity)) return;
        ElderGuardian.onDamage(asLivingEntity(), source, amount, cir);
    }

    @Inject(method = "damage", at = @At("TAIL"), cancellable = true)
    private void dryHusks(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!(source.getAttacker() instanceof HuskEntity)) return;
        Husk.onAttack(asLivingEntity());
    }

    @Inject(method = "damage", at = @At("TAIL"), cancellable = true)
    private void evokerSummon(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!(asLivingEntity() instanceof EvokerEntity)) return;
        Evoker.onDamage(world, source, amount, asLivingEntity());
    }

    @Inject(method = "damage", at = @At("TAIL"), cancellable = true)
    private void evokerSlow(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!(source.getAttacker() instanceof EvokerEntity)) return;
        if (!(asLivingEntity() instanceof ServerPlayerEntity)) return;
        Evoker.onAttack(asPlayer());
    }

    @Inject(method = "damage", at = @At("TAIL"), cancellable = true)
    private void vexHeal(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Entity vex = source.getAttacker();
        if (!(vex instanceof VexEntity)) return;
        if (!(asLivingEntity() instanceof ServerPlayerEntity)) return;
        Vex.onAttack(world, vex);
    }

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void preventDrownedTridentDamage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!(asLivingEntity() instanceof DrownedEntity)) return;
        Drowned.onDamage(source, cir);
    }

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void onEndermanAttack(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) { 
        if (!(source.getSource() instanceof EndermanEntity && Math.random() < 0.2f)) return;
        Enderman.onAttack(world, asLivingEntity());
    }

    @Inject(method = "damage", at = @At("TAIL"), cancellable = true)
    private void scaldingBlazes(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!(source.getAttacker() instanceof BlazeEntity)) return;
        Blaze.onAttack(asLivingEntity());
    }

    @Inject(method = "damage", at = @At("TAIL"), cancellable = true)
    private void unmountSpider(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!(asLivingEntity() instanceof SpiderEntity)) return;
        Spider.onDamage(world, asLivingEntity());
    }

    @Inject(method = "damage", at = @At("TAIL"), cancellable = true)
    private void makeGhastsCry(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!(asLivingEntity() instanceof GhastEntity)) return;
        Ghast.onDamage(world, asLivingEntity());
    }

    // On Death Triggers
    @Inject(method = "onDeath", at = @At("TAIL"))
    private void caveSpiderDeathRattle(DamageSource source, CallbackInfo ci) {
        if (!(asLivingEntity() instanceof CaveSpiderEntity)) return;
        CaveSpider.onDeath(asLivingEntity());
    }

    @Inject(method = "onDeath", at = @At("TAIL"))
    private void spiderHeadCrab(DamageSource source, CallbackInfo ci) {
        if (!(asLivingEntity() instanceof SpiderEntity)) return;
        Spider.onDeath(source, asLivingEntity(), ci);
    }

    @Inject(method = "onDeath", at = @At("TAIL"))
    private void magmaCubeDeathRattle(DamageSource source, CallbackInfo ci) {
        if (!(asLivingEntity() instanceof MagmaCubeEntity)) return;
        MagmaCube.onDeath(source, asLivingEntity(), ci);
    }

    // Status Effect Triggers
    @Inject(method = "setHealth", at = @At("HEAD"), cancellable = true)
    private void preventHealthIncrease(float health, CallbackInfo ci) {
        if (!(asLivingEntity().hasStatusEffect(ModEffects.SCALDING))) return;
        ScaldedEntity.onHeal(asLivingEntity(), health, ci);
    }

    @Inject(method = "travel", at = @At("HEAD"), cancellable = true)
    private void preventSwimming(Vec3d movementInput , CallbackInfo ci) {
        if (!(asLivingEntity().hasStatusEffect(ModEffects.ANCHORED))) return;
        AnchoredEntity.onSwim(asLivingEntity(), ci);
    }

    @Inject(method = "applyFluidMovingSpeed", at = @At("RETURN"), cancellable = true)
    private void sinkLikeRock(double gravity, boolean falling, Vec3d motion, CallbackInfoReturnable<Vec3d> cir) {
        if (!(asLivingEntity().hasStatusEffect(ModEffects.ANCHORED))) return;
        AnchoredEntity.onMoveInFluid(asLivingEntity(), motion, cir);
    }

    // Other Triggers
    @Inject(method = "tick", at = @At("TAIL"), cancellable = true) 
    private void naturalRegeneration(CallbackInfo ci) {
        if (!(asLivingEntity().getType().isIn(ModTags.ALIVE))) return;
        if (!(asLivingEntity().getWorld().getTimeOfDay() % 80 == 0)) return;
        if ((asLivingEntity().hasStatusEffect(StatusEffects.REGENERATION))) return;
        asLivingEntity().addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 20, 2, false, false, false));
    }
}