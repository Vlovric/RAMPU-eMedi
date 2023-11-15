package hr.foi.rampu.emedi

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import hr.foi.rampu.emedi.R

class DoctorInformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_information)

        // Retrieve data from the intent
        val intent = intent
        val doctorName = intent.getStringExtra("doctorName")
        val doctorSurname = intent.getStringExtra("doctorSurname")
        val doctorSpecialization = intent.getStringExtra("doctorSpecialization")
        val doctorYears = intent.getIntExtra("doctorYears", 0)
        val doctorDescription = intent.getStringExtra("doctorDescription")
        val doctorClinic = intent.getStringExtra("doctorClinic")
        val doctorAddress = intent.getStringExtra("doctorAddress")
        val doctorEmail = intent.getStringExtra("doctorEmail")
        val doctorTelephone = intent.getStringExtra("doctorTelephone")

        // Initialize TextViews
        val tvName = findViewById<TextView>(R.id.tv_static_name)
        val tvSurname = findViewById<TextView>(R.id.tv_static_surname)
        val tvSpecialization = findViewById<TextView>(R.id.tv_static_specialization)
        val tvYearsEmployed = findViewById<TextView>(R.id.tv_static_years_employed)
        val tvJobDescription = findViewById<TextView>(R.id.tv_static_job_description)
        val tvClinicName = findViewById<TextView>(R.id.tv_static_clinic_name)
        val tvAddress = findViewById<TextView>(R.id.tv_static_address)
        val tvEmail = findViewById<TextView>(R.id.tv_static_email)
        val tvTelephone = findViewById<TextView>(R.id.tv_static_telephone)

        // Set the text of TextViews with the retrieved data
        tvName.text = "Name: $doctorName"
        tvSurname.text = "Surname: $doctorSurname"
        tvSpecialization.text = "Specialization: $doctorSpecialization"
        tvYearsEmployed.text = "Years employed: $doctorYears"
        tvJobDescription.text = "Job description: $doctorDescription"
        tvClinicName.text = "Clinic name: $doctorClinic"
        tvAddress.text = "Address: $doctorAddress"
        tvEmail.text = "Email: $doctorEmail"
        tvTelephone.text = "Telephone: $doctorTelephone"
    }
}
