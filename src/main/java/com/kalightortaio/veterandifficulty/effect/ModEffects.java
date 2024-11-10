package com.kalightortaio.veterandifficulty.effect;

import com.kalightortaio.veterandifficulty.VeteranDifficulty;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final StatusEffect SCALDING_EFFECT = registerEffect("scalding", StatusEffectCategory.HARMFUL, 0xcf1515);
    public static final RegistryEntry<StatusEffect> SCALDING = getRegistryEntry(SCALDING_EFFECT);
    public static final StatusEffect ANCHORED_EFFECT = registerEffect("anchored", StatusEffectCategory.HARMFUL, 0x00095b);
    public static final RegistryEntry<StatusEffect> ANCHORED = getRegistryEntry(ANCHORED_EFFECT);

    private static StatusEffect registerEffect(String name, StatusEffectCategory category, int color) {
        StatusEffect effect = new CustomStatusEffect(category, color);
        Registry.register(Registries.STATUS_EFFECT, Identifier.of(VeteranDifficulty.MOD_ID, name), effect);
        return effect;
    }

    private static RegistryEntry<StatusEffect> getRegistryEntry(StatusEffect effect) {
        return Registries.STATUS_EFFECT.getEntry(effect);
    }

    public static void registerModEffects() {
        VeteranDifficulty.LOGGER.info("Registering Mod Effects for " + VeteranDifficulty.MOD_ID);
    }

    private static class CustomStatusEffect extends StatusEffect {

        protected CustomStatusEffect(StatusEffectCategory category, int color) {
            super(category, color);
        }

        @Override
        public boolean canApplyUpdateEffect(int duration, int amplifier) {
            return true;
        }
    }
}
