package com.kalightortaio.veterandifficulty.mob;

import com.kalightortaio.veterandifficulty.interfaces.IEntityState;
import com.kalightortaio.veterandifficulty.systems.mechanics.EntityModifiers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.EvokerEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.event.GameEvent.Emitter;

public class Evoker {

    public static void onLoad(ServerWorld world, MinecraftServer server, Entity entity) {
        if (entity instanceof EvokerEntity evoker && !((IEntityState) evoker).getBooleanState(EntityModifiers._KEY)) {
            evoker.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(100.0f);
            evoker.setHealth(100.0f);
            EntityModifiers.tagEntity(evoker, server);
        }
    }
    
    public static void onAttack(ServerPlayerEntity player) {
        player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 0, false, true, true));
    }

    public static void onDamage(ServerWorld world, DamageSource source, float amount, LivingEntity evoker) {
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
}