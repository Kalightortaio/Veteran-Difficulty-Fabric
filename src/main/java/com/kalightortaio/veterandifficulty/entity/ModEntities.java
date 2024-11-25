package com.kalightortaio.veterandifficulty.entity;

import com.kalightortaio.veterandifficulty.VeteranDifficulty;
import com.kalightortaio.veterandifficulty.entity.custom.MagicProjectileEntity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.DefaultedRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<MagicProjectileEntity> MAGIC_PROJECTILE = registerEntityType("magic_projectile", EntityType.Builder.<MagicProjectileEntity>create(MagicProjectileEntity::new, SpawnGroup.MISC).dimensions(0.25F, 0.25F));

    private static <T extends Entity> EntityType<T> registerEntityType(String path, EntityType.Builder<T> builder) {
        Identifier id = Identifier.of(VeteranDifficulty.MOD_ID, path);
        DefaultedRegistry<EntityType<?>> registry = Registries.ENTITY_TYPE;
        return Registry.register(registry, id, builder.build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, id)));
    }

    public static void registerModEntities() {
        VeteranDifficulty.LOGGER.info("Registering Mod Entities for " + VeteranDifficulty.MOD_ID);
    }
}
