package com.invisiblearmor.playerstatusupdater

import com.invisiblearmor.announcement.DebugLogAnnounce
import org.bukkit.entity.Player

class NullStatusUpdater : PlayerStatusUpdater {

    override fun updateStatus(player: Player) {
        val debugMessage = "Status Updaterに登録されてないダメージタイプがあります"
        val debugLogAnnounce = DebugLogAnnounce(player)
        debugLogAnnounce.announce(debugMessage)
    }
}
