package hr.foi.rampu.emedi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import hr.foi.rampu.emedi.R
import hr.foi.rampu.emedi.entities.User
import hr.foi.rampu.emedi.helpers.ProfileChangeHelper
import hr.foi.rampu.emedi.helpers.UserSession
import hr.foi.rampu.emedi.helpers.UserSession.loggedUser
import java.util.Date

enum class ProfileState {
    Viewing,
    Editing
}

class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val profileState = ProfileState.Viewing
        if (UserSession.loggedUser != null) {
            val profileChangeHelper = ProfileChangeHelper(view, UserSession.loggedUser)
        }
    }
}