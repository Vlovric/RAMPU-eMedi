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
import androidx.core.content.ContextCompat
import hr.foi.rampu.emedi.entities.ColorPalette
import hr.foi.rampu.emedi.helpers.TextSizeUtility

class SettingsActivity : AppCompatActivity() {

    private lateinit var textSizeUtility: TextSizeUtility
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var fontSpinner: Spinner
    private lateinit var btnAppColor: Button  // Declare btnAppColor as a property

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

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        btnAppColor = findViewById(R.id.btnAppColor) // Initialize btnAppColor

        btnAppColor.setOnClickListener {
            startColorSelectionActivity()
        }

        fontSpinner = findViewById(R.id.fontSpinner)

        fontSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                textSizeUtility.registerTextViewStyle(this@SettingsActivity, findViewById(R.id.tv_FontSize), position)
                textSizeUtility.registerButtonStyle(this@SettingsActivity, findViewById(R.id.btnBack), position)
                textSizeUtility.registerButtonStyle(this@SettingsActivity, btnAppColor, position) // Use btnAppColor
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {}
        }

        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun startColorSelectionActivity() {
        val intent = Intent(this, AppColorActivity::class.java)
        startActivityForResult(intent, COLOR_SELECTION_REQUEST_CODE)
    }

    private fun applyColorsToUI(colorPalette: ColorPalette) {
        // Apply the selected colors to the UI elements in your activity
        // For example, change the background color, text color, etc.

        // Apply background color to the whole application
        findViewById<View>(android.R.id.content).setBackgroundColor(
            ContextCompat.getColor(this, android.R.color.background_dark)
        )

        // Apply button background color
        btnAppColor.setBackgroundColor(android.graphics.Color.parseColor(colorPalette.buttonColor))

        // Apply text color to other elements
        // ...

        // You may need to adjust this based on your actual UI structure
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == COLOR_SELECTION_REQUEST_CODE && resultCode == RESULT_OK) {
            val selectedPalette =
                data?.getParcelableExtra<ColorPalette>(AppColorActivity.SELECTED_COLORS)
            selectedPalette?.let {
                applyColorsToUI(it)
            }
        }
    }

    companion object {
        private const val COLOR_SELECTION_REQUEST_CODE = 100
    }
}
