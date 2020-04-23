package rs.raf.projekat1.darko_dimitrijevic_rn9418.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import rs.raf.projekat1.darko_dimitrijevic_rn9418.model.Patient
import kotlin.random.Random

class RecyclerWaitingRoomPatients : ViewModel() {

    private val patientsInWaitingRoom : MutableLiveData<List<Patient>> = MutableLiveData()

    private val tmpPatientsWaitingInRoom: MutableList<Patient> = mutableListOf()

    init {
        val patient1 = Patient(1, "Pera", "Peric", "https://images.unsplash.com/photo-1530645298377-82c8416d3f90?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80", "Ne znamo sta je")
        val patient2 = Patient(2, "Mika", "Djuric","https://htmlstream.com/preview/unify-v2.6.3/assets/img-temp/400x450/img5.jpg", "Temperatura")
        val patient3 = Patient(3, "Vidan", "Lazic","https://htmlstream.com/preview/unify-v2.6.3/assets/img-temp/400x450/img5.jpg", "Temperatura i suvi kasalj")
        val patient4 = Patient(4, "Marko", "Vidojevic","https://htmlstream.com/preview/unify-v2.6.3/assets/img-temp/400x450/img5.jpg", "Temperatura")
        val patient5 = Patient(5, "Zika", "Lazic","https://htmlstream.com/preview/unify-v2.6.3/assets/img-temp/400x450/img5.jpg", "Temperatura")

        tmpPatientsWaitingInRoom.add(patient1)
        tmpPatientsWaitingInRoom.add(patient2)
        tmpPatientsWaitingInRoom.add(patient3)
        tmpPatientsWaitingInRoom.add(patient4)
        tmpPatientsWaitingInRoom.add(patient5)


        val tmp = mutableListOf<Patient>()
        tmp.addAll(tmpPatientsWaitingInRoom)
        patientsInWaitingRoom.value = tmp
    }

    fun getPatientsInWaitingRoom(): LiveData<List<Patient>> {
        return patientsInWaitingRoom
    }

    fun addPatient(name: String, lastName: String, symptoms: String) {

        var randomId: Int
        while(true) {
            /** Check if random generated id is available, and loop until it find first available */
            randomId = Random.nextInt(1,1000)
            if(isAvailableId(randomId))
                break
        }

        val patient = Patient(randomId, name, lastName, "https://htmlstream.com/preview/unify-v2.6.3/assets/img-temp/400x450/img5.jpg", symptoms)
        tmpPatientsWaitingInRoom.add(patient)

        val listToSubmit = mutableListOf<Patient>()
        listToSubmit.addAll(tmpPatientsWaitingInRoom)

        patientsInWaitingRoom.value = listToSubmit
    }

    fun isAvailableId(id: Int): Boolean {
        var fleg = true

        patientsInWaitingRoom.value?.map { patient -> if (patient.id == id) fleg = false }

        return fleg
    }

    fun deletePatient(patient: Patient) {
        tmpPatientsWaitingInRoom.remove(patient)

        val listToSubmit = mutableListOf<Patient>()
        listToSubmit.addAll(tmpPatientsWaitingInRoom)

        patientsInWaitingRoom.value = listToSubmit
    }

}