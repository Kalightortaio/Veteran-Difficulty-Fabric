package com.kalightortaio.veterandifficulty.systems.mechanics;

import java.util.UUID;

import com.kalightortaio.veterandifficulty.interfaces.IEntityState;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityModifiers {
    private static final String VD_PROCESSED_KEY = "vd_processed";

    public static void registerHook() {
        ServerEntityEvents.ENTITY_LOAD.register((Entity entity, ServerWorld world) -> {
            MinecraftServer server = world.getServer();

            if (entity instanceof WolfEntity wolf && world.getRegistryKey() == World.OVERWORLD) {
                if (!((IEntityState) wolf).getBooleanState(VD_PROCESSED_KEY)) {
                    wolf.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(40.0f);
                    wolf.setHealth(40.0f);
                    tagEntity(wolf, server);
                } else {
                    // Bugfix for loading into world with angry wolves
                    if (wolf.getAngerTime() > 0) {
                        wolf.setAngryAt((UUID) null);
                        wolf.setAttacker((LivingEntity) null);
                        wolf.setTarget((LivingEntity) null);
                        wolf.setAngerTime(0);
                    }
                }
            }

            if (entity instanceof SlimeEntity slime && !((IEntityState) slime).getBooleanState(VD_PROCESSED_KEY)) {
                slime.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, Integer.MAX_VALUE, 1, false, false, false));
                slime.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, Integer.MAX_VALUE, 5, false, false, false));
                tagEntity(slime, server);
            }

            if (entity instanceof DrownedEntity drowned && !((IEntityState) drowned).getBooleanState(VD_PROCESSED_KEY)) {
                if (Math.random() < 0.1 && !drowned.isBaby()) {
                    drowned.equipStack(EquipmentSlot.MAINHAND, Items.TRIDENT.getDefaultStack());
                } else if (drowned.getEquippedStack(EquipmentSlot.MAINHAND).getItem() == Items.TRIDENT) {
                    drowned.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                }
                tagEntity(drowned, server);
            }

            if (entity instanceof GhastEntity ghast && !((IEntityState) ghast).getBooleanState(VD_PROCESSED_KEY)) {
                ghast.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, Integer.MAX_VALUE, 9, false, false, false));
                tagEntity(ghast, server);
            }

            if (entity instanceof FireballEntity fireball && !((IEntityState) fireball).getBooleanState(VD_PROCESSED_KEY)) {
                server.getCommandManager().executeWithPrefix(
                    server.getCommandSource().withSilent(), 
                    "data merge entity " + fireball.getUuidAsString() + " {ExplosionPower:3}"
                );
                tagEntity(fireball, server);
            }

            if (entity instanceof EndermanEntity enderman && world.getRegistryKey() == World.END && !((IEntityState) enderman).getBooleanState(VD_PROCESSED_KEY)) {
                if (Math.random() < 0.15) {
                    BlockPos spawnPos = enderman.getBlockPos().up(32);
                    PhantomEntity phantom = new PhantomEntity(EntityType.PHANTOM, world);
                    phantom.refreshPositionAndAngles(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), 0.0F, 0.0F);
                    world.spawnEntity(phantom);
                    ((IEntityState) phantom).setIntState("Age", 0);
                }
                tagEntity(enderman, server);
            }
        });
    }

    private static void tagEntity(Entity entity, MinecraftServer server) {
        ((IEntityState) entity).setBooleanState(VD_PROCESSED_KEY, true);
        server.getCommandManager().executeWithPrefix(
            server.getCommandSource().withSilent(),
            "tag " + entity.getUuidAsString() + " add " + VD_PROCESSED_KEY
        );
    }
}