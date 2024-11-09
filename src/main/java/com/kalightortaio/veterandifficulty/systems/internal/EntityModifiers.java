package com.kalightortaio.veterandifficulty.systems.internal;

import com.kalightortaio.veterandifficulty.interfaces.IEntityState;
import com.kalightortaio.veterandifficulty.mob.Bat;
import com.kalightortaio.veterandifficulty.mob.Creeper;
import com.kalightortaio.veterandifficulty.mob.Drowned;
import com.kalightortaio.veterandifficulty.mob.ElderGuardian;
import com.kalightortaio.veterandifficulty.mob.Evoker;
import com.kalightortaio.veterandifficulty.mob.Ghast;
import com.kalightortaio.veterandifficulty.mob.Phantom;
import com.kalightortaio.veterandifficulty.mob.Slime;
import com.kalightortaio.veterandifficulty.mob.Spider;
import com.kalightortaio.veterandifficulty.mob.Vex;
import com.kalightortaio.veterandifficulty.mob.Wolf;
import com.kalightortaio.veterandifficulty.mob.Zombie;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;

public class EntityModifiers {
    public static final String _KEY = "vd_processed";

    public static void registerHook() {
        ServerEntityEvents.ENTITY_LOAD.register((Entity entity, ServerWorld world) -> {
            MinecraftServer server = world.getServer();
            Phantom.canSpawn(world, server, entity);
            Evoker.onLoad(world, server, entity);
            Wolf.onLoad(world, server, entity);
            Vex.onLoad(world, server, entity);
            Bat.onLoad(world, server, entity);
            ElderGuardian.onLoad(server, entity);
            Ghast.onAttack(server, entity);
            Drowned.onLoad(server, entity);
            Creeper.onLoad(server, entity);
            Zombie.onLoad(server, entity);
            Spider.onLoad(server, entity);
            Slime.onLoad(server, entity);
            Ghast.onLoad(server, entity);
        });
    }

    public static void tagEntity(Entity entity, MinecraftServer server) {
        tagEntity(entity, server, _KEY);
    }

    public static void tagEntity(Entity entity, MinecraftServer server, String key) {
        ((IEntityState) entity).setBooleanState(key, true);
        server.getCommandManager().executeWithPrefix(
            server.getCommandSource().withSilent(),
            "tag " + entity.getUuidAsString() + " add " + key
        );
    }
}