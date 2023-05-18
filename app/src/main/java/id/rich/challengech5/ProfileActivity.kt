package id.rich.challengech5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import id.rich.challengech5.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnClickListener()
    }

    private fun btnClickListener() {
        binding.btnThemeSetting.setOnClickListener {
            startActivity(Intent(this, ThemeActivity::class.java))

            }
        binding.btnGameHistory.setOnClickListener {

            }
        binding.btnLogOut.setOnClickListener {
                showLogoutDialog()
            }
        }

    private fun showLogoutDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Konfirmasi Logout")
        builder.setMessage("Apakah Anda yakin ingin logout?")
        builder.setPositiveButton("Ya") { dialog, which ->
            logout()
        }
        builder.setNegativeButton("Tidak", null)

        val dialog = builder.create()
        dialog.show()
    }

    private fun logout() {

        // menambahkan implementasi yang sesuai untuk menghapus data sesi pengguna,
        // seperti menghapus token autentikasi atau data sesi lainnya

        startActivity(Intent(this, MenuPageActivity::class.java))
    }

}
