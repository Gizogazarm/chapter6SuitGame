package id.rich.challengech5

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user")
    fun getAllUser(): List<User>
}

@Entity
data class SuitEntity(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "player_id") var player_id: Int?,
    @ColumnInfo(name = "mode") var mode: String?,
    @ColumnInfo(name = "hasil") var hasil: String?
)