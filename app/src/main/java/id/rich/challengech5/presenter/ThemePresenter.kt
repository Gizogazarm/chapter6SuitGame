package id.rich.challengech5.presenter

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import id.rich.challengech5.view.ThemeContract

class ThemePresenter(private val view: ThemeContract.View) : ThemeContract.Presenter {
    private lateinit var sharedPreferences: SharedPreferences

    override fun getCurrentTheme() {
        sharedPreferences =
            view.getContext().getSharedPreferences("ThemePreferences", Context.MODE_PRIVATE)
        val currentTheme = sharedPreferences.getString("theme", "auto")
        if (currentTheme != null) {
            view.updateTheme(currentTheme)
        }
    }

    override fun updateTheme(theme: String) {
        sharedPreferences.edit {
            putString("theme", theme)
            apply()
        }
        view.updateTheme(theme)
    }
}