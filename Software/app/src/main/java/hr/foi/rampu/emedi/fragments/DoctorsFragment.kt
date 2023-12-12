package hr.foi.rampu.emedi.fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.foi.rampu.emedi.DoctorInformationActivity
import hr.foi.rampu.emedi.R
import hr.foi.rampu.emedi.adapters.DoctorsAdapter
import hr.foi.rampu.emedi.database.AppDatabase
import hr.foi.rampu.emedi.helpers.MockDataCity
import hr.foi.rampu.emedi.helpers.MockDataSpecialization

class DoctorsFragment : Fragment() {
//    private val mockDoctors = MockDataDoctor.getDemoData()
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchTextBox : EditText
    private lateinit var errorMessage : TextView
    private lateinit var citySpinner : Spinner
    private lateinit var specializationSpinner : Spinner
    private lateinit var reviewSpinner : Spinner
    private val doctorsDAO = AppDatabase.getInstance().getDoctorsDao()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_doctors, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        citySpinner = view.findViewById(R.id.spn_city_filter)
        specializationSpinner = view.findViewById(R.id.spn_specialisation_filter)
        reviewSpinner = view.findViewById(R.id.spn_review_filter)

        errorMessage = view.findViewById(R.id.tv_error_message)
        recyclerView = view.findViewById(R.id.rv_doctors)
        searchTextBox = view.findViewById(R.id.tv_search_doctor)
        recyclerView.adapter = DoctorsAdapter(MockDataDoctor.getDemoData()){
            val intent = Intent(requireContext(), DoctorInformationActivity::class.java)
            val whichDoctor = doctorsDAO.getDoctor(it.id)
            intent.putExtra("doctor", whichDoctor)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        searchTextBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val searchText = s.toString().trim()
                getDoctorsByName(searchText)
            }
        })
        val citySpinnerAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            MockDataCity.cityList
        )
        val specializationSpinnerAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            MockDataSpecialization.specializationList
        )
        val reviewSpinnerAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            arrayOf<String>("Select item", "5", "4", "3", "2", "1")
        )

        citySpinner.adapter = citySpinnerAdapter
        specializationSpinner.adapter = specializationSpinnerAdapter
        reviewSpinner.adapter = reviewSpinnerAdapter
    }
    private fun getDoctorsByName(name: String) {
        val doctorsList = if (name.isNotBlank()) {
            doctorsDAO.getDoctorByName("$name%")
        } else {
            doctorsDAO.getAllDoctors()
        }
        if (doctorsList.isNotEmpty()) {
            recyclerView.adapter = DoctorsAdapter(doctorsList) { doctor ->
                val intent = Intent(requireContext(), DoctorInformationActivity::class.java)
                intent.putExtra("doctor", doctor)
                startActivity(intent)
            }
            errorMessage.visibility = View.GONE
        } else {
            recyclerView.adapter = DoctorsAdapter(emptyList()) {
            }
            errorMessage.visibility = View.VISIBLE
            errorMessage.text = "Nije pronađen niti jedan doktor s tim imenom!"
        }
    }
}