package id.rich.challengech5.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import id.rich.challengech5.R
import id.rich.challengech5.databinding.ActivityThemeBinding
import id.rich.challengech5.presenter.ThemePresenter
import id.rich.challengech5.view.ThemeContract

class ThemeActivity : AppCompatActivity(), ThemeContract.View {
    private lateinit var binding: ActivityThemeBinding
    private lateinit var presenter: ThemeContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = ThemePresenter(this)
        presenter.getCurrentTheme()

        binding.rgThemeOptions.setOnCheckedChangeListener { _, checkedId ->
            val theme = when (checkedId) {
                R.id.rb_autoTheme -> "auto"
                R.id.rb_lightTheme -> "light"
                R.id.rb_darkTheme -> "dark"
                else -> "auto"
            }
            presenter.updateTheme(theme)
        }
    }

    override fun updateTheme(theme: String) {
        when (theme) {
            "auto" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            "light" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            "dark" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    override fun getContext(): Context {
        return this
    }
}