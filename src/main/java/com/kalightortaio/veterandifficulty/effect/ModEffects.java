package com.kalightortaio.veterandifficulty.effect;

import com.kalightortaio.veterandifficulty.VeteranDifficulty;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final StatusEffect SCALDING_EFFECT = registerEffect("scalding", new ScaldingEffect());

    private static StatusEffect registerEffect(String path, StatusEffect effect) {
        Registry.register(Registries.STATUS_EFFECT, Identifier.of(VeteranDifficulty.MOD_ID, path), effect);
        return effect;
    }

    public static void registerModEffects() {
        VeteranDifficulty.LOGGER.info("Registering Mod Effects for " + VeteranDifficulty.MOD_ID);
    }
}
