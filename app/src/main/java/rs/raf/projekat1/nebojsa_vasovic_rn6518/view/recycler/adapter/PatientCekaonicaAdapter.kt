package rs.raf.projekat1.nebojsa_vasovic_rn6518.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat1.nebojsa_vasovic_rn6518.R
import rs.raf.projekat1.nebojsa_vasovic_rn6518.model.Patient
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.recycler.diff.PatientCekaonicaDiff
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.recycler.viewholder.PatientCekaonicaViewHolder


class PatientCekaonicaAdapter(
    patientCekaonicaDiff: PatientCekaonicaDiff,
    private val onPatientClickedZdrav: (Patient) -> Unit,
    private val onPatientClickedHospitalizacija: (Patient) -> Unit
) :
    ListAdapter<Patient, PatientCekaonicaViewHolder>(patientCekaonicaDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientCekaonicaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val containerView =
            layoutInflater.inflate(R.layout.patient_cekaonica_list_item, parent, false)
        return PatientCekaonicaViewHolder(containerView,
            { val patient = getItem(it);onPatientClickedZdrav.invoke(patient) },
            { val patient = getItem(it);onPatientClickedHospitalizacija.invoke(patient) })
    }

    override fun onBindViewHolder(holder: PatientCekaonicaViewHolder, position: Int) {
        val patient = getItem(position)
        holder.bind(patient)
    }

}