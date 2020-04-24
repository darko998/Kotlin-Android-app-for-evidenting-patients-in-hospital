package rs.raf.projekat1.darko_dimitrijevic_rn9418.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import org.w3c.dom.Text
import rs.raf.projekat1.darko_dimitrijevic_rn9418.R
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.viewmodel.RecyclerFreePatients
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.viewmodel.RecyclerHospitalizePatients
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.viewmodel.RecyclerWaitingRoomPatients

class StateFragment : Fragment(R.layout.fragment_state) {

    val recyclerHospitalizePatients: RecyclerHospitalizePatients by activityViewModels()
    val recyclerWaitingRoomPatients: RecyclerWaitingRoomPatients by activityViewModels()
    val recyclerFreePatients: RecyclerFreePatients by activityViewModels()

    lateinit var hospitalizePatientsNumber: TextView
    lateinit var waitingRoomPatientsNumber: TextView
    lateinit var freePatientsNumber: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)
    }

    fun initView(view: View) {
        hospitalizePatientsNumber = view.findViewById(R.id.tv_patient_number_in_room_value)
        waitingRoomPatientsNumber = view.findViewById(R.id.tv_hospitalize_patients_value)
        freePatientsNumber = view.findViewById(R.id.tv_free_patients_value)

        hospitalizePatientsNumber.setText(recyclerHospitalizePatients.getNumberOfPatients().toString())
        waitingRoomPatientsNumber.setText(recyclerWaitingRoomPatients.getNumberOfPatients().toString())
        freePatientsNumber.setText(recyclerFreePatients.getNumberOfPatients().toString())
    }

}