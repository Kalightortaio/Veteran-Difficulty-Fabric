package com.kalightortaio.veterandifficulty.mixin;

import java.util.HashMap;
import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kalightortaio.veterandifficulty.interfaces.IEntityState;
import net.minecraft.entity.Entity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;

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

    @Inject(method = "writeData", at = @At("TAIL"))
    private void onWriteData(WriteView view, CallbackInfo ci) {
        WriteView child = view.get("vd_states");
        booleanStates.forEach(child::putBoolean);
        intStates.forEach(child::putInt);
        floatStates.forEach(child::putFloat);
        longStates.forEach(child::putLong);
    }

    @Inject(method = "readData", at = @At("TAIL"))
    private void onReadData(ReadView view, CallbackInfo ci) {
        view.getOptionalReadView("vd_states").ifPresent(child -> {
            booleanStates.replaceAll((k, v) -> child.getBoolean(k, v));
            intStates.replaceAll    ((k, v) -> child.getInt   (k, v));
            floatStates.replaceAll  ((k, v) -> child.getFloat (k, v));
            longStates.replaceAll   ((k, v) -> child.getLong  (k, v));
        });
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
