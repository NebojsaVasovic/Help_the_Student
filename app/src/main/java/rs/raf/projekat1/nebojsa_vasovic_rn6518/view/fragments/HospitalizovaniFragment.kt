package rs.raf.projekat1.nebojsa_vasovic_rn6518.view.fragments


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_hospitalizovani.*


import rs.raf.projekat1.nebojsa_vasovic_rn6518.R
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.activities.KartonPacijentaActivity
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.recycler.adapter.PatientHospitalizovaniAdapter
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.recycler.diff.PatientHospitalizovaniDiff
import rs.raf.projekat1.nebojsa_vasovic_rn6518.viewmodel.SharedViewModel
import kotlin.properties.Delegates

class HospitalizovaniFragment : Fragment(R.layout.fragment_hospitalizovani) {

    companion object {
        const val KEY_INTENT = 123
        const val NAME_KEY = "name"
        const val LAST_NAME_KEY = "last_name"
        const val CON_REC = "con_rec"
        const val CON_REL = "con_rel"
        const val DATE_REC = "date_rec"
    }

    private lateinit var patientHospitalizovaniAdapter: PatientHospitalizovaniAdapter

    private val sharedViewModel: SharedViewModel by activityViewModels()

    private var id by Delegates.notNull<Long>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUI()
        initObservers()
    }

    private fun initObservers() {
        sharedViewModel.getPatientsHospitalizovani().observe(viewLifecycleOwner, Observer {
            patientHospitalizovaniAdapter.submitList(it)
        })
    }

    private fun initUI() {

        hospitalizovani_rv.layoutManager = LinearLayoutManager(context)

        patientHospitalizovaniAdapter =
            PatientHospitalizovaniAdapter(PatientHospitalizovaniDiff(), {
                sharedViewModel.addOtpustene(it)
            }, {
                id = it.id
                val intent = Intent(activity, KartonPacijentaActivity::class.java)
                intent.putExtra(NAME_KEY, it.name)
                intent.putExtra(LAST_NAME_KEY, it.lastName)
                intent.putExtra(CON_REC, it.conditionRec)
                intent.putExtra(CON_REL, it.conditionRel)
                intent.putExtra(DATE_REC, it.dateRec)
                startActivityForResult(intent, KEY_INTENT)
            })
        hospitalizovani_rv.adapter = patientHospitalizovaniAdapter

        hospitalizovani_et_search.doAfterTextChanged {
            sharedViewModel.filterPatientHospitalizovani(it.toString())
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        if (requestCode == KEY_INTENT) {
            if (data != null) {
                sharedViewModel.izmeniPatientHospitalizovani(
                    id,
                    data.getStringExtra(NAME_KEY),
                    data.getStringExtra(LAST_NAME_KEY),
                    data.getStringExtra(CON_REC),
                    data.getStringExtra(CON_REL),
                    data.getStringExtra(DATE_REC)
                )
            }
        }
    }
}

