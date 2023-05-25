package id.rich.challengech5.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import id.rich.challengech5.R
import me.relex.circleindicator.CircleIndicator3

class LandingPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.landing_page)


        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        val fragments: ArrayList<Fragment> = arrayListOf(
            LandingPage1(),
            LandingPage2(),
            LoginFragment()
        )


        val indicator = findViewById<CircleIndicator3>(R.id.indicator)
        val adapter = ViewPagerAdapter(fragments, this)
        viewPager.adapter = adapter
        viewPager.setAdapter(adapter)
        indicator.setViewPager(viewPager)

    }
}