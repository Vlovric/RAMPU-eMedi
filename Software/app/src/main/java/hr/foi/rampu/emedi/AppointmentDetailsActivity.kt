package hr.foi.rampu.emedi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import hr.foi.rampu.emedi.database.AppDatabase

class AppointmentDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment_details)

        val appointmentId = intent.getSerializableExtra("AppointmentId") as Int

        val appointment = AppDatabase.getInstance().getAppointmentsDao().getAppointmentById(appointmentId)
        Log.i("APPT", appointment.toString())
    }
}