package com.neoinvisiblearmor.listener

import com.neoinvisiblearmor.NeoInvisibleArmor
import com.neoinvisiblearmor.armordata.loader.ArmorEPFLoader
import com.neoinvisiblearmor.armordata.loader.PlayerArmorLoader
import com.neoinvisiblearmor.event.PlayerDamagedByAreaCloudEffectEvent
import com.neoinvisiblearmor.util.DamageCalculator
import net.minecraft.server.v1_16_R3.EnumItemSlot
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent.DamageCause

class PlayerTakeDamageByAreaEffectCloudListener(private val plugin: NeoInvisibleArmor) : Listener {

    @EventHandler
    fun onTakingDamage(event: PlayerDamagedByAreaCloudEffectEvent) {
        val player = event.player
        val armorEPF = ArmorEPFLoader(plugin, player, event.damageType).fetchTotalEPF()
        val itemOnPlayerHead = PlayerArmorLoader(plugin, player).loadArmor(EnumItemSlot.HEAD).type
        val finalDamage = calcFinalDamage(event.takenDamage, armorEPF, event.damageType, itemOnPlayerHead)
        sendPlayerDamageInfo(player, event.damageType, event.takenDamage, finalDamage)
        player.lastDamage = finalDamage
    }

    /*
    最終的なダメージをDamageCalculatorを使って計算して、返すメソッド
     */
    private fun calcFinalDamage(
        takenDamage: Double,
        playerArmorEPF: Double,
        damageType: DamageCause,
        itemOnPlayerHead: Material
    ): Double {

        // AreaCloudEffectはアーマーによるダメージ軽減が無効
        val playerArmorPoint = 0.0
        val playerArmorToughness = 0.0
        return DamageCalculator()
            .setTakenDamage(takenDamage)
            .setArmorPoint(playerArmorPoint)
            .setArmorEPF(playerArmorEPF)
            .setArmorToughness(playerArmorToughness)
            .setDamageType(damageType)
            .setItemOnPlayerHead(itemOnPlayerHead)
            .calcFinalDamage()
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
