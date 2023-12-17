package hr.foi.rampu.emedi.fragments

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
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
    private var selectedColorPalette: ColorPalette? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_settings, container, false)

        TextSizeUtility.initialize(requireContext())
        textSizeUtility = TextSizeUtility.getInstance()
        sharedPreferences = requireActivity().getSharedPreferences("Prefs", Context.MODE_PRIVATE)

        val seekBarFontSize: SeekBar = view.findViewById(R.id.fontSizeSeekBar)
        val btnBack: Button = view.findViewById(R.id.btnBack)

        val savedFontSize = sharedPreferences.getFloat("fontSize", 12f)
        seekBarFontSize.progress = (savedFontSize - 12f).toInt()

        seekBarFontSize.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {

                val newSize = 12f + progress.toFloat()
                textSizeUtility.setTextSize(newSize)

                with(sharedPreferences.edit()) {
                    putFloat("fontSize", newSize)
                    selectedColorPalette?.let {
                        putInt("color1", it.color1)
                        putInt("color2", it.color2)
                        putInt("color3", it.color3)
                    }
                    apply()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        btnAppColor = view.findViewById(R.id.btnAppColor)

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
                with(sharedPreferences.edit()) {
                    putInt("selectedPosition", position)
                    apply()
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {}
        }

        btnBack.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        changeTextSize(view)
        return view
    }

    private fun changeTextSize(view: View) {
        val position = sharedPreferences.getInt("selectedPosition", 1)
        TextSizeUtility.initialize(requireContext())
        textSizeUtility = TextSizeUtility.getInstance()

        textSizeUtility.registerAllTextViews(view.findViewById(R.id.tv_FontSize))

        textSizeUtility.registerTextViewStyle(requireContext(), view.findViewById(R.id.tv_FontSize), position)

        textSizeUtility.registerAllButtons(
            view.findViewById(R.id.btnBack),
            view.findViewById(R.id.btnAppColor)
        )

        textSizeUtility.registerButtonStyle(requireContext(), view.findViewById(R.id.btnBack), position)
        textSizeUtility.registerButtonStyle(requireContext(), view.findViewById(R.id.btnAppColor), position)
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
        val button2 = view?.findViewById<Button>(R.id.btnBack)
        val textView1 = view?.findViewById<TextView>(R.id.tv_FontSize)

        val rootLayout = view?.findViewById<RelativeLayout>(R.id.root_layout)
        rootLayout?.setBackgroundColor(color1)

        button1?.setBackgroundColor(color2)
        button2?.setBackgroundColor(color2)
        textView1?.setBackgroundColor(color2)
        button1?.setTextColor(color3)
        button2?.setTextColor(color3)
        textView1?.setTextColor(color3)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == COLOR_SELECTION_REQUEST_CODE && resultCode == RESULT_OK) {
            selectedColorPalette =
                data?.getParcelableExtra<ColorPalette>(AppColorActivity.SELECTED_COLORS)
            selectedColorPalette?.let {
                applyColorsToUI(it)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // Save the selected color palette to the bundle
        outState.putParcelable("selectedColorPalette", selectedColorPalette)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Restore the selected color palette from the bundle
        selectedColorPalette = savedInstanceState?.getParcelable("selectedColorPalette")
        selectedColorPalette?.let {
            applyColorsToUI(it)
        }
    }

    companion object {
        private const val COLOR_SELECTION_REQUEST_CODE = 100
    }
}
