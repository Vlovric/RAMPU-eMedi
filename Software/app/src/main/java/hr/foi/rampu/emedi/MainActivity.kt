package hr.foi.rampu.emedi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Add login check
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)

        setContentView(R.layout.activity_main)
    }
}