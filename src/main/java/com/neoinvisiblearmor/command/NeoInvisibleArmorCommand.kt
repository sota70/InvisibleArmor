package com.neoinvisiblearmor.command

import com.invisiblearmor.armorgui.ArmorGUI
import com.neoinvisiblearmor.NeoInvisibleArmor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/**
 * プラグインのメインコマンドの処理を書くクラス
 */
class NeoInvisibleArmorCommand(private val plugin: NeoInvisibleArmor) : CommandExecutor {

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
        val player: Player = sender
        ArmorGUI(plugin, player).openGUI()
        return true
    }
}
