package com.kalightortaio.veterandifficulty.mob;

import com.kalightortaio.veterandifficulty.interfaces.IEntityState;

import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.event.GameEvent.Emitter;

public class Enderman {

    public static void onAttack(ServerWorld world, LivingEntity teleTarget) {
        int currentTime = (int) world.getTime();
        int lastTeleportTime = 0;
        if (teleTarget instanceof IEntityState teleStates) {
            lastTeleportTime = teleStates.getIntState("lastTeleportTime");
        } else {
            System.err.println("Failed to apply IEntityState to: " + teleTarget);
        }
        
        if (currentTime - lastTeleportTime < 200) {
            return;
        }
        
        double teleportDiameter = 32.0;
        for (int i = 0; i < 16; ++i) { 
            double x = teleTarget.getX() + (teleTarget.getRandom().nextDouble() - 0.5) * teleportDiameter;
            double y = MathHelper.clamp(teleTarget.getY() + (teleTarget.getRandom().nextDouble() - 0.5) * teleportDiameter, (double)world.getBottomY(), (double)(world.getBottomY() + ((ServerWorld)world).getLogicalHeight() - 1));
            double z = teleTarget.getZ() + (teleTarget.getRandom().nextDouble() - 0.5) * teleportDiameter;

            if (teleTarget.hasVehicle()) {
                teleTarget.stopRiding(); 
            }

            Vec3d teleTargetPos = teleTarget.getPos();
            if (teleTarget.teleport(x, y, z, true)) {
                world.emitGameEvent(GameEvent.TELEPORT, teleTargetPos, Emitter.of(teleTarget));

                SoundCategory soundCategory;
                SoundEvent soundEvent;
                soundEvent = SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                soundCategory = SoundCategory.PLAYERS;
                world.playSound(null, teleTarget.getX(), teleTarget.getY(), teleTarget.getZ(), soundEvent, soundCategory);

                teleTarget.onLanding();
                if (teleTarget instanceof IEntityState teleStates) {
                    teleStates.setIntState("lastTeleportTime", (currentTime));
                } else {
                    System.err.println("Failed to apply IEntityState to: " + teleTarget);
                }
                break;
            }
        }
    }
}