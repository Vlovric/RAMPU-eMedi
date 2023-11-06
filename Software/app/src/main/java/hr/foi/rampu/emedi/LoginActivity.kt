package hr.foi.rampu.emedi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import hr.foi.rampu.emedi.helpers.MockDataUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val linkRegistration = findViewById<TextView>(R.id.link_register)
        val loginButton = findViewById<Button>(R.id.btn_login)

        loginButton.setOnClickListener {
            val username = findViewById<EditText>(R.id.et_username_edit).text.toString()
            val password = findViewById<EditText>(R.id.et_password_edit).text.toString()
            // Ovdje dohvaćam podatke i elemente koji su mi potrebni za prijavu
            val mockDataUser = MockDataUser.getDemoData()
            // Ovom iteracijom kroz listu korisnika provjeravam nalaze li se ti korisnički podatci u klasi MockDataUser
            val loggedInUser = mockDataUser.find { it.username == username && it.password == password }

            if (loggedInUser != null) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                if(username.isEmpty() && password.isEmpty())
                    Toast.makeText(this, "Pogrešno korisničko ime ili lozinka", Toast.LENGTH_SHORT)
                        .show()
                else
                    Toast.makeText(this, "Unesite sve podatke za prijavu!", Toast.LENGTH_SHORT).show()
            }
        }

        //mockDataUser.forEach { Log.i("MOCK_USER", "${it.name} ${it.surname}")}  provjera dohvaćanja podataka

        linkRegistration.setOnClickListener{
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)

        }
    }
}