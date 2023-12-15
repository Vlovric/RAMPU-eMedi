package hr.foi.rampu.emedi

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import hr.foi.rampu.emedi.database.AppDatabase
import hr.foi.rampu.emedi.entities.Doctor
import hr.foi.rampu.emedi.entities.User
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DoctorInformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_information)

        val receivedDoctor = intent.getParcelableExtra<Doctor>("doctor")

        val btnBooking: Button = findViewById(R.id.btn_booking)
        btnBooking.setOnClickListener {
            val intent = Intent(this, BookingActivity::class.java)
            intent.putExtra("doctor", receivedDoctor)
            startActivity(intent)
        }

        val btnCheckReviews: Button = findViewById(R.id.btn_reviews)
        btnCheckReviews.setOnClickListener {
            checkReviews()
        }

        val btnViewBookings: Button = findViewById(R.id.btn_view_bookings)
        btnViewBookings.setOnClickListener {
            viewBookings()
        }

        // inicijaliziram textview-ove
        val tvNameSurname = findViewById<TextView>(R.id.tv_dynamic_name_surname)
        val tvSpecialization = findViewById<TextView>(R.id.tv_dynamic_specialization)
        val tvYearsEmployed = findViewById<TextView>(R.id.tv_dynamic_years)
        val tvJobDescription = findViewById<TextView>(R.id.tv_dynamic_job_description)
        val tvClinicName = findViewById<TextView>(R.id.tv_dynamic_clinic_name)
        val tvAddress = findViewById<TextView>(R.id.tv_dynamic_address)
        val tvEmail = findViewById<TextView>(R.id.tv_dynamic_email)
        val tvTelephone = findViewById<TextView>(R.id.tv_dynamic_telephone)

        // učitavam u text-view
        tvNameSurname.text = receivedDoctor?.name.orEmpty() + " " + receivedDoctor?.surname.orEmpty()
        tvSpecialization.text = receivedDoctor?.specialization.orEmpty()
        tvYearsEmployed.text = "${receivedDoctor?.yearsEmployed ?: 0}"
        tvJobDescription.text = receivedDoctor?.jobDescription.orEmpty()
        tvClinicName.text = receivedDoctor?.clinicName.orEmpty()
        tvAddress.text = receivedDoctor?.address.orEmpty()
        tvEmail.text = receivedDoctor?.email.orEmpty()
        tvTelephone.text = receivedDoctor?.telephone.orEmpty()
    }

    private fun checkReviews() {
        val receivedDoctor = intent.getParcelableExtra<Doctor>("doctor")
        val intent = Intent(this, AllReviewsActivity::class.java)
        intent.putExtra("doctor", receivedDoctor)
        startActivity(intent)
    }

    private fun viewBookings() {
        val receivedDoctor = intent.getParcelableExtra<Doctor>("doctor")
        val intent = Intent(this, BookingsActivity::class.java)
        intent.putExtra("doctor", receivedDoctor)
        startActivity(intent)
    }
}
