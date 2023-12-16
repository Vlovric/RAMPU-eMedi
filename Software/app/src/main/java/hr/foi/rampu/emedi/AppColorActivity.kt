package hr.foi.rampu.emedi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import hr.foi.rampu.emedi.entities.ColorPalette

class AppColorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appcolor)

        val colorPalettes = createColorPalettes(this)
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

            color1View.setBackgroundColor(palette.color1)
            color2View.setBackgroundColor(palette.color2)
            color3View.setBackgroundColor(palette.color3)

            paletteView.setOnClickListener { returnResult(palette) }

            gridLayout.addView(paletteView)


        }
    }


    private fun createColorPalettes(context: Context): List<ColorPalette> {
        return listOf(
            ColorPalette(ContextCompat.getColor(context, R.color.black), ContextCompat.getColor(context, R.color.gray), ContextCompat.getColor(context, R.color.white)),
            ColorPalette(ContextCompat.getColor(context, R.color.white), ContextCompat.getColor(context, R.color.steel_blue), ContextCompat.getColor(context, R.color.white)),
            ColorPalette(ContextCompat.getColor(context, R.color.beige), ContextCompat.getColor(context, R.color.olive_green), ContextCompat.getColor(context, R.color.black)),
            ColorPalette(ContextCompat.getColor(context, R.color.linen), ContextCompat.getColor(context, R.color.sienna), ContextCompat.getColor(context, R.color.white))
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
