package com.invisiblearmor.playerstatusupdater

import org.bukkit.entity.Player

/**
 * プレイヤーのステータスを更新するインターフェース
 */
interface PlayerStatusUpdater {

    /**
     * プレイヤーのステータスを更新するメソッド
     * 基本的にPlayerArmorLoaderと併用して使う
     *
     * @param player ステータスを更新する対象のプレイヤー
     */
    fun updateStatus(player: Player)
}
