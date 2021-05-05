package com.invisiblearmor.listener

import com.invisiblearmor.InvisibleArmor
import com.invisiblearmor.armordata.PlayerArmorInitializer
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

/**
 * プレイヤーがサーバーに参加した時の処理をするクラス
 *
 * @property plugin プラグインのメインクラス
 */
class JoinListener(private val plugin: InvisibleArmor) : Listener {

    /**
     * プレイヤーが参加したときにプレイヤーのアーマーデータをセットする処理をするメソッド
     *
     * @param event プレイヤーがサーバーに参加したことを感知するイベント
     */
    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        val playerArmorDataInitializer = PlayerArmorInitializer(plugin, player)
        playerArmorDataInitializer.initPlayerArmorData()
    }
}
