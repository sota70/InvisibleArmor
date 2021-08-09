package com.neoinvisiblearmor.armordata.loader

import com.neoinvisiblearmor.NeoInvisibleArmor
import com.neoinvisiblearmor.armordata.STACKABLE_ARMOR_ENCHANTMENTS
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
        return if (totalLevel == 0) {
            fetchLevelFromArmorContent()
        } else {
            totalLevel
        }
    }

    private fun fetchLevelFromArmorGUI(): Int {
        val helmetLevel = fetchLevelFromArmorGUI(EnumItemSlot.HEAD)
        val chestplateLevel = fetchLevelFromArmorGUI(EnumItemSlot.CHEST)
        val leggingsLevel = fetchLevelFromArmorGUI(EnumItemSlot.LEGS)
        val bootsLevel = fetchLevelFromArmorGUI(EnumItemSlot.FEET)
        if (isEnchantStackable()) return helmetLevel +
            chestplateLevel +
            leggingsLevel +
            bootsLevel
        return arrayOf(helmetLevel, chestplateLevel, leggingsLevel, bootsLevel).max() ?: 0
    }

    private fun fetchLevelFromArmorGUI(armorType: EnumItemSlot): Int =
        loadArmor(armorType).getEnchantmentLevel(enchantmentType)

    private fun fetchLevelFromArmorContent(): Int {
        val helmetLevel = fetchLevelFromArmorContent(EnumItemSlot.HEAD)
        val chestplateLevel = fetchLevelFromArmorContent(EnumItemSlot.CHEST)
        val leggingsLevel = fetchLevelFromArmorContent(EnumItemSlot.LEGS)
        val bootsLevel = fetchLevelFromArmorContent(EnumItemSlot.FEET)
        if (isEnchantStackable()) return helmetLevel +
            chestplateLevel +
            leggingsLevel +
            bootsLevel
        return arrayOf(helmetLevel, chestplateLevel, leggingsLevel, bootsLevel).max() ?: 0
    }

    private fun fetchLevelFromArmorContent(armorType: EnumItemSlot): Int = when (armorType) {
        EnumItemSlot.HEAD -> player.inventory.helmet?.getEnchantmentLevel(enchantmentType) ?: 0
        EnumItemSlot.CHEST -> player.inventory.chestplate?.getEnchantmentLevel(enchantmentType) ?: 0
        EnumItemSlot.LEGS -> player.inventory.leggings?.getEnchantmentLevel(enchantmentType) ?: 0
        EnumItemSlot.FEET -> player.inventory.boots?.getEnchantmentLevel(enchantmentType) ?: 0
        else -> 0
    }

    private fun isEnchantStackable(): Boolean = enchantmentType in STACKABLE_ARMOR_ENCHANTMENTS
}
