package com.kalightortaio.veterandifficulty.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MaceItem;
import net.minecraft.item.ShearsItem;
import net.minecraft.item.TridentItem;
import net.minecraft.registry.tag.ItemTags;

public class ToolUtil {
    public static boolean isValidWeaponOrTool(ItemStack stack) {
        if (stack.isEmpty()) return false;

        if (stack.isIn(ItemTags.PICKAXES)
            || stack.isIn(ItemTags.AXES)
            || stack.isIn(ItemTags.SHOVELS)
            || stack.isIn(ItemTags.HOES)
            || stack.isIn(ItemTags.SWORDS)) {
            return true;
        }

        Item item = stack.getItem();
        return item instanceof ShearsItem || item instanceof TridentItem || item instanceof MaceItem;
    }
}
