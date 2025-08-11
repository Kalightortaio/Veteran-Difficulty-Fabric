package com.kalightortaio.veterandifficulty.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kalightortaio.veterandifficulty.systems.internal.ModTags;
import com.kalightortaio.veterandifficulty.util.ToolTier;
import com.kalightortaio.veterandifficulty.util.ToolUtil;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    private PlayerEntity asPlayer() {
        return (PlayerEntity) (Object) this;
    }

    @Inject(method = "getBlockBreakingSpeed", at = @At("HEAD"), cancellable = true)
    private void onBlockBreak(BlockState state, CallbackInfoReturnable<Float> cir) {
        ItemStack heldItem = asPlayer().getMainHandStack();
        if (!ToolUtil.isValidWeaponOrTool(heldItem)) {
            if (state.isIn(ModTags.INCORRECT_FOR_NO_TOOL)) {
                cir.setReturnValue(0.0F);
                return;
            }
        }
        for (ToolTier t : ToolTier.values()) {
            if (heldItem.isIn(t.itemTier) && state.isIn(t.incorrectBlockTag)) {
                cir.setReturnValue(0.0F);
                return;
            }
        }
        if (!heldItem.isSuitableFor(state)) {
            cir.setReturnValue(0.25F);
        }
    }
}
