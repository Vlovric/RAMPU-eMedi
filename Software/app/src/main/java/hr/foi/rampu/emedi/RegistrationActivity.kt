package hr.foi.rampu.emedi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import hr.foi.rampu.emedi.entities.User
import hr.foi.rampu.emedi.helpers.MockDataUser
import hr.foi.rampu.emedi.helpers.RegistrationActivityHelper
import java.text.SimpleDateFormat
import java.util.Locale

class RegistrationActivity : AppCompatActivity() {
    private var registrationHelper : RegistrationActivityHelper? = null;
    private val sdfDate = SimpleDateFormat("dd.MM.yyyy.", Locale.US)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registrationHelper = RegistrationActivityHelper(this)
        setContentView(R.layout.activity_registration)

        // Support za Toolbar
        val toolBar = findViewById<Toolbar>(R.id.toolbar_login)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val birthDate = findViewById<EditText>(R.id.et_birthdate_edit)

        val btnRegister = findViewById<Button>(R.id.btn_register)
        btnRegister.setOnClickListener{
            val firstName = findViewById<EditText>(R.id.et_firstname_edit).text.toString()
            val lastName = findViewById<EditText>(R.id.et_lastname_edit).text.toString()
            val email = findViewById<EditText>(R.id.et_email_edit).text.toString()
            val phoneNumber = findViewById<EditText>(R.id.et_phone_edit).text.toString()
            val address = findViewById<EditText>(R.id.et_address_edit).text.toString()
            val username = findViewById<EditText>(R.id.tv_register_username_edit).text.toString()
            val password = findViewById<EditText>(R.id.et_register_password_edit).text.toString()
            val newUser = User(firstName, lastName, sdfDate.parse(birthDate.text.toString()), email, phoneNumber, address, username, password)

            MockDataUser.userList.add(newUser)
            Log.i("USERADDED", "User count: ${MockDataUser.userList.count()}")

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        birthDate.setOnFocusChangeListener{ view, hasFocus ->
            if(hasFocus) {
                registrationHelper!!.showDatePickerDialog()
                view.clearFocus()
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() // Deprecated je zato što treba naći neku noviju metodu
        return true
    }
}