package hr.foi.rampu.emedi.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.foi.rampu.emedi.R
import hr.foi.rampu.emedi.adapters.DoctorsAdapter
import hr.foi.rampu.emedi.helpers.MockDataDoctor

class DoctorsFragment : Fragment() {
    private val mockDoctors = MockDataDoctor.getDemoData()
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mockDoctors.forEach{ Log.i("MOCK_DOCTOR", it.name)}
        return inflater.inflate(R.layout.fragment_doctors, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.rv_doctors)
        recyclerView.adapter = DoctorsAdapter(MockDataDoctor.getDemoData())
        recyclerView.layoutManager = LinearLayoutManager(view.context)
    }
}