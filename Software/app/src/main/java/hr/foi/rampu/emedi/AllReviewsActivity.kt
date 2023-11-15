package hr.foi.rampu.emedi

import ReviewAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import hr.foi.rampu.emedi.entities.Review

class AllReviewsActivity : AppCompatActivity() {
    private lateinit var listViewReviews: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.all_reviews)

        val allReviews = Review.getAllReviews()

        listViewReviews = findViewById(R.id.listViewReviews)

        val adapter = ReviewAdapter(this, allReviews)
        listViewReviews.adapter = adapter
    }
    fun addReviewBtnClicked(view: View)  {
        val intent = Intent(this, AddReviewActivity::class.java)
        startActivity(intent)
    }
}
