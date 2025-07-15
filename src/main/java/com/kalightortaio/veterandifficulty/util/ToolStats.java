package com.kalightortaio.veterandifficulty.util;

import java.util.EnumMap;
import java.util.Map;

public final class ToolStats {
    record Row(int durability, float miningSpeed, float attackBonus, int enchantValue) {}

    private static final Map<ToolTier, Row> CORE = Map.ofEntries(
        Map.entry(ToolTier.FLINT,     new Row(59,    0.5F,  0.0F, 6)),
        Map.entry(ToolTier.WOOD,      new Row(106,   1.0F,  0.5F, 8)),
        Map.entry(ToolTier.STONE,     new Row(176,   2.0F,  1.0F, 10)),
        Map.entry(ToolTier.COPPER,    new Row(260,   3.0F,  1.5F, 12)),
        Map.entry(ToolTier.SILVER,    new Row(400,   4.0F,  2.0F, 14)),
        Map.entry(ToolTier.GOLD,      new Row(807,   5.0F,  2.5F, 16)),
        Map.entry(ToolTier.IRON,      new Row(1339,  6.0F,  3.0F, 18)),
        Map.entry(ToolTier.MITHRIL,   new Row(2143,  7.0F,  3.5F, 20)),
        Map.entry(ToolTier.DIAMOND,   new Row(3690,  8.0F,  4.0F, 22)),
        Map.entry(ToolTier.NETHERITE, new Row(6126,  9.0F,  4.5F, 24)),
        Map.entry(ToolTier.ENDERIUM,  new Row(7214,  10.0F, 5.0F, 26)),
        Map.entry(ToolTier.TENEBRIS,  new Row(10655, 11.0F, 5.5F, 28)),
        Map.entry(ToolTier.ZENITH,    new Row(99999, 12.0F, 6.0F, 30))
    );

    private static final Map<ToolType, Float> TOOL_DAMAGE = new EnumMap<>(ToolType.class);
    private static final Map<ToolType, Float> TOOL_SPEED  = new EnumMap<>(ToolType.class);
    static {
        TOOL_DAMAGE.put(ToolType.SWORD,   3.0F);
        TOOL_DAMAGE.put(ToolType.AXE,     6.0F);
        TOOL_DAMAGE.put(ToolType.PICKAXE, 1.0F);
        TOOL_DAMAGE.put(ToolType.SHOVEL,  1.5F);
        TOOL_DAMAGE.put(ToolType.HOE,     0.0F);

        TOOL_SPEED.put(ToolType.SWORD,   -2.4F);
        TOOL_SPEED.put(ToolType.AXE,     -3.2F);
        TOOL_SPEED.put(ToolType.PICKAXE, -2.8F);
        TOOL_SPEED.put(ToolType.SHOVEL,  -3.0F);
        TOOL_SPEED.put(ToolType.HOE,     -3.0F);
    }
    public static int durability(ToolTier tier)     { return CORE.get(tier).durability(); }
    public static float miningSpeed(ToolTier tier)  { return CORE.get(tier).miningSpeed(); }
    public static float attackDamage(ToolTier tier) { return CORE.get(tier).attackBonus(); }
    public static float attackDamage(ToolTier tier, ToolType kind) { 
        return CORE.get(tier).attackBonus() + TOOL_DAMAGE.get(kind); 
    };
    public static int enchantability(ToolTier tier) { return CORE.get(tier).enchantValue(); }
    public static float attackSpeed(ToolType kind)  { return TOOL_SPEED.get(kind); }

    private ToolStats() {}
}