package com.kalightortaio.veterandifficulty.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kalightortaio.veterandifficulty.interfaces.IBiteHandlerEntity;
import com.kalightortaio.veterandifficulty.mob.Bat;
import com.kalightortaio.veterandifficulty.mob.Bat.BiteHandler;

import net.minecraft.entity.passive.BatEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

@Mixin(BatEntity.class)
public abstract class BatEntityMixin implements IBiteHandlerEntity {
    
    @Shadow
    private BlockPos hangingPosition;
    private final Bat.BiteHandler biteHandler = new Bat.BiteHandler((BatEntity) (Object) this);

    @Inject(method = "mobTick", at = @At("HEAD"), cancellable = true)
    private void betterBatAI(ServerWorld world, CallbackInfo info) {
        Bat.onTick(world, ((BatEntity) (Object) this), hangingPosition, biteHandler, info);
    }

    @Override
    public BiteHandler getBiteHandler() {
        return biteHandler;
    }
}
