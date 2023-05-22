package id.rich.challengech5.ui

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit
import id.rich.challengech5.R
import id.rich.challengech5.databinding.ActivityThemeBinding

class ThemeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThemeBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("ThemePreferences", MODE_PRIVATE)
        editor = sharedPreferences.edit()

        val savedTheme = sharedPreferences.getString("theme", "auto")
        when (savedTheme) {
            "auto" -> binding.rbAutoTheme.isChecked = true
            "light" -> binding.rbLightTheme.isChecked = true
            "dark" -> binding.rbDarkTheme.isChecked = true
        }

        binding.rgThemeOptions.setOnCheckedChangeListener { _, checkedId ->
            val theme = when (checkedId) {
                R.id.rb_autoTheme -> "auto"
                R.id.rb_lightTheme -> "light"
                R.id.rb_darkTheme -> "dark"
                else -> "auto"
            }
            sharedPreferences.edit {
                putString("theme", theme)
                apply()
            }
            updateTheme(theme)
        }

    }

    override fun onResume() {
        super.onResume()
        val currentTheme = sharedPreferences.getString("theme", "auto")
        if (currentTheme != null) {
            updateTheme(currentTheme)
        }
    }

    private fun updateTheme(theme: String) {
        when (theme) {
            "auto" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            "light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            "dark" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}