package rs.raf.projekat1.darko_dimitrijevic_rn9418.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_list.*

import rs.raf.projekat1.darko_dimitrijevic_rn9418.R
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.viewpager.PageAdapterTab

class ListFragment : Fragment(R.layout.fragment_list) {

    lateinit var viewPager: ViewPager
    lateinit var tabLayout: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        init(view)

        return view
    }

    fun init(view: View?) {
        initTabs(view)
    }

    fun initTabs(view: View?) {
        if (view != null) {
            viewPager = view.findViewById(R.id.viewPagerTab)
            tabLayout = view.findViewById(R.id.tabLayout)
        }

        viewPager.adapter = PageAdapterTab(childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }


}
