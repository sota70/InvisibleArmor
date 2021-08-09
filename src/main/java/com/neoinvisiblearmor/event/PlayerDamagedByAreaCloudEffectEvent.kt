package com.neoinvisiblearmor.event

import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.HandlerList
import org.bukkit.event.entity.EntityDamageEvent.DamageCause
import org.bukkit.event.player.PlayerEvent

class PlayerDamagedByAreaCloudEffectEvent(
    victim: Player,
    val takenDamage: Double,
    val damageType: DamageCause
) : PlayerEvent(victim), Cancellable {

    private var cancelled = false

    override fun getHandlers(): HandlerList = handlerList

    override fun isCancelled(): Boolean = cancelled

    override fun setCancelled(cancel: Boolean) {
        cancelled = cancel
    }

    companion object {

        private val handlerList = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList = handlerList
    }
}
