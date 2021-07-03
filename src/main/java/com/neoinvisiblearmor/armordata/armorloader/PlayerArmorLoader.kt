package com.neoinvisiblearmor.armordata.armorloader

import com.neoinvisiblearmor.NeoInvisibleArmor
import net.minecraft.server.v1_16_R3.EnumItemSlot
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * プレイヤーのアーマーを読み込むクラス
 *
 * @property plugin プラグインのメインクラス
 * @property player 読み込む対象のプレイヤー
 */
open class PlayerArmorLoader(
    private val plugin: NeoInvisibleArmor,
    private val player: Player
) {

    /**
     * アーマーを読み込むメソッド
     *
     * @param equipmentSlot 読み込みたいアーマーの部位
     * @return 読み込んだ[ItemStack]を返す
     */
    fun loadArmor(equipmentSlot: EnumItemSlot): ItemStack {
        val playerArmorData = plugin.getPlayerArmorData()
        return when (equipmentSlot) {
            EnumItemSlot.HEAD -> playerArmorData[player]?.helmet ?: ItemStack(Material.AIR)
            EnumItemSlot.CHEST -> playerArmorData[player]?.chestplate ?: ItemStack(Material.AIR)
            EnumItemSlot.LEGS -> playerArmorData[player]?.leggings ?: ItemStack(Material.AIR)
            EnumItemSlot.FEET -> playerArmorData[player]?.boots ?: ItemStack(Material.AIR)
            else -> throw IllegalStateException("loadArmorメソッドで予期できない例外が発生しました")
        }
    }
}
