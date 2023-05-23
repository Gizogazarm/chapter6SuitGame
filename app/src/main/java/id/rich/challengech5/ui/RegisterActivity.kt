package id.rich.challengech5.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import id.rich.challengech5.R
import id.rich.challengech5.database.GameDatabase
import id.rich.challengech5.databinding.ActivityRegisterBinding
import id.rich.challengech5.presenter.RegisterPresenterImpl
import id.rich.challengech5.view.RegisterView

@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityRegisterBinding

class RegisterActivity : AppCompatActivity(), RegisterView {
    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //val database: GameDatabase by lazy { GameDatabase.getInstance(this) }
        val rgGender = findViewById<RadioGroup>(R.id.radioGroupGender)
        val getGenderRadioButtonId = rgGender.checkedRadioButtonId
        val gender = findViewById<RadioButton>(getGenderRadioButtonId)

        val database: GameDatabase by lazy { GameDatabase.getInstance(this) }

        val registerPresenterImpl = RegisterPresenterImpl(this, database.userDao())

        with(binding) {

            val builder =
                AlertDialog.Builder(this@RegisterActivity, R.style.CustomAlertDialog).create()
            val view = layoutInflater.inflate(R.layout.info_add_account, null)
            val textDialog = view.findViewById<TextView>(R.id.title_dialog)
            val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
            val imageBerhasil = view.findViewById<ImageView>(R.id.ic_berhasil)
            builder.setView(view)

            btnRegister.setOnClickListener {
                registerPresenterImpl.register(daftarUsername.text.toString(),
                    daftarNama.text.toString(), daftarPasword.text.toString(), gender.text.toString())

//                if (akunTrue) {

//                    dialogGone(textDialog, imageBerhasil, false)
//
//                    // dibuat ketika koneksi database berhasil / berhasil input ke database
//                    builder.setCanceledOnTouchOutside(false)
//                    builder.show()
//
//                    Handler(Looper.getMainLooper()).postDelayed({
//                        progressBar.visibility = View.VISIBLE
//                        Handler(Looper.getMainLooper()).postDelayed({
//                            progressBar.visibility = View.GONE
//                            dialogGone(textDialog, imageBerhasil, false)
//                            Handler(Looper.getMainLooper()).postDelayed(
//                                {
//                                    val intent = Intent(
//                                        this@RegisterActivity,
//                                        LandingPageActivity::class.java
//                                    )
//                                    startActivity(intent)
//                                },
//                                3000
//                            ) // Penundaan sebelum intent ke LandingPageActivity, disetel menjadi 3000 (3 detik)
//                        }, 3000) // Penundaan dialogGone selama 3 detik sebelum intent
//                    }, 1000)

//                }

            }
        }
    }

    override fun showMessage(message: String, looper: Boolean) {
        //Log.d("regist123", "loop : "+ Looper.myLooper())
        if (looper) {
            if (Looper.myLooper() == null){
                Looper.prepare()
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                Looper.loop()
            }
        } else {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun nextScreen() {
        finish()
        showMessage("Register berhasil", true)
    }

    override fun dialogGone(textView: TextView, imageView: ImageView, boolean: Boolean) {
        if (boolean) {
            textView.visibility = View.GONE
            imageView.visibility = View.GONE
        } else {
            textView.visibility = View.VISIBLE
            imageView.visibility = View.VISIBLE
        }
    }
}
