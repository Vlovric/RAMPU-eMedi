// SettingsActivity.kt
package hr.foi.rampu.emedi

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.SeekBar
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import hr.foi.rampu.emedi.helpers.TextSizeUtility

class SettingsActivity : AppCompatActivity() {

    private lateinit var textSizeUtility: TextSizeUtility
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var fontSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        TextSizeUtility.initialize(this)
        textSizeUtility = TextSizeUtility.getInstance()

        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        val seekBarFontSize: SeekBar = findViewById(R.id.fontSizeSeekBar)
        val btnBack: Button = findViewById(R.id.btnBack)

        val savedFontSize = sharedPreferences.getFloat("fontSize", 12f)
        seekBarFontSize.progress = (savedFontSize - 12f).toInt()

        seekBarFontSize.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {

                val newSize = 12f + progress.toFloat()
                textSizeUtility.setTextSize(newSize)

                textSizeUtility.registerTextView(findViewById(R.id.tv_FontSize))
                textSizeUtility.registerButton(findViewById(R.id.btnAppColor))
                textSizeUtility.registerButton(findViewById(R.id.btnBack))


                // Save the selected font size in SharedPreferences
                with(sharedPreferences.edit()) {
                    putFloat("fontSize", newSize)
                    apply()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })

        fontSpinner = findViewById(R.id.fontSpinner)

        fontSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                textSizeUtility.registerTextViewStyle(this@SettingsActivity,findViewById(R.id.tv_FontSize), position)
                textSizeUtility.registerButtonStyle(this@SettingsActivity,findViewById(R.id.btnBack), position)
                textSizeUtility.registerButtonStyle(this@SettingsActivity,findViewById(R.id.btnAppColor), position)
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {}
        }


        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}
