package id.rich.challengech5

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [LandingPage3.newInstance] factory method to
 * create an instance of this fragment.
 */
class LandingPage3 : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.landing_page3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bt_next = view.findViewById<Button>(R.id.bt_next)
        val playername = view.findViewById<EditText>(R.id.et_playername)
        val image_glider = view.findViewById<ImageView>(R.id.iv_landingpage3)
        val bt_register = view.findViewById<TextView>(R.id.bt_register)


        Glide.with(this)
            .load("https://i.ibb.co/HC5ZPgD/splash-screen1.png")
            .circleCrop()
            .into(image_glider)

        playername.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        bt_register.setOnClickListener{
            bt_register.setBackgroundResource(R.drawable.background_btnclick)
            val intent = Intent(activity, RegisterActivity::class.java)
            Handler(Looper.getMainLooper()).postDelayed({
               bt_register.setBackgroundResource(R.drawable.background_btnawal)
            }, 1000)

            startActivity(intent)

        }



        bt_next.setOnClickListener {
            val intent = Intent(activity, MenuPageActivity::class.java)
            intent.putExtra("player_name", playername.text.toString())
            startActivity(intent)
            activity?.finish()
        }


    }

}