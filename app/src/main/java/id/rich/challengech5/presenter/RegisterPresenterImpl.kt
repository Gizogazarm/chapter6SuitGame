package id.rich.challengech5.presenter

import android.util.Log
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

class RegisterPresenterImpl(private val view: RegisterView, private val database: GameDatabase, private val userDao: UserDao,  var akunTrue: Boolean) : RegisterPresenter {
    override fun register(username: String, name: String, password: String, gender: String) : Boolean {

        if (name.isEmpty()) {
            view.messageError("Mohon isi nama")
            akunTrue = false
        }
        else if(username.isEmpty()){
            view.messageError("Mohon isi username")
            akunTrue = false
        }
        else if (password.isEmpty()){
            view.messageError("Mohon isi password")
            akunTrue = false
        }
        else{
            CoroutineScope(Dispatchers.IO).launch {
                if (userDao?.findUserByUsername(username) != null){
                    val genderInsert : Gender

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

                    if(database.userDao().insertUser(objectUser) > 0){
                        akunTrue = true
                    }
                    else{
                        akunTrue = false
                    }
                }
                else{
                    view.messageError("Username sudah pernah digunakan")
                    akunTrue = false
                }
            }
        }
        return akunTrue
    }
}