package id.rich.challengech5.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide
import id.rich.challengech5.R


class SplashScreenActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences = getSharedPreferences("ThemePreferences", MODE_PRIVATE)
        val currentTheme = sharedPreferences.getString("theme", "auto")
        if (currentTheme != null) {
            updateTheme(currentTheme)
        }

        setContentView(R.layout.splash_screen)

        val iv_splashscreen1 = findViewById<ImageView>(R.id.iv_splashscreen1)

        Glide.with(this)
        .load("https://i.ibb.co/HC5ZPgD/splash-screen1.png")
        .circleCrop()
        .into(iv_splashscreen1)

        Handler(Looper.getMainLooper()).postDelayed({
            sharedPreferences = getSharedPreferences("LoginPreferences", MODE_PRIVATE)
            val username = sharedPreferences.getString("username", null)
            var intent = Intent(this, LandingPageActivity::class.java)

            if (username != null) {
                intent = Intent(this, MenuPageActivity::class.java)
                intent.putExtra("player_name", username)
            }

            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }, 2500)
    }

    private fun updateTheme(theme: String) {
        when (theme) {
            "auto" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            "light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            "dark" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}