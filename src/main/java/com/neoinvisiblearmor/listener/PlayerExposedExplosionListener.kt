package com.neoinvisiblearmor.listener

import com.neoinvisiblearmor.util.ExplosionRange
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityExplodeEvent
import org.bukkit.util.Vector
import kotlin.math.round

@Deprecated("Deprecated for too low percentage of explosion knockback reduction")
class PlayerExposedExplosionListener : Listener {

    @EventHandler
    fun onExposed(event: EntityExplodeEvent) {
        val explodedEntity = event.entity
        val explosionCenterLoc = event.location
        val rangeAdjustment = 0.7
        val explosionRange =
            (1.3 * (ExplosionRange.fetch(explodedEntity.type) / 0.225)) * 0.3 + rangeAdjustment
        val exposedPlayers = getAllPlayersExposed(explosionCenterLoc, explosionRange)
        exposedPlayers.forEach { player ->
            player.sendMessage("You've been exposed")
        }
    }

    private fun getAllPlayersExposed(
        explosionCenterLoc: Location,
        explosionRange: Double
    ): MutableList<Player> {
        val players = mutableListOf<Player>()
        val allEntities =
            explosionCenterLoc.world?.entities ?: throw Error("There's no entities in this world")
        val itr = allEntities.iterator()
        while (itr.hasNext()) {
            val entity = itr.next() as Entity
            if (entity !is Player) continue
            if (!isPlayerExposed(explosionCenterLoc, explosionRange, entity.location)) continue
            players.add(entity)
        }
        return players
    }

    private fun isPlayerExposed(
        explosionCenterLoc: Location,
        explosionRange: Double,
        playerLoc: Location
    ): Boolean {
        val distanceFromExplosion = explosionCenterLoc.distance(playerLoc)
        if (distanceFromExplosion >= explosionRange) return false
        println(
            """
            Explosion Center Location: $explosionCenterLoc
            Explosion Range: $explosionRange
            Distance From Explosion: $distanceFromExplosion
            """.trimIndent()
        )
        return true
    }

    private fun calcExplosionKnockback(
        knockbackReduction: Double,
        oldKnockback: Vector
    ): Vector {
        val xKnockback = round(oldKnockback.x - oldKnockback.x * knockbackReduction)
        val yKnockback = round(oldKnockback.y - oldKnockback.y * knockbackReduction)
        val zKnockback = round(oldKnockback.z - oldKnockback.z * knockbackReduction)
        return Vector(xKnockback, yKnockback, zKnockback)
    }
}
