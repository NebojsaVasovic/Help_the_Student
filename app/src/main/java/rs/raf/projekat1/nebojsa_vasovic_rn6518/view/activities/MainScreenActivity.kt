package rs.raf.projekat1.nebojsa_vasovic_rn6518.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main_screen.*
import rs.raf.projekat1.nebojsa_vasovic_rn6518.R
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.viewpager.PagerAdapterMain


class MainScreenActivity : AppCompatActivity(R.layout.activity_main_screen) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        initViewPager()
        initNavigation()
    }

    private fun initViewPager() {
        viewPager.adapter = PagerAdapterMain(supportFragmentManager)
    }

    private fun initNavigation() {
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_stanje -> {
                    viewPager.setCurrentItem(PagerAdapterMain.FRAGMENT_STANJE, false)
                }
                R.id.navigation_unos -> {
                    viewPager.setCurrentItem(PagerAdapterMain.FRAGMENT_UNOS, false)
                }
                R.id.navigation_liste -> {
                    viewPager.setCurrentItem(PagerAdapterMain.FRAGMENT_LISTE, false)
                }
                R.id.navigation_profil -> {
                    viewPager.setCurrentItem(PagerAdapterMain.FRAGMENT_PROFIL, false)
                }
            }
            true
        }
    }

}