package com.invisiblearmor.listener

import com.invisiblearmor.template.ArmorGUIName.ARMOR_GUI_NAME
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

/**
 * プレイヤーがインベントリー内のアイテムをクリックしたときの処理をするクラス
 */
class EditArmorGUIListener : Listener {

    /**
     * アーマーGUIをクリックした時のイベントメソッド
     * GUIの中身を勝手にいじられないために設けた
     */
    @EventHandler
    fun onClickInv(event: InventoryClickEvent) {
        if (event.view.title != ARMOR_GUI_NAME) return
        val clickedItem = event.currentItem
        if (isClickedItemInvalid(clickedItem?.type)) event.isCancelled = true
    }

    // クリックしたアイテムが何もイベントを含まないアイテムかどうかを確かめるメソッド
    private fun isClickedItemInvalid(material: Material?) = material == Material.GRAY_STAINED_GLASS_PANE
}
