package rs.raf.projekat1.darko_dimitrijevic_rn9418.view.recycler.free.viewholder

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import rs.raf.projekat1.darko_dimitrijevic_rn9418.R
import rs.raf.projekat1.darko_dimitrijevic_rn9418.model.Patient

class FreePatientViewHolder(val containerView: View) : RecyclerView.ViewHolder(containerView) {

    lateinit var circleImageViewPatient: ImageView
    lateinit var patientName: TextView
    lateinit var patientLastName: TextView
    lateinit var patientFreeDate: TextView


    init {
        initView()
    }

    fun bind(patient: Patient) {
        Picasso
            .get()
            .load(patient.pictureUrl.toString())
            .into(circleImageViewPatient)


        patientName.text = patient.name
        patientLastName.text = patient.lastName
        patientFreeDate.text = patient.symptoms
    }

    fun initView() {
        circleImageViewPatient = containerView.findViewById(R.id.circleImageViewPatient)
        patientName = containerView.findViewById(R.id.tv_name_patient)
        patientLastName = containerView.findViewById(R.id.tv_last_name_patient)
        patientFreeDate = containerView.findViewById(R.id.tv_free_date)
    }

}