package com.invisiblearmor.damagecalculator

import com.invisiblearmor.InvisibleArmor
import com.invisiblearmor.armordata.armorloader.PlayerArmorPointLoader
import com.invisiblearmor.armordata.armorloader.PlayerArmorToughnessLoader
import org.bukkit.entity.Player
import kotlin.math.min

/**
 * ダメージを計算するクラス
 *
 * @property player 計算対象のプレイヤー
 * @property damage 受けたダメージ
 */
class NormalDamageCalculator(
    private val plugin: InvisibleArmor,
    private val player: Player,
    private val damage: Double
) : DamageCalculator {

    /**
     * ダメージ軽減値を用いて最終的なダメージ値を計算するメソッド
     * [参考にしたサイト][https://minecraft.fandom.com/wiki/Armor]
     * 使った公式: マイクラのダメージ公式をそのまま採用
     *
     * @return 最終的なダメージ値を[Double]型で返す
     */
    override fun calcFinalDamage(): Double {
        val playerArmorPoint = PlayerArmorPointLoader(plugin, player).fetchPlayerArmorPoint()
        val playerArmorToughness = PlayerArmorToughnessLoader(plugin, player).fetchPlayerArmorToughness()
        val finalDamage =
            damage * (1 - (min(20.0, (playerArmorPoint / 5.0).coerceAtLeast(playerArmorPoint - (4 * damage) / (playerArmorToughness + 8)))) / 25)

        // デバッグ用
        println("Total Player ArmorPoint: $playerArmorPoint")
        return finalDamage
    }
}
