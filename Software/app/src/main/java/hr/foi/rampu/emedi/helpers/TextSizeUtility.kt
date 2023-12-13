package hr.foi.rampu.emedi.helpers

import android.content.Context
import android.content.SharedPreferences
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class TextSizeUtility(private val context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("TextSizePrefs", Context.MODE_PRIVATE)

    fun getTextSize(): Float {
        return preferences.getFloat("textSize", 12f)
    }

    fun setTextSize(size: Float) {
        preferences.edit().putFloat("textSize", size).apply()
    }

    fun applyTextSizeToView(view: View) {
        if (view is TextView) {
            val textSize = getTextSize()
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
        } else if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                applyTextSizeToView(view.getChildAt(i))
            }
        }
    }
}
