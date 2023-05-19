package id.rich.challengech5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class MenuPageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_page)

        val playername = intent.getStringExtra("player_name")
        val viewMenuPage = findViewById<ConstraintLayout>(R.id.view_menupage)
        val snackbar =
            Snackbar.make(viewMenuPage, "Selamat Datang $playername", Snackbar.LENGTH_INDEFINITE)
        snackbar.setAction("Tutup") {
            snackbar.dismiss()
        }
        snackbar.show()
        val iv_p2p = findViewById<ImageView>(R.id.iv_p2p)
        val iv_p2c = findViewById<ImageView>(R.id.iv_p2c)
        val tv_p2p = findViewById<TextView>(R.id.tv_p2p)
        val tv_p2c = findViewById<TextView>(R.id.tv_p2c)
        val ic_profile = findViewById<ImageView>(R.id.ic_profile)
        val intent = Intent(this, GameActivity::class.java)


        tv_p2p.setText("$playername vs Pemain")
        tv_p2c.setText("$playername vs CPU")

        iv_p2p.setOnClickListener {
            intent.putExtra("enemy", "Pemain 2")
            intent.putExtra("player_name", playername)
            startActivity(intent)
        }

        iv_p2c.setOnClickListener {
            intent.putExtra("enemy", "CPU")
            intent.putExtra("player_name", playername)
            startActivity(intent)
        }

        ic_profile.setOnClickListener {
            ic_profile.setBackgroundResource(R.drawable.bg_button_rounded)
            val intent = Intent(this,ProfileActivity::class.java)
            Handler(Looper.getMainLooper()).postDelayed({
                ic_profile.setBackgroundResource(R.drawable.background_btnawal)
            }, 1000)

            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == 3) {
            startActivity(Intent(this,LandingPageActivity::class.java))
            finish()
        }
    }

}