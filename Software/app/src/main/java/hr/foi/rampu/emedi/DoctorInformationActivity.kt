package hr.foi.rampu.emedi

import Doctor
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import hr.foi.rampu.emedi.R

class DoctorInformationActivity : AppCompatActivity() {

    lateinit var buttonAdd : Button
    lateinit var buttonShow : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_information)

        // intent
        val intent = intent
        val receivedDoctor = intent.getParcelableExtra<Doctor>("doctor")


        // inicijaliziram textview-ove
        val tvName = findViewById<TextView>(R.id.tv_dynamic_name)
        val tvSurname = findViewById<TextView>(R.id.tv_dynamic_surname)
        val tvSpecialization = findViewById<TextView>(R.id.tv_dynamic_specialization)
        val tvYearsEmployed = findViewById<TextView>(R.id.tv_dynamic_years)
        val tvJobDescription = findViewById<TextView>(R.id.tv_dynamic_job_description)
        val tvClinicName = findViewById<TextView>(R.id.tv_dynamic_clinic_name)
        val tvAddress = findViewById<TextView>(R.id.tv_dynamic_address)
        val tvEmail = findViewById<TextView>(R.id.tv_dynamic_email)
        val tvTelephone = findViewById<TextView>(R.id.tv_dynamic_telephone)

        // učitavam u text-view
        tvName.text = receivedDoctor?.name.orEmpty()
        tvSurname.text = receivedDoctor?.surname.orEmpty()
        tvSpecialization.text = receivedDoctor?.specialization.orEmpty()
        tvYearsEmployed.text = "${receivedDoctor?.yearsEmployed ?: 0}"
        tvJobDescription.text = receivedDoctor?.jobDescription.orEmpty()
        tvClinicName.text = receivedDoctor?.clinicName.orEmpty()
        tvAddress.text = receivedDoctor?.address.orEmpty()
        tvEmail.text = receivedDoctor?.email.orEmpty()
        tvTelephone.text = receivedDoctor?.telephone.orEmpty()

        buttonAdd = findViewById(R.id.btn_add_review)
        buttonAdd.setOnClickListener{
            val intent = Intent(this, AddReviewActivity::class.java)
            intent.putExtra("doctor", receivedDoctor)
            startActivity(intent)
        }
        buttonShow = findViewById(R.id.btn_show_reviews)
        buttonShow.setOnClickListener{
            val intent = Intent(this, AllReviewsActivity::class.java)
            intent.putExtra("doctor", receivedDoctor)
            startActivity(intent)
        }
    }
}
