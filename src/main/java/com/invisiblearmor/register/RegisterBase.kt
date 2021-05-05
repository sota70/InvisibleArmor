package com.invisiblearmor.register

import com.invisiblearmor.InvisibleArmor

/**
 * Registerクラスのベースとなるインターフェース
 */
interface RegisterBase {

    /**
     * プラグインのメインクラス
     */
    val plugin: InvisibleArmor

    /**
     * 何かを登録するメソッド
     */
    fun register()
}
