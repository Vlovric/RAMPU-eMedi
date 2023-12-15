package hr.foi.rampu.emedi.fragments

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.SeekBar
import android.widget.Spinner
import androidx.fragment.app.Fragment
import hr.foi.rampu.emedi.AppColorActivity
import hr.foi.rampu.emedi.MainActivity
import hr.foi.rampu.emedi.R
import hr.foi.rampu.emedi.entities.ColorPalette
import hr.foi.rampu.emedi.helpers.TextSizeUtility

class SettingsFragment : Fragment() {

    private lateinit var textSizeUtility: TextSizeUtility
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var fontSpinner: Spinner
    private lateinit var btnAppColor: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        TextSizeUtility.initialize(requireContext())
        textSizeUtility = TextSizeUtility.getInstance()
        var colorPalette = ColorPalette

        sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)

        val seekBarFontSize: SeekBar = view.findViewById(R.id.fontSizeSeekBar)
        val btnBack: Button = view.findViewById(R.id.btnBack)

        val savedFontSize = sharedPreferences.getFloat("fontSize", 12f)
        seekBarFontSize.progress = (savedFontSize - 12f).toInt()

        seekBarFontSize.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {

                val newSize = 12f + progress.toFloat()
                textSizeUtility.setTextSize(newSize)

                textSizeUtility.registerTextView(view.findViewById(R.id.tv_FontSize))
                textSizeUtility.registerButton(view.findViewById(R.id.btnAppColor))
                textSizeUtility.registerButton(view.findViewById(R.id.btnBack))

                with(sharedPreferences.edit()) {
                    putFloat("fontSize", newSize)
                    apply()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        btnAppColor = view.findViewById(R.id.btnAppColor) // Initialize btnAppColor

        btnAppColor.setOnClickListener {
            startColorSelectionActivity()
        }

        fontSpinner = view.findViewById(R.id.fontSpinner)

        fontSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                textSizeUtility.registerTextViewStyle(requireActivity(), view.findViewById(R.id.tv_FontSize), position)
                textSizeUtility.registerButtonStyle(requireActivity(), view.findViewById(R.id.btnBack), position)
                textSizeUtility.registerButtonStyle(requireActivity(), btnAppColor, position) // Use btnAppColor
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {}
        }

        btnBack.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }

        return view
    }

    private fun startColorSelectionActivity() {
        val intent = Intent(requireContext(), AppColorActivity::class.java)
        startActivityForResult(intent, COLOR_SELECTION_REQUEST_CODE)
    }

    private fun applyColorsToUI(colorPalette: ColorPalette) {

        val color1 = colorPalette.color1
        val color2 = colorPalette.color2
        val color3 = colorPalette.color3
        val button1 = view?.findViewById<Button>(R.id.btnAppColor)
        view?.findViewById<View>(android.R.id.content)?.setBackgroundColor(Color.parseColor(color1))
        button1?.setBackgroundColor(Color.parseColor(color2))
        button1?.setTextColor(Color.parseColor(color3))

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