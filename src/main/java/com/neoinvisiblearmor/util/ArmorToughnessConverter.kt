package com.neoinvisiblearmor.util

import org.bukkit.Material

class ArmorToughnessConverter(private val armorType: Material) {

    fun convert(): Double {
        val diamondArmor = arrayOf(
            Material.DIAMOND_HELMET,
            Material.DIAMOND_CHESTPLATE,
            Material.DIAMOND_LEGGINGS,
            Material.DIAMOND_BOOTS
        )
        val netheriteArmor = arrayOf(
            Material.NETHERITE_HELMET,
            Material.NETHERITE_CHESTPLATE,
            Material.NETHERITE_LEGGINGS,
            Material.NETHERITE_BOOTS
        )
        if (armorType in diamondArmor) return 2.0
        if (armorType in netheriteArmor) return 3.0
        return 0.0
    }
}
