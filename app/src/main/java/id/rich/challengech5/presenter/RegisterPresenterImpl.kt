package id.rich.challengech5.presenter

import android.widget.Toast
import id.rich.challengech5.database.UserDao
import id.rich.challengech5.model.Gender
import id.rich.challengech5.model.User
import id.rich.challengech5.view.RegisterView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterPresenterImpl(private val view: RegisterView, private val userDao: UserDao) : RegisterPresenter {
    override fun register(username: String, name: String, password: String, gender: String) {
        if (name.isEmpty()) {
            view.showMessage("Mohon isi nama", false)
        }
        else if(username.isEmpty()){
            view.showMessage("Mohon isi username", false)
        }
        else if (password.isEmpty()){
            view.showMessage("Mohon isi password", false)
        }
        else{
            CoroutineScope(Dispatchers.IO).launch {
                //Log.d("regist123", "register1 : "+ userDao.findUserByUsername(username))
                if (userDao.findUserByUsername(username) == null){

                    val genderInsert : Gender = when(gender){
                        "Laki-laki" -> Gender.MALE
                        else -> Gender.FEMALE
                    }

                    if(userDao.insertUser(User(username, name, password, genderInsert)) > 0){
                        view.nextScreen()
                    }
                }
                else{
                    view.showMessage("Username sudah pernah digunakan",true)
                }
            }
        }
    }
}