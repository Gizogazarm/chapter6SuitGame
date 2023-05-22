package id.rich.challengech5

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.rich.challengech5.database.GameDatabase
import id.rich.challengech5.databinding.ActivityHistoryBinding
import id.rich.challengech5.ui.ProfileActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    private var mDb : GameDatabase? = null
    var valID: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()
        valID = intent.getStringExtra("vaLID").toString()
        Log.d("VAL_ID", valID)

        mDb = GameDatabase.getInstance(this@HistoryActivity)
        binding.rvPlayer.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        ferchData()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, ProfileActivity::class.java))
        finish()
    }

    fun ferchData(){
        GlobalScope.launch {
            val listSuit = mDb?.gameHistoryDao()?.findGameHistoryByUsername(valID.toInt())

            runOnUiThread{
                listSuit?.let {
                    val adapter = ShowHistoryAdapter(it)
                    binding.rvPlayer.adapter = adapter
                }
            }
        }
    }
}