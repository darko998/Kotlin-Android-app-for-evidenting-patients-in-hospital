package rs.raf.projekat1.darko_dimitrijevic_rn9418.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import rs.raf.projekat1.darko_dimitrijevic_rn9418.model.Patient

class RecyclerFreePatients : ViewModel() {

    var freePatients: MutableLiveData<List<Patient>> = MutableLiveData()

    var tmpFreePatients: MutableList<Patient> = mutableListOf()

    fun getFreePatients() : LiveData<List<Patient>>{
        return freePatients
    }

    fun freePatient(patient: Patient) {

        if(!isPatientAlreadyFree(patient)) {
            tmpFreePatients.add(patient)

            val listToSubmit = mutableListOf<Patient>()
            listToSubmit.addAll(tmpFreePatients)

            freePatients.value = listToSubmit
        }
    }

    fun isPatientAlreadyFree(patient: Patient) : Boolean {
        /** Check if patient is already in freePatients list */

        var fleg = false

        tmpFreePatients.map { tmpPatient -> if(patient.id == tmpPatient.id) fleg = true }

        return fleg
    }

    fun filterPatients(key: String) {
        val filteredList = tmpFreePatients.filter {
            val fullName = it.name.toLowerCase() + " " + it.lastName.toLowerCase()
            fullName.contains(key.toLowerCase())
        }

        freePatients.value = filteredList
    }

    fun getNumberOfPatients(): Int {
        return tmpFreePatients.size
    }
}