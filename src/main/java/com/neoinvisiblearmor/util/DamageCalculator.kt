package com.neoinvisiblearmor.util

import org.bukkit.Material
import org.bukkit.event.entity.EntityDamageEvent.DamageCause

/**
 * ダメージを計算するクラス
 *
 * @property takenDamage 受けたダメージ量
 * @property armorPoint アーマーのポイント
 * @property armorEPF EPFポイント
 * @property armorToughness ArmorToughness（ダイヤ・ネザライト装備についてるやつ）
 */
class DamageCalculator {

    private var takenDamage = 0.0
    private var armorPoint = 0.0
    private var armorEPF = 0.0
    private var armorToughness = 0.0
    private var damageType = DamageCause.VOID
    private var itemOnPlayerHead = Material.AIR

    constructor()

    constructor(
        _takenDamage: Double,
        _armorPoint: Double,
        _armorEPF: Double,
        _armorToughness: Double,
        _damageType: DamageCause,
        _itemOnPlayerHead: Material
    ) {
        takenDamage = _takenDamage
        armorPoint = _armorPoint
        armorEPF = _armorEPF
        armorToughness = _armorToughness
        damageType = _damageType
        itemOnPlayerHead = _itemOnPlayerHead
    }

    /**
     * [armorPoint], [armorToughness], [armorEPF]をもとにダメージを計算して値を返すメソッド
     * [計算式引用元][https://minecraft.fandom.com/wiki/Armor#Damage_protection]
     * DamageCauseがFalling Blockかつ、プレイヤーがヘルメットを装備している場合に
     * ダメージを25%軽減させる
     *
     * @return 最終ダメージを[Double]型で返す
     */
    fun calcFinalDamage(): Double {
        sendArmorLog()
        val helmetReduction = 0.25
        val finalDamage =
            takenDamage * (1 - Math.min(20.0, Math.max(armorPoint / 5, armorPoint - takenDamage / (2 + armorToughness / 4))) / 25) * (1 - Math.min(armorEPF, 20.0) / 25)
        if (damageType == DamageCause.FALLING_BLOCK && itemOnPlayerHead != Material.AIR) {
            return finalDamage * (1 - helmetReduction)
        }
        return finalDamage
    }

    fun setTakenDamage(_takenDamage: Double): DamageCalculator =
        DamageCalculator(_takenDamage, armorPoint, armorEPF, armorToughness, damageType, itemOnPlayerHead)

    fun setArmorPoint(_armorPoint: Double): DamageCalculator =
        DamageCalculator(takenDamage, _armorPoint, armorEPF, armorToughness, damageType, itemOnPlayerHead)

    fun setArmorEPF(_armorEPF: Double): DamageCalculator =
        DamageCalculator(takenDamage, armorPoint, _armorEPF, armorToughness, damageType, itemOnPlayerHead)

    fun setArmorToughness(_armorToughness: Double): DamageCalculator =
        DamageCalculator(takenDamage, armorPoint, armorEPF, _armorToughness, damageType, itemOnPlayerHead)

    fun setDamageType(_damageType: DamageCause): DamageCalculator =
        DamageCalculator(takenDamage, armorPoint, armorEPF, armorToughness, _damageType, itemOnPlayerHead)

    fun setItemOnPlayerHead(_itemOnPlayerHead: Material): DamageCalculator =
        DamageCalculator(takenDamage, armorPoint, armorEPF, armorToughness, damageType, _itemOnPlayerHead)

    private fun sendArmorLog() {
        println(
            """
            ArmorPoint: $armorPoint
            ArmorToughness: $armorToughness
            ArmorEPF: $armorEPF
            """.trimIndent()
        )
    }
}
