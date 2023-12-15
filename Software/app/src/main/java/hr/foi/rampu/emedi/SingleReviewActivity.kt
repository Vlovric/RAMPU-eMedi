package hr.foi.rampu.emedi

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import hr.foi.rampu.emedi.helpers.TextSizeUtility
class SingleReviewActivity : AppCompatActivity() {
    lateinit var textSizeUtility: TextSizeUtility
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.single_review)
        textSizeUtility = TextSizeUtility.getInstance()
        textSizeUtility.registerTextView(findViewById(R.id.textViewDescription))
    }

}