package id.rich.challengech5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.core.graphics.alpha
import com.bumptech.glide.Glide
import kotlin.time.toDuration


class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        val iv_splashscreen1 = findViewById<ImageView>(R.id.iv_splashscreen1)

        Glide.with(this)
        .load("https://i.ibb.co/HC5ZPgD/splash-screen1.png")
        .circleCrop()
        .into(iv_splashscreen1)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LandingPageActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }, 2500)
    }
}