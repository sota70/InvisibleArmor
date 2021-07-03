package com.neoinvisiblearmor.armordata.armorloader

import com.neoinvisiblearmor.NeoInvisibleArmor
import com.neoinvisiblearmor.util.ArmorToughnessConverter
import net.minecraft.server.v1_16_R3.EnumItemSlot
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class ArmorToughnessLoader(
    plugin: NeoInvisibleArmor,
    private val player: Player
) : PlayerArmorLoader(plugin, player) {

    fun fetchTotalToughness(): Double {
        val totalPoint = fetchToughnessFromArmorGUI()
        if (totalPoint == 0.0) return fetchToughnessFromArmorContent()
        return totalPoint
    }

    private fun fetchToughnessFromArmorGUI(): Double {
        val helmet = loadArmor(EnumItemSlot.HEAD)
        val chestplate = loadArmor(EnumItemSlot.CHEST)
        val leggings = loadArmor(EnumItemSlot.LEGS)
        val boots = loadArmor(EnumItemSlot.FEET)
        val helmetToughness = ArmorToughnessConverter(helmet.type).convert()
        val chestplateToughness = ArmorToughnessConverter(chestplate.type).convert()
        val leggingsToughness = ArmorToughnessConverter(leggings.type).convert()
        val bootsToughness = ArmorToughnessConverter(boots.type).convert()
        return helmetToughness +
            chestplateToughness +
            leggingsToughness +
            bootsToughness
    }

    private fun fetchToughnessFromArmorContent(): Double {
        val helmet = player.inventory.helmet ?: ItemStack(Material.AIR)
        val chestplate = player.inventory.chestplate ?: ItemStack(Material.AIR)
        val leggings = player.inventory.leggings ?: ItemStack(Material.AIR)
        val boots = player.inventory.boots ?: ItemStack(Material.AIR)
        val helmetToughness = ArmorToughnessConverter(helmet.type).convert()
        val chestplateToughness = ArmorToughnessConverter(chestplate.type).convert()
        val leggingsToughness = ArmorToughnessConverter(leggings.type).convert()
        val bootsToughness = ArmorToughnessConverter(boots.type).convert()
        return helmetToughness +
            chestplateToughness +
            leggingsToughness +
            bootsToughness
    }
}
