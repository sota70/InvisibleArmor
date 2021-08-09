package com.neoinvisiblearmor.listener

import com.neoinvisiblearmor.event.PlayerHitByAnvilEvent
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class PlayerHitByAnvilListener : Listener {

    @EventHandler
    fun onHittingByAnvil(event: PlayerHitByAnvilEvent) {
        if (event.helmetType == Material.AIR) return
        val player = event.player
        val helmetDamageReduction = 0.25
        player.lastDamage -= player.lastDamage * helmetDamageReduction
    }
}
