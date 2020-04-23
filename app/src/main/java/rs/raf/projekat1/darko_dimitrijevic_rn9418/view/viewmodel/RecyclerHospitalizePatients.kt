package rs.raf.projekat1.darko_dimitrijevic_rn9418.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import rs.raf.projekat1.darko_dimitrijevic_rn9418.model.Patient

class RecyclerHospitalizePatients : ViewModel() {

    var hospitalizePatients: MutableLiveData<List<Patient>> = MutableLiveData()

    var tmpHospitalizePatients: MutableList<Patient> = mutableListOf()

    fun getHospitalizePatients(): LiveData<List<Patient>> {
        return hospitalizePatients
    }

    fun hospitalizePatient(patient: Patient) {

        if(!isPatientAlreadyHospitalize(patient)) {
            tmpHospitalizePatients.add(patient)

            val listToSubmit = mutableListOf<Patient>()
            listToSubmit.addAll(tmpHospitalizePatients)

            hospitalizePatients.value = listToSubmit
        }
    }

    fun isPatientAlreadyHospitalize(patient: Patient) : Boolean {
        /** Check if patient is already in freePatients list */

        var fleg = false
        tmpHospitalizePatients.map { tmpPatient -> if(patient.id == tmpPatient.id) fleg = true }

        return fleg
    }

}