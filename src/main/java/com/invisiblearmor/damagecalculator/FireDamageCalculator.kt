package com.invisiblearmor.damagecalculator

import com.invisiblearmor.InvisibleArmor
import com.invisiblearmor.armordata.armorloader.PlayerFireProtectionLoader
import org.bukkit.entity.Player

/**
 * 炎ダメージの計算クラス
 *
 * @property plugin プラグインのメインクラス
 * @property player ダメージを受けたプレイヤー
 * @property damage 受けたダメージ
 */
class FireDamageCalculator(
    private val plugin: InvisibleArmor,
    private val player: Player,
    private val damage: Double
) : DamageCalculator {

    /**
     * プレイヤーのFire Protection値からダメージを計算するメソッド
     *
     * @return 計算したダメージを[Double]型で返す
     */
    override fun calcFinalDamage(): Double {
        val playerFireProtection =
            PlayerFireProtectionLoader(plugin, player).fetchPlayerFireProtection()
        val damageReduction = damage * playerFireProtection
        return damage - damageReduction
    }
}
