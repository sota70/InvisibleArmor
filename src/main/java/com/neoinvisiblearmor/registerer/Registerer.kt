package com.neoinvisiblearmor.registerer

import com.neoinvisiblearmor.NeoInvisibleArmor

/**
 * プラグインにコンポーネントを登録するクラスのベースクラス
 */
interface Registerer {

    /**
     * プラグインのメインクラス
     */
    val plugin: NeoInvisibleArmor

    /**
     * コンポーネントを登録するメソッド
     */
    fun register()
}
