package com.kalightortaio.veterandifficulty.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kalightortaio.veterandifficulty.effect.entity.ScaldedEntity;
import com.kalightortaio.veterandifficulty.mob.Blaze;
import com.kalightortaio.veterandifficulty.mob.CaveSpider;
import com.kalightortaio.veterandifficulty.mob.Drowned;
import com.kalightortaio.veterandifficulty.mob.Enderman;
import com.kalightortaio.veterandifficulty.mob.Evoker;
import com.kalightortaio.veterandifficulty.mob.Ghast;
import com.kalightortaio.veterandifficulty.mob.MagmaCube;
import com.kalightortaio.veterandifficulty.mob.Spider;
import com.kalightortaio.veterandifficulty.mob.Vex;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.CaveSpiderEntity;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.EvokerEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

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
        Evoker.onDamage(world, source, amount, asLivingEntity());
    }

    @Inject(method = "damage", at = @At("TAIL"), cancellable = true)
    private void evokerSlow(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!(source.getAttacker() instanceof EvokerEntity)) return;
        if (!((Object) this instanceof ServerPlayerEntity)) return;
        Evoker.onAttack(asPlayer());
    }

    @Inject(method = "damage", at = @At("TAIL"), cancellable = true)
    private void vexHeal(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Entity vex = source.getAttacker();
        if (!(vex instanceof VexEntity)) return;
        if (!((Object) this instanceof ServerPlayerEntity)) return;
        Vex.onAttack(world, vex);
    }

    @Inject(method = "onDeath", at = @At("TAIL"))
    private void caveSpiderDeathRattle(DamageSource source, CallbackInfo ci) {
        if (!((Object) this instanceof CaveSpiderEntity)) return;
        CaveSpider.onDeath(asLivingEntity());
    }

    @Inject(method = "damage", at = @At("TAIL"), cancellable = true)
    private void scaldingBlazes(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!(source.getAttacker() instanceof BlazeEntity)) return;
        Blaze.onAttack(asLivingEntity());
    }

    @Inject(method = "setHealth", at = @At("HEAD"), cancellable = true)
    private void preventHealthIncrease(float health, CallbackInfo ci) {
        ScaldedEntity.onHeal(asLivingEntity(), health, ci);
    }

    @Inject(method = "damage", at = @At("TAIL"), cancellable = true)
    private void unmountSpider(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!((Object) this instanceof SpiderEntity)) return;
        Spider.onDamage(world, asLivingEntity());
    }

    @Inject(method = "damage", at = @At("TAIL"), cancellable = true)
    private void makeGhastsCry(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!((Object) this instanceof GhastEntity)) return;
        Ghast.onDamage(world, asLivingEntity());
    }

    @Inject(method = "onDeath", at = @At("TAIL"))
    private void spiderHeadCrab(DamageSource source, CallbackInfo ci) {
        if (!((Object) this instanceof SpiderEntity)) return;
        Spider.onDeath(source, asLivingEntity(), ci);
    }

    @Inject(method = "onDeath", at = @At("TAIL"))
    private void magmaCubeDeathRattle(DamageSource source, CallbackInfo ci) {
        if (!((Object) this instanceof MagmaCubeEntity)) return;
        MagmaCube.onDeath(source, asLivingEntity(), ci);
    }

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void preventDrownedTridentDamage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (!((Object) this instanceof DrownedEntity)) return;
        Drowned.onDamage(source, cir);
    }

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void onEndermanAttack(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) { 
        if (source.getSource() instanceof EndermanEntity && Math.random() < 0.2f) {
            Enderman.onAttack(world, asLivingEntity());
        }
    }
}