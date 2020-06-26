package rs.raf.projekat1.nebojsa_vasovic_rn6518.view.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.patient_hospitalizovani_list_item.*
import rs.raf.projekat1.nebojsa_vasovic_rn6518.model.Patient


class PatientHospitalizovaniViewHolder(
    override val containerView: View,
    private val onItemClickedOtpust: (Int) -> Unit,
    private val onItemClickedKarton: (Int) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    init {
        hospitalizovani_patient_btn_otpust.setOnClickListener {
            if(adapterPosition!=-1)
                onItemClickedOtpust.invoke(adapterPosition)
        }
        hospitalizovani_patient_btn_karton.setOnClickListener {
            if(adapterPosition!=-1)
                onItemClickedKarton.invoke(adapterPosition)
        }
    }


    fun bind(patient: Patient) {
        Picasso
            .get()
            .load(patient.picture)
            .into(hospitalizovani_patient_image)
        hospitalizovani_patient_ime.text = patient.name
        hospitalizovani_patient_prezime.text = patient.lastName
    }
}