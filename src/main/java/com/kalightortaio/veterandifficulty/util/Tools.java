package com.kalightortaio.veterandifficulty.util;

import java.util.EnumMap;
import java.util.Map;

import com.kalightortaio.veterandifficulty.systems.internal.ModTags;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MaceItem;
import net.minecraft.item.ShearsItem;
import net.minecraft.item.TridentItem;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

public class Tools {
    public enum Type { SWORD, AXE, PICKAXE, SHOVEL, HOE }

    public enum Tier {
        FLINT     (ModTags.FLINT_TIER,     ModTags.INCORRECT_FOR_FLINT_TOOL),
        WOODEN    (ModTags.WOODEN_TIER,    BlockTags.INCORRECT_FOR_WOODEN_TOOL),
        STONE     (ModTags.STONE_TIER,     BlockTags.INCORRECT_FOR_STONE_TOOL),
        COPPER    (ModTags.COPPER_TIER,    ModTags.INCORRECT_FOR_COPPER_TOOL),
        SILVER    (ModTags.SILVER_TIER,    ModTags.INCORRECT_FOR_SILVER_TOOL),
        GOLD      (ModTags.GOLD_TIER,      BlockTags.INCORRECT_FOR_GOLD_TOOL),
        IRON      (ModTags.IRON_TIER,      BlockTags.INCORRECT_FOR_IRON_TOOL),
        MITHRIL   (ModTags.MITHRIL_TIER,   ModTags.INCORRECT_FOR_MITHRIL_TOOL),
        DIAMOND   (ModTags.DIAMOND_TIER,   BlockTags.INCORRECT_FOR_DIAMOND_TOOL),
        NETHERITE (ModTags.NETHERITE_TIER, BlockTags.INCORRECT_FOR_NETHERITE_TOOL),
        ENDERIUM  (ModTags.ENDERIUM_TIER,  ModTags.INCORRECT_FOR_ENDERIUM_TOOL),
        TENEBRIS  (ModTags.TENEBRIS_TIER,  ModTags.INCORRECT_FOR_TENEBRIS_TOOL),
        ZENITH    (ModTags.ZENITH_TIER,    ModTags.INCORRECT_FOR_ZENITH_TOOL);

        public final TagKey<Item> itemTier;
        public final TagKey<Block> incorrectBlockTag;

        Tier(TagKey<Item> itemTier, TagKey<Block> incorrectBlockTag) {
            this.itemTier = itemTier;
            this.incorrectBlockTag = incorrectBlockTag;
        }
    }

    public static final class Stats {
        private record Row(int durability, float miningSpeed, float attackBonus, int enchantValue) {}

        private static final Map<Tier, Row> CORE = Map.ofEntries(
            Map.entry(Tier.FLINT,     new Row(59,    0.5F,  0.0F, 6)),
            Map.entry(Tier.WOODEN,    new Row(106,   1.0F,  0.5F, 8)),
            Map.entry(Tier.STONE,     new Row(176,   2.0F,  1.0F, 10)),
            Map.entry(Tier.COPPER,    new Row(260,   3.0F,  1.5F, 12)),
            Map.entry(Tier.SILVER,    new Row(400,   4.0F,  2.0F, 14)),
            Map.entry(Tier.GOLD,      new Row(807,   5.0F,  2.5F, 16)),
            Map.entry(Tier.IRON,      new Row(1339,  6.0F,  3.0F, 18)),
            Map.entry(Tier.MITHRIL,   new Row(2143,  7.0F,  3.5F, 20)),
            Map.entry(Tier.DIAMOND,   new Row(3690,  8.0F,  4.0F, 22)),
            Map.entry(Tier.NETHERITE, new Row(6126,  9.0F,  4.5F, 24)),
            Map.entry(Tier.ENDERIUM,  new Row(7214,  10.0F, 5.0F, 26)),
            Map.entry(Tier.TENEBRIS,  new Row(10655, 11.0F, 5.5F, 28)),
            Map.entry(Tier.ZENITH,    new Row(99999, 12.0F, 6.0F, 30))
        );

        private static final Map<Type, Float> TOOL_DAMAGE = new EnumMap<>(Type.class);
        private static final Map<Type, Float> TOOL_SPEED  = new EnumMap<>(Type.class);
        static {
            TOOL_DAMAGE.put(Type.SWORD,   3.0F);
            TOOL_DAMAGE.put(Type.AXE,     6.0F);
            TOOL_DAMAGE.put(Type.PICKAXE, 1.0F);
            TOOL_DAMAGE.put(Type.SHOVEL,  1.5F);
            TOOL_DAMAGE.put(Type.HOE,     0.0F);

            TOOL_SPEED.put(Type.SWORD,   -2.4F);
            TOOL_SPEED.put(Type.AXE,     -3.2F);
            TOOL_SPEED.put(Type.PICKAXE, -2.8F);
            TOOL_SPEED.put(Type.SHOVEL,  -3.0F);
            TOOL_SPEED.put(Type.HOE,     -3.0F);
        }
        public static int durability(Tier tier)     { return CORE.get(tier).durability(); }
        public static float miningSpeed(Tier tier)  { return CORE.get(tier).miningSpeed(); }
        public static float attackDamage(Tier tier) { return CORE.get(tier).attackBonus(); }
        public static float baseDamage(Type kind)   { return TOOL_DAMAGE.get(kind); }
        public static int enchantability(Tier tier) { return CORE.get(tier).enchantValue(); }
        public static float attackSpeed(Type kind)  { return TOOL_SPEED.get(kind); }

        private Stats() {}
    }

    public static final class Util {
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

    private Tools() {}
}
