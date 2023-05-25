package id.rich.challengech5.view

import android.content.Intent
import id.rich.challengech5.database.GameDatabase

interface LoginView {

    fun showMessage(message: String)

    fun nextScreen()

    fun saveLogin()
}