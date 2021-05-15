package com.invisiblearmor.playerstatus

import com.invisiblearmor.InvisibleArmor
import com.invisiblearmor.armordata.armorloader.PlayerFireTickReductionLoader
import org.bukkit.Bukkit
import org.bukkit.entity.Player

/**
 * Fire Protectionエンチャントを装備しているプレイヤーの
 * Fire Tickを軽減する[PlayerStatusUpdater]クラス
 *
 * @property plugin プラグインのメインクラス
 */
class FireTickStatusUpdater(private val plugin: InvisibleArmor) : PlayerStatusUpdater {

    /**
     * Fire Protectionエンチャントを装備しているプレイヤーのFire Tickを軽減するメソッド
     * ステータスを更新するタイミングを7tick送らせているが
     * 7tick遅らせてFire Tickを変更することによって、Fire Tickを上書きできる
     * 7tickより短いと上書きができないので、7tick以上待つ必要がある
     *
     * @param player ステータスを変更する対象のプレイヤー
     */
    override fun updateStatus(player: Player) {
        val fireTickReduction =
            player.fireTicks * PlayerFireTickReductionLoader(plugin, player).fetchPlayerFireTickReduction()
        Bukkit.getScheduler().runTaskLater(
            plugin,
            Runnable { player.fireTicks = player.fireTicks - fireTickReduction.toInt() },
            7
        )
    }
}
