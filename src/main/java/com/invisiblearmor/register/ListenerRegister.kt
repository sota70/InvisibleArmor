package com.invisiblearmor.register

import com.invisiblearmor.InvisibleArmor
import com.invisiblearmor.listener.CloseArmorGUIListener
import com.invisiblearmor.listener.EditArmorGUIListener
import com.invisiblearmor.listener.JoinListener
import com.invisiblearmor.listener.TakeDamageListener
import org.bukkit.Bukkit

/**
 * リスナーを登録するクラス
 *
 * @param plugin プラグインのメインクラス
 */
class ListenerRegister(override val plugin: InvisibleArmor) : RegisterBase {

    /**
     * リスナーを登録するメソッド
     */
    override fun register() {
        val pluginManager = Bukkit.getPluginManager()
        pluginManager.registerEvents(EditArmorGUIListener(), plugin)
        pluginManager.registerEvents(CloseArmorGUIListener(plugin), plugin)
        pluginManager.registerEvents(JoinListener(plugin), plugin)
        pluginManager.registerEvents(TakeDamageListener(plugin), plugin)
    }
}
