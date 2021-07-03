package com.neoinvisiblearmor.registerer.commandregisterer

import com.neoinvisiblearmor.NeoInvisibleArmor
import com.neoinvisiblearmor.command.NeoInvisibleArmorCommand
import com.neoinvisiblearmor.registerer.Registerer

/**
 * プラグインにコマンドを登録するクラス
 *
 * @param plugin プラグインのメインクラス
 */
class CommandRegisterer(
    override val plugin: NeoInvisibleArmor
) : Registerer {

    /**
     * プラグインにコマンドを登録するメソッド
     */
    override fun register() {
        plugin.getCommand(NEO_INVISIBLE_ARMOR)?.setExecutor(NeoInvisibleArmorCommand(plugin))
    }
}
