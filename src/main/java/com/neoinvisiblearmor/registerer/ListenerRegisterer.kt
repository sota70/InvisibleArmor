package com.neoinvisiblearmor.registerer

import com.neoinvisiblearmor.NeoInvisibleArmor
import com.neoinvisiblearmor.listener.* // ktlint-disable no-wildcard-imports
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
        Bukkit.getPluginManager().registerEvents(PlayerTakeDamageListener(plugin), plugin)
        Bukkit.getPluginManager().registerEvents(TakeStatusDamageListener(plugin), plugin)
        Bukkit.getPluginManager().registerEvents(PlayerBurnListener(plugin), plugin)
        Bukkit.getPluginManager().registerEvents(PlayerTakeDamageByEntityListener(plugin), plugin)
        Bukkit.getPluginManager().registerEvents(PlayerTakeDamageByAreaEffectCloudListener(plugin), plugin)

        /*
         爆発時の最大爆発軽減値があまりに低いため、爆発軽減機能は省くことにした
         バニラのエンチャントだとほぼ０に等しい量しか軽減されていない

         Bukkit.getPluginManager().registerEvents(PlayerExposedExplosionListener(), plugin)
         */
    }
}
