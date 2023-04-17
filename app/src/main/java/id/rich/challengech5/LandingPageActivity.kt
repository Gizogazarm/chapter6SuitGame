package id.rich.challengech5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import me.relex.circleindicator.CircleIndicator

class LandingPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.landing_page)

        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        val fragments: ArrayList<Fragment> = arrayListOf(
            LandingPage1(),
            LandingPage2(),
            LandingPage3()
        )

        val adapter = ViewPagerAdapter(fragments, this)
        viewPager.adapter = adapter
        viewPager.setAdapter(adapter)


    }
}