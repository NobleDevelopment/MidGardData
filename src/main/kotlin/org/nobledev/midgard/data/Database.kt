package org.nobledev.midgard.data

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

class Database(val conString : ConnectionString, val databaseName : String) {
    private val clientSettings = MongoClientSettings.builder()
        .applyConnectionString(conString)
        .build()

    private var client = KMongo.createClient(clientSettings).coroutine

    private val db = client.getDatabase(databaseName)

    suspend fun run(action : suspend (connection : CoroutineDatabase) -> Unit) {
        action.invoke(db)
    }

    suspend inline fun <reified T : Any> runWithCollection(crossinline action : suspend (CoroutineCollection<T>) -> Unit) {
        this.run {
            val collection = it.getCollection<T>()
            action.invoke(collection)
        }
    }
}