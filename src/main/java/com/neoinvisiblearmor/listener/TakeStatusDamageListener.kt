package com.neoinvisiblearmor.listener

import com.neoinvisiblearmor.NeoInvisibleArmor
import com.neoinvisiblearmor.event.statusevent.PlayerBurnEvent
import com.neoinvisiblearmor.event.statusevent.PlayerStatusDamageEvent
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.EntityDamageEvent.DamageCause

class TakeStatusDamageListener(private val plugin: NeoInvisibleArmor) : Listener {

    @EventHandler
    fun onTakingStatusDamage(event: EntityDamageEvent) {
        if (event.entity !is Player) return
        val player = event.entity as Player
        when (event.cause) {
            DamageCause.FIRE, DamageCause.LAVA -> sendPlayerEvent(PlayerBurnEvent(player))
            else -> println("This damage type is unknown")
        }
    }

    private fun sendPlayerEvent(event: PlayerStatusDamageEvent) {
        Bukkit.getPluginManager().callEvent(event)
        if (event.isCancelled) return
    }
}
