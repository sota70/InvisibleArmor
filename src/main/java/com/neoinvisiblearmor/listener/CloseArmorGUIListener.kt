package com.neoinvisiblearmor.listener

import com.neoinvisiblearmor.NeoInvisibleArmor
import com.neoinvisiblearmor.armordata.PlayerArmorComplement
import com.neoinvisiblearmor.armorgui.ARMOR_GUI_NAME
import com.neoinvisiblearmor.util.ArmorIdentifier
import net.minecraft.server.v1_16_R3.EnumItemSlot
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.PlayerInventory

/**
 * プレイヤーがインベントリーを閉じたときの処理をするクラス
 *
 * @property plugin プラグインのメインクラス
 */
class CloseArmorGUIListener(private val plugin: NeoInvisibleArmor) : Listener {

    /**
     * 閉じたインベントリーがArmorGUIだった場合、アーマーの保存処理を行うメソッド
     *
     * @param event プレイヤーがインベントリーを閉じたのを感知するイベント
     */
    @EventHandler
    fun onCloseInv(event: InventoryCloseEvent) {
        if (event.view.title != ARMOR_GUI_NAME) return
        val player = event.player as Player
        val armorGUI = event.inventory
        val helmet: ItemStack? = armorGUI.contents[10]
        val chestplate: ItemStack? = armorGUI.contents[12]
        val leggings: ItemStack? = armorGUI.contents[14]
        val boots: ItemStack? = armorGUI.contents[16]
        saveHelmetOrGiveBack(player, helmet)
        saveChestplateOrGiveBack(player, chestplate)
        saveLeggingsOrGiveBack(player, leggings)
        saveBootsOrGiveBack(player, boots)
    }

    // プレイヤーのヘルメットを保存するメソッド
    // もしアイテムがヘルメットでない場合、そのアイテムを返す
    private fun saveHelmetOrGiveBack(player: Player, helmet: ItemStack?) {
        val playerArmorComplement = PlayerArmorComplement(plugin, player)
        val armorIdentifier = ArmorIdentifier(helmet?.type)
        if (!armorIdentifier.isItemHelmet()) {
            giveItemBack(player, helmet)
            playerArmorComplement.complementArmor(ItemStack(Material.AIR), EnumItemSlot.HEAD)
            return
        }
        playerArmorComplement.complementArmor(helmet, EnumItemSlot.HEAD)
    }

    // プレイヤーのチェストプレートを保存するメソッド
    // もしアイテムがチェストプレートでない場合、そのアイテムを返す
    private fun saveChestplateOrGiveBack(player: Player, chestplate: ItemStack?) {
        val playerArmorComplement = PlayerArmorComplement(plugin, player)
        val armorIdentifier = ArmorIdentifier(chestplate?.type)
        if (!armorIdentifier.isItemChestplate()) {
            giveItemBack(player, chestplate)
            playerArmorComplement.complementArmor(ItemStack(Material.AIR), EnumItemSlot.CHEST)
            return
        }
        playerArmorComplement.complementArmor(chestplate, EnumItemSlot.CHEST)
    }

    // プレイヤーのレギンスを保存するメソッド
    // もしアイテムがレギンスでない場合、そのアイテムを返す
    private fun saveLeggingsOrGiveBack(player: Player, leggings: ItemStack?) {
        val playerArmorComplement = PlayerArmorComplement(plugin, player)
        val armorIdentifier = ArmorIdentifier(leggings?.type)
        if (!armorIdentifier.isItemLeggings()) {
            giveItemBack(player, leggings)
            playerArmorComplement.complementArmor(ItemStack(Material.AIR), EnumItemSlot.LEGS)
            return
        }
        playerArmorComplement.complementArmor(leggings, EnumItemSlot.LEGS)
    }

    // プレイヤーのブーツを保存するメソッド
    // もしアイテムがブーツでない場合、そのアイテムを返す
    private fun saveBootsOrGiveBack(player: Player, boots: ItemStack?) {
        val playerArmorComplement = PlayerArmorComplement(plugin, player)
        val armorIdentifier = ArmorIdentifier(boots?.type)
        if (!armorIdentifier.isItemBoots()) {
            giveItemBack(player, boots)
            playerArmorComplement.complementArmor(ItemStack(Material.AIR), EnumItemSlot.FEET)
            return
        }
        playerArmorComplement.complementArmor(boots, EnumItemSlot.FEET)
    }

    // アイテムをプレイヤーに返すメソッド
    // インベントリーが満タンだった場合はその場にアイテムを落とす
    private fun giveItemBack(player: Player, item: ItemStack?) {
        val playerInv = player.inventory
        if (item == null) return
        if (isInvFull(playerInv)) {
            player.world.dropItem(player.location, item)
        }
        playerInv.addItem(item)
    }

    // プレイヤーのインベントリーが満タンかどうか確認するメソッド
    private fun isInvFull(playerInv: PlayerInventory): Boolean =
        playerInv.firstEmpty() == -1
}
