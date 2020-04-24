package rs.raf.projekat1.darko_dimitrijevic_rn9418.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_patient_file.*
import rs.raf.projekat1.darko_dimitrijevic_rn9418.R
import rs.raf.projekat1.darko_dimitrijevic_rn9418.model.Patient
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.fragments.tabFragments.HospitalizeFragment
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.viewmodel.RecyclerHospitalizePatients

class PatientFileActivity : AppCompatActivity(R.layout.activity_patient_file) {

    lateinit var patient: Patient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUi()
        initListeners()
    }

    fun initUi() {
        var id: Int
        var name: String
        var lastName: String
        var symptoms: String
        var curr_symptoms: String
        var hospitalize_date: String
        var free_date: String


        intent.let {
            id = it.getIntExtra(HospitalizeFragment.PATIENT_ID, -1)
            name = it.getStringExtra(HospitalizeFragment.PATIENT_NAME).toString()
            lastName = it.getStringExtra(HospitalizeFragment.PATIENT_LAST_NAME).toString()
            symptoms = it.getStringExtra(HospitalizeFragment.PATIENT_SYMPTOMS).toString()
            curr_symptoms = it.getStringExtra(HospitalizeFragment.PATIENT_CURR_SYMPTOMS).toString()
            hospitalize_date = it.getStringExtra(HospitalizeFragment.PATIENT_HOSPITALIZE_DATE).toString()
            free_date = it.getStringExtra(HospitalizeFragment.PATIENT_FREE_DATE).toString()
        }

        this.patient = Patient(id, name, lastName, "",symptoms)
        this.patient.currentSymptoms = curr_symptoms
        this.patient.hospitalizeDate = hospitalize_date
        this.patient.hospitalizeFreeDate = free_date

        tv_patient_file_name.setText(patient.name)
        tv_patient_file_last_name.setText(patient.lastName)
        tv_patient_file_arrive_symptoms.setText(patient.symptoms)
        tv_patient_file_current_symptoms.setText(patient.currentSymptoms)
        tv_patient_file_arrive_date.setText(patient.hospitalizeDate)
    }

    fun initListeners() {
        button_patient_file_change.setOnClickListener {

            val name = tv_patient_file_name.text.toString()
            val lastName = tv_patient_file_last_name.text.toString()
            val arriveSymptoms = tv_patient_file_arrive_symptoms.text.toString()
            val currSymptoms = tv_patient_file_current_symptoms.text.toString()

            if(name == "" || lastName == "" || arriveSymptoms == "") {
                Toast.makeText(this, "Morate popuniti sva polja osim polja 'Trenutno stanje' (ovo polje je opciono)!", Toast.LENGTH_LONG).show()
            } else {

                val returnIntent = Intent()
                returnIntent.putExtra(HospitalizeFragment.PATIENT_ID, this.patient.id)
                returnIntent.putExtra(
                    HospitalizeFragment.PATIENT_NAME,
                    tv_patient_file_name.text.toString()
                )
                returnIntent.putExtra(
                    HospitalizeFragment.PATIENT_LAST_NAME,
                    tv_patient_file_last_name.text.toString()
                )
                returnIntent.putExtra(
                    HospitalizeFragment.PATIENT_SYMPTOMS,
                    tv_patient_file_arrive_symptoms.text.toString()
                )
                returnIntent.putExtra(
                    HospitalizeFragment.PATIENT_CURR_SYMPTOMS,
                    tv_patient_file_current_symptoms.text.toString()
                )
                setResult(Activity.RESULT_OK, returnIntent)
                finish()
            }
        }


        button_patient_file_give_up.setOnClickListener {
            finish()
        }
    }
}