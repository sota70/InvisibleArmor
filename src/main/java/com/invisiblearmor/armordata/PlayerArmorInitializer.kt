package com.invisiblearmor.armordata

import com.invisiblearmor.InvisibleArmor
import org.bukkit.entity.Player

class PlayerArmorInitializer(
    private val plugin: InvisibleArmor,
    private val player: Player
) {

    fun initPlayerArmorData() {
        if (isPlayerAlreadyInitialized()) return
        val playerArmorData = plugin.getPlayerArmorData()
        playerArmorData[player] = PlayerArmor()
    }

    private fun isPlayerAlreadyInitialized(): Boolean {
        val playerArmorData = plugin.getPlayerArmorData()
        return playerArmorData.containsKey(player)
    }
}
