package org.nobledev.midgard.data.profile

import org.bson.codecs.pojo.annotations.BsonId
import org.nobledev.midgard.data.item.ItemInstance
import org.nobledev.midgard.data.skills.Skill
import java.util.EnumMap

data class Profile(
    @BsonId val uuid : String,
    var displayName : String,
    var rank : Rank,
    var balance : Double = 0.0,
    var inventory : List<ItemInstance>,
    var bank : List<ItemInstance>,
    var xp : EnumMap<Skill, Double>
) {
}