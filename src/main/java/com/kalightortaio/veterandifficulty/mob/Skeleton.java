package com.kalightortaio.veterandifficulty.mob;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.SkeletonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

public class Skeleton {
    public static void updateSkeletonWeapons(ServerWorld world) {
        for (SkeletonEntity skeleton : world.getEntitiesByType(EntityType.SKELETON, skeleton -> true)) {
            ItemStack skeletonWeapon = skeleton.getEquippedStack(EquipmentSlot.MAINHAND);
            boolean hasBowEquipped = skeletonWeapon.getItem() == Items.BOW;
            boolean hasSwordEquipped = skeletonWeapon.getItem() == Items.IRON_SWORD;
            boolean isPlayerClose = false;
            boolean isPlayerFar = false;

            for (ServerPlayerEntity player : world.getPlayers()) {
                double distanceSq = skeleton.squaredDistanceTo(player);

                if (distanceSq <= 64 && hasBowEquipped) {
                    isPlayerClose = true;
                    break;
                } else if (distanceSq > 64 && distanceSq <= 256 && hasSwordEquipped) {
                    isPlayerFar = true;
                }
            }

            if (isPlayerClose && hasBowEquipped) {
                ItemStack sword = new ItemStack(Items.IRON_SWORD);
                copyEnchantments(world, skeleton.getEquippedStack(EquipmentSlot.MAINHAND), sword);
                skeleton.equipStack(EquipmentSlot.MAINHAND, sword);
            } else if (isPlayerFar && hasSwordEquipped) {
                ItemStack bow = new ItemStack(Items.BOW);
                copyEnchantments(world, skeleton.getEquippedStack(EquipmentSlot.MAINHAND), bow);
                skeleton.equipStack(EquipmentSlot.MAINHAND, bow);
            }
        }
    }

    private static final Map<String, String> ENCHANTMENT_CONVERSIONS = createBidirectionalMap(Map.of(
        "minecraft:flame", "minecraft:fire_aspect",
        "minecraft:punch", "minecraft:knockback",
        "minecraft:power", "minecraft:sharpness"
    ));

    private static Map<String, String> createBidirectionalMap(Map<String, String> baseMap) {
        Map<String, String> bidirectionalMap = new HashMap<>(baseMap);
        baseMap.forEach((key, value) -> bidirectionalMap.put(value, key));
        return bidirectionalMap;
    }

    private static void copyEnchantments(ServerWorld world, ItemStack source, ItemStack target) {
        if (source == null || !EnchantmentHelper.hasEnchantments(source)) return;

        EnchantmentHelper.getEnchantments(source).getEnchantmentEntries().forEach(entry -> {
            RegistryEntry<Enchantment> enchantmentEntry = entry.getKey();
            int level = entry.getIntValue();
            String enchantmentId = enchantmentEntry.getIdAsString();

            RegistryEntry<Enchantment> targetEnchantmentEntry = getCounterpartEnchantment(world, enchantmentId);
            if (targetEnchantmentEntry != null) {
                target.addEnchantment(targetEnchantmentEntry, level);
            }
        });
    }

    private static RegistryEntry<Enchantment> getCounterpartEnchantment(ServerWorld world, String enchantmentId) {
        String targetEnchantmentId = ENCHANTMENT_CONVERSIONS.getOrDefault(enchantmentId, enchantmentId);
        return world.getRegistryManager()
                .getOrThrow(RegistryKeys.ENCHANTMENT)
                .getEntry(Identifier.tryParse(targetEnchantmentId))
                .orElse(null);
    }
}
