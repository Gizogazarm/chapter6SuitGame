package id.rich.challengech5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import id.rich.challengech5.model.GameHistory

class ShowHistoryAdapter(val listPlayer: String): RecyclerView.Adapter<GameHistory.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHistory.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history, parent, false)
        return GameHistory.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameHistory.ViewHolder, position: Int) {
        val tvMode: TextView = holder.itemView.findViewById(R.id.tv_content_mode)
        val tvMsg: TextView = holder.itemView.findViewById(R.id.tv_content_msg)

        tvMode.text = listPlayer[position].toString()
        tvMsg.text = listPlayer[position].toString()
    }

    override fun getItemCount(): Int {
        return listPlayer.length
    }
}