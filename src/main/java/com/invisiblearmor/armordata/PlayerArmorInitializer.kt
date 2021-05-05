package com.invisiblearmor.armordata

import com.invisiblearmor.InvisibleArmor
import org.bukkit.entity.Player

/**
 * プレイヤーのアーマーデータを初期化するクラス
 *
 * @property plugin プラグインのメインクラス
 * @property player 初期化する対象のプレイヤー
 */
class PlayerArmorInitializer(
    private val plugin: InvisibleArmor,
    private val player: Player
) {

    /**
     * プレイヤーのデータを初期化するメソッド
     * ToDo: コンフィグにアーマーデータを保存して、初期化時に呼び出す処理を作る
     */
    fun initPlayerArmorData() {
        if (isPlayerAlreadyInitialized()) return
        val playerArmorData = plugin.getPlayerArmorData()
        playerArmorData[player] = PlayerArmor()
    }

    // すでにプレイヤーのアーマーデータが初期化されているか確認するメソッド
    private fun isPlayerAlreadyInitialized(): Boolean {
        val playerArmorData = plugin.getPlayerArmorData()
        return playerArmorData.containsKey(player)
    }
}
