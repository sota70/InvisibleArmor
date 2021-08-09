package com.neoinvisiblearmor.listener

import com.neoinvisiblearmor.NeoInvisibleArmor
import com.neoinvisiblearmor.armordata.loader.ArmorEPFLoader
import com.neoinvisiblearmor.armordata.loader.ArmorPointLoader
import com.neoinvisiblearmor.armordata.loader.ArmorToughnessLoader
import com.neoinvisiblearmor.armordata.loader.PlayerArmorLoader
import com.neoinvisiblearmor.event.PlayerDamagedByAreaCloudEffectEvent
import com.neoinvisiblearmor.util.DamageCalculator
import net.minecraft.server.v1_16_R3.EnumItemSlot
import org.bukkit.Bukkit
import org.bukkit.entity.AreaEffectCloud
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent.DamageCause

class PlayerTakeDamageByEntityListener(private val plugin: NeoInvisibleArmor) : Listener {

    @EventHandler
    fun onTakingDamageByEntity(event: EntityDamageByEntityEvent) {
        if (event.cause != DamageCause.ENTITY_ATTACK) return
        if (event.entity !is Player) return
        val player = event.entity as Player
        if (entrustEventIfAreaCloudEffect(event.damager, player, event.damage, event.cause)) return
        val armorPoint = ArmorPointLoader(plugin, player, event.cause).fetchTotalPoint()
        val armorEPF = ArmorEPFLoader(plugin, player, event.cause).fetchTotalEPF()
        val armorToughness = ArmorToughnessLoader(plugin, player).fetchTotalToughness()
        val itemOnPlayerHead = PlayerArmorLoader(plugin, player).loadArmor(EnumItemSlot.HEAD).type
        val finalDamage = DamageCalculator()
            .setTakenDamage(event.damage)
            .setArmorPoint(armorPoint)
            .setArmorEPF(armorEPF)
            .setArmorToughness(armorToughness)
            .setDamageType(event.cause)
            .setItemOnPlayerHead(itemOnPlayerHead)
            .calcFinalDamage()
        sendPlayerDamageInfo(player, event.cause, event.damage, finalDamage)
        event.damage = finalDamage
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

    private fun entrustEventIfAreaCloudEffect(
        damager: Entity,
        player: Player,
        takenDamage: Double,
        damageType: DamageCause
    ): Boolean {
        if (damager !is AreaEffectCloud) return false
        val event = PlayerDamagedByAreaCloudEffectEvent(player, takenDamage, damageType)
        Bukkit.getPluginManager().callEvent(event)
        if (event.isCancelled) return true
        return true
    }
}
