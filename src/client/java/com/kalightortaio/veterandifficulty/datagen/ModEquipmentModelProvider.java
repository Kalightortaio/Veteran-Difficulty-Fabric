package com.kalightortaio.veterandifficulty.datagen;

import com.kalightortaio.veterandifficulty.VeteranDifficulty;
import com.kalightortaio.veterandifficulty.util.Armors;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class ModEquipmentModelProvider implements DataProvider {
    private final FabricDataOutput out;

    public ModEquipmentModelProvider(FabricDataOutput out) {
        this.out = out;
    }

    @Override
    public CompletableFuture<?> run(DataWriter writer) {
        EquipmentModelGenerator gen = new EquipmentModelGenerator(out, writer);

        List<CompletableFuture<?>> tasks = new ArrayList<>();
        for (Armors.Tier tier : Armors.Tier.values()) {
            switch (tier) {
                case FLINT, COPPER, SILVER, MITHRIL, ENDERIUM, TENEBRIS, ZENITH ->
                    tasks.add(gen.register(tier));
                default -> { /* skip vanilla */ }
            }
        }

        return CompletableFuture.allOf(tasks.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "Equipment Models (" + VeteranDifficulty.MOD_ID + ")";
    }
}