package hr.foi.rampu.emedi

import Doctor
import ReviewAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class AllReviewsActivity : AppCompatActivity() {
    private lateinit var listViewReviews: ListView
    private lateinit var currentDoctor: Doctor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.all_reviews)

        currentDoctor = intent.getParcelableExtra<Doctor>("doctor")!!

        val allReviews = Review.getReviewsForDoctor(currentDoctor)

        listViewReviews = findViewById(R.id.listViewReviews)

        val adapter = ReviewAdapter(this, allReviews)
        listViewReviews.adapter = adapter
    }

    fun addReviewBtnClicked(view: View)  {
        val intent = Intent(this, AddReviewActivity::class.java)
        startActivity(intent)
    }
}
