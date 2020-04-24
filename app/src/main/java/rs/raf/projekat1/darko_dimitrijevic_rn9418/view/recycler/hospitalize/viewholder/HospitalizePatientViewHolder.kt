package rs.raf.projekat1.darko_dimitrijevic_rn9418.view.recycler.hospitalize.viewholder

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import rs.raf.projekat1.darko_dimitrijevic_rn9418.R
import rs.raf.projekat1.darko_dimitrijevic_rn9418.model.Patient

class HospitalizePatientViewHolder(val containerView: View, val onPatientClicked: (Int, Int) -> Unit) : RecyclerView.ViewHolder(containerView) {

    lateinit var circleImageViewPatient: ImageView
    lateinit var patientName: TextView
    lateinit var patientLastName: TextView
    lateinit var patientFileButton: Button
    lateinit var freeButton: Button

    init {
        initView()

        freeButton.setOnClickListener {
            onPatientClicked.invoke(adapterPosition, 0) /** This zero is fleg for freeButton and one is for patientFileButton */
        }

        patientFileButton.setOnClickListener {
            onPatientClicked.invoke(adapterPosition, 1)
        }
    }

    fun bind(patient: Patient) {

        Picasso
            .get()
            .load(patient.pictureUrl.toString())
            .into(circleImageViewPatient)


        patientName.text = patient.name
        patientLastName.text = patient.lastName
    }

    fun initView() {
        circleImageViewPatient = containerView.findViewById(R.id.circleImageViewPatient)
        patientName = containerView.findViewById(R.id.tv_name_patient)
        patientLastName = containerView.findViewById(R.id.tv_last_name_patient)
        patientFileButton = containerView.findViewById(R.id.button_medical_record)
        freeButton = containerView.findViewById(R.id.button_free)
    }
}