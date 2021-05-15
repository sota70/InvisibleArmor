package com.invisiblearmor.damagecalculator

import com.invisiblearmor.InvisibleArmor
import com.invisiblearmor.armordata.armorloader.PlayerFeatherFallingProtectionLoader
import org.bukkit.entity.Player

/**
 * 落下ダメージを計算するクラス
 *
 * @property plugin プラグインのメインクラス
 * @property player ダメージを受けたプレイヤー
 * @property damage 受けたダメージ
 */
class FallDamageCalculator(
    private val plugin: InvisibleArmor,
    private val player: Player,
    private val damage: Double
) : DamageCalculator {

    /**
     * 落下ダメージを落下体制エンチャントによるダメージ軽減値から計算して最終ダメージを返すメソッド
     * [参考にしたサイト][https://minecraft.fandom.com/wiki/Feather_Falling]
     * 使った公式: finalDamage = damage - (damage * 12 * EPF / 100)
     *
     * @return 最終ダメージを[Double]型で返す
     */
    override fun calcFinalDamage(): Double {
        val playerFallingProtection =
            PlayerFeatherFallingProtectionLoader(plugin, player).fetchPlayerFeatherFallingProtection()
        val damageReduction = damage * playerFallingProtection
        return damage - damageReduction
    }
}
