package hr.foi.rampu.emedi

import Doctor
import Review
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity

class AddReviewActivity : AppCompatActivity() {
    private lateinit var ratingBar: RatingBar
    private lateinit var editTextReview: EditText
    private lateinit var submitButton: Button
    private lateinit var currentDoctor: Doctor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_review)

        ratingBar = findViewById(R.id.ratingBar)
        editTextReview = findViewById(R.id.editTextReview)
        submitButton = findViewById(R.id.submitButton)

        currentDoctor = intent.getParcelableExtra<Doctor>("doctor")!!

        submitButton.setOnClickListener {
            addReviewAndDisplayAllReviews()
        }
    }

    private fun addReviewAndDisplayAllReviews() {
        val userGrade = ratingBar.rating.toInt()
        val userDescription = editTextReview.text.toString()

        val userReview = Review(userGrade, userDescription, currentDoctor)
        Review.addReview(userReview)

        val intent = Intent(this, AllReviewsActivity::class.java)
        intent.putExtra("doctor", currentDoctor)
        startActivity(intent)
    }
}
