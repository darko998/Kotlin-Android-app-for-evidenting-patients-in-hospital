package rs.raf.projekat1.darko_dimitrijevic_rn9418.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import rs.raf.projekat1.darko_dimitrijevic_rn9418.R
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.viewpager.PageAdapter

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    fun init(){
        initViewPager()
        initNavigation()
    }

    fun initViewPager() {
        viewPager.adapter = PageAdapter(supportFragmentManager)
    }


    fun initNavigation() {
        bottom_nav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_state -> {
                    viewPager.setCurrentItem(PageAdapter.STATE_FRAGMENT, false)
                }
                R.id.nav_input -> {
                    viewPager.setCurrentItem(PageAdapter.INPUT_FRAGMENT, false)
                }
                R.id.nav_list -> {
                    viewPager.setCurrentItem(PageAdapter.LIST_FRAGMENT)
                }
                else -> {
                    viewPager.setCurrentItem(PageAdapter.PROFILE_FRAGMENT, false)
                }
            }

            true
        }
    }
}
