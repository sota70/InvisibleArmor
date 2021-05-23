package com.invisiblearmor.listener

import com.invisiblearmor.InvisibleArmor
import com.invisiblearmor.damagecalculator.DamageCalculatorFactory
import com.invisiblearmor.playerstatusupdater.StatusUpdaterFactory
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityDamageEvent.DamageCause

/**
 * ダメージを受けたことを感知するリスナークラス
 */
class TakeDamageListener(private val plugin: InvisibleArmor) : Listener {

    /**
     * ダメージを受けた時にArmorGUIにあるアーマーのアーマー値を持ってきて
     * 装備を着ている時と同じダメージに計算しなおすメソッド
     *
     * @param event ダメージを受けたことを感知するイベント
     */
    @EventHandler
    fun onTakeDamage(event: EntityDamageEvent) {
        if (event.entity !is Player) return
        val player = event.entity as Player
        val takenDamage = event.finalDamage
        val finalDamage = calcFinalDamage(player, takenDamage, event.cause)
        event.damage = finalDamage
        updatePlayerStatus(player, event.cause)

        // デバッグ用
        println("Taken Damage: $takenDamage")
        println("Final Damage: $finalDamage")
    }

    // ArmorGUIにあるアーマーのアーマー値からFinal Damageを計算して返すメソッド
    private fun calcFinalDamage(
        player: Player,
        takenDamage: Double,
        damageType: DamageCause
    ): Double {
        val damageCalculator =
            DamageCalculatorFactory(plugin, player, takenDamage).createCalculator(damageType)
        return damageCalculator.calcFinalDamage()
    }

    // プレイヤーの状態を更新するメソッド
    private fun updatePlayerStatus(
        player: Player,
        damageType: DamageCause
    ) {
        val playerStatusUpdater = StatusUpdaterFactory(plugin).createStatusUpdater(damageType)
        playerStatusUpdater.updateStatus(player)
    }
}
