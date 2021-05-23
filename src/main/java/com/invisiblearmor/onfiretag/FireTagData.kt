package com.invisiblearmor.onfiretag

import org.bukkit.entity.Player

/**
 * Fire Tagが付いているプレイヤーとその持続時間を保存しているマップを保存しているオブジェクト
 * ここから[fireTaggedPlayer]を取り出すことによって、コード内でデータを共有できる
 */
object FireTagData {

    /**
     * Fire Tagが付いているプレイヤーとその持続時間を保存しているマップ
     */
    val fireTaggedPlayer = HashMap<Player, Int>()
}
