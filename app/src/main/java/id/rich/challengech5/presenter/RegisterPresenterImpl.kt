package id.rich.challengech5.presenter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import id.rich.challengech5.database.GameDatabase
import id.rich.challengech5.database.UserDao
import id.rich.challengech5.model.Gender
import id.rich.challengech5.model.User
import id.rich.challengech5.view.LoginView
import id.rich.challengech5.view.RegisterView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterPresenterImpl(private val view: RegisterView, private val database: GameDatabase) : RegisterPresenter {
    override fun register(username: String, name: String, password: String, gender: String) {

        if (username.isEmpty()) {
            view.messageError("Mohon isi username")
        }
        else if(name.isEmpty()){
            view.messageError("Mohon isi nama")
        }
        else if (password.isEmpty()){
            view.messageError("Mohon isi password")
        }
        else{
            var genderInsert : Gender

            genderInsert = when(gender){
                "Laki-laki" -> Gender.MALE
                else -> Gender.FEMALE
            }

            val objectUser = User(
                username,
                name,
                password,
                genderInsert
            )

            CoroutineScope(Dispatchers.IO).launch {
                database.userDao().insertUser(objectUser)
            }
        }
    }


}