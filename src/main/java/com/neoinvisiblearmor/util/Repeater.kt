package com.neoinvisiblearmor.util

import com.neoinvisiblearmor.NeoInvisibleArmor
import org.bukkit.Bukkit

class Repeater {

    private lateinit var plugin: NeoInvisibleArmor
    private lateinit var task: () -> Unit
    private var delay: Long = 0
    private var period: Long = 0

    constructor(
        _plugin: NeoInvisibleArmor,
        _delay: Long,
        _period: Long
    ) {
        plugin = _plugin
        delay = _delay
        period = _period
    }

    constructor(
        _plugin: NeoInvisibleArmor,
        _delay: Long,
        _period: Long,
        _task: () -> Unit
    ) {
        plugin = _plugin
        delay = _delay
        period = _period
        task = _task
    }

    private var taskId: Int? = null

    fun start() {
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, task, delay, period)
    }

    fun stop() {
        taskId?.let { task ->
            Bukkit.getScheduler().cancelTask(task)
        } ?: return
    }

    fun setTask(_task: () -> Unit) {
        task = _task
    }
}
