package hr.foi.rampu.emedi

import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout

class SettingsActivity : AppCompatActivity() {

    lateinit var tabLayout: TabLayout
    lateinit var viewPager2: ViewPager2
    lateinit var seekBarFontSize: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_settings)

        setTabLayoutAndViewpager()

        // Initialize SeekBar
        seekBarFontSize = findViewById(R.id.fontSizeSeekBar)
        seekBarFontSize.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Update text size in the custom application class
                Application.textSize = 12f + progress.toFloat()

                // Notify other components of the application about the text size change if needed
            }

            // onStartTrackingTouch and onStopTrackingTouch remain the same
        })


        // ... rest of your onCreate code
    }

    // ... rest of your SettingsActivity code
}
