package com.kalightortaio.veterandifficulty.systems.mechanics;

import com.kalightortaio.veterandifficulty.interfaces.IEntityState;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;

public class EntityModifiers {
    private static final String VD_PROCESSED_KEY = "vd_processed";

    public static void registerHook() {
        ServerEntityEvents.ENTITY_LOAD.register((Entity entity, ServerWorld world) -> {
            if (entity instanceof DrownedEntity drowned && !((IEntityState) drowned).getBooleanState(VD_PROCESSED_KEY)) {
                if (Math.random() < 0.1 && !drowned.isBaby()) {
                    drowned.equipStack(EquipmentSlot.MAINHAND, Items.TRIDENT.getDefaultStack());
                } else if (drowned.getEquippedStack(EquipmentSlot.MAINHAND).getItem() == Items.TRIDENT) {
                    drowned.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
                }
                tagEntity(drowned, world.getServer());
            }

            if (entity instanceof GhastEntity ghast && !((IEntityState) ghast).getBooleanState(VD_PROCESSED_KEY)) {
                ghast.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, Integer.MAX_VALUE, 9, false, false, false));
                tagEntity(ghast, world.getServer());
            }

            if (entity instanceof FireballEntity fireball && !((IEntityState) fireball).getBooleanState(VD_PROCESSED_KEY)) {
                MinecraftServer server = world.getServer();
                server.getCommandManager().executeWithPrefix(
                    server.getCommandSource().withSilent(), 
                    "data merge entity " + fireball.getUuidAsString() + " {ExplosionPower:3}"
                );
                tagEntity(fireball, world.getServer());
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