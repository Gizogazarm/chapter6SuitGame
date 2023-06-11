//package id.rich.challengech5.ui
//
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import id.rich.challengech5.database.GameDatabase
//import id.rich.challengech5.binding.ActivityHistoryBinding
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch
//
//class HistoryActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityHistoryBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
//
//        supportActionBar?.hide()
//        valID = intent.getStringExtra("valID").toString()
//        Log.d("VAL_ID", valID)
//
//        mDb = GameDatabase.getInstance(this@HistoryActivity)
//        binding.rvPlayer.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        fetchData()
//    }
//
//    override fun onBackPressed() {
//        super.onBackPressed()
//        startActivity(Intent(this, ProfileActivity::class.java))
//        finish()
//    }
//
//    fun fetchData() {
//        GlobalScope.launch {
//            val listSuit = mDb?.gameHistoryDao()?.findGameHistoryByUsername(valID.toInt())
//
//            runOnUiThread {
//                listSuit?.let {
//                    val adapter = ShowHistoryAdapter(it)
//                    binding.rvPlayer.adapter = adapter
//                }
//            }
//        }
//    }
//}
//

package id.rich.challengech5.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.rich.challengech5.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity(){
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var adapter: ShowHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ShowHistoryAdapter(arrayListOf(), this@HistoryActivity)
        binding.rvPlayer.layoutManager = LinearLayoutManager(this)
        binding.rvPlayer.setHasFixedSize(true)
        binding.rvPlayer.adapter = adapter
        getSharedPreferences()

    }

    fun getSharedPreferences() {
        TODO("Not yet implemented")
    }

}


