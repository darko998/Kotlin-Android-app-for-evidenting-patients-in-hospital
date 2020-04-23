package rs.raf.projekat1.darko_dimitrijevic_rn9418.view.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.fragments.InputPatientFragment
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.fragments.ListFragment
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.fragments.ProfileFragment
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.fragments.StateFragment

class PageAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        const val ITEM_COUNT = 4
        const val STATE_FRAGMENT = 0
        const val INPUT_FRAGMENT = 1
        const val LIST_FRAGMENT = 2
        const val PROFILE_FRAGMENT = 3
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            STATE_FRAGMENT -> StateFragment()
            INPUT_FRAGMENT -> InputPatientFragment()
            LIST_FRAGMENT -> ListFragment()
            else -> ProfileFragment()
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            STATE_FRAGMENT -> "Stanje"
            INPUT_FRAGMENT -> "Unos"
            LIST_FRAGMENT -> "Liste"
            else -> "Profil"
        }
    }

}