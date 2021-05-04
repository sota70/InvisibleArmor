package com.invisiblearmor.listener

import com.invisiblearmor.InvisibleArmor
import com.invisiblearmor.armordata.PlayerArmorInitializer
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class JoinListener(private val plugin: InvisibleArmor) : Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        val playerArmorDataInitializer = PlayerArmorInitializer(plugin, player)
        playerArmorDataInitializer.initPlayerArmorData()
    }
}
