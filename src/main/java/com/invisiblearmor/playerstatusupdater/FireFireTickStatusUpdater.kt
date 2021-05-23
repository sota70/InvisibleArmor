package com.invisiblearmor.playerstatusupdater

import com.invisiblearmor.InvisibleArmor
import com.invisiblearmor.armordata.armorloader.PlayerFireTickReductionLoader
import com.invisiblearmor.onfiretag.FireTagger
import org.bukkit.Bukkit
import org.bukkit.entity.Player

/**
 * Fire Protectionエンチャントを装備しているプレイヤーの
 * Fire Tickを軽減する[PlayerStatusUpdater]クラス
 */
class FireFireTickStatusUpdater(
    private val plugin: InvisibleArmor
) : PlayerStatusUpdater {

    /**
     * 炎のFire Tickを再計算してプレイヤーにセットするメソッド
     * 炎に当たる毎に再計算するとFire Tickの値がずれるため
     * 炎から出た時だけ再計算するようにしてある
     *
     * @param player Fire Tickを再計算する対象のプレイヤー
     */
    override fun updateStatus(player: Player) {
        val fireTagger = FireTagger(player)
        if (!fireTagger.isPlayerTagged()) {
            putFireTagTemporarily(player)
            return
        }
        fireTagger.resetFireTaggedTime()
    }

    // 一時的にFire Tagをプレイヤーにセットするメソッド
    private fun putFireTagTemporarily(player: Player) {
        val fireTagger = FireTagger(player)
        fireTagger.putFireTag()
        decreaseFireTaggedTime(player)
    }

    /*
     Fire Tagの持続時間を1 Tick毎に減らしていくメソッド
     持続時間が０になった場合、Fire Tagをプレイヤーから外し
     プレイヤーのFire Tickを再計算してプレイヤーにセットする
     */
    private fun decreaseFireTaggedTime(player: Player) {
        val fireTagger = FireTagger(player)
        val oldTaggedTime = fireTagger.fetchFireTaggedTime()
        val task = Runnable {
            if (fireTagger.isTaggedTimeExpired()) {
                fireTagger.removeFireTag()
                recalculateFireTick(player)
                return@Runnable
            }
            fireTagger.changeFireTaggedTime(oldTaggedTime - 1)
            decreaseFireTaggedTime(player)
        }
        Bukkit.getScheduler().runTaskLater(
            plugin,
            task,
            1
        )
    }

    // プレイヤーのFire Tickを再計算してセットするメソッド
    private fun recalculateFireTick(player: Player) {
        val fireTickReduction =
            player.fireTicks * PlayerFireTickReductionLoader(plugin, player).fetchPlayerFireTickReduction()
        player.fireTicks = player.fireTicks - fireTickReduction.toInt()
    }
}
