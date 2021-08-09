package com.neoinvisiblearmor.listener

import com.neoinvisiblearmor.NeoInvisibleArmor
import com.neoinvisiblearmor.armordata.loader.ArmorEnchantmentLevelLoader
import com.neoinvisiblearmor.event.statusevent.PlayerBurnEvent
import com.neoinvisiblearmor.tag.PlayerOnFireTag
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class PlayerBurnListener(private val plugin: NeoInvisibleArmor) : Listener {

    @EventHandler
    fun onBurning(event: PlayerBurnEvent) {

        // Debug
        println("Refactored Previous FireTick: ${event.player.fireTicks}")
        val player = event.player
        val fireTickReduction = calcFireTickReduction(player)
        val playerOnFireTag = PlayerOnFireTag(plugin, player) {
            player.fireTicks -= (player.fireTicks * fireTickReduction).toInt()

            // Debug
            println("Refactored After FireTick: ${player.fireTicks}")
        }
        playerOnFireTag.putFireTag()
    }

    private fun calcFireTickReduction(player: Player): Double {
        val fireProtLevel =
            ArmorEnchantmentLevelLoader(plugin, player, Enchantment.PROTECTION_FIRE).fetchTotalLevel()
        val reductionCoefficient = 0.15
        return fireProtLevel * reductionCoefficient
    }
}
