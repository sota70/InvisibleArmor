package com.neoinvisiblearmor.armordata

import com.neoinvisiblearmor.NeoInvisibleArmor
import org.bukkit.entity.Player

/**
 * プレイヤーのアーマーデータを初期化するクラス
 *
 * @property plugin プラグインのメインクラス
 * @property player 初期化する対象のプレイヤー
 */
class PlayerArmorInitializer(
    private val plugin: NeoInvisibleArmor,
    private val player: Player
) {

    /**
     * プレイヤーのデータを初期化するメソッド
     */
    fun initPlayerArmorData() {
        if (isPlayerAlreadyInitialized()) return
        val playerArmorData = plugin.getPlayerArmorData()
        playerArmorData[player] = PlayerArmor()
    }

    // すでにプレイヤーのアーマーデータが初期化されているか確認するメソッド
    fun isPlayerAlreadyInitialized(): Boolean {
        val playerArmorData = plugin.getPlayerArmorData()
        return playerArmorData.containsKey(player)
    }
}
