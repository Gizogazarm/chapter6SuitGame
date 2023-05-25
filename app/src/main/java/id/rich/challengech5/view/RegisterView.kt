package id.rich.challengech5.view

import android.widget.ImageView
import android.widget.TextView

interface RegisterView {
    fun dialogGone(textView: TextView, imageView: ImageView, boolean: Boolean)

    fun showMessage(message: String, looper: Boolean)

    fun nextScreen()
}
