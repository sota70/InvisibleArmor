package com.invisiblearmor.listener

import com.invisiblearmor.InvisibleArmor
import com.invisiblearmor.armordata.PlayerArmorComplement
import com.invisiblearmor.template.ArmorGUIName.ARMOR_GUI_NAME
import net.minecraft.server.v1_16_R3.EnumItemSlot
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.Inventory

class CloseArmorGUIListener(private val plugin: InvisibleArmor) : Listener {

    @EventHandler
    fun onCloseInv(event: InventoryCloseEvent) {
        if (event.view.title != ARMOR_GUI_NAME) return
        val player = event.player as Player
        val closedInventory = event.inventory
        val playerArmorComplement = PlayerArmorComplement(plugin, player)
        savePlayerHelmet(playerArmorComplement, closedInventory)
        savePlayerChestplate(playerArmorComplement, closedInventory)
        savePlayerLeggings(playerArmorComplement, closedInventory)
        savePlayerBoots(playerArmorComplement, closedInventory)
    }

    // プレイヤーのヘルメットを保存するメソッド
    private fun savePlayerHelmet(
        playerArmorComplement: PlayerArmorComplement,
        inventory: Inventory
    ) {
        val helmet = inventory.contents[10]
        playerArmorComplement.complementArmor(helmet, EnumItemSlot.HEAD)
    }

    // プレイヤーのチェストプレートを保存するメソッド
    private fun savePlayerChestplate(
        playerArmorComplement: PlayerArmorComplement,
        inventory: Inventory
    ) {
        val chestplate = inventory.contents[12]
        playerArmorComplement.complementArmor(chestplate, EnumItemSlot.CHEST)
    }

    // プレイヤーのレギンスを保存するメソッド
    private fun savePlayerLeggings(
        playerArmorComplement: PlayerArmorComplement,
        inventory: Inventory
    ) {
        val leggings = inventory.contents[14]
        playerArmorComplement.complementArmor(leggings, EnumItemSlot.LEGS)
    }

    // プレイヤーのブーツを保存するメソッド
    private fun savePlayerBoots(
        playerArmorComplement: PlayerArmorComplement,
        inventory: Inventory
    ) {
        val boots = inventory.contents[16]
        playerArmorComplement.complementArmor(boots, EnumItemSlot.FEET)
    }
}
