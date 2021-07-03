package com.neoinvisiblearmor.armordata.armorloader

import com.neoinvisiblearmor.NeoInvisibleArmor
import com.neoinvisiblearmor.util.ArmorPointConverter
import net.minecraft.server.v1_16_R3.EnumItemSlot
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageEvent.DamageCause

/**
 * プレイヤーのアーマーポイントをロードするクラス
 *
 * @param plugin プラグインのメインクラス
 * @property player アーマーをロードする対象のプレイヤー
 * @property damageType 受けたダメージの種類
 */
class ArmorPointLoader(
    plugin: NeoInvisibleArmor,
    private val player: Player,
    private val damageType: DamageCause
) : PlayerArmorLoader(plugin, player) {

    /**
     * アーマーポイントを取得するメソッド
     * ArmorGUIに保存しているアーマーがなければ、ArmorContentからポイントを取得する
     *
     * @return アーマーポイントを取得して[Double]型で返す
     */
    fun fetchTotalPoint(): Double {
        var totalPoint = fetchArmorPointFromArmorGUI()

        /*
         ある特定のダメージの種類にはアーマーによるダメージ軽減が適応しないものがあるため
         if文でそれらを最初に抜き出して、0.0を返すようにする必要がある
         そうしないと、ちゃんとしたダメージが計算できない
         */
        if (damageType != DamageCause.ENTITY_ATTACK &&
            damageType != DamageCause.PROJECTILE &&
            damageType != DamageCause.FIRE &&
            damageType != DamageCause.LAVA &&
            damageType != DamageCause.CONTACT &&
            damageType != DamageCause.ENTITY_EXPLOSION &&
            damageType != DamageCause.BLOCK_EXPLOSION &&
            damageType != DamageCause.LIGHTNING &&
            damageType != DamageCause.FALLING_BLOCK &&
            damageType != DamageCause.THORNS
        ) {
            return 0.0
        }
        if (totalPoint == 0.0) {
            totalPoint = fetchArmorPointFromArmorContent()
        }
        return totalPoint
    }

    // ArmorContentからアーマーポイントを取得するメソッド
    private fun fetchArmorPointFromArmorContent(): Double {
        val helmetType = player.inventory.helmet?.type ?: Material.BARRIER
        val chestplateType = player.inventory.chestplate?.type ?: Material.BARRIER
        val leggingsType = player.inventory.leggings?.type ?: Material.BARRIER
        val bootsType = player.inventory.boots?.type ?: Material.BARRIER
        val helmetPoint = ArmorPointConverter(helmetType).convert()
        val chestplatePoint = ArmorPointConverter(chestplateType).convert()
        val leggingsPoint = ArmorPointConverter(leggingsType).convert()
        val bootsPoint = ArmorPointConverter(bootsType).convert()
        return helmetPoint +
            chestplatePoint +
            leggingsPoint +
            bootsPoint
    }

    // ArmorGUIからアーマーポイントを取得するメソッド
    private fun fetchArmorPointFromArmorGUI(): Double {
        val helmetType = loadArmor(EnumItemSlot.HEAD).type
        val chestplateType = loadArmor(EnumItemSlot.CHEST).type
        val leggingsType = loadArmor(EnumItemSlot.LEGS).type
        val bootsType = loadArmor(EnumItemSlot.FEET).type
        val helmetPoint = ArmorPointConverter(helmetType).convert()
        val chestplatePoint = ArmorPointConverter(chestplateType).convert()
        val leggingsPoint = ArmorPointConverter(leggingsType).convert()
        val bootsPoint = ArmorPointConverter(bootsType).convert()
        return helmetPoint +
            chestplatePoint +
            leggingsPoint +
            bootsPoint
    }
}
