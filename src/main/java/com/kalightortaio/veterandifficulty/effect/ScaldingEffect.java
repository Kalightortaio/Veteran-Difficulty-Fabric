package com.kalightortaio.veterandifficulty.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class ScaldingEffect extends StatusEffect{
    protected ScaldingEffect() {
		super(StatusEffectCategory.HARMFUL, 0xcf1515);
	}

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
