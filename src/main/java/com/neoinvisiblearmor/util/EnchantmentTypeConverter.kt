package com.neoinvisiblearmor.util

import org.bukkit.enchantments.Enchantment
import org.bukkit.event.entity.EntityDamageEvent.DamageCause

/**
 * ダメージの種類から適切なエンチャントに変換するクラス
 *
 * @property damageType 受けたダメージの種類
 */
class EnchantmentTypeConverter(
    private val damageType: DamageCause
) {

    /**
     * ダメージの種類から適切なエンチャントに変換するメソッド
     * 受けたダメージの種類によっては、適切なエンチャントが２つ以上になる場合があるため
     * [MutableList]にして、複数のエンチャントを返せるようにしてある
     *
     * @return 適切なエンチャントを[MutableList]型で返す
     */
    fun convertToAppropriateType(): MutableList<Enchantment> {
        val enchantments = mutableListOf<Enchantment>()
        if (damageType != DamageCause.STARVATION &&
            damageType != DamageCause.SUICIDE &&
            damageType != DamageCause.VOID &&
            damageType != DamageCause.FALL
        ) {
            enchantments.add(Enchantment.PROTECTION_ENVIRONMENTAL)
        }
        if (damageType == DamageCause.FIRE ||
            damageType == DamageCause.LAVA ||
            damageType == DamageCause.FIRE_TICK
        ) {
            enchantments.add(Enchantment.PROTECTION_FIRE)
        }
        if (damageType == DamageCause.ENTITY_EXPLOSION ||
            damageType == DamageCause.BLOCK_EXPLOSION
        ) {
            enchantments.add(Enchantment.PROTECTION_EXPLOSIONS)
        }
        if (damageType == DamageCause.PROJECTILE) {
            enchantments.add(Enchantment.PROTECTION_PROJECTILE)
        }
        if (damageType == DamageCause.FALL) {
            enchantments.add(Enchantment.PROTECTION_FALL)
        }
        return enchantments
    }
}
