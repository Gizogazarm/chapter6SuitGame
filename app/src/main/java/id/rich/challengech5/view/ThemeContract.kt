package id.rich.challengech5.view

import android.content.Context

interface ThemeContract {

    interface View {
        fun updateTheme(theme: String)
        fun getContext(): Context
    }

    interface Presenter {
        fun getCurrentTheme()
        fun updateTheme(theme: String)
    }

}