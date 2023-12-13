package hr.foi.rampu.emedi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import hr.foi.rampu.emedi.R
import hr.foi.rampu.emedi.adapters.AppointmentAdapter
import hr.foi.rampu.emedi.database.AppDatabase
import hr.foi.rampu.emedi.helpers.UserSession

class AppointmentsFragment : Fragment() {
    private lateinit var listViewAppointments: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_appointments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var appointmentList = AppDatabase.getInstance().getAppointmentsDao().getAppointmentsForUser(
            UserSession.loggedUser.id)
        listViewAppointments = view.findViewById(R.id.lv_all_appointments)

        val adapter = AppointmentAdapter(view.context, appointmentList)
        listViewAppointments.adapter = adapter
    }
}