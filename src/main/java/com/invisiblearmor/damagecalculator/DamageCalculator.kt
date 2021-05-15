package com.invisiblearmor.damagecalculator

/**
 * ダメージ計算クラスのベースとなるインターフェース
 */
interface DamageCalculator {

    /**
     * ダメージ軽減値から計算した最終ダメージを返すメソッド
     *
     * @return 最終ダメージを[Double]型で返す
     */
    fun calcFinalDamage(): Double
}
