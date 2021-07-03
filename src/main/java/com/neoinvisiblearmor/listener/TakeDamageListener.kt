package com.neoinvisiblearmor.listener

import com.neoinvisiblearmor.NeoInvisibleArmor
import com.neoinvisiblearmor.armordata.armorloader.ArmorEPFLoader
import com.neoinvisiblearmor.armordata.armorloader.ArmorPointLoader
import com.neoinvisiblearmor.armordata.armorloader.ArmorToughnessLoader
import com.neoinvisiblearmor.util.DamageCalculator
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent

/**
 * ArmorGUIに保存してあるアーマーのデータからダメージを計算しなおす用のリスナークラス
 *
 * @property plugin プラグインのメインクラス
 */
class TakeDamageListener(
    private val plugin: NeoInvisibleArmor
) : Listener {

    /**
     * ArmorGUIに保存してあるアーマーのデータからダメージを計算しなおして
     * FinalDamageとしてセットするメソッド
     *
     * @param event エンティティがダメージを受けたときに呼び出されるイベント
     **/
    @EventHandler
    fun onTakingDamage(event: EntityDamageEvent) {
        if (event.entity !is Player) return
        val player = event.entity as Player
        val takenDamage = event.damage
        val playerArmorPoint =
            ArmorPointLoader(plugin, player, event.cause).fetchTotalPoint()
        val playerArmorEPF =
            ArmorEPFLoader(plugin, player, event.cause).fetchTotalEPF()
        val playerToughness =
            ArmorToughnessLoader(plugin, player).fetchTotalToughness()
        val damageCalculator =
            DamageCalculator(takenDamage, playerArmorPoint, playerArmorEPF, playerToughness)

        // エンチャントがついたアーマーとついてないアーマーで計算式が違うので、if文で分ける必要がある
        val finalDamage = if (playerArmorEPF == 0.0) {
            damageCalculator.calcFinalDamageWithoutEPF()
        } else {
            damageCalculator.calcFinalDamageWithEPF()
        }
        event.damage = finalDamage

        // Debug
        player.sendMessage("TakenDamage: $takenDamage")
        player.sendMessage("FinalDamage: ${event.damage}")
    }
}
