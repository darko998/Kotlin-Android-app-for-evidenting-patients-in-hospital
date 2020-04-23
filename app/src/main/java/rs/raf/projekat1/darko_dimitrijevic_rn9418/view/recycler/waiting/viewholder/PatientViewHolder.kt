package rs.raf.projekat1.darko_dimitrijevic_rn9418.view.recycler.waiting.viewholder

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import rs.raf.projekat1.darko_dimitrijevic_rn9418.R
import rs.raf.projekat1.darko_dimitrijevic_rn9418.model.Patient

class PatientViewHolder(val containerView: View, onPatientClickHospitalize: (Int, Int) -> Unit) : RecyclerView.ViewHolder(containerView) {

    lateinit var circleImageViewPatient: ImageView
    lateinit var patientName: TextView
    lateinit var patientLastName: TextView
    lateinit var patientSymptoms: TextView
    lateinit var hospitalizeButton: Button
    lateinit var healthyButton: Button

    init {
        initView()

        hospitalizeButton.setOnClickListener {
            /** This zero is fleg that tells that call is from hospitalButton */
            onPatientClickHospitalize.invoke(adapterPosition,0)
        }

        healthyButton.setOnClickListener {
            /** This zero is fleg that tells that call is from healthyButton */
            onPatientClickHospitalize.invoke(adapterPosition, 1)
        }
    }

    fun bind(patient: Patient) {

        Picasso
            .get()
            .load(patient.pictureUrl.toString())
            .into(circleImageViewPatient)


        patientName.text = patient.name
        patientLastName.text = patient.lastName
        patientSymptoms.text = patient.symptoms
    }

    fun initView() {
        circleImageViewPatient = containerView.findViewById(R.id.circleImageViewPatient)
        patientName = containerView.findViewById(R.id.tv_name_patient)
        patientLastName = containerView.findViewById(R.id.tv_last_name_patient)
        patientSymptoms = containerView.findViewById(R.id.tv_symptoms)
        hospitalizeButton = containerView.findViewById(R.id.button_hospitalization)
        healthyButton = containerView.findViewById(R.id.button_healthy)
    }
}