package hr.foi.rampu.emedi.adapters

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import hr.foi.rampu.emedi.R
import hr.foi.rampu.emedi.entities.Appointment
import hr.foi.rampu.emedi.entities.Review
import java.text.SimpleDateFormat
import java.util.Locale

class AppointmentAdapter(private val context: Context, private val appointments: List<Appointment>): BaseAdapter() {
    private val sdfDate = SimpleDateFormat("dd.MM.yyyy.", Locale.US)
    private val sdfTime = SimpleDateFormat("HH:mm", Locale.US)

    override fun getCount(): Int {
        return appointments.size
    }

    override fun getItem(position: Int): Any {
        return appointments[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.appointment, parent, false)

        val tvDoctor = rowView.findViewById<TextView>(R.id.tv_appointment_doctor)
        val tvDateAndTime = rowView.findViewById<TextView>(R.id.tv_appointment_date_and_time)
        val tvSymptoms = rowView.findViewById<TextView>(R.id.tv_appointment_symptoms)

        val appointment = getItem(position) as Appointment
        val appDoctor = appointment.doctor
        val appBookingReason = appointment.bookingReason

        tvDoctor.text =
            "Appointment with ${appDoctor.name} ${appDoctor.surname} (${appDoctor.specialization})"
        tvDateAndTime.text =
            "${sdfDate.format(appointment.appointmentDate)} at ${sdfTime.format(appointment.appointmentStartTime)}"
        tvSymptoms.text = appBookingReason.symptoms

        return rowView
    }
}