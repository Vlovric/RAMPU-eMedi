package hr.foi.rampu.emedi.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.foi.rampu.emedi.DoctorInformationActivity
import hr.foi.rampu.emedi.R
import hr.foi.rampu.emedi.adapters.DoctorsAdapter
import hr.foi.rampu.emedi.database.AppDatabase
import hr.foi.rampu.emedi.entities.ColorPalette
import hr.foi.rampu.emedi.entities.Doctor
import hr.foi.rampu.emedi.entities.Review
import hr.foi.rampu.emedi.helpers.TextSizeUtility

class DoctorsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var errorMessage: TextView
    private lateinit var filterButton: Button
    private lateinit var clearButton: Button
    private lateinit var filteredList: List<Doctor>
    private val doctorsDAO = AppDatabase.getInstance().getDoctorsDao()

    private lateinit var textSizeUtility: TextSizeUtility
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_doctors, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        filterButton = view.findViewById(R.id.btn_filter)
        clearButton = view.findViewById(R.id.btn_clear_filter)

        errorMessage = view.findViewById(R.id.tv_error_message)
        recyclerView = view.findViewById(R.id.rv_doctors)
        recyclerView.adapter = DoctorsAdapter(emptyList()) {
            val intent = Intent(requireContext(), DoctorInformationActivity::class.java)
            val whichDoctor = doctorsDAO.getDoctor(it.id)
            intent.putExtra("doctor", whichDoctor)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        filterButton.setOnClickListener {
            // Your filter logic
        }

        clearButton.setOnClickListener {
            recyclerView.adapter = DoctorsAdapter(emptyList()) {
                val intent = Intent(requireContext(), DoctorInformationActivity::class.java)
                val whichDoctor = doctorsDAO.getDoctor(it.id)
                intent.putExtra("doctor", whichDoctor)
                startActivity(intent)
            }
        }

        view?.post {
            applyColorsToUI()
        }
    }

    private fun applyColorsToUI() {
        sharedPreferences = requireContext().applicationContext.getSharedPreferences("Prefs", Context.MODE_PRIVATE)
        val color1 = sharedPreferences.getInt("color1", DEFAULT_1)
        val color2 = sharedPreferences.getInt("color2", DEFAULT_2)
        val color3 = sharedPreferences.getInt("color3", DEFAULT_3)

        Log.d("DoctorsFragment", "Color1: $color1")
        Log.d("DoctorsFragment", "Color2: $color2")
        Log.d("DoctorsFragment", "Color3: $color3")
        Log.d("DoctorFragment", "TheView: $view")

        val button1 = view?.findViewById<Button>(R.id.btn_clear_filter)
        val button2 = view?.findViewById<Button>(R.id.btn_filter)
        val rootLayout = view?.findViewById<LinearLayout>(R.id.root_layout)

        rootLayout?.setBackgroundColor(color1)
        button1?.setBackgroundColor(color2)
        button2?.setBackgroundColor(color2)
        button1?.setTextColor(color3)
        button2?.setTextColor(color3)
    }


    companion object{
        val DEFAULT_1 = android.graphics.Color.BLACK
        val DEFAULT_2 = android.graphics.Color.BLACK
        val DEFAULT_3 = android.graphics.Color.BLACK
    }
}
