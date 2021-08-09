package com.neoinvisiblearmor.armordata.loader

import com.neoinvisiblearmor.NeoInvisibleArmor
import com.neoinvisiblearmor.util.EnchantmentEPFConverter
import com.neoinvisiblearmor.util.EnchantmentTypeConverter
import net.minecraft.server.v1_16_R3.EnumItemSlot
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageEvent.DamageCause
import org.bukkit.inventory.ItemStack

/**
 * EPFポイントを取得するクラス
 * ArmorGUIに保存しているEPFポイントが無ければ、ArmorContentから取得する
 *
 * @param plugin プラグインのメインクラス
 * @property player EPFポイントを取得する対象のプレイヤー
 * @property damageType 受けたダメージの種類
 */
class ArmorEPFLoader(
    plugin: NeoInvisibleArmor,
    private val player: Player,
    private val damageType: DamageCause
) : PlayerArmorLoader(plugin, player) {

    /**
     * EPFポイントを取得するメソッド
     * ArmorGUIに保存しているEPFポイントが無ければ、ArmorContentから取得する
     *
     * @return EPFポイントを[Double]型で返す
     */
    fun fetchTotalEPF(): Double {
        val totalPoint = fetchEPFFromArmorGUI()
        if (totalPoint == 0.0) return fetchEPFFromArmorContent()
        return totalPoint
    }

    // ArmorGUIからEPFポイントを取得するメソッド
    private fun fetchEPFFromArmorGUI(): Double {
        var totalPoint = 0.0
        val helmet = loadArmor(EnumItemSlot.HEAD)
        val chestplate = loadArmor(EnumItemSlot.CHEST)
        val leggings = loadArmor(EnumItemSlot.LEGS)
        val boots = loadArmor(EnumItemSlot.FEET)
        val enchantmentTypes =
            EnchantmentTypeConverter(damageType).convertToAppropriateType()
        enchantmentTypes.forEach { type ->
            val helmetPoint = EnchantmentEPFConverter(helmet, type).convert()
            val chestplatePoint = EnchantmentEPFConverter(chestplate, type).convert()
            val leggingsPoint = EnchantmentEPFConverter(leggings, type).convert()
            val bootsPoint = EnchantmentEPFConverter(boots, type).convert()
            totalPoint += helmetPoint +
                chestplatePoint +
                leggingsPoint +
                bootsPoint
        }
        return totalPoint
    }

    // ArmorContentからEPFポイントを取得するメソッド
    private fun fetchEPFFromArmorContent(): Double {
        var totalPoint = 0.0
        val helmet = player.inventory.helmet ?: ItemStack(Material.BARRIER)
        val chestplate = player.inventory.chestplate ?: ItemStack(Material.BARRIER)
        val leggings = player.inventory.leggings ?: ItemStack(Material.BARRIER)
        val boots = player.inventory.boots ?: ItemStack(Material.BARRIER)
        val enchantmentTypes =
            EnchantmentTypeConverter(damageType).convertToAppropriateType()
        enchantmentTypes.forEach { type ->
            val helmetPoint = EnchantmentEPFConverter(helmet, type).convert()
            val chestplatePoint = EnchantmentEPFConverter(chestplate, type).convert()
            val leggingsPoint = EnchantmentEPFConverter(leggings, type).convert()
            val bootsPoint = EnchantmentEPFConverter(boots, type).convert()
            totalPoint += helmetPoint +
                chestplatePoint +
                leggingsPoint +
                bootsPoint
        }
        return totalPoint
    }
}
