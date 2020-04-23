package rs.raf.projekat1.darko_dimitrijevic_rn9418.view.fragments.tabFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import rs.raf.projekat1.darko_dimitrijevic_rn9418.R
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.recycler.free.adapter.FreePatientAdapter
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.recycler.free.diff.FreePatientDiffItemCallback
import rs.raf.projekat1.darko_dimitrijevic_rn9418.view.viewmodel.RecyclerFreePatients

/**
 * A simple [Fragment] subclass.
 */
class FreeFragment : Fragment() {

    val recyclerFreePatients: RecyclerFreePatients by activityViewModels()

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: FreePatientAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_free, container, false)

        init(view)

        return view
    }

    fun init(view: View) {
        initRecycler(view)
        initObservers()
    }

    fun initRecycler(view: View) {
        recyclerView = view.findViewById(R.id.recycler_view_free_patients)
        recyclerView.layoutManager = GridLayoutManager(this.context, 2)
        adapter = FreePatientAdapter(FreePatientDiffItemCallback())
        recyclerView.adapter = adapter
    }

    fun initObservers() {
        recyclerFreePatients.getFreePatients().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

}
