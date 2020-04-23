package rs.raf.projekat1.darko_dimitrijevic_rn9418.view.recycler.waiting.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat1.darko_dimitrijevic_rn9418.R
import rs.raf.projekat1.darko_dimitrijevic_rn9418.model.Patient
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.recycler.waiting.diff.PatientDiffItemCallback
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.recycler.waiting.viewholder.PatientViewHolder

class PatientAdapter(patientDiffItemCallback: PatientDiffItemCallback, val onPatientClickedHospitalize: (Patient, Int) -> Unit) : ListAdapter<Patient, PatientViewHolder>(patientDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.layout_waiting_patient_list_item, parent, false)

        return PatientViewHolder(containerView) { it: Int, fleg: Int ->
            /** With fleg we knows which button was clicked on viewholder */
            val patient = getItem(it)
            onPatientClickedHospitalize.invoke(patient, fleg)
        }
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val patient = getItem(position)
        holder.bind(patient)
    }
}