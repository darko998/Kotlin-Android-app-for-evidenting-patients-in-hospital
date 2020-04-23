package rs.raf.projekat1.darko_dimitrijevic_rn9418.view.fragments.tabFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import rs.raf.projekat1.darko_dimitrijevic_rn9418.R
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.recycler.hospitalize.adapter.HospitalizePatientAdapter
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.recycler.hospitalize.diff.HospitalizePatientDiffItemCallback
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.recycler.waiting.adapter.PatientAdapter
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.recycler.waiting.diff.PatientDiffItemCallback
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.viewmodel.RecyclerFreePatients
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.viewmodel.RecyclerHospitalizePatients
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.viewmodel.RecyclerWaitingRoomPatients

/**
 * A simple [Fragment] subclass.
 */
class HospitalizeFragment : Fragment() {

    private val hospitalizePatients: RecyclerHospitalizePatients by activityViewModels()
    private val freePatients: RecyclerFreePatients by activityViewModels()

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: HospitalizePatientAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_hospitalize, container, false)

        init(view)

        return view
    }

    fun init(view: View) {
        initRecycler(view)
        initObservers()
    }

    fun initRecycler(view: View) {
        recyclerView = view.findViewById(R.id.recycler_view_hospitalize_patients)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        adapter = HospitalizePatientAdapter(HospitalizePatientDiffItemCallback()) {
            freePatients.freePatient(it)
        }
        recyclerView.adapter = adapter
    }

    fun initObservers() {
        hospitalizePatients.getHospitalizePatients().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

}
