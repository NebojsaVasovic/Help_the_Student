package rs.raf.projekat1.nebojsa_vasovic_rn6518.view.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.patient_cekaonica_list_item.*
import rs.raf.projekat1.nebojsa_vasovic_rn6518.model.Patient

class PatientCekaonicaViewHolder(
    override val containerView: View,
    private val onItemClickedZdrav: (Int) -> Unit,
    private val onItemClickedHospitalizacija: (Int) -> Unit
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    init {
        cekaonica_patient_btn_zdrav.setOnClickListener {
            if(adapterPosition!=-1)
                onItemClickedZdrav.invoke(adapterPosition)
        }
        cekaonica_patient_btn_hospitalizacija.setOnClickListener {
            if(adapterPosition!=-1)
                onItemClickedHospitalizacija.invoke(adapterPosition)
        }
    }


    fun bind(patient: Patient) {
        Picasso
            .get()
            .load(patient.picture)
            .into(cekaonica_patient_image)
        cekaonica_patient_ime.text = patient.name
        cekaonica_patient_prezime.text = patient.lastName
        cekaonica_patient_stanje.text = patient.conditionRec
    }
}