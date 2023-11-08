package hr.foi.rampu.emedi.helpers

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.ProgressDialog.show
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import hr.foi.rampu.emedi.R
import hr.foi.rampu.emedi.entities.User
import hr.foi.rampu.emedi.fragments.ProfileState

class ProfileChangeHelper(private val view: View, private val user: User) {
    private val nameAndSurname: TextView = view.findViewById(R.id.tv_name_and_surname)
    private val editingMessage: TextView = view.findViewById(R.id.tv_editing_text)
    private val allowEditingButton: ImageView = view.findViewById(R.id.iv_edit_profile)
    private val saveChangesButton: ImageView = view.findViewById(R.id.iv_save_changes)

    private val usernameText: TextView = view.findViewById(R.id.tv_username)
    private val emailAddressText: TextView = view.findViewById(R.id.tv_email)
    private val telephoneNumberText: TextView = view.findViewById(R.id.tv_telephone_number)
    private val addressText: TextView = view.findViewById(R.id.tv_address)

    private val emailAddressChangeText: TextView = view.findViewById(R.id.tv_change_email)
    private val telephoneNumberChangeText: TextView = view.findViewById(R.id.tv_change_telephone_number)
    private val addressChangeText: TextView = view.findViewById(R.id.tv_change_address)

    init {
        var profileState: ProfileState = ProfileState.Viewing
        adjustElementsByState(profileState)
        writeUserDataToTextViews()

        allowEditingButton.setOnClickListener {
            profileState = ProfileState.Editing
            adjustElementsByState(profileState)
            writeUserDataToTextViews()
        }
        saveChangesButton.setOnClickListener {
            profileState = ProfileState.Viewing
            adjustElementsByState(profileState)
            writeUserDataToTextViews()
        }

        emailAddressChangeText.setOnClickListener {
            buildDialog(user.email, "electronic mail") { newEmail ->
                user.email = newEmail
                adjustElementsByState(profileState)
                writeUserDataToTextViews()
            }
        }
        telephoneNumberChangeText.setOnClickListener {
            buildDialog(user.telephoneNumber, "phone number") { newPhoneNumber ->
                user.telephoneNumber = newPhoneNumber
                adjustElementsByState(profileState)
                writeUserDataToTextViews()
            }
        }
        addressChangeText.setOnClickListener {
            buildDialog(user.address, "address") { newAddress ->
                user.address = newAddress
                adjustElementsByState(profileState)
                writeUserDataToTextViews()
            }
        }
    }

    private fun buildDialog(text: String, propertyName: String, positiveAction: (String) -> Unit) {
        val textChangeDialogView = LayoutInflater
            .from(view.context)
            .inflate(R.layout.text_change_dialog, null)

        val newTextField = textChangeDialogView.findViewById<EditText>(R.id.et_new_text)
        val errorMessage = textChangeDialogView.findViewById<TextView>(R.id.tv_error_message)

        newTextField.setText(text)
        errorMessage.text = ""

        newTextField.afterTextChanged { /* Implementirati provjeru unesenog polja */ }

        AlertDialog.Builder(view.context)
            .setView(textChangeDialogView)
            .setTitle(view.context.getString(R.string.change_in_dialog, propertyName))
            .setPositiveButton("Save changes") { _, _ ->
                positiveAction(newTextField.text.toString())
            }
            .show()

    }

    private fun adjustElementsByState(state: ProfileState) {
        when (state) {
            ProfileState.Viewing -> {
                editingMessage.visibility = View.GONE
                allowEditingButton.visibility = View.VISIBLE
                saveChangesButton.visibility = View.GONE
                emailAddressChangeText.visibility = View.GONE
                telephoneNumberChangeText.visibility = View.GONE
                addressChangeText.visibility = View.GONE
            }
            else -> {
                editingMessage.visibility = View.VISIBLE
                allowEditingButton.visibility = View.GONE
                saveChangesButton.visibility = View.VISIBLE
                emailAddressChangeText.visibility = View.VISIBLE
                telephoneNumberChangeText.visibility = View.VISIBLE
                addressChangeText.visibility = View.VISIBLE
            }
        }
    }

    private fun writeUserDataToTextViews() {
        nameAndSurname.text = "${user.name} ${user.surname}"
        usernameText.text = user.username
        emailAddressText.text = user.email
        telephoneNumberText.text = user.telephoneNumber
        addressText.text = user.address
    }

    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(editable: Editable?) {
                afterTextChanged.invoke(editable.toString())
            }
        })
    }
}