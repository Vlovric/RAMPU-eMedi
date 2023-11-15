package hr.foi.rampu.emedi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import hr.foi.rampu.emedi.R

class DoctorInformationActivity : AppCompatActivity() {

    lateinit var button : Button
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
        val tvName = findViewById<TextView>(R.id.tv_dynamic_name)
        val tvSurname = findViewById<TextView>(R.id.tv_dynamic_surname)
        val tvSpecialization = findViewById<TextView>(R.id.tv_dynamic_specialization)
        val tvYearsEmployed = findViewById<TextView>(R.id.tv_dynamic_years)
        val tvJobDescription = findViewById<TextView>(R.id.tv_dynamic_job_description)
        val tvClinicName = findViewById<TextView>(R.id.tv_dynamic_clinic_name)
        val tvAddress = findViewById<TextView>(R.id.tv_dynamic_address)
        val tvEmail = findViewById<TextView>(R.id.tv_dynamic_email)
        val tvTelephone = findViewById<TextView>(R.id.tv_dynamic_telephone)

        // Set the text of TextViews with the retrieved data
        tvName.text = "$doctorName"
        tvSurname.text = "$doctorSurname"
        tvSpecialization.text = "$doctorSpecialization"
        tvYearsEmployed.text = "$doctorYears"
        tvJobDescription.text = "$doctorDescription"
        tvClinicName.text = "$doctorClinic"
        tvAddress.text = "$doctorAddress"
        tvEmail.text = "$doctorEmail"
        tvTelephone.text = "$doctorTelephone"

        button = findViewById(R.id.btn_reviews)
        button.setOnClickListener{
            val intent = Intent(this, AddReviewActivity::class.java)
            startActivity(intent)
        }
    }
}
