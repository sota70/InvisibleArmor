package com.invisiblearmor.util

import org.bukkit.Material

/**
 * アーマーの種類を識別するクラス
 */
class ArmorIdentifier {

    /**
     * 渡された[type]の種類がヘルメットかどうか確認するメソッド
     *
     * @param type アイテムの種類
     * @return ヘルメットだった場合にtrue、そうでない場合はfalse
     */
    fun isItemHelmet(type: Material?): Boolean {
        val helmet = arrayOf(
            Material.LEATHER_HELMET,
            Material.CHAINMAIL_HELMET,
            Material.IRON_HELMET,
            Material.GOLDEN_HELMET,
            Material.DIAMOND_HELMET
        )
        if (type == null) return false
        return type in helmet
    }

    /**
     * 渡された[type]の種類がチェストプレートかどうか確認するメソッド
     *
     * @param type アイテムの種類
     * @return チェストプレートだった場合にtrue、そうでない場合はfalse
     */
    fun isItemChestplate(type: Material?): Boolean {
        val chestplate = arrayOf(
            Material.LEATHER_CHESTPLATE,
            Material.CHAINMAIL_CHESTPLATE,
            Material.IRON_CHESTPLATE,
            Material.GOLDEN_CHESTPLATE,
            Material.DIAMOND_CHESTPLATE
        )
        if (type == null) return false
        return type in chestplate
    }

    /**
     * 渡された[type]の種類がレギンスかどうか確認するメソッド
     *
     * @param type アイテムの種類
     * @return レギンスだった場合にtrue、そうでない場合はfalse
     */
    fun isItemLeggings(type: Material?): Boolean {
        val leggings = arrayOf(
            Material.LEATHER_LEGGINGS,
            Material.CHAINMAIL_LEGGINGS,
            Material.IRON_LEGGINGS,
            Material.GOLDEN_LEGGINGS,
            Material.DIAMOND_LEGGINGS
        )
        if (type == null) return false
        return type in leggings
    }

    /**
     * 渡された[type]の種類がブーツかどうか確認するメソッド
     *
     * @param type アイテムの種類
     * @return ブーツだった場合にtrue、そうでない場合はfalse
     */
    fun isItemBoots(type: Material?): Boolean {
        val boots = arrayOf(
            Material.LEATHER_BOOTS,
            Material.CHAINMAIL_BOOTS,
            Material.IRON_BOOTS,
            Material.GOLDEN_BOOTS,
            Material.DIAMOND_BOOTS
        )
        if (type == null) return false
        return type in boots
    }
}
