package id.rich.challengech5.presenter

import android.content.Context
import id.rich.challengech5.database.UserDao
import id.rich.challengech5.model.User
import id.rich.challengech5.view.ProfileContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfilePresenter(
    private val view: ProfileContract.View,
    private val userDao: UserDao,
    private val context: Context
) : ProfileContract.Presenter {

    private lateinit var user: User

    override fun setDataProfil(username: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            if (username != null) {
                user = userDao.findUserByUsername(username)
                withContext(Dispatchers.Main) {
                    view.showUserName(user.name)
                    view.showUserGender(user.gender)
                }
            }
        }
    }

    override fun onThemeSettingClicked() {
        view.navigateToThemeActivity()
    }

    override fun onGameHistoryClicked() {
        // Implementasikan aksi yang diinginkan saat tombol game history diklik
    }

    override fun onLogOutClicked() {
        view.showDialog()
    }

    override fun onLogOutConfirmation() {
        val sharedPreferences =
            context.getSharedPreferences("LoginPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()

        view.dismissDialog()
        view.setResultAndFinish(3)
    }

    override fun onLogOutCanceled() {
        view.dismissDialog()
    }
}
