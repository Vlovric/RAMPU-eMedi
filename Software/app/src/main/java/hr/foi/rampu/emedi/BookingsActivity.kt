package hr.foi.rampu.emedi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import hr.foi.rampu.emedi.adapters.BookingReasonAdapter
import hr.foi.rampu.emedi.database.AppDatabase
import hr.foi.rampu.emedi.entities.Doctor
import hr.foi.rampu.emedi.helpers.UserSession

class BookingsActivity : AppCompatActivity() {
    private lateinit var listBookings: ListView
    private lateinit var currentDoctor: Doctor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookings)

        currentDoctor = intent.getParcelableExtra<Doctor>("doctor")!!

        val allBookings = AppDatabase.getInstance().getBookingReasonsDao().getBookingsForDoctorAndUser(currentDoctor.id, UserSession.loggedUser.id)
        listBookings = findViewById(R.id.list_bookings)

        val adapter = BookingReasonAdapter(this, allBookings)
        listBookings.adapter = adapter
    }
}