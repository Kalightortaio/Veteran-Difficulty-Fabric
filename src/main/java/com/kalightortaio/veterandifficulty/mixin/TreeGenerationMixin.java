package com.kalightortaio.veterandifficulty.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.MarkerEntity;
import net.minecraft.text.Text;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kalightortaio.veterandifficulty.interfaces.IEntityState;

import java.util.function.BiConsumer;

@Mixin(TreeFeature.class)
public abstract class TreeGenerationMixin {

    @Inject(method = "generate", at = @At("RETURN"))
    private void replaceBaseLog(StructureWorldAccess world, Random random, BlockPos pos, BiConsumer<BlockPos, BlockState> rootPlacerReplacer, BiConsumer<BlockPos, BlockState> trunkPlacerReplacer, FoliagePlacer.BlockPlacer blockPlacer, TreeFeatureConfig config, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValue()) {
            BlockPos basePos = config.rootPlacer.map(rootPlacer -> rootPlacer.trunkOffset(pos, random)).orElse(pos);
            MarkerEntity marker = new MarkerEntity(EntityType.MARKER, world.toServerWorld());
            marker.setPosition(Vec3d.ofCenter(basePos));
            marker.setCustomName(Text.of("VDTree"));
            long timeCreated = world.toServerWorld().getTime();
            ((IEntityState) marker).setLongState("timeCreated", timeCreated);
            world.spawnEntity(marker);
        }
    }
}