package com.invisiblearmor.announcement

import org.bukkit.entity.Player

interface Announce {

    val player: Player

    fun announce()
}
