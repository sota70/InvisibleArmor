package com.invisiblearmor.announcement

import org.bukkit.entity.Player

/**
 * Announceクラスのベースクラス
 * メッセージを送信するときに使う
 */
interface Announce {

    /**
     * メッセージを送信する対象のプレイヤー
     */
    val player: Player

    /**
     * メッセージを送信するメソッド (単体メッセージ）
     */
    fun announce(announceMessage: String)

    /**
     * メッセージを送信するメソッド (複数メッセージ）
     */
    fun announce(announceMessages: Array<String>)
}
