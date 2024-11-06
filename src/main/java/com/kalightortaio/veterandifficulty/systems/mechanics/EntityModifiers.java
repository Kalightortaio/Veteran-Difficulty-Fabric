package com.kalightortaio.veterandifficulty.systems.mechanics;

import com.kalightortaio.veterandifficulty.interfaces.IEntityState;
import com.kalightortaio.veterandifficulty.mob.Creeper;
import com.kalightortaio.veterandifficulty.mob.Drowned;
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
            Evoker.onLoad(world, server, entity);
            Vex.onLoad(world, server, entity);
            Wolf.onLoad(world, server, entity);
            Slime.onLoad(server, entity);
            Drowned.onLoad(server, entity);
            Ghast.onLoad(server, entity);
            Ghast.onAttack(server, entity);
            Creeper.onLoad(server, entity);
            Zombie.onLoad(server, entity);
            Spider.onLoad(server, entity);
            Phantom.canSpawn(world, server, entity);
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