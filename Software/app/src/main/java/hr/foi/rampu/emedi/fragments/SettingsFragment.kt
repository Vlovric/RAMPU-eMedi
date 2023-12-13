// SettingsFragment.kt
package hr.foi.rampu.emedi.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import hr.foi.rampu.emedi.R
import hr.foi.rampu.emedi.SettingsActivity

class SettingsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        // Find the button by its ID
        val btnOpenSettings = view.findViewById<Button>(R.id.btnOpenSettings)

        // Set a click listener for the button
        btnOpenSettings.setOnClickListener {
            // When the button is clicked, open the SettingsActivity
            val intent = Intent(activity, SettingsActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}
