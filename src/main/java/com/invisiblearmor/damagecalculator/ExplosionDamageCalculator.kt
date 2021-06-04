package com.invisiblearmor.damagecalculator

import com.invisiblearmor.InvisibleArmor
import com.invisiblearmor.armordata.armorloader.PlayerArmorPointLoader
import com.invisiblearmor.armordata.armorloader.PlayerArmorToughnessLoader
import com.invisiblearmor.armordata.armorloader.PlayerExplosionProtectionLoader
import org.bukkit.entity.Player
import kotlin.math.min

/**
 * ブロックによる爆発ダメージを計算するクラス
 *
 * @property plugin プラグインのメインクラス
 * @property player ダメージを受けたプレイヤー
 * @property damage 受けたダメージ
 */
class ExplosionDamageCalculator(
    private val plugin: InvisibleArmor,
    private val player: Player,
    private val damage: Double
) : DamageCalculator {

    /**
     * 爆発ダメージをプレイヤーのBlast Protection値とプレイヤーのアーマー値から計算するメソッド
     *
     * @return 計算された値を[Double]型で返す
     */
    override fun calcFinalDamage(): Double {
        val playerExplosionProtection =
            PlayerExplosionProtectionLoader(plugin, player).fetchPlayerExplosionProtection()

        // 爆発ダメージはアーマーポイントによって軽減されるので、その処理も含む
        val damageReducedByArmor = fetchDamageReducedByArmor()
        val damageReduction = damageReducedByArmor * playerExplosionProtection
        return damageReducedByArmor - damageReduction
    }

    // アーマーによって軽減されたダメージを取得するメソッド
    private fun fetchDamageReducedByArmor(): Double {
        val playerArmorPoint = PlayerArmorPointLoader(plugin, player).fetchPlayerArmorPoint()
        val playerArmorToughness = PlayerArmorToughnessLoader(plugin, player).fetchPlayerArmorToughness()
        return damage * (1 - (min(20.0, (playerArmorPoint / 5.0).coerceAtLeast(playerArmorPoint - (4 * damage) / (playerArmorToughness + 8)))) / 25)
    }
}
