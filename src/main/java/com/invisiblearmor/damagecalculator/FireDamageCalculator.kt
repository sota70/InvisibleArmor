package com.invisiblearmor.damagecalculator

import com.invisiblearmor.InvisibleArmor
import com.invisiblearmor.armordata.armorloader.PlayerFireProtectionLoader
import org.bukkit.entity.Player

class FireDamageCalculator(
    private val plugin: InvisibleArmor,
    private val player: Player,
    private val damage: Double
) : DamageCalculator {

    override fun calcFinalDamage(): Double {
        val playerFireProtection =
            PlayerFireProtectionLoader(plugin, player).fetchPlayerFireProtection()
        val damageReduction = damage * playerFireProtection
        return damage - damageReduction
    }
}
