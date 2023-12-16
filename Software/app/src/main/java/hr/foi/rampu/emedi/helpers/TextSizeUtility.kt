// TextSizeUtility.kt
package hr.foi.rampu.emedi.helpers

import android.content.Context
import android.content.SharedPreferences
import android.util.TypedValue
import android.widget.Button
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import hr.foi.rampu.emedi.R

class TextSizeUtility private constructor(private val context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("TextSizePrefs", Context.MODE_PRIVATE)

    private val viewsToApplyTextSize: MutableList<TextView> = mutableListOf()
    private val buttonsToApplyTextSize: MutableList<Button> = mutableListOf()
    private val viewsToApplyStyle: MutableList<TextView> = mutableListOf()
    private val buttonsToApplyStyle: MutableList<Button> = mutableListOf()
    private fun getTextSize(): Float {
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

    private fun applyTextSizeToView(textView: TextView) {
        val textSize = getTextSize()
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
    }
    fun registerTextViewStyle(context: Context, textView: TextView, position: Int){
        viewsToApplyStyle.add(textView)
        applyFont(context, textView, position)
    }

    fun registerButtonStyle(context: Context, button: Button, position: Int) {
        buttonsToApplyStyle.add(button)
        applyFont(context, button, position)
    }
    fun unregisterTextViewStyle(textView: TextView) {
        viewsToApplyStyle.remove(textView)
    }
    fun unregisterButtonStyle(button: Button) {
        viewsToApplyStyle.remove(button)
    }


    companion object {
        private lateinit var instance: TextSizeUtility

        fun initialize(context: Context) {
            instance = TextSizeUtility(context)
        }

        fun getInstance(): TextSizeUtility {
            return instance
        }

        private fun applyFont(context: Context, textView: TextView, position: Int) {

            val fontResourceId = getFontResourceId(position)

            val typeface = ResourcesCompat.getFont(context, fontResourceId)
            textView.typeface = typeface
        }

        private fun getFontResourceId(position: Int): Int {

            return when (position) {
                0 -> R.font.jetbrainsmono
                1 -> R.font.dyslexie
                2 -> R.font.robotomono
                3 -> R.font.slabo
                4 -> R.font.titilliumweb

                else -> R.font.robotomono
            }
        }

    }

}

