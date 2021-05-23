package com.invisiblearmor.announcement

import org.bukkit.entity.Player

class DebugLogAnnounce(
    override val player: Player,
    private val playerInfo: String
) : Announce {

    override fun announce() {
        player.sendMessage("${player.displayName}'s info")
        player.sendMessage(playerInfo)
        println("${player.displayName}'s info")
        println(playerInfo)
    }
}
