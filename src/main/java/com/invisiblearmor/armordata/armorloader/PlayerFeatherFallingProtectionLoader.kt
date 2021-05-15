package com.invisiblearmor.armordata.armorloader

import com.invisiblearmor.InvisibleArmor
import com.invisiblearmor.util.ArmorIdentifier
import net.minecraft.server.v1_16_R3.EnumItemSlot
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player

/**
 * プレイヤーの保存しているブーツから落下体制のエンチャントがついていれば
 * 落下時のダメージ軽減値を取得するクラス
 *
 * @property plugin プラグインのメインクラス
 * @property player 値を取得する対象のプレイヤー
 */
class PlayerFeatherFallingProtectionLoader(
    plugin: InvisibleArmor,
    player: Player
) : PlayerArmorLoader(plugin, player) {

    /**
     * プレイヤーの保存しているブーツから落下体制のエンチャントがついていれば
     * 落下時のダメージ軽減値を取得するクラス
     *
     * @return 落下時のダメージ軽減値を[Double]型で返す
     */
    fun fetchPlayerFeatherFallingProtection(): Double {
        val boots = loadArmor(EnumItemSlot.FEET)
        val armorIdentifier = ArmorIdentifier(boots.type)
        if (!armorIdentifier.isItemBoots()) return 0.0
        return boots.getEnchantmentLevel(Enchantment.PROTECTION_FALL).toDouble() * 12 / 100
    }
}
