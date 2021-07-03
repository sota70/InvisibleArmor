package com.neoinvisiblearmor.util

import org.bukkit.Material

/**
 * アーマーの種類からアーマーポイントに変換するクラス
 *
 * @property armorType アーマーの種類
 */
class ArmorPointConverter(
    private val armorType: Material
) {

    private val armorPoint = hashMapOf(
        Material.LEATHER_HELMET to 1.0,
        Material.LEATHER_CHESTPLATE to 3.0,
        Material.LEATHER_LEGGINGS to 2.0,
        Material.LEATHER_BOOTS to 1.0,
        Material.CHAINMAIL_HELMET to 2.0,
        Material.CHAINMAIL_CHESTPLATE to 5.0,
        Material.CHAINMAIL_LEGGINGS to 4.0,
        Material.CHAINMAIL_BOOTS to 1.0,
        Material.IRON_HELMET to 2.0,
        Material.IRON_CHESTPLATE to 6.0,
        Material.IRON_LEGGINGS to 5.0,
        Material.IRON_BOOTS to 2.0,
        Material.DIAMOND_HELMET to 3.0,
        Material.DIAMOND_CHESTPLATE to 8.0,
        Material.DIAMOND_LEGGINGS to 6.0,
        Material.DIAMOND_BOOTS to 3.0,
        Material.GOLDEN_HELMET to 2.0,
        Material.GOLDEN_CHESTPLATE to 5.0,
        Material.GOLDEN_LEGGINGS to 3.0,
        Material.GOLDEN_BOOTS to 1.0
    )

    /**
     * アーマーの種類からアーマーポイントに変換するメソッド
     * もしも渡された種類が装備ではなかった場合、0.0を返す
     *
     * @return 返還したアーマーポイントを[Double]型で返す
     */
    fun convert(): Double =
        armorPoint[armorType] ?: 0.0
}
