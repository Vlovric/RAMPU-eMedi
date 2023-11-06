package hr.foi.rampu.emedi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import hr.foi.rampu.emedi.entities.Review

class AddReviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_reviews)

        val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
        val editTextReview = findViewById<EditText>(R.id.editTextReview)
        val submitButton = findViewById<Button>(R.id.submitButton)

        submitButton.setOnClickListener {

            val userGrade = ratingBar.rating.toInt()
            val userDescription = editTextReview.text.toString()

            if (userGrade >= 1 && userGrade <= 5 && userDescription.isNotEmpty()) {
                val userReview = Review(userGrade, userDescription)
                val reviewsList: MutableList<Review> = mutableListOf()
                reviewsList.add(userReview)

                finish()
            } /* else {
                TODO
            } */
        }
    }


}