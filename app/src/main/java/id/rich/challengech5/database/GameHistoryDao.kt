package id.rich.challengech5.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.rich.challengech5.model.GameHistory

@Dao
interface GameHistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertGameHistory(gameHistory: GameHistory)

    @Query("SELECT * FROM game_history")
    suspend fun getAllGameHistory(): List<GameHistory>


    @Query("SELECT * FROM GAME_HISTORY WHERE " +
            "username = :username")
    fun findGameHistoryByUsername(username: Int): List<GameHistory>


}