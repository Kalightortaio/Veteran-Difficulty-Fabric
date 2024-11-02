package com.kalightortaio.veterandifficulty.mixin;

import java.util.HashMap;
import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kalightortaio.veterandifficulty.interfaces.IEntityState;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;

@Mixin(Entity.class)
public class EntityMixin implements IEntityState {

    @Unique
    private final Map<String, Boolean> booleanStates = new HashMap<>();
    @Unique
    private final Map<String, Integer> intStates = new HashMap<>();

    @Inject(method = "writeNbt", at = @At("TAIL"))
    private void writeCustomDataToNbt(NbtCompound nbt, CallbackInfoReturnable<Boolean> cir) {
        saveStatesToNbt(nbt);
    }

    @Inject(method = "readNbt", at = @At("TAIL"))
    private void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        loadStatesFromNbt(nbt);
    }

    private void saveStatesToNbt(NbtCompound nbt) {
        for (Map.Entry<String, Boolean> entry : booleanStates.entrySet()) {
            nbt.putBoolean(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, Integer> entry : intStates.entrySet()) {
            nbt.putInt(entry.getKey(), entry.getValue());
        }
    }

    private void loadStatesFromNbt(NbtCompound nbt) {
        for (String key : booleanStates.keySet()) {
            if (nbt.contains(key)) {
                booleanStates.put(key, nbt.getBoolean(key));
            }
        }
        for (String key : intStates.keySet()) {
            if (nbt.contains(key)) {
                intStates.put(key, nbt.getInt(key));
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

    @Override
    public int getIntState(String stateName) {
        return intStates.getOrDefault(stateName, 0);
    }

    @Override
    public void setIntState(String stateName, int value) {
        intStates.put(stateName, value);
    }
}
