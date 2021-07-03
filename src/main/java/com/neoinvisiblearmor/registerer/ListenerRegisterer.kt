package com.neoinvisiblearmor.registerer

import com.neoinvisiblearmor.NeoInvisibleArmor
import com.neoinvisiblearmor.listener.CloseArmorGUIListener
import com.neoinvisiblearmor.listener.EditArmorGUIListener
import com.neoinvisiblearmor.listener.JoinListener
import com.neoinvisiblearmor.listener.TakeDamageListener
import org.bukkit.Bukkit

/**
 * プラグインにリスナーを登録するクラス
 *
 * @param plugin プラグインのメインクラス
 */
class ListenerRegisterer(
    override val plugin: NeoInvisibleArmor
) : Registerer {

    /**
     * プラグインにリスナーを登録するメソッド
     */
    override fun register() {
        Bukkit.getPluginManager().registerEvents(CloseArmorGUIListener(plugin), plugin)
        Bukkit.getPluginManager().registerEvents(EditArmorGUIListener(), plugin)
        Bukkit.getPluginManager().registerEvents(JoinListener(plugin), plugin)
        Bukkit.getPluginManager().registerEvents(TakeDamageListener(plugin), plugin)
    }
}
