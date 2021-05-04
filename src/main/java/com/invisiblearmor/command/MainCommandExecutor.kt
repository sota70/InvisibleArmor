package com.invisiblearmor.command

import com.invisiblearmor.InvisibleArmor
import com.invisiblearmor.armorgui.ArmorGUI
import com.invisiblearmor.template.CommandName.MAIN_COMMAND_NAME
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * プラグインのメインコマンドの処理を書くクラス
 */
class MainCommandExecutor(private val plugin: InvisibleArmor) : CommandExecutor {

    /**
     * メインコマンドの処理を書くメソッド
     *
     * @param sender コマンドを送った人
     * @param command コマンド
     * @param label ラベル
     * @param args コマンド引数
     * @return trueとfalseで返せるようになっているが、返す値はどちらでも良い
     */
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<String>
    ): Boolean {
        if (sender !is Player) return false
        if (!isMainCommand(command.name)) return false
        val player: Player = sender
        ArmorGUI(plugin, player).openGUI()
        return true
    }

    // プレイヤーが打ったコマンド名がメインコマンド名と一致しているか確認するメソッド
    private fun isMainCommand(commandName: String): Boolean = commandName == MAIN_COMMAND_NAME
}
