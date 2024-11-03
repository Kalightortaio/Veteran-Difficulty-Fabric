package com.kalightortaio.veterandifficulty.interfaces;

public interface IEntityState {
    boolean getBooleanState(String stateName);
    void setBooleanState(String stateName, boolean value);

    int getIntState(String stateName);
    void setIntState(String stateName, int value);

    float getFloatState(String stateName);
    void setFloatState(String stateName, float value);
}