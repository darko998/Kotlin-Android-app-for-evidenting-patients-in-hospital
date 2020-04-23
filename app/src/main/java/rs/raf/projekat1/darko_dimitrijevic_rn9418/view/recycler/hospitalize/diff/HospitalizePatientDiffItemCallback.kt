package rs.raf.projekat1.darko_dimitrijevic_rn9418.view.recycler.hospitalize.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat1.darko_dimitrijevic_rn9418.model.Patient

class HospitalizePatientDiffItemCallback : DiffUtil.ItemCallback<Patient>() {

    override fun areItemsTheSame(oldItem: Patient, newItem: Patient): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Patient, newItem: Patient): Boolean {
        return oldItem.name == newItem.name &&
                oldItem.lastName == newItem.lastName &&
                oldItem.pictureUrl == newItem.pictureUrl &&
                oldItem.symptoms == newItem.symptoms
    }
}