package com.kalightortaio.veterandifficulty.mob;

import com.kalightortaio.veterandifficulty.interfaces.IEntityState;
import com.kalightortaio.veterandifficulty.mixin.accessor.MobEntityAccessor;
import com.kalightortaio.veterandifficulty.systems.mechanics.EntityModifiers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;

public class Creeper {

    public static void onLoad(MinecraftServer server, Entity entity) {
        if (entity instanceof CreeperEntity creeper && !((IEntityState) creeper).getBooleanState(EntityModifiers._KEY)) {
            GoalSelector targetSelector = ((MobEntityAccessor) creeper).getTargetSelector();
            
            targetSelector.getGoals().forEach(prioritizedGoal -> {
                if (prioritizedGoal.getGoal() instanceof ActiveTargetGoal) {
                    targetSelector.remove(prioritizedGoal.getGoal());
                }
            });
            targetSelector.add(1, new ActiveTargetGoal<PlayerEntity>(creeper, PlayerEntity.class, false));
            EntityModifiers.tagEntity(creeper, server);
        }
    }
}
