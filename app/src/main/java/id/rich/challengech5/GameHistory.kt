package id.rich.challengech5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
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
{

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    fun onCreateViewHolder(parent: ViewGroup, ViewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history, parent, false)
        return ViewHolder(view)
    }
}