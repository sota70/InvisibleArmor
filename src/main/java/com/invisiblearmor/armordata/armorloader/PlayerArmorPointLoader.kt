package com.invisiblearmor.armordata.armorloader

import com.invisiblearmor.InvisibleArmor
import com.invisiblearmor.util.ArmorIdentifier
import net.minecraft.server.v1_16_R3.EnumItemSlot
import org.bukkit.Material
import org.bukkit.entity.Player

/**
 * プレイヤーの保存しているアーマーからアーマーポイントを取得するクラス
 *
 * @property plugin プラグインのメインクラス
 * @property player 取得するアーマーを装備しているプレイヤー
 */
class PlayerArmorPointLoader(
    plugin: InvisibleArmor,
    player: Player
) : PlayerArmorLoader(plugin, player) {

    /**
     * プレイヤーの装備しているアーマーのアーマーポイントを取得してその合計を計算して返すメソッド
     *
     * @return アーマーポイントの合計値を[Double]型で返す
     */
    fun fetchPlayerArmorPoint(): Double {
        return fetchHelmetArmorPoint() +
            fetchChestplateArmorPoint() +
            fetchLeggingsArmorPoint() +
            fetchBootsArmorPoint()
    }

    // ヘルメットのアーマーポイントを取得するメソッド
    private fun fetchHelmetArmorPoint(): Double {
        val playerHelmet = loadArmor(EnumItemSlot.HEAD)
        val armorIdentifier = ArmorIdentifier(playerHelmet.type)
        if (!armorIdentifier.isItemHelmet()) return 0.0
        return when (playerHelmet.type) {
            Material.LEATHER_HELMET -> 1.0
            Material.CHAINMAIL_HELMET -> 2.0
            Material.IRON_HELMET -> 2.0
            Material.GOLDEN_HELMET -> 2.0
            Material.DIAMOND_HELMET -> 3.0
            else -> throw IllegalStateException("fetchHelmetArmorPointで予期できない例外が発生しました")
        }
    }

    // チェストプレートのアーマーポイントを取得するメソッド
    private fun fetchChestplateArmorPoint(): Double {
        val playerChestplate = loadArmor(EnumItemSlot.CHEST)
        val armorIdentifier = ArmorIdentifier(playerChestplate.type)
        if (!armorIdentifier.isItemChestplate()) return 0.0

        // デバッグ用
        // attributeModifiersがチェストプレートにそもそもない
        return when (playerChestplate.type) {
            Material.LEATHER_CHESTPLATE -> 3.0
            Material.CHAINMAIL_CHESTPLATE -> 5.0
            Material.IRON_CHESTPLATE -> 6.0
            Material.GOLDEN_CHESTPLATE -> 5.0
            Material.DIAMOND_CHESTPLATE -> 8.0
            else -> throw IllegalStateException("fetchHelmetArmorPointで予期できない例外が発生しました")
        }
    }

    // レギンスのアーマーポイントを取得するメソッド
    private fun fetchLeggingsArmorPoint(): Double {
        val playerLeggings = loadArmor(EnumItemSlot.LEGS)
        val armorIdentifier = ArmorIdentifier(playerLeggings.type)
        if (!armorIdentifier.isItemLeggings()) return 0.0
        return when (playerLeggings.type) {
            Material.LEATHER_LEGGINGS -> 2.0
            Material.CHAINMAIL_LEGGINGS -> 4.0
            Material.IRON_LEGGINGS -> 5.0
            Material.GOLDEN_LEGGINGS -> 3.0
            Material.DIAMOND_LEGGINGS -> 6.0
            else -> throw IllegalStateException("fetchHelmetArmorPointで予期できない例外が発生しました")
        }
    }

    // ブーツのアーマーポイントを取得するメソッド
    private fun fetchBootsArmorPoint(): Double {
        val playerBoots = loadArmor(EnumItemSlot.FEET)
        val armorIdentifier = ArmorIdentifier(playerBoots.type)
        if (!armorIdentifier.isItemBoots()) return 0.0
        return when (playerBoots.type) {
            Material.LEATHER_BOOTS -> 1.0
            Material.CHAINMAIL_BOOTS -> 1.0
            Material.IRON_BOOTS -> 2.0
            Material.GOLDEN_BOOTS -> 1.0
            Material.DIAMOND_BOOTS -> 3.0
            else -> throw IllegalStateException("fetchHelmetArmorPointで予期できない例外が発生しました")
        }
    }
}
