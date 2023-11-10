package hr.foi.rampu.emedi.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.foi.rampu.emedi.DoctorInformationActivity
import hr.foi.rampu.emedi.R
import hr.foi.rampu.emedi.adapters.DoctorsAdapter
import hr.foi.rampu.emedi.helpers.MockDataDoctor

class DoctorsFragment : Fragment() {
    private val mockDoctors = MockDataDoctor.getDemoData()
    private lateinit var recyclerView: RecyclerView
    private lateinit var button : Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_doctors, container, false)
        button = rootView.findViewById(R.id.btn_doctor)
        button.setOnClickListener{
            //tu treba parcelable interface napravit u klasi Doctor
            val intent = Intent(requireContext(), DoctorInformationActivity::class.java)
            intent.putExtra("doctorName", mockDoctors[0].name)
            intent.putExtra("doctorSurname", mockDoctors[0].surname)
            intent.putExtra("doctorSpecialization", mockDoctors[0].specialization)
            intent.putExtra("doctorYears", mockDoctors[0].yearsEmployed)
            intent.putExtra("doctorDescription", mockDoctors[0].jobDescription)
            intent.putExtra("doctorClinic", mockDoctors[0].clinicName)
            intent.putExtra("doctorAddress", mockDoctors[0].address)
            intent.putExtra("doctorEmail", mockDoctors[0].email)
            intent.putExtra("doctorTelephone", mockDoctors[0].telephone)
            startActivity(intent)
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.rv_doctors)
        recyclerView.adapter = DoctorsAdapter(MockDataDoctor.getDemoData())
        recyclerView.layoutManager = LinearLayoutManager(view.context)
    }
}