package hr.foi.rampu.emedi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class DoctorInformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_information)

        val doctorNameSurname = intent.getStringExtra("doctorNameSurname")
        Log.i("OnClick", "Usao u drugu aktivnost")
        val textView = findViewById<TextView>(R.id.tv_doctor_name_surname)
        textView.text = doctorNameSurname
    }
}