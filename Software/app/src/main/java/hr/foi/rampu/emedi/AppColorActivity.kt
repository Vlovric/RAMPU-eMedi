package hr.foi.rampu.emedi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity
import hr.foi.rampu.emedi.entities.ColorPalette

class AppColorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appcolor)

        val colorPalettes = createColorPalettes()
        setupColorPaletteGrid(colorPalettes)
        val btnBack = findViewById<Button>(R.id.btnBack)
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
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


        }
    }


    private fun createColorPalettes(): List<ColorPalette> {
        return listOf(
            ColorPalette("#000000", "#777777", "#FFFFFF"),
            ColorPalette("#FFFFFF", "#34495e", "#FFFFFF"),
            ColorPalette("#F5F5DC", "#789E2B", "#000000"),
            ColorPalette("#FAF0E6", "#A35519", "#FFFFFF")
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
