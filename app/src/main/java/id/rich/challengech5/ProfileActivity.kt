package id.rich.challengech5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.rich.challengech5.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnClickListener()
    }

    private fun btnClickListener() {
        binding.btnThemeSetting.setOnClickListener {
            startActivity(Intent(this, ThemeActivity::class.java))
        }
        binding.btnGameHistory.setOnClickListener {

        }
        binding.btnLogOut.setOnClickListener {
            startActivity(Intent(this, ThemeActivity::class.java))
        }
    }
}