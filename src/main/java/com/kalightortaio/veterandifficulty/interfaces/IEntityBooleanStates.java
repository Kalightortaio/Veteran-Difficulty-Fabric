package com.kalightortaio.veterandifficulty.interfaces;

public interface IEntityBooleanStates {
    boolean getBooleanState(String stateName);
    void setBooleanState(String stateName, boolean value);
}