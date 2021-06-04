package com.invisiblearmor.damagecalculator

import com.invisiblearmor.InvisibleArmor
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageEvent.DamageCause

/**
 * プレイヤーの受けたダメージの種類によって適当な[DamageCalculator]のインスタンスを渡してあげるクラス
 *
 * @property plugin プラグインのメインクラス
 * @property player ダメージを受けたプレイヤープレイヤー
 * @property damage 受けたダメージ
 */
class DamageCalculatorFactory(
    private val plugin: InvisibleArmor,
    private val player: Player,
    private val damage: Double
) {

    /**
     * プレイヤーの受けたダメージの種類によって適当な[DamageCalculator]のインスタンスを渡すメソッド
     *
     * @param damageType 受けたダメージの種類
     * @return 受けたダメージの種類に合った適当な[DamageCalculator]を返す
     */
    fun createCalculator(damageType: DamageCause): DamageCalculator {
        return when (damageType) {
            DamageCause.ENTITY_ATTACK -> NormalDamageCalculator(plugin, player, damage)
            DamageCause.FALL -> FallDamageCalculator(plugin, player, damage)
            DamageCause.LAVA -> LavaDamageCalculator(plugin, player, damage)
            DamageCause.FIRE -> FireDamageCalculator(plugin, player, damage)
            DamageCause.FIRE_TICK -> FireDamageCalculator(plugin, player, damage)
            DamageCause.BLOCK_EXPLOSION -> ExplosionDamageCalculator(plugin, player, damage)
            DamageCause.ENTITY_EXPLOSION -> ExplosionDamageCalculator(plugin, player, damage)
            else -> throw IllegalStateException("予期せぬ例外が発生しました")
        }
    }
}
