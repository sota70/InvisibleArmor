package com.invisiblearmor.damagecalculator

import com.invisiblearmor.InvisibleArmor
import com.invisiblearmor.armordata.armorloader.PlayerArmorPointLoader
import com.invisiblearmor.armordata.armorloader.PlayerArmorToughnessLoader
import com.invisiblearmor.armordata.armorloader.PlayerFireProtectionLoader
import org.bukkit.entity.Player
import kotlin.math.min

/**
 * 溶岩によって受けたダメージ軽減とFire Tick軽減をするクラス
 *
 * @property plugin プラグインのメインクラス
 * @property player ダメージを受けたプレイヤー
 * @property damage 受けたダメージ
 */
class LavaDamageCalculator(
    private val plugin: InvisibleArmor,
    private val player: Player,
    private val damage: Double
) : DamageCalculator {

    /**
     * Fire Protectionエンチャントを付けた装備を装備しているプレイヤーの
     * 溶岩によって受けたダメージを軽減した値を返すとともに
     * Fire Tickを軽減するメソッド
     *
     * @return 溶岩によって受けたダメージを軽減した値を[Double]型で返す
     */
    override fun calcFinalDamage(): Double {
        val playerFireProtection =
            PlayerFireProtectionLoader(plugin, player).fetchPlayerFireProtection()

        // 溶岩のダメージはアーマーで軽減できるので、アーマーダメージ軽減の計算をここでしている
        val damageReducedByArmor = fetchDamageReducedByArmor()
        val damageReduction = damageReducedByArmor * playerFireProtection
        return damageReducedByArmor - damageReduction
    }

    // アーマーによって軽減されたダメージを取得するメソッド
    private fun fetchDamageReducedByArmor(): Double {
        val playerArmorPoint = PlayerArmorPointLoader(plugin, player).fetchPlayerArmorPoint()
        val playerArmorToughness = PlayerArmorToughnessLoader(plugin, player).fetchPlayerArmorToughness()
        return damage * (1 - (min(20.0, (playerArmorPoint / 5.0).coerceAtLeast(playerArmorPoint - (4 * damage) / (playerArmorToughness + 8)))) / 25)
    }
}
