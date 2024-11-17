package com.kalightortaio.veterandifficulty.systems.internal;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static final TagKey<Block> AIR = TagKey.of(RegistryKeys.BLOCK, Identifier.of("veterandifficulty", "air"));
    public static final TagKey<Block> MONUMENT = TagKey.of(RegistryKeys.BLOCK, Identifier.of("veterandifficulty", "monument"));
    public static final TagKey<Block> SEA_FAUNA = TagKey.of(RegistryKeys.BLOCK, Identifier.of("veterandifficulty", "sea_fauna"));
    public static final TagKey<Block> SAPLING_SURFACE = TagKey.of(RegistryKeys.BLOCK, Identifier.of("veterandifficulty", "sapling_surface"));
    public static final TagKey<Item> REGROWTH = TagKey.of(RegistryKeys.ITEM, Identifier.of("veterandifficulty", "regrowth"));
}
