package hr.foi.rampu.emedi.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hr.foi.rampu.emedi.LoginActivity
import hr.foi.rampu.emedi.MainActivity
import hr.foi.rampu.emedi.R
import hr.foi.rampu.emedi.database.AppDatabase
import hr.foi.rampu.emedi.entities.User
import hr.foi.rampu.emedi.helpers.ProfileChangeHelper
import hr.foi.rampu.emedi.helpers.UserSession
import hr.foi.rampu.emedi.helpers.UserSession.loggedUser
import kotlinx.coroutines.Dispatchers.Main
import java.util.Date

enum class ProfileState {
    Viewing,
    Editing
}

class ProfileFragment : Fragment() {
    val testString: String = "ide gas 1 2 3"

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

        val logoutButton: Button = view.findViewById(R.id.btn_logout)
        logoutButton.setOnClickListener {
            if (profileState == ProfileState.Editing) {
                Toast.makeText(view.context, "Please, save changes first!", Toast.LENGTH_LONG)
                    .show()
            } else {
                val intent = Intent(activity, LoginActivity::class.java)
                activity?.startActivity(intent)
            }
        }
        val deleteButton: Button = view.findViewById(R.id.btn_delete)
        deleteButton.setOnClickListener {
            if (profileState == ProfileState.Editing) {
                Toast.makeText(view.context, "Please, save changes first!", Toast.LENGTH_LONG)
                    .show()
            } else {
                AppDatabase.getInstance().getUsersDao().deleteUser(UserSession.loggedUser)
                val intent = Intent(activity, LoginActivity::class.java)
                activity?.startActivity(intent)
            }
        }
    }
}