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
public abstract class EntityMixin implements IEntityState {

    @Unique
    private final Map<String, Boolean> booleanStates = new HashMap<>();
    @Unique
    private final Map<String, Integer> intStates = new HashMap<>();
    @Unique
    private final Map<String, Float> floatStates = new HashMap<>();
    @Unique
    private final Map<String, Long> longStates = new HashMap<>();

    @Inject(method = "writeNbt", at = @At("TAIL"))
    private void writeCustomDataToNbt(NbtCompound nbt, CallbackInfoReturnable<Boolean> cir) {
        saveStatesToNbt(nbt);
    }

    @Inject(method = "readNbt", at = @At("TAIL"))
    private void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo ci) {
        loadStatesFromNbt(nbt);
    }

    private void saveStatesToNbt(NbtCompound nbt) {
        booleanStates.forEach(nbt::putBoolean);
        intStates.forEach(nbt::putInt);
        floatStates.forEach(nbt::putFloat);
        longStates.forEach(nbt::putLong);
    }

    private void loadStatesFromNbt(NbtCompound nbt) {
        for (String key : nbt.getKeys()) {
            if (nbt.contains(key, NbtCompound.BYTE_TYPE)) {
                booleanStates.put(key, nbt.getBoolean(key));
            } else if (nbt.contains(key, NbtCompound.INT_TYPE)) {
                intStates.put(key, nbt.getInt(key));
            } else if (nbt.contains(key, NbtCompound.FLOAT_TYPE)) {
                floatStates.put(key, nbt.getFloat(key));
            } else if (nbt.contains(key, NbtCompound.LONG_TYPE)) {
                longStates.put(key, nbt.getLong(key));
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

    @Override
    public float getFloatState(String stateName) {
        return floatStates.getOrDefault(stateName, 0.0f);
    }

    @Override
    public void setFloatState(String stateName, float value) {
        floatStates.put(stateName, value);
    }

    @Override
    public long getLongState(String stateName) {
        return longStates.getOrDefault(stateName, 0L);
    }

    @Override
    public void setLongState(String stateName, long value) {
        longStates.put(stateName, value);
    }
}
