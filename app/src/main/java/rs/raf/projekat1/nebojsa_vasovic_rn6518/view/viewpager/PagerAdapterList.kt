package rs.raf.projekat1.nebojsa_vasovic_rn6518.view.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.fragments.*

class PagerAdapterList(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private const val ITEM_COUNT = 3
        const val FRAGMENT_CEKAONICA = 0
        const val FRAGMENT_HOSPITALIZOVANI = 1
        const val FRAGMENT_OTPUSTENI = 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            FRAGMENT_CEKAONICA -> CekaonicaFragment()
            FRAGMENT_HOSPITALIZOVANI -> HospitalizovaniFragment()
            FRAGMENT_OTPUSTENI-> OtpusteniFragment()
            else -> CekaonicaFragment()
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            FRAGMENT_CEKAONICA -> "Čekaonica"
            FRAGMENT_HOSPITALIZOVANI -> "Hospitalizovani"
            FRAGMENT_OTPUSTENI -> "Otpušteni"
            else -> "Čekaonica"
        }
    }

}