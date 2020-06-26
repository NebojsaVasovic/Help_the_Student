package rs.raf.projekat1.nebojsa_vasovic_rn6518.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat1.nebojsa_vasovic_rn6518.R
import rs.raf.projekat1.nebojsa_vasovic_rn6518.model.Patient
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.recycler.diff.PatientHospitalizovaniDiff
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.recycler.viewholder.PatientHospitalizovaniViewHolder

class PatientHospitalizovaniAdapter(
    patientHospitalizovaniDiff: PatientHospitalizovaniDiff,
    private val onPatientClickedOtpust: (Patient) -> Unit,
    private val onPatientClickedKarton: (Patient) -> Unit
) :
    ListAdapter<Patient, PatientHospitalizovaniViewHolder>(patientHospitalizovaniDiff) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PatientHospitalizovaniViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val containerView =
            layoutInflater.inflate(R.layout.patient_hospitalizovani_list_item, parent, false)
        return PatientHospitalizovaniViewHolder(containerView, {
            val patient = getItem(it)
            onPatientClickedOtpust.invoke(patient)
        }, {
            val patient = getItem(it)
            onPatientClickedKarton.invoke(patient)
        })
    }

    override fun onBindViewHolder(holder: PatientHospitalizovaniViewHolder, position: Int) {
        val patient = getItem(position)
        holder.bind(patient)
    }

}