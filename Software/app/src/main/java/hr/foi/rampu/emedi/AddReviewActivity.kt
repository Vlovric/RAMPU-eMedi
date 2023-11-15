package hr.foi.rampu.emedi

import ReviewAdapter
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity
import hr.foi.rampu.emedi.entities.Review

class AddReviewActivity : AppCompatActivity() {
    private lateinit var ratingBar: RatingBar
    private lateinit var editTextReview: EditText
    private lateinit var submitButton: Button
    private lateinit var listViewReviews: ListView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_review)

        ratingBar = findViewById(R.id.ratingBar)
        editTextReview = findViewById(R.id.editTextReview)
        submitButton = findViewById(R.id.submitButton)


        submitButton.setOnClickListener {
            addReviewAndDisplayAllReviews()
        }
    }

    private fun addReviewAndDisplayAllReviews() {

        val userGrade = ratingBar.rating.toInt()
        val userDescription = editTextReview.text.toString()


        val userReview = Review(userGrade, userDescription)

        Review.addReview(userReview)

        displayAllReviews()
    }

    private fun displayAllReviews() {

        setContentView(R.layout.all_reviews)

        val allReviews = Review.getAllReviews()

        listViewReviews = findViewById(R.id.listViewReviews)

        val adapter = ReviewAdapter(this, allReviews)
        listViewReviews.adapter = adapter
    }
}
