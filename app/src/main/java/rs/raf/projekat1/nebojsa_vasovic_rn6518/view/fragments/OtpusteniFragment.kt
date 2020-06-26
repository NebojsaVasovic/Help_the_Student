package rs.raf.projekat1.nebojsa_vasovic_rn6518.view.fragments

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_otpusteni.*
import rs.raf.projekat1.nebojsa_vasovic_rn6518.R
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.recycler.adapter.PatientOtpusteniAdapter
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.recycler.diff.PatientOtpusteniDiff
import rs.raf.projekat1.nebojsa_vasovic_rn6518.viewmodel.SharedViewModel
import java.util.Collections.reverse

class OtpusteniFragment : Fragment(R.layout.fragment_otpusteni) {

    companion object{
        var scrollToChanged = false
    }

    private lateinit var patientOtpusteniAdapter: PatientOtpusteniAdapter

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUI()
        initObservers()
    }

    private fun initObservers() {
        sharedViewModel.getPatientsOtpusteni().observe(viewLifecycleOwner, Observer {
            reverse(it)
            patientOtpusteniAdapter.submitList(it)
        })
    }

    private fun initUI() {

        otpusteni_rv.layoutManager = GridLayoutManager(context,2)

        patientOtpusteniAdapter =
            PatientOtpusteniAdapter(PatientOtpusteniDiff())

        otpusteni_rv.adapter = patientOtpusteniAdapter

        otpusteni_et_search.doAfterTextChanged {
            sharedViewModel.filterPatientOtpusteni(it.toString())
        }
    }

    override fun onResume() {
        super.onResume()
        if (scrollToChanged && otpusteni_rv.layoutManager != null) {
            otpusteni_rv.layoutManager?.smoothScrollToPosition(
                otpusteni_rv,
                null,
                0
            )
            scrollToChanged = false
        }
    }

}
