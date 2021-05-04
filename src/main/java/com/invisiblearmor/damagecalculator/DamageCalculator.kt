package com.invisiblearmor.damagecalculator

import com.invisiblearmor.InvisibleArmor
import org.bukkit.entity.Player
import kotlin.math.min

/**
 * ダメージを計算するクラス
 *
 * @property player 計算対象のプレイヤー
 * @property damage 受けたダメージ
 */
class DamageCalculator(
    private val plugin: InvisibleArmor,
    private val player: Player,
    private val damage: Double
) {

    /**
     * ダメージ軽減値を用いて最終的なダメージ値を計算するメソッド
     * [参考にしたサイト][https://minecraft.fandom.com/wiki/Talk:Armor]
     * 参考にした公式: (25 - Armor value) / 25 * damage
     *
     * @return 最終的なダメージ値を[Double]型で返す
     */
    fun calcFinalDamage(): Double {
        val playerArmorPoint = ArmorPointCalculator(plugin, player).calcPlayerArmorPoint().toInt()
        val finalDamage =
            damage * (1 - (min(20.0, (playerArmorPoint / 5.0).coerceAtLeast(playerArmorPoint - (4 * damage) / 8))) / 25)

        // デバッグ用
        println("Total Player ArmorPoint: $playerArmorPoint")
        return finalDamage
    }
}
