package com.invisiblearmor.armordata.armorloader

import com.invisiblearmor.InvisibleArmor
import com.invisiblearmor.util.ArmorIdentifier
import net.minecraft.server.v1_16_R3.EnumItemSlot
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player

class PlayerFireTickReductionLoader(
    plugin: InvisibleArmor,
    player: Player
) : PlayerArmorLoader(plugin, player) {

    fun fetchPlayerFireTickReduction(): Double {
        /*
        fire protectionがエンチャントされている装備がいくつかある場合
        その中で一番EPFが高いものを選ばなければならないため装備の中から一つだけ選んでいる
        */
        val fireTickReductionList = arrayOf(
            fetchHelmetFireTickReduction(),
            fetchChestplateFireTickReduction(),
            fetchLeggingsFireTickReduction(),
            fetchBootsFireTickReduction()
        )
        val highestFireTickReduction = fireTickReductionList.max() ?: 0.0
        return highestFireTickReduction / 100
    }

    private fun fetchHelmetFireTickReduction(): Double {
        val helmet = loadArmor(EnumItemSlot.HEAD)
        val armorIdentifier = ArmorIdentifier(helmet.type)
        if (!armorIdentifier.isItemHelmet()) return 0.0
        val fireProtectionEPF = helmet.getEnchantmentLevel(Enchantment.PROTECTION_FIRE)
        return fireProtectionEPF.toDouble() * 15
    }

    private fun fetchChestplateFireTickReduction(): Double {
        val chestplate = loadArmor(EnumItemSlot.CHEST)
        val armorIdentifier = ArmorIdentifier(chestplate.type)
        if (!armorIdentifier.isItemChestplate()) return 0.0
        val fireProtectionEPF = chestplate.getEnchantmentLevel(Enchantment.PROTECTION_FIRE)
        return fireProtectionEPF.toDouble() * 15
    }

    private fun fetchLeggingsFireTickReduction(): Double {
        val leggings = loadArmor(EnumItemSlot.LEGS)
        val armorIdentifier = ArmorIdentifier(leggings.type)
        if (!armorIdentifier.isItemLeggings()) return 0.0
        val fireProtectionEPF = leggings.getEnchantmentLevel(Enchantment.PROTECTION_FIRE)
        return fireProtectionEPF.toDouble() * 15
    }

    private fun fetchBootsFireTickReduction(): Double {
        val boots = loadArmor(EnumItemSlot.FEET)
        val armorIdentifier = ArmorIdentifier(boots.type)
        if (!armorIdentifier.isItemBoots()) return 0.0
        val fireProtectionEPF = boots.getEnchantmentLevel(Enchantment.PROTECTION_FIRE)
        return fireProtectionEPF.toDouble() * 15
    }
}
