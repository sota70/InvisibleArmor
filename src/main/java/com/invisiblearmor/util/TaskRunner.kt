package com.invisiblearmor.util

import com.invisiblearmor.InvisibleArmor
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitScheduler

/**
 * [BukkitScheduler]を使ってタスクを実行するクラス
 *
 * @property plugin プラグインのメインクラス
 */
class TaskRunner(private val plugin: InvisibleArmor) {

    /**
     * タスクを[delay]Tick送らせて実行するメソッド
     *
     * @param delay タスクの実行までの遅延、単位はTick
     * @param task 実行するタスク
     */
    fun runTaskLater(delay: Long, task: () -> Unit) {
        Bukkit.getScheduler().runTaskLater(
            plugin,
            task,
            delay
        )
    }

    /**
     * タスクを[delay]Tick送らせて実行するメソッド
     *
     * @param delay タスクの実行までの遅延、単位はTick
     * @param task 実行するタスク
     */
    fun runTaskLater(delay: Long, task: Runnable) {
        Bukkit.getScheduler().runTaskLater(
            plugin,
            task,
            delay
        )
    }
}
