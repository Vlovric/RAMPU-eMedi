package hr.foi.rampu.emedi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity
import hr.foi.rampu.emedi.entities.ColorPalette

class AppColorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appcolor)

        val colorPalettes = createColorPalettes()
        setupColorPaletteGrid(colorPalettes)
    }

    private fun setupColorPaletteGrid(colorPalettes: List<ColorPalette>) {
        val gridLayout = findViewById<GridLayout>(R.id.colorPaletteGrid)

        colorPalettes.forEachIndexed { rowIndex, palette ->
            val paletteView = layoutInflater.inflate(R.layout.item_color_palette, gridLayout, false)

            val color1View = paletteView.findViewById<View>(R.id.color1)
            val color2View = paletteView.findViewById<View>(R.id.color2)
            val color3View = paletteView.findViewById<View>(R.id.color3)

            color1View.setBackgroundColor(android.graphics.Color.parseColor(palette.color1))
            color2View.setBackgroundColor(android.graphics.Color.parseColor(palette.color2))
            color3View.setBackgroundColor(android.graphics.Color.parseColor(palette.color3))

            paletteView.setOnClickListener { returnResult(palette) }

            gridLayout.addView(paletteView)

            if (rowIndex == 1) {
                val params = paletteView.layoutParams as GridLayout.LayoutParams
                params.topMargin = resources.getDimensionPixelSize(R.dimen.palette_margin_top)
                paletteView.layoutParams = params
            }
        }
    }


    private fun createColorPalettes(): List<ColorPalette> {
        return listOf(
            ColorPalette("#FF5733", "#3498DB", "#2E2E2E"),
            ColorPalette("#27AE60", "#E74C3C", "#F8F9FA"),
            ColorPalette("#3498DB", "#FFC300", "#34495E"),
            ColorPalette("#9B59B6", "#1ABC9C", "#E74C3C")
        )
    }

    private fun returnResult(colorPalette: ColorPalette) {
        val resultIntent = Intent()
        resultIntent.putExtra(SELECTED_COLORS, colorPalette)
        setResult(RESULT_OK, resultIntent)
        finish()
    }

    companion object {
        const val SELECTED_COLORS = "selected_colors"
    }
}
