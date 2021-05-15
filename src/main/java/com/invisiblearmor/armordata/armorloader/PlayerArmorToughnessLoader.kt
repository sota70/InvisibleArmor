package com.invisiblearmor.armordata.armorloader

import com.invisiblearmor.InvisibleArmor
import com.invisiblearmor.util.ArmorIdentifier
import net.minecraft.server.v1_16_R3.EnumItemSlot
import org.bukkit.Material
import org.bukkit.entity.Player

/**
 * プレイヤーの保存しているアーマーからToughness値を取得するクラス
 *
 * @property plugin プラグインのメインクラス
 * @property player 値を取得する対象のプレイヤー
 */
class PlayerArmorToughnessLoader(
    plugin: InvisibleArmor,
    player: Player
) : PlayerArmorLoader(plugin, player) {

    /**
     * プレイヤーの保存しているアーマーからToughness値を取得するメソッド
     *
     * @return 各アーマーのToughness値の合計を[Double]型で返す
     */
    fun fetchPlayerArmorToughness(): Double {
        return fetchHelmetToughness() +
            fetchChestplateToughness() +
            fetchLeggingsToughness() +
            fetchBootsToughness()
    }

    // ヘルメットのToughness値を取得するメソッド
    private fun fetchHelmetToughness(): Double {
        val playerHelmet = loadArmor(EnumItemSlot.HEAD)
        val armorIdentifier = ArmorIdentifier(playerHelmet.type)
        if (armorIdentifier.isItemHelmet()) return 0.0
        return when (playerHelmet.type) {
            Material.DIAMOND_HELMET -> 2.0
            Material.NETHERITE_HELMET -> 3.0
            else -> 0.0
        }
    }

    // チェストプレートのToughness値を取得するメソッド
    private fun fetchChestplateToughness(): Double {
        val playerChestplate = loadArmor(EnumItemSlot.CHEST)
        val armorIdentifier = ArmorIdentifier(playerChestplate.type)
        if (armorIdentifier.isItemChestplate()) return 0.0
        return when (playerChestplate.type) {
            Material.DIAMOND_CHESTPLATE -> 2.0
            Material.NETHERITE_CHESTPLATE -> 3.0
            else -> 0.0
        }
    }

    // レギンスのToughness値を取得するメソッド
    private fun fetchLeggingsToughness(): Double {
        val playerLeggings = loadArmor(EnumItemSlot.LEGS)
        val armorIdentifier = ArmorIdentifier(playerLeggings.type)
        if (armorIdentifier.isItemLeggings()) return 0.0
        return when (playerLeggings.type) {
            Material.DIAMOND_LEGGINGS -> 2.0
            Material.NETHERITE_LEGGINGS -> 3.0
            else -> 0.0
        }
    }

    // ブーツのToughness値を取得するメソッド
    private fun fetchBootsToughness(): Double {
        val playerBoots = loadArmor(EnumItemSlot.FEET)
        val armorIdentifier = ArmorIdentifier(playerBoots.type)
        if (armorIdentifier.isItemBoots()) return 0.0
        return when (playerBoots.type) {
            Material.DIAMOND_BOOTS -> 2.0
            Material.NETHERITE_BOOTS -> 3.0
            else -> 0.0
        }
    }
}
