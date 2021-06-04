package com.invisiblearmor.announcement

import org.bukkit.entity.Player

/**
 * デバッグ用に使う[Announce]クラス
 *
 * @property player メッセージを送信する対象のプレイヤー
 */
class DebugLogAnnounce(override val player: Player) : Announce {

    /**
     * メッセージをプレイヤーとコンソール、両方に送信するメソッド (単体メッセージ）
     *
     * @param announceMessage メッセージの内容
     */
    override fun announce(announceMessage: String) {
        announceToPlayer(announceMessage)
        announceToConsole(announceMessage)
    }

    /**
     * メッセージをプレイヤーとコンソール、両方に送信するメソッド (複数メッセージ)
     *
     * @param announceMessages メッセージの内容
     */
    override fun announce(announceMessages: Array<String>) {
        if (announceMessages.size < 2) {
            val announceMessage = announceMessages[0]
            announceToPlayer(announceMessage)
            announceToConsole(announceMessage)
        }
        announceToPlayer(announceMessages)
        announceToConsole(announceMessages)
    }

    // プレイヤーにメッセージを送信するメソッド (単体メッセージ)
    private fun announceToPlayer(announceMessage: String) {
        player.sendMessage("${player.displayName}'s info")
        player.sendMessage(announceMessage)
    }

    // プレイヤーにメッセージを送信するメソッド (複数メッセージ）
    private fun announceToPlayer(announceMessages: Array<String>) {
        player.sendMessage("${player.displayName}'s info")
        announceMessages.forEach { player.sendMessage(it) }
    }

    // コンソールにメッセージを送信するメソッド (単体メッセージ)
    private fun announceToConsole(announceMessage: String) {
        println("${player.displayName}'s info")
        println(announceMessage)
    }

    // コンソールにメッセージを送信するメソッド (複数メッセージ）
    private fun announceToConsole(announceMessages: Array<String>) {
        println("${player.displayName}'s info")
        announceMessages.forEach { println(it) }
    }
}
