package id.rich.challengech5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import id.rich.challengech5.databinding.ActivityRegisterBinding

private lateinit var binding: ActivityRegisterBinding

// testing commit feature register

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {


        }

    }

}