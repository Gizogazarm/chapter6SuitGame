package id.rich.challengech5.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_history")
data class GameHistory (
    @PrimaryKey(autoGenerate = true) var id_game: Int?,
    @ColumnInfo("username") var username: String,
    @ColumnInfo("opponent") var opponent: String,
    @ColumnInfo("result") var result: GameResult
)

enum class GameResult { DRAW, LOSE, WIN }