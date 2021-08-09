package com.neoinvisiblearmor.util

import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack

/**
 * エンチャントのレベルと種類から適当なReduction値を返すクラス
 *
 * @param armor 値を取得したいアーマー
 * @param enchantType 取得したい値のエンチャントの種類
 */
class EnchantmentEPFConverter(
    private val armor: ItemStack,
    private val enchantType: Enchantment
) {

    private val epfLevels = hashMapOf(
        Enchantment.PROTECTION_ENVIRONMENTAL to arrayOf(1.0, 2.0, 3.0, 4.0),
        Enchantment.PROTECTION_FALL to arrayOf(3.0, 6.0, 9.0, 12.0),
        Enchantment.PROTECTION_EXPLOSIONS to arrayOf(2.0, 4.0, 6.0, 8.0),
        Enchantment.PROTECTION_FIRE to arrayOf(2.0, 4.0, 6.0, 8.0),
        Enchantment.PROTECTION_PROJECTILE to arrayOf(2.0, 4.0, 6.0, 8.0)
    )

    /**
     * 各エンチャント、エンチャントレベルに適したepf値を[epfLevels]から取ってきて返すメソッド
     *
     * @return 各エンチャント、エンチャントレベルに適したepf値を[Double]型で返す
     */
    fun convert(): Double {
        val enchantmentLevel = armor.getEnchantmentLevel(enchantType)
        if (enchantmentLevel == 0 || enchantmentLevel > 4) return 0.0
        return epfLevels[enchantType]?.get(enchantmentLevel - 1) ?: 0.0
    }
}
