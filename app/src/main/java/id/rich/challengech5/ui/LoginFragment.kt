package id.rich.challengech5.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import id.rich.challengech5.R
import id.rich.challengech5.database.GameDatabase
import id.rich.challengech5.view.LoginView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment(), LoginView {
    // TODO: Rename and change types of parameters

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bt_login = view.findViewById<Button>(R.id.bt_login)
        val playername = view.findViewById<EditText>(R.id.et_playername)
        val password = view.findViewById<EditText>(R.id.et_password)
        val image_glider = view.findViewById<ImageView>(R.id.iv_landingpage3)
        val bt_register = view.findViewById<TextView>(R.id.bt_register)


        Glide.with(this)
            .load("https://i.ibb.co/HC5ZPgD/splash-screen1.png")
            .circleCrop()
            .into(image_glider)

        bt_register.setOnClickListener{
            bt_register.setBackgroundResource(R.drawable.background_btnclick)
            val intent = Intent(activity, RegisterActivity::class.java)
            Handler(Looper.getMainLooper()).postDelayed({
               bt_register.setBackgroundResource(R.drawable.background_btnawal)
            }, 1000)

            startActivity(intent)

        }

        val database: GameDatabase by lazy { GameDatabase.getInstance(requireActivity()) }

        bt_login.setOnClickListener {
            if (playername.text.isEmpty() || password.text.isEmpty()) {
                showMessage("Mohon isi username dan password")
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    if (database.userDao().getUserByUsername(playername.text.toString(), password.text.toString()).isNotEmpty()) {
                        val intent = Intent(activity, MenuPageActivity::class.java)
                        intent.putExtra("player_name", playername.text.toString())
                        startActivity(intent)
                        requireActivity().finish()
                    } else {
                        Looper.prepare()
                        showMessage("Username atau password salah")
                        Looper.loop()
                    }
                }
            }
        }


    }

    override fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

}