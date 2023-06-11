package id.rich.challengech5.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import id.rich.challengech5.R
import id.rich.challengech5.database.GameDatabase
import id.rich.challengech5.database.UserDao
import id.rich.challengech5.databinding.ActivityProfileBinding
import id.rich.challengech5.model.Gender
import id.rich.challengech5.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var userDao: UserDao
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setDataProfil()
        btnClickListener()
    }

    private fun setDataProfil () {
        val db = GameDatabase.getInstance(this)
        userDao = db.userDao()
        val username = intent.getStringExtra("player_name")

        CoroutineScope(Dispatchers.IO).launch {
            if (username != null) {
                user = userDao.findUserByUsername(username)
            }
            binding.tvNamaUser.text = user.name
        }

        CoroutineScope(Dispatchers.IO).launch {
            if (username != null) {
                user = userDao.findUserByUsername(username)
                if (user.gender == Gender.FEMALE) {
                    binding.ivGender.setImageResource(R.drawable.girl)
                }
            }
        }

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