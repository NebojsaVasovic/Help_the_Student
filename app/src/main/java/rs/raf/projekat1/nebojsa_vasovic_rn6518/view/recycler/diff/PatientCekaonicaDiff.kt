package rs.raf.projekat1.nebojsa_vasovic_rn6518.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat1.nebojsa_vasovic_rn6518.model.Patient

class PatientCekaonicaDiff : DiffUtil.ItemCallback<Patient>() {


    override fun areItemsTheSame(oldItem: Patient, newItem: Patient): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Patient, newItem: Patient): Boolean {
        return oldItem.picture == newItem.picture &&
                oldItem.name == newItem.name &&
                oldItem.lastName == newItem.lastName &&
                oldItem.conditionRec == newItem.conditionRec
    }

}
