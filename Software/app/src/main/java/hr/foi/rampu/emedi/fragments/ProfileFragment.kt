package hr.foi.rampu.emedi.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import hr.foi.rampu.emedi.R
import hr.foi.rampu.emedi.entities.User
import java.util.Date

class ProfileFragment : Fragment() {
    val loggedUser = User("Ivan", // Potrebno dodati logiku i povezati sa logiranjem
        "Ivic",
        Date(),
        "ivan.ivic@mail.hr",
        "123 456-7890",
        "Ulica Grada Sela 123",
        "admin",
        "test")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<TextView>(R.id.tv_user_name_and_surname).text = loggedUser.name + " " + loggedUser.surname
    }
}