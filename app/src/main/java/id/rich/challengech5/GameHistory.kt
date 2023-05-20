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
    * result :
    * 0 -> draw
    * 1 -> lose
    * 2 -> win
    * */
    )