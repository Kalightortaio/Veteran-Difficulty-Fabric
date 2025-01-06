package com.kalightortaio.veterandifficulty.mob;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.kalightortaio.veterandifficulty.interfaces.IEntityState;
import com.kalightortaio.veterandifficulty.systems.internal.EntityModifiers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.EvokerEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public class Vex {

    public static void onLoad(ServerWorld world, MinecraftServer server, Entity entity) {
        if (entity instanceof VexEntity vex && !((IEntityState) vex).getBooleanState(EntityModifiers._KEY)) {
            vex.getAttributeInstance(EntityAttributes.SCALE).setBaseValue(1.5f);
            EntityModifiers.tagEntity(vex, server);
        }
    }

    public static void onAttack(ServerWorld world, Entity vex) {
        EvokerEntity evoker = (EvokerEntity) ((VexEntity) vex).getOwner();
        if (evoker == null || evoker.isRemoved() || evoker.isDead()) return;
        addAnimation(world, vex.getPos(), evoker.getPos(), 20);
        evoker.heal(10.0f);
    }

    private static final List<VexAnimation> vexAnimations = new ArrayList<>();

    public static void addAnimation(ServerWorld world, Vec3d startPos, Vec3d endPos, int durationTicks) {
        vexAnimations.add(new VexAnimation(world, startPos, endPos, durationTicks));
    }

    public static void updateAnimations() {
        Iterator<VexAnimation> iterator = vexAnimations.iterator();
        while (iterator.hasNext()) {
            VexAnimation animation = iterator.next();
            if (animation.tick()) {
                iterator.remove();
            }
        }
    }

    private static class VexAnimation {
        private final ServerWorld world;
        private Vec3d currentPos;
        private final Vec3d direction;
        private final double stepSize;
        private int remainingTicks;

        public VexAnimation(ServerWorld world, Vec3d startPos, Vec3d endPos, int durationTicks) {
            this.world = world;
            this.currentPos = startPos;
            this.direction = endPos.subtract(startPos).normalize();
            this.stepSize = startPos.distanceTo(endPos) / durationTicks;
            this.remainingTicks = durationTicks;
        }

        public boolean tick() {
            if (remainingTicks <= 0) return true;

            world.spawnParticles(ParticleTypes.RAID_OMEN, currentPos.x, currentPos.y, currentPos.z, 1, 0, 0, 0, 0);
            currentPos = currentPos.add(direction.multiply(stepSize));
            remainingTicks--;

            return false;
        }
    }
}