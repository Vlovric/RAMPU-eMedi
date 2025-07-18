package hr.foi.rampu.emedi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import hr.foi.rampu.emedi.database.AppDatabase
import hr.foi.rampu.emedi.entities.Doctor
import hr.foi.rampu.emedi.helpers.TextSizeUtility

class DoctorInformationActivity : AppCompatActivity() {

    private lateinit var textSizeUtility: TextSizeUtility
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_information)

        textSizeUtility = TextSizeUtility.getInstance()
        textSizeUtility.registerTextView(findViewById(R.id.tv_static_name))
        textSizeUtility.registerTextView(findViewById(R.id.tv_static_surname))
        textSizeUtility.registerTextView(findViewById(R.id.tv_static_specialization))
        textSizeUtility.registerTextView(findViewById(R.id.tv_static_about))
        textSizeUtility.registerTextView(findViewById(R.id.tv_static_years_employed))
        textSizeUtility.registerTextView(findViewById(R.id.tv_static_job_description))
        textSizeUtility.registerTextView(findViewById(R.id.tv_static_location))
        textSizeUtility.registerTextView(findViewById(R.id.tv_static_clinic_name))
        textSizeUtility.registerTextView(findViewById(R.id.tv_static_address))
        textSizeUtility.registerTextView(findViewById(R.id.tv_static_contact))
        textSizeUtility.registerTextView(findViewById(R.id.tv_static_email))
        textSizeUtility.registerTextView(findViewById(R.id.tv_static_telephone))
        textSizeUtility.registerTextView(findViewById(R.id.tv_dynamic_name))
        textSizeUtility.registerTextView(findViewById(R.id.tv_dynamic_surname))
        textSizeUtility.registerTextView(findViewById(R.id.tv_dynamic_specialization))
        textSizeUtility.registerTextView(findViewById(R.id.tv_dynamic_years))
        textSizeUtility.registerTextView(findViewById(R.id.tv_dynamic_job_description))
        textSizeUtility.registerTextView(findViewById(R.id.tv_dynamic_clinic_name))
        textSizeUtility.registerTextView(findViewById(R.id.tv_dynamic_address))
        textSizeUtility.registerTextView(findViewById(R.id.tv_dynamic_email))
        textSizeUtility.registerTextView(findViewById(R.id.tv_dynamic_telephone))


        val btnCheckReviews: Button = findViewById(R.id.btn_reviews)
        btnCheckReviews.setOnClickListener {
            checkReviews()
        }

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
    }

    private fun checkReviews() {
        val receivedDoctor = intent.getParcelableExtra<Doctor>("doctor")
        val intent = Intent(this, AllReviewsActivity::class.java)
        intent.putExtra("doctor", receivedDoctor)
        startActivity(intent)
    }
}
