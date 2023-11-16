package hr.foi.rampu.emedi

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.addCallback
import hr.foi.rampu.emedi.helpers.MockDataUser
import hr.foi.rampu.emedi.helpers.UserSession

class LoginActivity : AppCompatActivity() {
    private val onBackPressedCallback: OnBackPressedCallback = object: OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        UserSession.loggedIn = false
        UserSession.loggedUser = MockDataUser.getDummyUser()

        val linkRegistration = findViewById<TextView>(R.id.link_register)
        val loginButton = findViewById<Button>(R.id.btn_login)

        loginButton.setOnClickListener {
            val username = findViewById<EditText>(R.id.et_username_edit).text.toString()
            val password = findViewById<EditText>(R.id.et_password_edit).text.toString()
            // Ovom iteracijom kroz listu korisnika provjeravam nalaze li se ti korisnički podatci u klasi MockDataUser
            val loggedInUser =
                MockDataUser.userList.find { it.username == username && it.password == password }

            if (loggedInUser != null) {
                UserSession.loggedIn = true
                UserSession.loggedUser = loggedInUser
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                if (username.isEmpty() && password.isEmpty())
                    Toast.makeText(this, "Pogrešno korisničko ime ili lozinka", Toast.LENGTH_SHORT)
                        .show()
                else {
                    Toast.makeText(this, "Unesite sve podatke za prijavu!", Toast.LENGTH_SHORT)
                        .show()
                    Log.e("LOGINFAILURE", "$username $password failed")
                    MockDataUser.userList.forEach {
                        Log.i("USER", "${it.username} ${it.password}")
                    }
                }
            }
        }

        linkRegistration.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)

        }

        onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }
}