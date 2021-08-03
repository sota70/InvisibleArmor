package com.neoinvisiblearmor.armordata.armorloader

import com.neoinvisiblearmor.NeoInvisibleArmor
import net.minecraft.server.v1_16_R3.EnumItemSlot
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player

class ArmorEnchantmentLevelLoader(
    plugin: NeoInvisibleArmor,
    private val player: Player,
    private val enchantmentType: Enchantment
) : PlayerArmorLoader(plugin, player) {

    fun fetchTotalLevel(): Int {
        val totalLevel = fetchLevelFromArmorGUI()
        if (totalLevel == 0) return fetchLevelFromArmorContent()
        return totalLevel
    }

    private fun fetchLevelFromArmorGUI(): Int {
        val helmetLevel =
            loadArmor(EnumItemSlot.HEAD).getEnchantmentLevel(enchantmentType)
        val chestplateLevel =
            loadArmor(EnumItemSlot.CHEST).getEnchantmentLevel(enchantmentType)
        val leggingsLevel =
            loadArmor(EnumItemSlot.LEGS).getEnchantmentLevel(enchantmentType)
        val bootsLevel =
            loadArmor(EnumItemSlot.FEET).getEnchantmentLevel(enchantmentType)
        if (isEnchantStackable()) return helmetLevel +
            chestplateLevel +
            leggingsLevel +
            bootsLevel
        return arrayOf(helmetLevel, chestplateLevel, leggingsLevel, bootsLevel).max() ?: 0
    }

    private fun fetchLevelFromArmorContent(): Int {
        val helmetLevel =
            player.inventory.helmet?.getEnchantmentLevel(enchantmentType) ?: 0
        val chestplateLevel =
            player.inventory.chestplate?.getEnchantmentLevel(enchantmentType) ?: 0
        val leggingsLevel =
            player.inventory.leggings?.getEnchantmentLevel(enchantmentType) ?: 0
        val bootsLevel =
            player.inventory.boots?.getEnchantmentLevel(enchantmentType) ?: 0
        if (isEnchantStackable()) return helmetLevel +
            chestplateLevel +
            leggingsLevel +
            bootsLevel
        return arrayOf(helmetLevel, chestplateLevel, leggingsLevel, bootsLevel).max() ?: 0
    }

    private fun isEnchantStackable(): Boolean = when (enchantmentType) {
        Enchantment.PROTECTION_FIRE -> false
        else -> true
    }
}
