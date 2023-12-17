package hr.foi.rampu.emedi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.RelativeLayout
import android.widget.TextView
import hr.foi.rampu.emedi.R
import hr.foi.rampu.emedi.adapters.AppointmentAdapter
import hr.foi.rampu.emedi.database.AppDatabase
import hr.foi.rampu.emedi.entities.ColorPalette
import hr.foi.rampu.emedi.helpers.UserSession

class AppointmentsFragment : Fragment() {
    private lateinit var listViewAppointments: ListView

    private lateinit var myView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_appointments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        myView = view
        updateAppointmentData()
    }

    override fun onResume() {
        updateAppointmentData()
        super.onResume()
    }


    private fun updateAppointmentData() {
        var appointmentList = AppDatabase.getInstance().getAppointmentsDao().getAppointmentsForUser(
            UserSession.loggedUser.id)
        listViewAppointments = myView.findViewById(R.id.lv_all_appointments)

        val adapter = AppointmentAdapter(myView.context, appointmentList)
        listViewAppointments.adapter = adapter
    }
    private fun applyColorsToUI(colorPalette: ColorPalette) {

        val color1 = colorPalette.color1
        val color2 = colorPalette.color2
        val color3 = colorPalette.color3
        val button1 = view?.findViewById<Button>(R.id.btnAppColor)
        val button2 = view?.findViewById<Button>(R.id.btnBack)
        val textView1 = view?.findViewById<TextView>(R.id.tv_FontSize)

        val rootLayout = view?.findViewById<RelativeLayout>(R.id.root_layout)
        rootLayout?.setBackgroundColor(color1)

        button1?.setBackgroundColor(color2)
        button2?.setBackgroundColor(color2)
        textView1?.setBackgroundColor(color2)
        button1?.setTextColor(color3)
        button2?.setTextColor(color3)
        textView1?.setTextColor(color3)

    }
}