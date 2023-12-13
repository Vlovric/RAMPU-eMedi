// SettingsActivity.kt
package hr.foi.rampu.emedi

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import hr.foi.rampu.emedi.helpers.TextSizeUtility

class SettingsActivity : AppCompatActivity() {

    private lateinit var textSizeUtility: TextSizeUtility
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        TextSizeUtility.initialize(this) // Initialize the utility
        textSizeUtility = TextSizeUtility.getInstance() // Get the instance

        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        val seekBarFontSize: SeekBar = findViewById(R.id.fontSizeSeekBar)
        val btnBack: Button = findViewById(R.id.btnBack)

        // Load the saved font size from SharedPreferences
        val savedFontSize = sharedPreferences.getFloat("fontSize", 12f)
        seekBarFontSize.progress = (savedFontSize - 12f).toInt()

        seekBarFontSize.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // Update text size dynamically
                val newSize = 12f + progress.toFloat()
                textSizeUtility.setTextSize(newSize)

                textSizeUtility.registerButton(findViewById(R.id.btnFontSize))
                textSizeUtility.registerButton(findViewById(R.id.btnFontStyle))
                textSizeUtility.registerButton(findViewById(R.id.btnAppColor))

                // Save the selected font size in SharedPreferences
                with(sharedPreferences.edit()) {
                    putFloat("fontSize", newSize)
                    apply()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // Not needed for this example
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Not needed for this example
            }
        })



        btnBack.setOnClickListener {
            navigateToMainActivity()
        }
    }

    private fun navigateToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
