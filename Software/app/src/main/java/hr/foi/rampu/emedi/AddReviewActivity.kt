package hr.foi.rampu.emedi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.ListView
import ReviewAdapter
import hr.foi.rampu.emedi.entities.Review

class AddReviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_review) // Use the appropriate layout for adding a review

        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val editTextReview = findViewById<EditText>(R.id.editTextReview)
        val submitButton = findViewById<Button>(R.id.submitButton)
    }
}

