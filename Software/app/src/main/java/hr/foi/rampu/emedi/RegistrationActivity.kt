package hr.foi.rampu.emedi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import hr.foi.rampu.emedi.entities.User
import hr.foi.rampu.emedi.helpers.InputCheckHelper
import hr.foi.rampu.emedi.helpers.InputCheckHelper.afterTextChanged
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
        // EditText objekti za unos
        val birthDate = findViewById<EditText>(R.id.et_birthdate_edit)
        val firstName = findViewById<EditText>(R.id.et_firstname_edit)
        val lastName = findViewById<EditText>(R.id.et_lastname_edit)
        val email = findViewById<EditText>(R.id.et_email_edit)
        val phoneNumber = findViewById<EditText>(R.id.et_phone_edit)
        val address = findViewById<EditText>(R.id.et_address_edit)
        val username = findViewById<EditText>(R.id.et_register_username_edit)
        val password = findViewById<EditText>(R.id.et_register_password_edit)
        // EditText objekti za unos


        val btnRegister = findViewById<Button>(R.id.btn_register)
        btnRegister.setOnClickListener{
            // EditText objekti za unos
            val firstNameButton = firstName.text.toString()
            val lastNameButton = lastName.text.toString()
            val emailButton = email.text.toString()
            val phoneNumberButton = phoneNumber.text.toString()
            val addressButton = address.text.toString()
            val usernameButton = username.text.toString()
            val passwordButton = password.text.toString()


            val newUser = User(MockDataUser.getNewUserId(),
                firstNameButton,
                lastNameButton,
                sdfDate.parse(birthDate.text.toString()),
                emailButton,
                phoneNumberButton,
                addressButton,
                usernameButton,
                passwordButton
            )

            MockDataUser.insertUser(newUser)
            Log.i("USERADDED", "User count: ${MockDataUser.getUserCount()}")

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
        firstName.afterTextChanged {
            val firstNameErr = findViewById<TextView>(R.id.tv_firstname)
            val errorMessage = InputCheckHelper.checkFirstname(firstName.text.toString())
            if (firstName.text.isBlank()) {
                firstNameErr.visibility = View.VISIBLE;
                firstNameErr.text = errorMessage;
            } else {
                firstNameErr.visibility = View.INVISIBLE;
            }
        }
        lastName.afterTextChanged {
            val lastNameErr = findViewById<TextView>(R.id.tv_lastname)
            val errorMessage = InputCheckHelper.checkLastname(lastName.text.toString())
            if (lastName.text.isBlank()) {
                lastNameErr.visibility = View.VISIBLE;
                lastNameErr.text = errorMessage;
            } else {
                lastNameErr.visibility = View.INVISIBLE;
            }
        }
        address.afterTextChanged {
            val addressErr = findViewById<TextView>(R.id.tv_adrress)
            val errorMessage = InputCheckHelper.checkAddress(address.text.toString())
            if (address.text.isBlank()) {
                addressErr.visibility = View.VISIBLE;
                addressErr.text = errorMessage;
            } else {
                addressErr.visibility = View.INVISIBLE;
            }
        }
        /*email.emailAfterTextChanged {
            val emailErr = findViewById<TextView>(R.id.tv_email)
            val errorMessage = InputCheckHelper.emailAddressCheck(lastName.text.toString())
            if (lastName.text.isBlank()) {
                emailErr.visibility = View.VISIBLE;
                emailErr.text = errorMessage;
            } else {
                emailErr.visibility = View.INVISIBLE;
            }
        }*/
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed() // Deprecated je zato što treba naći neku noviju metodu
        return true
    }
}