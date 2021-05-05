package com.invisiblearmor.damagecalculator

import com.invisiblearmor.InvisibleArmor
import com.invisiblearmor.armordata.PlayerArmorLoader
import com.invisiblearmor.util.ArmorIdentifier
import net.minecraft.server.v1_16_R3.EnumItemSlot
import org.bukkit.Material
import org.bukkit.entity.Player

/**
 * アーマーのアーマーポイントを取得してその合計を計算して返すクラス
 *
 * @property plugin プラグインのメインクラス
 * @property player 取得するアーマーを装備しているプレイヤー
 */
class ArmorPointCalculator(
    private val plugin: InvisibleArmor,
    private val player: Player
) {

    /**
     * プレイヤーの装備しているアーマーのアーマーポイントを取得してその合計を計算して返すメソッド
     *
     * @return アーマーポイントの合計値を[Double]型で返す
     */
    fun calcPlayerArmorPoint(): Double {
        val playerArmorLoader = PlayerArmorLoader(plugin, player)
        return fetchHelmetArmorPoint(playerArmorLoader) +
            fetchChestplateArmorPoint(playerArmorLoader) +
            fetchLeggingsArmorPoint(playerArmorLoader) +
            fetchBootsArmorPoint(playerArmorLoader)
    }

    // ヘルメットのアーマーポイントを取得するメソッド
    private fun fetchHelmetArmorPoint(playerArmorLoader: PlayerArmorLoader): Double {
        val playerHelmet = playerArmorLoader.loadArmor(EnumItemSlot.HEAD)
        val armorIdentifier = ArmorIdentifier()
        if (!armorIdentifier.isItemHelmet(playerHelmet.type)) return 0.0
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
    private fun fetchChestplateArmorPoint(playerArmorLoader: PlayerArmorLoader): Double {
        val playerChestplate = playerArmorLoader.loadArmor(EnumItemSlot.CHEST)
        val armorIdentifier = ArmorIdentifier()
        if (!armorIdentifier.isItemChestplate(playerChestplate.type)) return 0.0
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
    private fun fetchLeggingsArmorPoint(playerArmorLoader: PlayerArmorLoader): Double {
        val playerLeggings = playerArmorLoader.loadArmor(EnumItemSlot.LEGS)
        val armorIdentifier = ArmorIdentifier()
        if (!armorIdentifier.isItemLeggings(playerLeggings.type)) return 0.0
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
    private fun fetchBootsArmorPoint(playerArmorLoader: PlayerArmorLoader): Double {
        val playerBoots = playerArmorLoader.loadArmor(EnumItemSlot.FEET)
        val armorIdentifier = ArmorIdentifier()
        if (!armorIdentifier.isItemBoots(playerBoots.type)) return 0.0
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
