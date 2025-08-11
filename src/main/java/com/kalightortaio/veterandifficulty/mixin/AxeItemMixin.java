package com.kalightortaio.veterandifficulty.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.kalightortaio.veterandifficulty.item.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

@Mixin(AxeItem.class)
public abstract class AxeItemMixin {
    @Unique private static record Bark(TagKey<Block> tag, Item item) {}

    @Unique private static final List<Bark> BARKS = List.of(
        new Bark(BlockTags.OAK_LOGS,        ModItems.OAK_BARK),
        new Bark(BlockTags.SPRUCE_LOGS,     ModItems.SPRUCE_BARK),
        new Bark(BlockTags.BIRCH_LOGS,      ModItems.BIRCH_BARK),
        new Bark(BlockTags.JUNGLE_LOGS,     ModItems.JUNGLE_BARK),
        new Bark(BlockTags.ACACIA_LOGS,     ModItems.ACACIA_BARK),
        new Bark(BlockTags.DARK_OAK_LOGS,   ModItems.DARK_OAK_BARK),
        new Bark(BlockTags.MANGROVE_LOGS,   ModItems.MANGROVE_BARK),
        new Bark(BlockTags.CHERRY_LOGS,     ModItems.CHERRY_BARK),
        new Bark(BlockTags.PALE_OAK_LOGS,   ModItems.PALE_OAK_BARK),
        new Bark(BlockTags.CRIMSON_STEMS,   ModItems.CRIMSON_BARK),
        new Bark(BlockTags.WARPED_STEMS,    ModItems.WARPED_BARK)
    );

    @Inject(method = "useOnBlock", at = @At("TAIL"))
    private void barkOnStrip(ItemUsageContext ctx, CallbackInfoReturnable<ActionResult> cir) {
        if (cir.getReturnValue() != ActionResult.SUCCESS) return;

        World w = ctx.getWorld();
        if (w.isClient) return;

        ServerWorld sw = (ServerWorld) w;
        if (sw.getRandom().nextInt(10) != 0) return;

        BlockState state = sw.getBlockState(ctx.getBlockPos());
        Item bark = barkFor(state);
        if (bark == null) return;

        Block.dropStack(sw, ctx.getBlockPos(), new ItemStack(bark));
    }

    @Unique private static Item barkFor(BlockState state) {
        for (Bark b : BARKS) if (state.isIn(b.tag())) return b.item();
        return null;
    }
}
