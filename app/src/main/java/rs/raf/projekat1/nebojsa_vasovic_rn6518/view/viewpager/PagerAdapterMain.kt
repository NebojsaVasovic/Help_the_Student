package rs.raf.projekat1.nebojsa_vasovic_rn6518.view.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.fragments.StanjeFragment
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.fragments.ProfilFragment
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.fragments.UnosFragment
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.fragments.ListeFragment

class PagerAdapterMain(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private const val ITEM_COUNT = 4
        const val FRAGMENT_STANJE = 0
        const val FRAGMENT_UNOS = 1
        const val FRAGMENT_LISTE = 2
        const val FRAGMENT_PROFIL = 3
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            FRAGMENT_STANJE -> StanjeFragment()
            FRAGMENT_UNOS -> UnosFragment()
            FRAGMENT_LISTE -> ListeFragment()
            FRAGMENT_PROFIL -> ProfilFragment()
            else -> StanjeFragment()
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            FRAGMENT_STANJE -> "1"
            FRAGMENT_UNOS -> "2"
            FRAGMENT_LISTE -> "3"
            FRAGMENT_PROFIL -> "4"
            else -> "1"
        }
    }

}