package id.rich.challengech5.presenter

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Looper
import id.rich.challengech5.database.GameDatabase
import id.rich.challengech5.database.UserDao
import id.rich.challengech5.ui.MenuPageActivity
import id.rich.challengech5.view.LoginView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginPresenterImpl (private val view: LoginView, private val userDao: UserDao) : LoginPresenter {

    override fun login(username: String, password: String) {
        if (username.isEmpty() || password.isEmpty()) {
            view.showMessage("Mohon isi username dan password")
        } else {
            CoroutineScope(Dispatchers.IO).launch {
                if (userDao.getUserByUsername(username, password).isNotEmpty()) {
                    view.saveLogin()

                    view.nextScreen()
                } else {
                    view.showMessage("Username atau password salah")
                }
            }
        }
    }
}