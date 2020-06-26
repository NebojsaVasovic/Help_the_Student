package rs.raf.projekat1.nebojsa_vasovic_rn6518.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat1.nebojsa_vasovic_rn6518.R
import rs.raf.projekat1.nebojsa_vasovic_rn6518.model.Patient
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.recycler.diff.PatientOtpusteniDiff
import rs.raf.projekat1.nebojsa_vasovic_rn6518.view.recycler.viewholder.PatientOtpusteniViewHolder

class PatientOtpusteniAdapter(
    patientOtpusteniDiff: PatientOtpusteniDiff
) :
    ListAdapter<Patient, PatientOtpusteniViewHolder>(patientOtpusteniDiff) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PatientOtpusteniViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val containerView =
            layoutInflater.inflate(R.layout.patient_otpusteni_list_item, parent, false)
        return PatientOtpusteniViewHolder(containerView)
    }

    override fun onBindViewHolder(holder: PatientOtpusteniViewHolder, position: Int) {
        val patient = getItem(position)
        holder.bind(patient)
    }

}