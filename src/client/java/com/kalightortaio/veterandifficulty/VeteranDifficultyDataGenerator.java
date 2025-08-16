package com.kalightortaio.veterandifficulty;

import com.kalightortaio.veterandifficulty.datagen.ModBlockTagProvider;
import com.kalightortaio.veterandifficulty.datagen.ModEquipmentModelProvider;
import com.kalightortaio.veterandifficulty.datagen.ModItemTagProvider;
import com.kalightortaio.veterandifficulty.datagen.ModModelProvider;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class VeteranDifficultyDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModEquipmentModelProvider::new);
	}
}
