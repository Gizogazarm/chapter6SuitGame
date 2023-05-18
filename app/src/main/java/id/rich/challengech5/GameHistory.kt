package id.rich.challengech5

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_history")
class GameHistory (
    @PrimaryKey(autoGenerate = true) var id_game: Int?,
    @ColumnInfo("username") var username: String,
    @ColumnInfo("opponent") var opponent: String,
    @ColumnInfo("result") var result: Int
    /*
    * 0 -> lose
    * 1 -> win
    * 2 -> draw
    * */
)