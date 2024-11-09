package com.kalightortaio.veterandifficulty.systems.internal;

import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static final TagKey<Block> AIR = TagKey.of(RegistryKeys.BLOCK, Identifier.of("veterandifficulty", "air"));
    public static final TagKey<Block> MONUMENT = TagKey.of(RegistryKeys.BLOCK, Identifier.of("veterandifficulty", "monument"));
    public static final TagKey<Block> SEA_FAUNA = TagKey.of(RegistryKeys.BLOCK, Identifier.of("veterandifficulty", "sea_fauna"));
}
