package com.neoinvisiblearmor.util

import org.bukkit.entity.EntityType

const val TNT_EXPLOSION_RANGE = 4.0

class ExplosionRange {

    companion object {

        fun fetch(entityType: EntityType): Double = when (entityType) {
            EntityType.PRIMED_TNT -> TNT_EXPLOSION_RANGE
            else -> throw Error("This is not an explosion")
        }
    }
}
