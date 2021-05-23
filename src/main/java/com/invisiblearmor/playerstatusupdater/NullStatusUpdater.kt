package com.invisiblearmor.playerstatusupdater

import org.bukkit.entity.Player

class NullStatusUpdater : PlayerStatusUpdater {

    override fun updateStatus(player: Player) {
        player.sendMessage("Status Updaterに登録されてないダメージタイプがあります")
    }
}
