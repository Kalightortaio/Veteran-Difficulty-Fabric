package com.kalightortaio.veterandifficulty.mixin.accessor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.entity.ItemEntity;

@Mixin(ItemEntity.class)
public interface ItemEntityAccessor {
    @Accessor("health")
    void setHealth(int health);
}
