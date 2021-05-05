package com.invisiblearmor.listener

import com.invisiblearmor.InvisibleArmor
import com.invisiblearmor.damagecalculator.DamageCalculator
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent

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
        val finalDamage = DamageCalculator(plugin, player, takenDamage).calcFinalDamage()
        event.damage = finalDamage

        // デバッグ用
        println("Taken Damage: $takenDamage")
        println("Final Damage: $finalDamage")
    }
}
