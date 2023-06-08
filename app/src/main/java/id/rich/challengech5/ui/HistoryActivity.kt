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

import java.util.Arrays
import java.util.Arrays.asList

interface History {
    interface View {
        fun showProgress()
        fun hideProgress()
        fun setString(string: String?)
    }

    interface Model {
        interface OnFinishedListener {
            fun onFinished(string: String?)
        }
        fun getNextHistory(onFinishedListener: OnFinishedListener?)
    }

    interface Presenter {
        fun onButtonClick()

        fun onDestroy()
    }
}

import android.os.Handler

class Model : History.Model {
    private val arrayList =
        Arrays.asList(

        )asList(

        )

    override fun getNextHistory(onFinishedListener: History.Model.OnFinishedListener?) {
        Handler().postDelayed({ onFinishedListener!!.onFinished(getRandomString)}, 1200)
    }

    private val getRandomString: String

}




import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.annotations.Contract

class HistoryActivity : AppCompatActivity(), Contract.View{

}
