package com.neoinvisiblearmor.tag

import com.neoinvisiblearmor.NeoInvisibleArmor
import com.neoinvisiblearmor.util.Repeater
import org.bukkit.entity.Player

class PlayerOnFireTag(
    private val plugin: NeoInvisibleArmor,
    private val player: Player,
    private val finishTask: () -> Unit
) {

    fun putFireTag() {
        val coolDownTime = 11
        if (isPlayerOnFire()) {
            BURNINGPLAYERS[player] = coolDownTime
            return
        }
        BURNINGPLAYERS[player] = coolDownTime
        startDecreasing()
    }

    private fun removeFireTag() {
        BURNINGPLAYERS.remove(player)
    }

    private fun isPlayerOnFire(): Boolean = BURNINGPLAYERS.containsKey(player)

    private fun getBurningCoolDown(): Int = BURNINGPLAYERS[player] ?: 0

    private fun setBurningCoolDown(coolDownTime: Int) {
        if (!isPlayerOnFire()) return
        BURNINGPLAYERS[player] = coolDownTime
    }

    private fun startDecreasing() {
        val repeater = Repeater(plugin, 0, 1)
        val task = {
            if (getBurningCoolDown() <= 0) {
                finishTask()
                removeFireTag()
                repeater.stop()
            }
            setBurningCoolDown(getBurningCoolDown() - 1)
        }
        repeater.setTask(task)
        repeater.start()
    }
}
