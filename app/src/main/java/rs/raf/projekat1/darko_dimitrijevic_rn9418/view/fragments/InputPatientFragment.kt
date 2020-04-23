package rs.raf.projekat1.darko_dimitrijevic_rn9418.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import rs.raf.projekat1.darko_dimitrijevic_rn9418.R
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.viewmodel.RecyclerWaitingRoomPatients

class InputPatientFragment : Fragment(R.layout.fragment_input_patient) {

    private val recyclerWaitingRoomPatients: RecyclerWaitingRoomPatients by activityViewModels()

    lateinit var nameTv: TextView
    lateinit var lastNameTv: TextView
    lateinit var symptomsTv: TextView
    lateinit var addInWaitingRoomButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        if(view != null)
            init(view)

        return view
    }

    fun init(view: View) {
        initView(view)
        initListeners()
    }

    fun initView(view: View){
        addInWaitingRoomButton = view.findViewById(R.id.button_add_in_waiting_room)
        nameTv = view.findViewById(R.id.tv_input_name)
        lastNameTv = view.findViewById(R.id.tv_input_last_name)
        symptomsTv = view.findViewById(R.id.tv_input_symptoms)
    }

    fun initListeners() {
        addInWaitingRoomButton.setOnClickListener {
            val name = nameTv.text.toString()
            val lastName = lastNameTv.text.toString()
            val symptoms = symptomsTv.text.toString()

            if(name == "" || lastName == "" || symptoms == ""){
                Toast.makeText(this.context, "Morate popuniti sva polja!", Toast.LENGTH_SHORT).show()
            } else {
                recyclerWaitingRoomPatients.addPatient(name,lastName,symptoms)
                Toast.makeText(this.context, "Uspesan unos u cekaonicu.", Toast.LENGTH_SHORT).show()
                clearTextViews()
            }
        }
    }

    fun clearTextViews(){
        nameTv.text = ""
        lastNameTv.text = ""
        symptomsTv.text = ""
    }

}