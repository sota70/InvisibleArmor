package com.invisiblearmor.armordata

import com.invisiblearmor.InvisibleArmor
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
class PlayerArmorLoader(
    private val plugin: InvisibleArmor,
    private val player: Player
) {

    /**
     * アーマーを読み込むメソッド
     *
     * @param equipmentSlot 読み込みたいアーマーの部位
     * @return 読み込んだ[ItemStack]を返す
     */
    fun loadArmor(equipmentSlot: EnumItemSlot): ItemStack = when (equipmentSlot) {
        EnumItemSlot.HEAD -> loadHeadEquipment()
        EnumItemSlot.CHEST -> loadChestEquipment()
        EnumItemSlot.LEGS -> loadLegsEquipment()
        EnumItemSlot.FEET -> loadFeetEquipment()
        else -> throw IllegalStateException("loadArmorメソッドで予期できない例外が発生しました")
    }

    // ヘルメットを読み込むメソッド
    private fun loadHeadEquipment(): ItemStack {
        val playerArmorData = plugin.getPlayerArmorData()
        val playerHeadEquipment = playerArmorData[player]?.helmet
        return playerHeadEquipment ?: ItemStack(Material.AIR)
    }

    // チェストプレートを読み込むメソッド
    private fun loadChestEquipment(): ItemStack {
        val playerArmorData = plugin.getPlayerArmorData()
        val playerChestEquipment = playerArmorData[player]?.chestplate
        return playerChestEquipment ?: ItemStack(Material.AIR)
    }

    // レギンスを読み込むメソッド
    private fun loadLegsEquipment(): ItemStack {
        val playerArmorData = plugin.getPlayerArmorData()
        val playerLegsEquipment = playerArmorData[player]?.leggings
        return playerLegsEquipment ?: ItemStack(Material.AIR)
    }

    // ブーツを読み込むメソッド
    private fun loadFeetEquipment(): ItemStack {
        val playerArmorData = plugin.getPlayerArmorData()
        val playerFeetEquipment = playerArmorData[player]?.boots
        return playerFeetEquipment ?: ItemStack(Material.AIR)
    }
}
