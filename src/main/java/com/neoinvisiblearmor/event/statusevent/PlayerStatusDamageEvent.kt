package com.neoinvisiblearmor.event.statusevent

import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.HandlerList
import org.bukkit.event.player.PlayerEvent

abstract class PlayerStatusDamageEvent(victim: Player) : PlayerEvent(victim), Cancellable {

    abstract override fun getHandlers(): HandlerList
    abstract override fun isCancelled(): Boolean
    abstract override fun setCancelled(cancel: Boolean)
}
