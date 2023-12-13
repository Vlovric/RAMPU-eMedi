package hr.foi.rampu.emedi.helpers

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.util.TypedValue

class TextSizeUtility(private val context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("TextSizePrefs", Context.MODE_PRIVATE)

    fun getTextSize(): Float {
        val textSize = preferences.getFloat("textSize", 12f)
        Log.d("TextSizeUtility", "Retrieved text size: $textSize")
        return textSize
    }

    fun updateTextSize(size: Float) {
        preferences.edit().putFloat("textSize", size).apply()
        Log.d("TextSizeUtility", "Updated text size: $size")
    }
}

