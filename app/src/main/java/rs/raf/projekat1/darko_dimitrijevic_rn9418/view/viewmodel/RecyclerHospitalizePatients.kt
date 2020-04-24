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


    fun deletePatient(patient: Patient) {
        tmpHospitalizePatients.remove(patient)

        val listToSubmit = mutableListOf<Patient>()
        listToSubmit.addAll(tmpHospitalizePatients)

        hospitalizePatients.value = listToSubmit
    }

    fun filterPatients(key: String) {
        val filteredList = tmpHospitalizePatients.filter {
            val fullName = it.name.toLowerCase() + " " + it.lastName.toLowerCase()
            fullName.contains(key.toLowerCase())
        }

        hospitalizePatients.value = filteredList
    }

    fun getPatientWithId(id: Int?): Patient? {
        var tmpPatient: Patient? = null
        tmpHospitalizePatients.map { patient -> if(patient.id == id) tmpPatient = patient}

        return tmpPatient
    }

    fun changePatientDetails(id: Int, name: String, lastName: String, arrivedSymptoms: String, currSymptoms: String) {

        var patient: Patient
        for(i in 0 until tmpHospitalizePatients.size) {
            if (tmpHospitalizePatients[i].id == id) {

                patient = Patient(id, name, lastName, tmpHospitalizePatients[i].pictureUrl, arrivedSymptoms)
                patient.hospitalizeDate = tmpHospitalizePatients[i].hospitalizeDate
                patient.hospitalizeFreeDate = tmpHospitalizePatients[i].hospitalizeFreeDate
                patient.currentSymptoms = currSymptoms

                tmpHospitalizePatients.remove(tmpHospitalizePatients[i])
                tmpHospitalizePatients.add(i,patient)
            }
        }


        val listToSubmit = mutableListOf<Patient>()
        listToSubmit.addAll(tmpHospitalizePatients)

        hospitalizePatients.value = listToSubmit
    }


    fun getNumberOfPatients(): Int {
        return tmpHospitalizePatients.size
    }
}