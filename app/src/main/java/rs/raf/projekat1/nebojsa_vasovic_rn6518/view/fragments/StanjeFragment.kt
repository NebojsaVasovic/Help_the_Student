package rs.raf.projekat1.nebojsa_vasovic_rn6518.view.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_stanje.*
import rs.raf.projekat1.nebojsa_vasovic_rn6518.R
import rs.raf.projekat1.nebojsa_vasovic_rn6518.viewmodel.SharedViewModel

class StanjeFragment : Fragment(R.layout.fragment_stanje) {

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }


    private fun init() {
        initObservers()
    }

    private fun initObservers() {
        sharedViewModel.getPatientsCekaonica().observe(viewLifecycleOwner, Observer {
            stanje_cekaonica_tv_value.text = it.size.toString()
        })
        sharedViewModel.getPatientsHospitalizovani().observe(viewLifecycleOwner, Observer {
            stanje_hospitalizovani_tv_value.text = it.size.toString()
        })
        sharedViewModel.getPatientsOtpusteni().observe(viewLifecycleOwner, Observer {
            stanje_otpusteni_tv_value.text = it.size.toString()
        })
    }

}