package com.kalightortaio.veterandifficulty;

import com.kalightortaio.veterandifficulty.entity.ModEntities;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EmptyEntityRenderer;

@Environment(EnvType.CLIENT)
public class VeteranDifficultyClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(ModEntities.MAGIC_PROJECTILE, EmptyEntityRenderer::new);
    }
}
