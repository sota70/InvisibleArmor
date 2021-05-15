package com.invisiblearmor.armorgui

import com.invisiblearmor.InvisibleArmor
import com.invisiblearmor.armordata.armorloader.PlayerArmorLoader
import com.invisiblearmor.template.ArmorGUIName.ARMOR_GUI_NAME
import net.minecraft.server.v1_16_R3.EnumItemSlot
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

/**
 * アーマーGUIクラス
 *
 * @property player アーマーGUIを開けさせる対象のプレイヤー
 */
class ArmorGUI(
    private val plugin: InvisibleArmor,
    private val player: Player
) {

    /**
     * アーマーを保管するGUI
     * ０はガラスで１がアーマーを入れるところ
     *
     *  0 0 0 0 0 0 0 0 0
     *  0 1 0 1 0 1 0 1 0
     *  0 0 0 0 0 0 0 0 0
     *
     */
    private val armorGUIInventory = Bukkit.createInventory(null, 27, ARMOR_GUI_NAME)

    /**
     * アーマーGUIをプレイヤーに開けさせるメソッド
     */
    fun openGUI() {
        initializeGUI()
        player.openInventory(armorGUIInventory)
    }

    // GUIを初期化するメソッド
    private fun initializeGUI() {
        fillGUIWithGlassPane()
        loadPlayerArmor()
    }

    // アーマーをセットする場所以外を全てグレイの板ガラスで埋めるメソッド
    private fun fillGUIWithGlassPane() {
        armorGUIInventory.setItem(0, ItemStack(Material.GRAY_STAINED_GLASS_PANE))
        armorGUIInventory.setItem(1, ItemStack(Material.GRAY_STAINED_GLASS_PANE))
        armorGUIInventory.setItem(2, ItemStack(Material.GRAY_STAINED_GLASS_PANE))
        armorGUIInventory.setItem(3, ItemStack(Material.GRAY_STAINED_GLASS_PANE))
        armorGUIInventory.setItem(4, ItemStack(Material.GRAY_STAINED_GLASS_PANE))
        armorGUIInventory.setItem(5, ItemStack(Material.GRAY_STAINED_GLASS_PANE))
        armorGUIInventory.setItem(6, ItemStack(Material.GRAY_STAINED_GLASS_PANE))
        armorGUIInventory.setItem(7, ItemStack(Material.GRAY_STAINED_GLASS_PANE))
        armorGUIInventory.setItem(8, ItemStack(Material.GRAY_STAINED_GLASS_PANE))
        armorGUIInventory.setItem(9, ItemStack(Material.GRAY_STAINED_GLASS_PANE))
        armorGUIInventory.setItem(11, ItemStack(Material.GRAY_STAINED_GLASS_PANE))
        armorGUIInventory.setItem(13, ItemStack(Material.GRAY_STAINED_GLASS_PANE))
        armorGUIInventory.setItem(15, ItemStack(Material.GRAY_STAINED_GLASS_PANE))
        armorGUIInventory.setItem(17, ItemStack(Material.GRAY_STAINED_GLASS_PANE))
        armorGUIInventory.setItem(18, ItemStack(Material.GRAY_STAINED_GLASS_PANE))
        armorGUIInventory.setItem(19, ItemStack(Material.GRAY_STAINED_GLASS_PANE))
        armorGUIInventory.setItem(20, ItemStack(Material.GRAY_STAINED_GLASS_PANE))
        armorGUIInventory.setItem(21, ItemStack(Material.GRAY_STAINED_GLASS_PANE))
        armorGUIInventory.setItem(22, ItemStack(Material.GRAY_STAINED_GLASS_PANE))
        armorGUIInventory.setItem(23, ItemStack(Material.GRAY_STAINED_GLASS_PANE))
        armorGUIInventory.setItem(24, ItemStack(Material.GRAY_STAINED_GLASS_PANE))
        armorGUIInventory.setItem(25, ItemStack(Material.GRAY_STAINED_GLASS_PANE))
        armorGUIInventory.setItem(26, ItemStack(Material.GRAY_STAINED_GLASS_PANE))
    }

    // プレイヤーが保管しているアーマーをGUIにセットするメソッド
    private fun loadPlayerArmor() {
        val playerArmorLoader = PlayerArmorLoader(plugin, player)
        val helmet = playerArmorLoader.loadArmor(EnumItemSlot.HEAD)
        val chestplate = playerArmorLoader.loadArmor(EnumItemSlot.CHEST)
        val leggings = playerArmorLoader.loadArmor(EnumItemSlot.LEGS)
        val boots = playerArmorLoader.loadArmor(EnumItemSlot.FEET)
        armorGUIInventory.setItem(10, helmet)
        armorGUIInventory.setItem(12, chestplate)
        armorGUIInventory.setItem(14, leggings)
        armorGUIInventory.setItem(16, boots)
    }
}
