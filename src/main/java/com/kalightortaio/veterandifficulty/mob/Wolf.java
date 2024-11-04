package com.kalightortaio.veterandifficulty.mob;

import java.util.Comparator;
import java.util.UUID;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.server.world.ServerWorld;

public class Wolf {
    public static void processHunt(ServerWorld world, long time) {
        if (time == 0 || time < 13000) return;
        for (WolfEntity wolf : world.getEntitiesByType(EntityType.WOLF, wolf -> !wolf.isTamed())) {
            if (time >= 13000 && time < 23000) {
                if (wolf.getAngerTime() == 0) {
                    wolf.setAngerTime((int) (23000 - time));
                } else {
                    world.getPlayers().stream()
                        .sorted(Comparator.comparingDouble(player -> wolf.squaredDistanceTo(player)))
                        .filter(player -> wolf.squaredDistanceTo(player) <= 256)
                        .findFirst()
                        .ifPresent(player -> {
                            if (!player.getUuid().equals(wolf.getAngryAt())) {
                                wolf.setAngryAt(player.getUuid());
                            }
                        });
                }
            } else if (time >= 23000 && time < 23020) {
                if (wolf.getAngerTime() > 0) {
                    wolf.setAngryAt((UUID) null);
                    wolf.setAttacker((LivingEntity) null);
                    wolf.setTarget((LivingEntity) null);
                    wolf.setAngerTime(0);
                }
            }
        }
    }
}
