package rs.raf.projekat1.darko_dimitrijevic_rn9418.view.recycler.hospitalize.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat1.darko_dimitrijevic_rn9418.R
import rs.raf.projekat1.darko_dimitrijevic_rn9418.model.Patient
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.recycler.hospitalize.diff.HospitalizePatientDiffItemCallback
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.recycler.hospitalize.viewholder.HospitalizePatientViewHolder

class HospitalizePatientAdapter(
    hospitalizePatientDiffItemCallback: HospitalizePatientDiffItemCallback,
    val onPatientClicked: (Patient, Int) -> Unit
) : ListAdapter<Patient, HospitalizePatientViewHolder>(hospitalizePatientDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HospitalizePatientViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView =
            layoutInflater.inflate(R.layout.layout_hospitalize_patient_list_item, parent, false)

        return HospitalizePatientViewHolder(containerView) {it: Int, fleg: Int ->
            val patient = getItem(it)
            onPatientClicked.invoke(patient, fleg)
        }
    }

    override fun onBindViewHolder(holder: HospitalizePatientViewHolder, position: Int) {
        val patient = getItem(position)
        holder.bind(patient)
    }


}