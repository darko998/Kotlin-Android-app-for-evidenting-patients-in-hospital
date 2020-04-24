package rs.raf.projekat1.darko_dimitrijevic_rn9418.view.fragments.tabFragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import rs.raf.projekat1.darko_dimitrijevic_rn9418.R
import rs.raf.projekat1.darko_dimitrijevic_rn9418.model.Patient
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.activity.PatientFileActivity
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.recycler.hospitalize.adapter.HospitalizePatientAdapter
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.recycler.hospitalize.diff.HospitalizePatientDiffItemCallback
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.viewmodel.RecyclerFreePatients
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.viewmodel.RecyclerHospitalizePatients
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class HospitalizeFragment : Fragment() {

    private val hospitalizePatients: RecyclerHospitalizePatients by activityViewModels()
    private val freePatients: RecyclerFreePatients by activityViewModels()

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: HospitalizePatientAdapter
    lateinit var searchView: SearchView

    companion object {
        const val MESSAGE_REQUEST_CODE = 1

        const val PATIENT_ID = "patient_id"
        const val PATIENT_NAME = "patient_name"
        const val PATIENT_LAST_NAME = "patient_last_name"
        const val PATIENT_SYMPTOMS = "patient_sympotms"
        const val PATIENT_CURR_SYMPTOMS = "patient_curr_symptoms"
        const val PATIENT_HOSPITALIZE_DATE = "patient_hospitalize_date"
        const val PATIENT_FREE_DATE = "patient_free_date"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hospitalize, container, false)

        init(view)

        return view
    }

    fun init(view: View) {
        initUi(view)
        initRecycler(view)
        initObservers()
    }

    fun initUi(view: View) {
        searchView = view.findViewById(R.id.hospitalize_search_view)
    }

    fun initRecycler(view: View) {
        recyclerView = view.findViewById(R.id.recycler_view_hospitalize_patients)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        adapter =
            HospitalizePatientAdapter(HospitalizePatientDiffItemCallback()) { it: Patient, fleg: Int ->

                if (fleg == 0) {
                    /** If fleg == 0 that means freeButton is clicked */
                    freePatients.freePatient(it)
                    it.hospitalizeFreeDate = getCurrentDate()
                    hospitalizePatients.deletePatient(it)
                    Toast.makeText(context, "Pacijent ${it.name} je otpusten.", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    /** If fleg == 1 that means patientFileButton is clicked */
                    val intent = Intent(this.context, PatientFileActivity::class.java)
                    intent.putExtra(PATIENT_ID, it.id)
                    intent.putExtra(PATIENT_NAME, it.name)
                    intent.putExtra(PATIENT_LAST_NAME, it.lastName)
                    intent.putExtra(PATIENT_SYMPTOMS, it.symptoms)
                    intent.putExtra(PATIENT_CURR_SYMPTOMS, it.currentSymptoms)
                    intent.putExtra(PATIENT_HOSPITALIZE_DATE, it.hospitalizeDate)
                    intent.putExtra(PATIENT_FREE_DATE, it.hospitalizeFreeDate)
                    startActivityForResult(intent, MESSAGE_REQUEST_CODE)
                }
            }
        recyclerView.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode != Activity.RESULT_OK) {
            Toast.makeText(context, "Neuspesna izmena podataka!", Toast.LENGTH_SHORT).show()
            return
        }

        if (requestCode == MESSAGE_REQUEST_CODE) {

            var id: Int
            var name: String
            var lastName: String
            var symptoms: String
            var curr_symptoms: String

            data?.let {
                id = it.getIntExtra(PATIENT_ID, -1)
                name = it.getStringExtra(PATIENT_NAME).toString()
                lastName = it.getStringExtra(PATIENT_LAST_NAME).toString()
                symptoms = it.getStringExtra(PATIENT_SYMPTOMS).toString()
                curr_symptoms = it.getStringExtra(PATIENT_CURR_SYMPTOMS).toString()


                hospitalizePatients.changePatientDetails(id, name, lastName, symptoms, curr_symptoms)
            }
        }

    }

    fun initObservers() {
        hospitalizePatients.getHospitalizePatients().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                hospitalizePatients.filterPatients(newText.toString())
                return true
            }

        })
    }

    fun getCurrentDate(): String {
        val time = SimpleDateFormat("dd/M/yyyy")
        val date = time.format(Date())

        return date.toString()
    }

}
