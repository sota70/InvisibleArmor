package com.invisiblearmor.armordata

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

/**
 * ArmorGUIに保存する用のアーマークラス
 *
 * @property helmet プレイヤーのヘルメット
 * @property chestplate プレイヤーのチェストプレート
 * @property leggings プレイヤーのレギンス
 * @property boots プレイヤーのブーツ
 */
class PlayerArmor {

    var helmet: ItemStack = ItemStack(Material.AIR)
    var chestplate: ItemStack = ItemStack(Material.AIR)
    var leggings: ItemStack = ItemStack(Material.AIR)
    var boots: ItemStack = ItemStack(Material.AIR)
}
