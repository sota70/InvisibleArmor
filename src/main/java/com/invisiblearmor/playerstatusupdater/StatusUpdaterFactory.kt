package com.invisiblearmor.playerstatusupdater

import com.invisiblearmor.InvisibleArmor
import org.bukkit.event.entity.EntityDamageEvent.DamageCause

/**
 * [PlayerStatusUpdater]クラスのインスタンスを作って渡すFactoryクラス
 *
 * @property plugin プラグインのメインクラス
 */
class StatusUpdaterFactory(private val plugin: InvisibleArmor) {

    /**
     * 受けたダメージの種類によって適切な[PlayerStatusUpdater]を渡すメソッド
     *
     * @return ダメージの種類に合った[PlayerStatusUpdater]クラスを返す
     */
    fun createStatusUpdater(damageType: DamageCause): PlayerStatusUpdater = when (damageType) {
        DamageCause.LAVA -> LavaFireTickStatusUpdater(plugin)
        DamageCause.FIRE -> FireFireTickStatusUpdater(plugin)
        else -> NullStatusUpdater()
    }
}
