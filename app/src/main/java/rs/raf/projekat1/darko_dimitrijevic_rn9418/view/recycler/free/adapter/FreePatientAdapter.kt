package rs.raf.projekat1.darko_dimitrijevic_rn9418.view.recycler.free.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat1.darko_dimitrijevic_rn9418.R
import rs.raf.projekat1.darko_dimitrijevic_rn9418.model.Patient
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.recycler.free.diff.FreePatientDiffItemCallback
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.recycler.free.viewholder.FreePatientViewHolder

class FreePatientAdapter(freePatientDiffItemCallback: FreePatientDiffItemCallback) : ListAdapter<Patient, FreePatientViewHolder>(freePatientDiffItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FreePatientViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.layout_free_patient_list_item, parent, false)

        return FreePatientViewHolder(containerView)
    }

    override fun onBindViewHolder(holder: FreePatientViewHolder, position: Int) {
        val patient = getItem(position)
        holder.bind(patient)
    }
}