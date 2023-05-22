package id.rich.challengech5.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import id.rich.challengech5.HistoryActivity
import id.rich.challengech5.R
import id.rich.challengech5.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Buat logic jika database yang terpilih laki - laki maka akan terpilih boy.png begitu sebaliknya


        btnClickListener()
    }

    private fun btnClickListener() {
        binding.btnThemeSetting.setOnClickListener {
            startActivity(Intent(this, ThemeActivity::class.java))
        }
        binding.btnGameHistory.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))

        }
        binding.btnLogOut.setOnClickListener {
            val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
            val view = layoutInflater.inflate(R.layout.dialog_logout,null)
            builder.setView(view)

            val btnYes = view.findViewById<Button>(R.id.btn_yes)
            val btnNo = view.findViewById<Button>(R.id.btn_no)

            val dialog = builder.create()

            btnYes.setOnClickListener{
                sharedPreferences = getSharedPreferences("LoginPreferences", MODE_PRIVATE)
                editor = sharedPreferences.edit()
                editor.clear()
                editor.apply()

                dialog.dismiss()
                setResult(3)
                finish()
            }

            btnNo.setOnClickListener{
                dialog.dismiss()
            }

            dialog.setCancelable(false)
            dialog.show()

        }
    }
}