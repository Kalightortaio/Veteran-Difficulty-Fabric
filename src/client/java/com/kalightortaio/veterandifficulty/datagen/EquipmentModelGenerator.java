package com.kalightortaio.veterandifficulty.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.kalightortaio.veterandifficulty.VeteranDifficulty;
import com.kalightortaio.veterandifficulty.util.Armors;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.DataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.util.Identifier;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class EquipmentModelGenerator {
    private final FabricDataOutput out;
    private final DataWriter writer;

    public EquipmentModelGenerator(FabricDataOutput out, DataWriter writer) {
        this.out = out;
        this.writer = writer;
    }

    public CompletableFuture<?> register(Armors.Tier tier) {
        String name = Armors.Stats.id(tier).getValue().getPath();
        String ns   = VeteranDifficulty.MOD_ID;
        JsonObject root   = new JsonObject();
        JsonObject layers = new JsonObject();
        JsonArray humanoid = new JsonArray();
        JsonObject h0 = new JsonObject();
        h0.addProperty("texture", ns + ":" + name);
        humanoid.add(h0);
        layers.add("humanoid", humanoid);
        JsonArray leggings = new JsonArray();
        JsonObject l0 = new JsonObject();
        l0.addProperty("texture", ns + ":" + name);
        leggings.add(l0);
        layers.add("humanoid_leggings", leggings);
        root.add("layers", layers);
        DataOutput.PathResolver resolver = out.getResolver(DataOutput.OutputType.RESOURCE_PACK, "equipment");
        Path path = resolver.resolveJson(Identifier.of(ns, name));
        return DataProvider.writeToPath(writer, root, path);
    }
}