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

        // Initialize views from add_review.xml
        ratingBar = findViewById(R.id.ratingBar)
        editTextReview = findViewById(R.id.editTextReview)
        submitButton = findViewById(R.id.submitButton)

        // Set click listener for the submit button
        submitButton.setOnClickListener {
            addReviewAndDisplayAllReviews()
        }
    }

    private fun addReviewAndDisplayAllReviews() {
        // Get user input
        val userGrade = ratingBar.rating.toInt()
        val userDescription = editTextReview.text.toString()

        // Create a new review
        val userReview = Review(userGrade, userDescription)

        // Add the new review
        Review.addReview(userReview)

        // Display all reviews in the ListView from all_reviews.xml
        displayAllReviews()
    }

    private fun displayAllReviews() {
        // Set the content view to all_reviews.xml
        setContentView(R.layout.all_reviews)

        // Retrieve all reviews from the Review companion object
        val allReviews = Review.getAllReviews()

        // Find the ListView by its ID in the current layout
        listViewReviews = findViewById(R.id.listViewReviews)

        // Create and set the adapter for the ListView
        val adapter = ReviewAdapter(this, allReviews)
        listViewReviews.adapter = adapter
    }
}
