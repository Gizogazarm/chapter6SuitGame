package id.rich.challengech5.view

import id.rich.challengech5.model.Gender

interface ProfileContract {
    interface View {
        fun showUserName(name: String)
        fun showUserGender(gender: Gender)
        fun navigateToThemeActivity()
        fun showDialog()
        fun dismissDialog()
        fun setResultAndFinish(resultCode: Int)
    }

    interface Presenter {
        fun setDataProfil(username: String?)
        fun onThemeSettingClicked()
        fun onGameHistoryClicked()
        fun onLogOutClicked()
        fun onLogOutConfirmation()
        fun onLogOutCanceled()
    }
}
