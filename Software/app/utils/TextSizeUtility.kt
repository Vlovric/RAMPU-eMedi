object TextSizeUtility {
    var textSize: Float = 12f

    fun setTextViewSize(textView: TextView) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
    }
}
