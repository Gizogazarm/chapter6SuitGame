package id.rich.challengech5

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import id.rich.challengech5.database.GameDatabase
import id.rich.challengech5.databinding.ActivityRegisterBinding
import id.rich.challengech5.model.Gender
import id.rich.challengech5.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityRegisterBinding


/*kondisi dimana AKUN berhasil ditambahkan ke database ya , default false */
private var akunTrue = true


class RegisterActivity : AppCompatActivity() {
    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {

            val builder =
                AlertDialog.Builder(this@RegisterActivity, R.style.CustomAlertDialog).create()
            val view = layoutInflater.inflate(R.layout.info_add_account, null)
            val textDialog = view.findViewById<TextView>(R.id.title_dialog)
            val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
            val imageBerhasil = view.findViewById<ImageView>(R.id.ic_berhasil)
            builder.setView(view)

            btnRegister.setOnClickListener {
                dialogGone(textDialog, imageBerhasil, true)
                if (akunTrue) {
                    // dibuat ketika koneksi database berhasil / berhasil input ke database
                    builder.setCanceledOnTouchOutside(false)
                    builder.show()

                    Handler(Looper.getMainLooper()).postDelayed({
                        progressBar.visibility = View.VISIBLE
                        Handler(Looper.getMainLooper()).postDelayed({
                            progressBar.visibility = View.GONE
                            dialogGone(textDialog, imageBerhasil, false)
                            Handler(Looper.getMainLooper()).postDelayed(
                                {
                                    val intent = Intent(
                                        this@RegisterActivity,
                                        LandingPageActivity::class.java
                                    )
                                    startActivity(intent)
                                },
                                3000
                            ) // Penundaan sebelum intent ke LandingPageActivity, disetel menjadi 3000 (3 detik)
                        }, 3000) // Penundaan dialogGone selama 3 detik sebelum intent
                    }, 1000)

                }
            }
        }
    }

    private fun dialogGone(textView: TextView, imageView: ImageView, boolean: Boolean) {
        if (boolean) {
            textView.visibility = View.GONE
            imageView.visibility = View.GONE
        } else {
            textView.visibility = View.VISIBLE
            imageView.visibility = View.VISIBLE
        }
    }

//    val database: GameDatabase by lazy { GameDatabase.getInstance(this) }
//
//    fun register() {
//        CoroutineScope(Dispatchers.IO).launch {
//            database.userDao().insertUser(User("riky", "Riky", "pasword", Gender.MALE))
//        }
//    }
}
