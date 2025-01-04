package com.kalightortaio.veterandifficulty.mob;

import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kalightortaio.veterandifficulty.interfaces.IEntityState;
import com.kalightortaio.veterandifficulty.systems.internal.EntityModifiers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class Drowned {

    public static void onLoad(MinecraftServer server, Entity entity) {
        if (entity instanceof DrownedEntity drowned && drowned instanceof IEntityState drownedStates && !drownedStates.getBooleanState(EntityModifiers._KEY)) {
            if (Math.random() < 0.1 && !drowned.isBaby()) {
                drowned.equipStack(EquipmentSlot.MAINHAND, Items.TRIDENT.getDefaultStack());
            } else if (drowned.getEquippedStack(EquipmentSlot.MAINHAND).getItem() == Items.TRIDENT) {
                drowned.equipStack(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
            }
            EntityModifiers.tagEntity(drowned, server);
        }
    }

    public static void dynamicSwimmingSpeed(ServerWorld world) {
        final String IN_WATER_KEY = "lastInWater";

        for (DrownedEntity drowned : world.getEntitiesByType(EntityType.DROWNED, drowned -> true)) {
            boolean isInWater = world.getFluidState(BlockPos.ofFloored(drowned.getPos())).isIn(FluidTags.WATER);
            
            if (drowned instanceof IEntityState drownedStates && drownedStates.getBooleanState(IN_WATER_KEY) != isInWater) {
                drowned.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(isInWater ? 1.196 : 0.23);
                drownedStates.setBooleanState(IN_WATER_KEY, isInWater);
            }
        }
    }

    public static void onDamage(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        if (source.getSource() instanceof TridentEntity trident) {
            if (trident.getOwner() instanceof DrownedEntity) {
                cir.setReturnValue(false);
            }
        }
    }
}