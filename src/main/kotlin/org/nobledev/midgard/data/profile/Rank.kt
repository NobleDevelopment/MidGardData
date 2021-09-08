package org.nobledev.midgard.data.profile

import org.bson.codecs.pojo.annotations.BsonId

data class Rank(
    @BsonId val id : String,
    var formattedName : String,
    val permissions : List<String>
) {
}