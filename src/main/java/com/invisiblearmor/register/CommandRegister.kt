package com.invisiblearmor.register

import com.invisiblearmor.InvisibleArmor
import com.invisiblearmor.command.MainCommandExecutor
import com.invisiblearmor.template.CommandName.MAIN_COMMAND_NAME

/**
 * コマンドを登録するクラス
 *
 * @property plugin プラグインのメインクラス
 */
class CommandRegister(override val plugin: InvisibleArmor) : RegisterBase {

    /**
     * コマンドを登録するメソッド
     */
    override fun register() {
        plugin.getCommand(MAIN_COMMAND_NAME)?.setExecutor(MainCommandExecutor(plugin))
    }
}
