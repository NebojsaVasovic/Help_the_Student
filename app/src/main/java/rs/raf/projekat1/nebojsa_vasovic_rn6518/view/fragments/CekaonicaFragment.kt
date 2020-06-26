package rs.raf.projekat1.nebojsa_vasovic_rn6518.view.fragments


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_cekaonica.*
import rs.raf.projekat1.nebojsa_vasovic_rn6518.R
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.recycler.adapter.PatientCekaonicaAdapter
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.recycler.diff.PatientCekaonicaDiff
import rs.raf.projekat1.nebojsa_vasovic_rn6518.viewmodel.SharedViewModel


class CekaonicaFragment : Fragment(R.layout.fragment_cekaonica) {

    companion object {
        var scrollToNew = false
    }

    private lateinit var patientCekaonicaAdapter: PatientCekaonicaAdapter

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
        sharedViewModel.getPatientsCekaonica().observe(viewLifecycleOwner, Observer {
            patientCekaonicaAdapter.submitList(it)
        })
    }

    override fun onResume() {
        super.onResume()
        if (scrollToNew && cekaonica_rv.layoutManager != null) {
            cekaonica_rv.layoutManager?.smoothScrollToPosition(
                cekaonica_rv,
                null,
                sharedViewModel.countCekaonica()
            )
            scrollToNew = false
        }
    }


    private fun initUI() {

        cekaonica_rv.layoutManager = LinearLayoutManager(context)

        patientCekaonicaAdapter = PatientCekaonicaAdapter(
            PatientCekaonicaDiff(),
            { sharedViewModel.removePatientCekaonica(it.id) },
            { sharedViewModel.addHospitalizovane(it) }
        )

        cekaonica_rv.adapter = patientCekaonicaAdapter

        cekaonica_et_search.doAfterTextChanged {
            sharedViewModel.filterPatientCekaonica(it.toString())
        }
    }

}
