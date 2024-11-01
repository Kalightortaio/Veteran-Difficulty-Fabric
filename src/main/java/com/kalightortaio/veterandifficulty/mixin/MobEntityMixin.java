package com.kalightortaio.veterandifficulty.mixin;

import com.kalightortaio.veterandifficulty.interfaces.IEntityBooleanStates;

import net.minecraft.entity.mob.MobEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.HashMap;
import java.util.Map;

@Mixin(MobEntity.class)
public class MobEntityMixin implements IEntityBooleanStates {

    @Unique
    private final Map<String, Boolean> booleanStates = new HashMap<>();

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo ci) {
        saveBooleanStatesToNbt(nbt);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        loadBooleanStatesFromNbt(nbt);
    }

    private void saveBooleanStatesToNbt(NbtCompound nbt) {
        for (Map.Entry<String, Boolean> entry : booleanStates.entrySet()) {
            nbt.putBoolean(entry.getKey(), entry.getValue());
        }
    }

    private void loadBooleanStatesFromNbt(NbtCompound nbt) {
        for (String key : booleanStates.keySet()) {
            if (nbt.contains(key)) {
                booleanStates.put(key, nbt.getBoolean(key));
            }
        }
    }

    @Override
    public boolean getBooleanState(String stateName) {
        return booleanStates.getOrDefault(stateName, false);
    }

    @Override
    public void setBooleanState(String stateName, boolean value) {
        booleanStates.put(stateName, value);
    }
}
