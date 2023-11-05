package hr.foi.rampu.emedi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val linkRegistration = findViewById<TextView>(R.id.link_register)

        linkRegistration.setOnClickListener{
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)

        }
    }
}