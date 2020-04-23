package rs.raf.projekat1.darko_dimitrijevic_rn9418.view.fragments.tabFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
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

        searchView = view.findViewById(R.id.waiting_room_search_view)
        recylerView = view.findViewById(R.id.recycler_view_waiting_patients)

        init()

        return view
    }

    fun init() {
        initRecycler()
        initObservers()
    }

    fun initRecycler() {
        recylerView.layoutManager = LinearLayoutManager(this.context)
        adapter = PatientAdapter(PatientDiffItemCallback()) { it: Patient, fleg:Int ->
            if(fleg == 0)
                recyclerHospitalizePatients.hospitalizePatient(it)
            else
                recyclerWaitingRoomPatients.deletePatient(it)
        }
        recylerView.adapter = adapter
    }

    fun initObservers() {
        recyclerWaitingRoomPatients.getPatientsInWaitingRoom().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

}
