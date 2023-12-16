package hr.foi.rampu.emedi

import ReviewAdapter
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebSettings.TextSize
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import hr.foi.rampu.emedi.entities.Doctor
import hr.foi.rampu.emedi.entities.Review
import hr.foi.rampu.emedi.helpers.TextSizeUtility
import org.w3c.dom.Text

class AllReviewsActivity : AppCompatActivity() {
    private lateinit var listViewReviews: ListView
    private lateinit var currentDoctor: Doctor
    private lateinit var textSizeUtility: TextSizeUtility
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.all_reviews)


        currentDoctor = intent.getParcelableExtra<Doctor>("doctor")!!
        if (currentDoctor != null) {
            Log.i("DOBIVEN DOKTOR", currentDoctor.id.toString())
        }

        textSizeUtility = TextSizeUtility.getInstance()

        val allReviews = Review.getReviewsForDoctor(currentDoctor)

        listViewReviews = findViewById(R.id.listViewReviews)

        val adapter = ReviewAdapter(this, allReviews)
        listViewReviews.adapter = adapter
    }

    fun addReviewBtnClicked(view: View) {
        val intent = Intent(this, AddReviewActivity::class.java)
        intent.putExtra("doctor", currentDoctor)
        startActivity(intent)
    }
}
