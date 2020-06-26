package rs.raf.projekat1.nebojsa_vasovic_rn6518.view.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.patient_otpusteni_list_item.*
import rs.raf.projekat1.nebojsa_vasovic_rn6518.model.Patient


class PatientOtpusteniViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {


    fun bind(patient: Patient) {
        Picasso
            .get()
            .load(patient.picture)
            .into(otpusteni_patient_image)
        otpusteni_patient_ime.text = patient.name
        otpusteni_patient_prezime.text = patient.lastName
        otpusteni_patient_vreme.text = patient.dateRel
    }
}