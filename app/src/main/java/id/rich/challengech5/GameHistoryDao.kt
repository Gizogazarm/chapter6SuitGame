package id.rich.challengech5

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GameHistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGameHistory(gameHistory: GameHistory)

    @Query("SELECT * FROM game_history")
    fun getAllGameHistory(): List<GameHistory>
}