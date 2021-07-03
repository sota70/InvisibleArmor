package com.neoinvisiblearmor.armordata

import com.neoinvisiblearmor.NeoInvisibleArmor
import net.minecraft.server.v1_16_R3.EnumItemSlot
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * アーマーを保管しておくクラス
 *
 * @property plugin プラグインのメインクラス
 * @property player 補完する対象のプレイヤー
 */
class PlayerArmorComplement(
    private val plugin: NeoInvisibleArmor,
    private val player: Player
) {

    /**
     * アーマーを保管するメソッド
     *
     * @param armorEquipment 補完したいアーマー
     * @param equipmentSlot アーマーの部位
     */
    fun complementArmor(armorEquipment: ItemStack?, equipmentSlot: EnumItemSlot) {
        when (equipmentSlot) {
            EnumItemSlot.HEAD -> complementHeadEquipment(armorEquipment)
            EnumItemSlot.CHEST -> complementChestEquipment(armorEquipment)
            EnumItemSlot.LEGS -> complementLegsEquipment(armorEquipment)
            EnumItemSlot.FEET -> complementFeetEquipment(armorEquipment)
            else -> throw IllegalStateException("complementArmorメソッドで予期できない例外が起こりました")
        }
    }

    // ヘルメットを保管するメソッド
    private fun complementHeadEquipment(armorEquipment: ItemStack?) {
        val playerArmorData = plugin.getPlayerArmorData()
        if (armorEquipment == null) {
            playerArmorData[player]?.helmet = ItemStack(Material.AIR)
            return
        }
        playerArmorData[player]?.helmet = armorEquipment
    }

    // チェストプレートを保管するメソッド
    private fun complementChestEquipment(armorEquipment: ItemStack?) {
        val playerArmorData = plugin.getPlayerArmorData()
        if (armorEquipment == null) {
            playerArmorData[player]?.chestplate = ItemStack(Material.AIR)
            return
        }
        playerArmorData[player]?.chestplate = armorEquipment
    }

    // レギンスを保管するメソッド
    private fun complementLegsEquipment(armorEquipment: ItemStack?) {
        val playerArmorData = plugin.getPlayerArmorData()
        if (armorEquipment == null) {
            playerArmorData[player]?.leggings = ItemStack(Material.AIR)
            return
        }
        playerArmorData[player]?.leggings = armorEquipment
    }

    // ブーツを保管するメソッド
    private fun complementFeetEquipment(armorEquipment: ItemStack?) {
        val playerArmorData = plugin.getPlayerArmorData()
        if (armorEquipment == null) {
            playerArmorData[player]?.boots = ItemStack(Material.AIR)
            return
        }
        playerArmorData[player]?.boots = armorEquipment
    }
}
