package rs.raf.projekat1.nebojsa_vasovic_rn6518.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_liste.*
import rs.raf.projekat1.nebojsa_vasovic_rn6518.R
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.viewpager.PagerAdapterList

class ListeFragment : Fragment(R.layout.fragment_liste){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initTabs()
    }

    private fun initTabs() {

        viewPager.adapter = PagerAdapterList(childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }
}