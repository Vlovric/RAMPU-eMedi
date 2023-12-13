// TextSizeUtility.kt
package hr.foi.rampu.emedi.helpers

import android.content.Context
import android.content.SharedPreferences
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class TextSizeUtility private constructor(private val context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("TextSizePrefs", Context.MODE_PRIVATE)

    private val viewsToApplyTextSize: MutableList<TextView> = mutableListOf()
    private val buttonsToApplyTextSize: MutableList<Button> = mutableListOf()

    fun getTextSize(): Float {
        return preferences.getFloat("textSize", 12f)
    }

    fun setTextSize(size: Float) {
        preferences.edit().putFloat("textSize", size).apply()
        applyTextSizeToViews()
    }

    fun registerTextView(textView: TextView) {
        viewsToApplyTextSize.add(textView)
        applyTextSizeToView(textView)
    }

    fun unregisterTextView(textView: TextView) {
        viewsToApplyTextSize.remove(textView)
    }


    fun registerButton(button: Button) {
        buttonsToApplyTextSize.add(button)
        applyTextSizeToView(button)
    }

    fun unregisterButton(button: Button) {
        buttonsToApplyTextSize.remove(button)
    }

    private fun applyTextSizeToViews() {
        for (textView in viewsToApplyTextSize) {
            applyTextSizeToView(textView)
        }
    }

    fun applyTextSizeToView(textView: TextView) {
        val textSize = getTextSize()
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
    }

    companion object {
        private lateinit var instance: TextSizeUtility

        fun initialize(context: Context) {
            instance = TextSizeUtility(context)
        }

        fun getInstance(): TextSizeUtility {
            return instance
        }
    }
}
