package id.rich.challengech5.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import id.rich.challengech5.R
import id.rich.challengech5.database.GameDatabase
import id.rich.challengech5.database.UserDao
import id.rich.challengech5.databinding.ActivityProfileBinding
import id.rich.challengech5.model.Gender
import id.rich.challengech5.presenter.ProfilePresenter
import id.rich.challengech5.view.ProfileContract

class ProfileActivity : AppCompatActivity(), ProfileContract.View {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var userDao: UserDao
    private lateinit var presenter: ProfileContract.Presenter
    private var dialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = GameDatabase.getInstance(this)
        userDao = db.userDao()

        presenter = ProfilePresenter(this, userDao, this)

        val username = intent.getStringExtra("player_name")
        presenter.setDataProfil(username)

        btnClickListener()
    }

    override fun showUserName(name: String) {
        binding.tvNamaUser.text = name
    }

    override fun showUserGender(gender: Gender) {
        if (gender == Gender.FEMALE) {
            binding.ivGender.setImageResource(R.drawable.girl)
        }
    }

    override fun navigateToThemeActivity() {
        startActivity(Intent(this, ThemeActivity::class.java))
    }

    override fun showDialog() {
        val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog)
        val view = layoutInflater.inflate(R.layout.dialog_logout, null)
        builder.setView(view)

        val btnYes = view.findViewById<Button>(R.id.btn_yes)
        val btnNo = view.findViewById<Button>(R.id.btn_no)

        dialog = builder.create()

        btnYes.setOnClickListener {
            presenter.onLogOutConfirmation()
        }

        btnNo.setOnClickListener {
            presenter.onLogOutCanceled()
        }

        dialog?.setCancelable(false)
        dialog?.setOnCancelListener {
            presenter.onLogOutCanceled()
        }
        dialog?.show()
    }

    override fun dismissDialog() {
        dialog?.dismiss()
        dialog = null
    }

    override fun setResultAndFinish(resultCode: Int) {
        setResult(resultCode)
        finish()
    }

    private fun btnClickListener() {
        binding.btnThemeSetting.setOnClickListener {
            presenter.onThemeSettingClicked()
        }
        binding.btnGameHistory.setOnClickListener {
            presenter.onGameHistoryClicked()
        }
        binding.btnLogOut.setOnClickListener {
            presenter.onLogOutClicked()
        }
    }
}
