package com.invisiblearmor.armordata.armorloader

import com.invisiblearmor.InvisibleArmor
import com.invisiblearmor.util.ArmorIdentifier
import net.minecraft.server.v1_16_R3.EnumItemSlot
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player

class PlayerFireProtectionLoader(
    plugin: InvisibleArmor,
    player: Player
) : PlayerArmorLoader(plugin, player) {

    fun fetchPlayerFireProtection(): Double {
        val totalFireProtection = (
            fetchHelmetFireProtection() +
                fetchChestplateFireProtection() +
                fetchLeggingsFireProtection() +
                fetchBootsFireProtection()
            ) / 100
        if (totalFireProtection >= 0.8) return 0.8
        return totalFireProtection
    }

    private fun fetchHelmetFireProtection(): Double {
        val helmet = loadArmor(EnumItemSlot.HEAD)
        val armorIdentifier = ArmorIdentifier(helmet.type)
        if (!armorIdentifier.isItemHelmet()) return 0.0
        val fireProtectionEPF = helmet.getEnchantmentLevel(Enchantment.PROTECTION_FIRE)
        return fireProtectionEPF.toDouble() * 8
    }

    private fun fetchChestplateFireProtection(): Double {
        val chestplate = loadArmor(EnumItemSlot.CHEST)
        val armorIdentifier = ArmorIdentifier(chestplate.type)
        if (!armorIdentifier.isItemChestplate()) return 0.0
        val fireProtectionEPF = chestplate.getEnchantmentLevel(Enchantment.PROTECTION_FIRE)
        return fireProtectionEPF.toDouble() * 8
    }

    private fun fetchLeggingsFireProtection(): Double {
        val leggings = loadArmor(EnumItemSlot.LEGS)
        val armorIdentifier = ArmorIdentifier(leggings.type)
        if (!armorIdentifier.isItemLeggings()) return 0.0
        val fireProtectionEPF = leggings.getEnchantmentLevel(Enchantment.PROTECTION_FIRE)
        return fireProtectionEPF.toDouble() * 8
    }

    private fun fetchBootsFireProtection(): Double {
        val boots = loadArmor(EnumItemSlot.FEET)
        val armorIdentifier = ArmorIdentifier(boots.type)
        if (!armorIdentifier.isItemBoots()) return 0.0
        val fireProtectionEPF = boots.getEnchantmentLevel(Enchantment.PROTECTION_FIRE)
        return fireProtectionEPF.toDouble() * 8
    }
}