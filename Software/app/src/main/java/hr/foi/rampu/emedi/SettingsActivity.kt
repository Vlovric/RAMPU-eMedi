package hr.foi.rampu.emedi

import android.os.Bundle
import android.util.TypedValue
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import hr.foi.rampu.emedi.helpers.TextSizeUtility

class SettingsActivity : AppCompatActivity() {

    lateinit var seekBarFontSize: SeekBar
    private lateinit var textSizeUtility: TextSizeUtility

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_settings)

        textSizeUtility = TextSizeUtility(this)

        val textView = findViewById<TextView>(R.id.probniid)
        updateTextSize(textView, textSizeUtility.getTextSize())

        // Initialize SeekBar
        seekBarFontSize = findViewById(R.id.fontSizeSeekBar)
        seekBarFontSize.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val newTextSize = 12f + progress.toFloat()
                updateTextSize(textView, newTextSize)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Not needed for this example
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Not needed for this example
            }
        })

        // ... rest of your onCreate code
    }

    private fun updateTextSize(textView: TextView, textSize: Float) {
        textView.textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, textSize, resources.displayMetrics)
        textSizeUtility.updateTextSize(textSize) // Update preferences
    }

    // ... rest of your SettingsActivity code
}
