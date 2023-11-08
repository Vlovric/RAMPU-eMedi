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
import java.util.Date

enum class ProfileState {
    Viewing,
    Editing
}

class ProfileFragment : Fragment() {
    val loggedUser = User("Ana", // Potrebno dodati logiku i povezati sa logiranjem
        "Novak",
        Date(),
        "ananovak@ideposta.net",
        "0915121024",
        "Ulica Grada Sela 123",
        "anovak",
        "test123456")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val profileState = ProfileState.Viewing
        val profileChangeHelper = ProfileChangeHelper(view, loggedUser)
    }
}