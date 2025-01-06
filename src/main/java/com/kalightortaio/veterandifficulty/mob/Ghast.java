package com.kalightortaio.veterandifficulty.mob;

import com.kalightortaio.veterandifficulty.interfaces.IEntityState;
import com.kalightortaio.veterandifficulty.systems.internal.EntityModifiers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;

public class Ghast {

    public static void onLoad(MinecraftServer server, Entity entity) {
        if (entity instanceof GhastEntity ghast && !((IEntityState) ghast).getBooleanState(EntityModifiers._KEY)) {
            ghast.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, Integer.MAX_VALUE, 9, false, false, false));
            EntityModifiers.tagEntity(ghast, server);
        }
    }

    public static void onDamage(ServerWorld world, LivingEntity ghast) {
        int tearsToDrop = 1 + (int) (Math.random() * 3);
        
        for (int i = 0; i < tearsToDrop; i++) {
            ItemEntity ghastTear = new ItemEntity(world, ghast.getX(), (ghast.getY() + ghast.getEyeHeight(ghast.getPose())), ghast.getZ(), new ItemStack(Items.GHAST_TEAR));
            world.spawnEntity(ghastTear);
        }
    }

    public static void onAttack(MinecraftServer server, Entity entity) {
        if (entity instanceof FireballEntity fireball && !((IEntityState) fireball).getBooleanState(EntityModifiers._KEY)) {
            server.getCommandManager().executeWithPrefix(
                server.getCommandSource().withSilent(), 
                "data merge entity " + fireball.getUuidAsString() + " {ExplosionPower:3}"
            );
            EntityModifiers.tagEntity(fireball, server);
        }
    }
}