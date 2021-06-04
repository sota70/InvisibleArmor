package com.invisiblearmor.armordata.armorloader

import com.invisiblearmor.InvisibleArmor
import net.minecraft.server.v1_16_R3.EnumItemSlot
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player

/**
 * プレイヤーのBlast Protectionを取得するクラス
 *
 * @property plugin プラグインのメインクラス
 * @property player 取得する対象のプレイヤー
 */
class PlayerExplosionProtectionLoader(
    plugin: InvisibleArmor,
    player: Player
) : PlayerArmorLoader(plugin, player) {

    /**
     * プレイヤーのBlast Protectionを取得するメソッド
     *
     * @return プレイヤーのBlast Protectionの合計値を[Double]型で返す
     */
    fun fetchPlayerExplosionProtection(): Double {
        val totalProtection = (
            fetchHelmetExplosionProtection() +
                fetchChestplateExplosionProtection() +
                fetchLeggingsExplosionProtection() +
                fetchBootsExplosionProtection()
            ) / 100

        // Blast Protectionの合計値が80％以上にならないようにフィルターをかけている
        if (totalProtection >= 0.8) return 0.8
        return totalProtection
    }

    // プレイヤーが装備しているヘルメットのBlast Protectionを取得するメソッド
    private fun fetchHelmetExplosionProtection(): Double {
        val helmet = loadArmor(EnumItemSlot.HEAD)
        val helmetExplosionEPF =
            helmet.getEnchantmentLevel(Enchantment.PROTECTION_EXPLOSIONS).toDouble()
        return helmetExplosionEPF * 8
    }

    // プレイヤーが装備しているチェストプレートのBlast Protectionを取得するメソッド
    private fun fetchChestplateExplosionProtection(): Double {
        val chestplate = loadArmor(EnumItemSlot.CHEST)
        val chestplateExplosionEPF =
            chestplate.getEnchantmentLevel(Enchantment.PROTECTION_EXPLOSIONS).toDouble()
        return chestplateExplosionEPF * 8
    }

    // プレイヤーが装備しているレギンスのBlast Protectionを取得するメソッド
    private fun fetchLeggingsExplosionProtection(): Double {
        val leggings = loadArmor(EnumItemSlot.LEGS)
        val leggingsExplosionEPF =
            leggings.getEnchantmentLevel(Enchantment.PROTECTION_EXPLOSIONS).toDouble()
        return leggingsExplosionEPF * 8
    }

    // プレイヤーが装備しているブーツのBlast Protectionを取得するメソッド
    private fun fetchBootsExplosionProtection(): Double {
        val boots = loadArmor(EnumItemSlot.FEET)
        val bootsExplosionEPF =
            boots.getEnchantmentLevel(Enchantment.PROTECTION_EXPLOSIONS).toDouble()
        return bootsExplosionEPF * 8
    }
}
