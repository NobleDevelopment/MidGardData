import com.mongodb.ConnectionString
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.nobledev.midgard.data.Database
import org.nobledev.midgard.data.eq
import org.nobledev.midgard.data.item.ItemDefinition
import org.nobledev.midgard.data.profile.Profile
import org.nobledev.midgard.data.profile.Rank
import org.nobledev.midgard.data.skills.Skill
import java.util.EnumMap
import java.util.UUID

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DataTest {

    val DB_USER : String = System.getenv("db_user")
    val DB_PASS : String = System.getenv("db_pass")
    val DB_URL : String = System.getenv("db_url")
    val connectionString = ConnectionString("mongodb+srv://$DB_USER:$DB_PASS@$DB_URL")
    val database = Database(connectionString, "test")

    @Test
    fun `test profile accuracy`() {
        val profile = Profile(
            UUID.randomUUID().toString(),
            "Test Profile",
            Rank("Test Rank", "Test Rank", listOf()),
            0.0,
            listOf(),
            listOf(),
            EnumMap(Skill::class.java)
        )
        runBlocking {
            database.runWithCollection<Profile> {
                it.insertOne(profile)
            }
            database.runWithCollection<Profile> {
                val serial = it.findOneAndDelete("_id" eq profile.uuid)
                assert(serial != null)
            }
        }

    }
    @Test
    fun `test item definition accuracy`() {
        val item = ItemDefinition(
            "Test Item"
        )
        runBlocking {
            database.runWithCollection<ItemDefinition> {
                it.insertOne(item)
            }
            database.runWithCollection<ItemDefinition> {
                val serial = it.findOneAndDelete("_id" eq item.id)
                assert(serial != null)
            }
        }
    }

    @Test
    fun `test rank accuracy`() {
        val rank = Rank("Test Rank", "Test Rank", listOf("Some.Permission"))
        runBlocking {
            database.runWithCollection<Rank> {
                it.insertOne(rank)
            }
            database.runWithCollection<Rank> {
                val serial = it.findOneAndDelete("_id" eq rank.id)
                assert(serial != null)
            }
        }
    }
}