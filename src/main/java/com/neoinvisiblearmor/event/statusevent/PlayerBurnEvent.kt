package com.neoinvisiblearmor.event.statusevent

import org.bukkit.entity.Player
import org.bukkit.event.HandlerList

class PlayerBurnEvent(player: Player) : PlayerStatusDamageEvent(player) {

    private var cancelled = false

    override fun getHandlers(): HandlerList {
        return handlerList
    }

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
