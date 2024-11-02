package com.kalightortaio.veterandifficulty.mob;

import com.kalightortaio.veterandifficulty.interfaces.IEntityState;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.DrownedEntity;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class Drowned {
    public static void dynamicSwimmingSpeed(ServerWorld world) {
        final String IN_WATER_KEY = "lastInWater";

        for (DrownedEntity drowned : world.getEntitiesByType(EntityType.DROWNED, entity -> true)) {
            boolean isInWater = world.getFluidState(BlockPos.ofFloored(drowned.getPos())).isIn(FluidTags.WATER);
            
            if (drowned instanceof IEntityState drownedStates && drownedStates.getBooleanState(IN_WATER_KEY) != isInWater) {
                drowned.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(isInWater ? 1.196 : 0.23);
                drownedStates.setBooleanState(IN_WATER_KEY, isInWater);
            }
        }
    }
}
