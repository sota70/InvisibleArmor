package com.neoinvisiblearmor

import com.neoinvisiblearmor.armordata.PlayerArmor
import com.neoinvisiblearmor.registerer.ListenerRegisterer
import com.neoinvisiblearmor.registerer.commandregisterer.CommandRegisterer
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

/**
 * プラグインのメインクラス
 */
class NeoInvisibleArmor : JavaPlugin() {

    // プレイヤー全員のアーマーデータ
    private val playerArmorData = hashMapOf<Player, PlayerArmor>()

    override fun onEnable() {
        registerComponents()
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    /**
     * プレイヤーが保存しているアーマーデータの[HashMap]を取得するメソッド
     *
     * @return プレイヤー全員のアーマーデータが入った[HashMap]
     */
    fun getPlayerArmorData(): HashMap<Player, PlayerArmor> = playerArmorData

    // プラグインにListenerやCommandなどのComponentを追加するメソッド
    private fun registerComponents() {
        val registerers = arrayOf(
            CommandRegisterer(this),
            ListenerRegisterer(this)
        )
        registerers.forEach { it.register() }
    }
}
