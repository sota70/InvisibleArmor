package com.neoinvisiblearmor.util

/**
 * ダメージを計算するクラス
 *
 * @property takenDamage 受けたダメージ量
 * @property armorPoint アーマーのポイント
 * @property armorEPF EPFポイント
 * @property armorToughness ArmorToughness（ダイヤ・ネザライト装備についてるやつ）
 */
class DamageCalculator(
    private val takenDamage: Double,
    private val armorPoint: Double,
    private val armorEPF: Double,
    private val armorToughness: Double
) {

    /**
     * [armorPoint]、[armorEPF]をもとにダメージを計算するメソッド
     *
     * @return 計算したダメージを[Double]型で返す
     */
    fun calcFinalDamageWithEPF(): Double {
        val reduction =
            (100 - (armorPoint * 4)) * armorEPF * 0.04 * 0.0075
        return takenDamage * reduction
    }

    /**
     * [armorPoint]をもとにダメージを計算するメソッド
     *
     * @return 計算したダメージを[Double]型で返す
     */
    fun calcFinalDamageWithoutEPF(): Double {
        val reduction =
            1 - (Math.min(20.0, Math.max(armorPoint / 5, armorPoint - (takenDamage * 4 / (armorToughness + 8))))) / 25
        return takenDamage * reduction
    }
}
