package id.rich.challengech5.ui

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.room.Database
import androidx.room.Room
import id.rich.challengech5.R
import id.rich.challengech5.database.GameDatabase
import id.rich.challengech5.database.UserDao
import id.rich.challengech5.databinding.ActivityProfileBinding
import id.rich.challengech5.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var userDAO: UserDao
    private lateinit var newUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Buat logic jika database yang terpilih laki - laki maka akan terpilih boy.png begitu sebaliknya
        val db = GameDatabase.getInstance(applicationContext)
        userDAO = db.userDao()

        val coroutineScope = CoroutineScope(Dispatchers.Main)

        coroutineScope.launch {
            val user = withContext(Dispatchers.IO) {
                userDAO.getAllUser()
            }
            val name = user.map { it.name }
            binding.tvNamaUser.text = name.joinToString(", ")
        }

        btnClickListener()
    }

    private fun btnClickListener() {
        binding.btnThemeSetting.setOnClickListener {
            startActivity(Intent(this, ThemeActivity::class.java))
        }
        binding.btnGameHistory.setOnClickListener {

        }
        binding.btnLogOut.setOnClickListener {
            val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
            val view = layoutInflater.inflate(R.layout.dialog_logout, null)
            builder.setView(view)

            val btnYes = view.findViewById<Button>(R.id.btn_yes)
            val btnNo = view.findViewById<Button>(R.id.btn_no)

            val dialog = builder.create()

            btnYes.setOnClickListener {
                sharedPreferences = getSharedPreferences("LoginPreferences", MODE_PRIVATE)
                editor = sharedPreferences.edit()
                editor.clear()
                editor.apply()

                dialog.dismiss()
                setResult(3)
                finish()
            }

            btnNo.setOnClickListener {
                dialog.dismiss()
            }

            dialog.setCancelable(false)
            dialog.show()

        }
    }

}