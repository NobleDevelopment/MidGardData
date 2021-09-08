package org.nobledev.midgard.data.item

import org.bson.codecs.pojo.annotations.BsonId

data class ItemDefinition(
    @BsonId val id : String,
    val displayName : String = "New Item",
    val material : String = "AIR",
    val description : String = "Some Item",
    val levelRequirement : Int = 0,
    val tradeable : Boolean = false,
    val worth : Double = 0.0,
    val rarity : ItemRarity = ItemRarity.SCRAP,
    val type : ItemType = ItemType.OTHER
) {
}