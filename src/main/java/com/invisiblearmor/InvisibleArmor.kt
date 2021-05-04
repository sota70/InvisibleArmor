package com.invisiblearmor

import com.invisiblearmor.armordata.PlayerArmor
import com.invisiblearmor.register.CommandRegister
import com.invisiblearmor.register.ListenerRegister
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

/**
 * プラグインのメインクラス
 */
class InvisibleArmor : JavaPlugin() {

    private val playerArmorData = hashMapOf<Player, PlayerArmor>()

    /**
     * プラグインの起動メソッド
     */
    override fun onEnable() {
        registerComponents()
    }

    /**
     * プラグインのシャットダウンメソッド
     */
    override fun onDisable() {
    }

    fun getPlayerArmorData() = playerArmorData

    private fun registerComponents() {
        val registers = arrayOf(
            CommandRegister(this),
            ListenerRegister(this)
        )
        registers.forEach { it.register() }
    }
}
