package rs.raf.projekat1.darko_dimitrijevic_rn9418.view.viewpager

import android.content.Context
import android.util.AttributeSet
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.fragments.tabFragments.FreeFragment
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.fragments.tabFragments.HospitalizeFragment
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.fragments.tabFragments.WaitingRoomFragment

class PageAdapterTab(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        const val ITEM_COUNT = 3
        const val WAITING_ROOM = 0
        const val HOSPITALIZE = 1
        const val FREE = 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            WAITING_ROOM -> WaitingRoomFragment()
            HOSPITALIZE -> HospitalizeFragment()
            else -> FreeFragment()
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            WAITING_ROOM -> "Cekaonica"
            HOSPITALIZE -> "Hospitalizovani"
            else -> "Otpusteni"
        }
    }
}