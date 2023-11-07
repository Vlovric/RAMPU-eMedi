package hr.foi.rampu.emedi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DoctorInformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_information)

        val doctorName = intent.getStringExtra("doctorName")
        val textView = findViewById<TextView>(R.id.tv_doctor_name_surname)
        textView.text = doctorName
    }
}