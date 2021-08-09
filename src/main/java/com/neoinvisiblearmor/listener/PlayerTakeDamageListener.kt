package com.neoinvisiblearmor.listener

import com.neoinvisiblearmor.NeoInvisibleArmor
import com.neoinvisiblearmor.armordata.loader.ArmorEPFLoader
import com.neoinvisiblearmor.armordata.loader.ArmorPointLoader
import com.neoinvisiblearmor.armordata.loader.ArmorToughnessLoader
import com.neoinvisiblearmor.armordata.loader.PlayerArmorLoader
import com.neoinvisiblearmor.util.DamageCalculator
import net.minecraft.server.v1_16_R3.EnumItemSlot
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityDamageEvent.DamageCause

/**
 * ArmorGUIに保存してあるアーマーのデータからダメージを計算しなおす用のリスナークラス
 *
 * @property plugin プラグインのメインクラス
 **/
class PlayerTakeDamageListener(private val plugin: NeoInvisibleArmor) : Listener {

    /**
     * ArmorGUIに保存してあるアーマーのデータからダメージを計算しなおして
     * FinalDamageとしてセットするメソッド
     *
     * @param event エンティティがダメージを受けたときに呼び出されるイベント
     **/
    @EventHandler
    fun onTakingDamage(event: EntityDamageEvent) {
        if (event.entity !is Player) return

        // ENTITY_ATTACKはDRAGON_BREATHダメージイベントを処理するために、別々で処理している
        if (event.cause == DamageCause.ENTITY_ATTACK) return
        val player = event.entity as Player
        val playerArmorPoint = ArmorPointLoader(plugin, player, event.cause).fetchTotalPoint()
        val playerArmorEPF = ArmorEPFLoader(plugin, player, event.cause).fetchTotalEPF()
        val playerToughness = ArmorToughnessLoader(plugin, player).fetchTotalToughness()
        val takenDamage = event.damage
        val itemOnPlayerHead = PlayerArmorLoader(plugin, player).loadArmor(EnumItemSlot.HEAD).type
        event.damage = DamageCalculator()
            .setTakenDamage(takenDamage)
            .setArmorPoint(playerArmorPoint)
            .setArmorEPF(playerArmorEPF)
            .setArmorToughness(playerToughness)
            .setDamageType(event.cause)
            .setItemOnPlayerHead(itemOnPlayerHead)
            .calcFinalDamage()
        sendPlayerDamageInfo(player, event.cause, takenDamage, event.damage)
    }

    /*
    プレイヤーとコンソールに対して、受けたダメージの種類・受けたダメージ量・最終的なダメージ量を送るメソッド
     */
    private fun sendPlayerDamageInfo(
        player: Player,
        damageType: DamageCause,
        takenDamage: Double,
        finalDamage: Double
    ) {
        println("DamageType: $damageType")
        println("TakenDamage: $takenDamage")
        println("FinalDamage: $finalDamage")
        player.sendMessage("DamageType: $damageType")
        player.sendMessage("TakenDamage: $takenDamage")
        player.sendMessage("FinalDamage: $finalDamage")
    }
}
