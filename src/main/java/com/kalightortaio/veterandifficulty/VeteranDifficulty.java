package com.kalightortaio.veterandifficulty;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kalightortaio.veterandifficulty.block.ModBlocks;
import com.kalightortaio.veterandifficulty.block.entity.ModBlockEntities;
import com.kalightortaio.veterandifficulty.effect.ModEffects;
import com.kalightortaio.veterandifficulty.item.ModItems;
import com.kalightortaio.veterandifficulty.systems.internal.TickManager;
import com.kalightortaio.veterandifficulty.systems.internal.EntityModifiers;
import com.kalightortaio.veterandifficulty.systems.internal.GameSettings;

public class VeteranDifficulty implements ModInitializer {
	public static final String MOD_ID = "veterandifficulty";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModBlockEntities.registerModBlockEntities();
		ModEffects.registerModEffects();
		//
		TickManager.register();
		// 
		EntityModifiers.registerHook();
		//
		GameSettings.register();
	}
}