package hr.foi.rampu.emedi.helpers

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
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
}