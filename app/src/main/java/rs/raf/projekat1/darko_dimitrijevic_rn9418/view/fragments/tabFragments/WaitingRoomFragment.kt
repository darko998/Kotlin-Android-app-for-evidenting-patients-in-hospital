package rs.raf.projekat1.darko_dimitrijevic_rn9418.view.fragments.tabFragments

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
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.recycler.waiting.adapter.PatientAdapter
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.recycler.waiting.diff.PatientDiffItemCallback
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.viewmodel.RecyclerHospitalizePatients
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.viewmodel.RecyclerWaitingRoomPatients
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class WaitingRoomFragment : Fragment() {

    private val recyclerWaitingRoomPatients: RecyclerWaitingRoomPatients by activityViewModels()
    private val recyclerHospitalizePatients: RecyclerHospitalizePatients by activityViewModels()

    private lateinit var adapter: PatientAdapter

    lateinit var searchView: SearchView
    lateinit var recylerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_waiting_room, container, false)

        init(view)

        return view
    }

    fun init(view: View) {
        initUi(view)
        initRecycler()
        initObservers()
    }

    fun initUi(view: View) {
        searchView = view.findViewById(R.id.waiting_room_search_view)
        recylerView = view.findViewById(R.id.recycler_view_waiting_patients)
    }

    fun initRecycler() {
        recylerView.layoutManager = LinearLayoutManager(this.context)
        adapter = PatientAdapter(PatientDiffItemCallback()) { it: Patient, fleg:Int ->
            if(fleg == 0) {
                recyclerHospitalizePatients.hospitalizePatient(it)
                recyclerWaitingRoomPatients.deletePatient(it)
                it.hospitalizeDate = getCurrentDate()
                Toast.makeText(context, "Pacijent ${it.name} je uspesno hospitalizovan.", Toast.LENGTH_SHORT).show()
            } else {
                recyclerWaitingRoomPatients.deletePatient(it)
                Toast.makeText(context, "Pacijent ${it.name} je izbrisan iz liste.", Toast.LENGTH_SHORT).show()
            }
        }
        recylerView.adapter = adapter
    }

    fun initObservers() {
        recyclerWaitingRoomPatients.getPatientsInWaitingRoom().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) recyclerWaitingRoomPatients.filterPatients(newText.toString())
                return true
            }
        })
    }

    fun getCurrentDate(): String{
        val time = SimpleDateFormat("dd/M/yyyy")
        val date = time.format(Date())

        return date.toString()
    }

}
