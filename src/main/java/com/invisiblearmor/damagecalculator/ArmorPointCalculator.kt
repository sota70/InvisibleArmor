package com.invisiblearmor.damagecalculator

import com.invisiblearmor.InvisibleArmor
import com.invisiblearmor.armordata.PlayerArmorLoader
import net.minecraft.server.v1_16_R3.EnumItemSlot
import org.bukkit.Material
import org.bukkit.entity.Player

class ArmorPointCalculator(
    private val plugin: InvisibleArmor,
    private val player: Player
) {

    fun calcPlayerArmorPoint(): Double {
        val playerArmorLoader = PlayerArmorLoader(plugin, player)
        return fetchHelmetArmorPoint(playerArmorLoader) +
            fetchChestplateArmorPoint(playerArmorLoader) +
            fetchLeggingsArmorPoint(playerArmorLoader) +
            fetchBootsArmorPoint(playerArmorLoader)
    }

    private fun fetchHelmetArmorPoint(playerArmorLoader: PlayerArmorLoader): Double {
        val playerHelmet = playerArmorLoader.loadArmor(EnumItemSlot.HEAD)
        if (!isHelmet(playerHelmet.type)) return 0.0
        return when (playerHelmet.type) {
            Material.LEATHER_HELMET -> 1.0
            Material.CHAINMAIL_HELMET -> 2.0
            Material.IRON_HELMET -> 2.0
            Material.GOLDEN_HELMET -> 2.0
            Material.DIAMOND_HELMET -> 3.0
            else -> throw IllegalStateException("fetchHelmetArmorPointで予期できない例外が発生しました")
        }
    }

    private fun fetchChestplateArmorPoint(playerArmorLoader: PlayerArmorLoader): Double {
        val playerChestplate = playerArmorLoader.loadArmor(EnumItemSlot.CHEST)
        if (!isChestplate(playerChestplate.type)) return 0.0
        return when (playerChestplate.type) {
            Material.LEATHER_CHESTPLATE -> 3.0
            Material.CHAINMAIL_CHESTPLATE -> 5.0
            Material.IRON_CHESTPLATE -> 6.0
            Material.GOLDEN_CHESTPLATE -> 5.0
            Material.DIAMOND_CHESTPLATE -> 8.0
            else -> throw IllegalStateException("fetchHelmetArmorPointで予期できない例外が発生しました")
        }
    }

    private fun fetchLeggingsArmorPoint(playerArmorLoader: PlayerArmorLoader): Double {
        val playerLeggings = playerArmorLoader.loadArmor(EnumItemSlot.LEGS)
        if (!isLeggings(playerLeggings.type)) return 0.0
        return when (playerLeggings.type) {
            Material.LEATHER_LEGGINGS -> 2.0
            Material.CHAINMAIL_LEGGINGS -> 4.0
            Material.IRON_LEGGINGS -> 5.0
            Material.GOLDEN_LEGGINGS -> 3.0
            Material.DIAMOND_LEGGINGS -> 6.0
            else -> throw IllegalStateException("fetchHelmetArmorPointで予期できない例外が発生しました")
        }
    }

    private fun fetchBootsArmorPoint(playerArmorLoader: PlayerArmorLoader): Double {
        val playerBoots = playerArmorLoader.loadArmor(EnumItemSlot.FEET)
        if (!isBoots(playerBoots.type)) return 0.0
        return when (playerBoots.type) {
            Material.LEATHER_BOOTS -> 1.0
            Material.CHAINMAIL_BOOTS -> 1.0
            Material.IRON_BOOTS -> 2.0
            Material.GOLDEN_BOOTS -> 1.0
            Material.DIAMOND_BOOTS -> 3.0
            else -> throw IllegalStateException("fetchHelmetArmorPointで予期できない例外が発生しました")
        }
    }

    private fun isHelmet(itemType: Material): Boolean {
        val helmet = arrayOf(
            Material.LEATHER_HELMET,
            Material.CHAINMAIL_HELMET,
            Material.IRON_HELMET,
            Material.GOLDEN_HELMET,
            Material.DIAMOND_HELMET
        )
        return itemType in helmet
    }

    private fun isChestplate(itemType: Material): Boolean {
        val chestplate = arrayOf(
            Material.LEATHER_CHESTPLATE,
            Material.CHAINMAIL_CHESTPLATE,
            Material.IRON_CHESTPLATE,
            Material.GOLDEN_CHESTPLATE,
            Material.DIAMOND_CHESTPLATE
        )
        return itemType in chestplate
    }

    private fun isLeggings(itemType: Material): Boolean {
        val leggings = arrayOf(
            Material.LEATHER_LEGGINGS,
            Material.CHAINMAIL_LEGGINGS,
            Material.IRON_LEGGINGS,
            Material.GOLDEN_LEGGINGS,
            Material.DIAMOND_LEGGINGS
        )
        return itemType in leggings
    }

    private fun isBoots(itemType: Material): Boolean {
        val boots = arrayOf(
            Material.LEATHER_BOOTS,
            Material.CHAINMAIL_BOOTS,
            Material.IRON_BOOTS,
            Material.GOLDEN_BOOTS,
            Material.DIAMOND_BOOTS
        )
        return itemType in boots
    }
}
