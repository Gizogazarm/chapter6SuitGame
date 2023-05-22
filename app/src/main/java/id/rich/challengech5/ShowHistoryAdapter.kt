package id.rich.challengech5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ShowHistoryAdapter(val listPlayer: List<GameHistory>): RecyclerView.Adapter<GameHistory.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHistory.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history, parent, false)
        return GameHistory.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameHistory.ViewHolder, position: Int) {
        val tvMode: TextView = holder.itemView.findViewById(R.id.tv_content_mode)
        val tvMsg: TextView = holder.itemView.findViewById(R.id.tv_content_msg)

        tvMode.text = listPlayer[position].opponent
        tvMsg.text = listPlayer[position].result.toString()
    }

    override fun getItemCount(): Int {
        return listPlayer.size
    }
}