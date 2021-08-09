package com.neoinvisiblearmor.event

import com.neoinvisiblearmor.NeoInvisibleArmor
import com.neoinvisiblearmor.armordata.loader.PlayerArmorLoader
import com.neoinvisiblearmor.event.statusevent.PlayerStatusDamageEvent
import net.minecraft.server.v1_16_R3.EnumItemSlot
import org.bukkit.entity.Player
import org.bukkit.event.HandlerList

class PlayerHitByAnvilEvent(victim: Player, plugin: NeoInvisibleArmor) : PlayerStatusDamageEvent(victim) {

    val helmetType = PlayerArmorLoader(plugin, player).loadArmor(EnumItemSlot.HEAD).type

    private var cancelled = false

    override fun setCancelled(cancel: Boolean) {
        cancelled = cancel
    }

    override fun isCancelled(): Boolean = cancelled

    override fun getHandlers(): HandlerList = handlerList

    companion object {

        private val handlerList = HandlerList()

        @JvmStatic
        fun getHandlerList(): HandlerList = handlerList
    }
}
