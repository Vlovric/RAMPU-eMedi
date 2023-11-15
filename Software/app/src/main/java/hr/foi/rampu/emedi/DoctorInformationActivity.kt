package hr.foi.rampu.emedi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DoctorInformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_information)

        val btnCheckReviews: Button = findViewById(R.id.btn_reviews)
        btnCheckReviews.setOnClickListener {
            checkReviews()
        }
    }

    private fun checkReviews() {
        val intent = Intent(this, AllReviewsActivity::class.java)
        startActivity(intent)
    }
}
