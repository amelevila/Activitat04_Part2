package com.adria.activitat04part2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.adria.activitat04part2.MainActivity.Companion.IMC_KEY

class ResultActivity : AppCompatActivity() {

    private lateinit var tvRangeIMC: TextView
    private lateinit var tvNumberIMC: TextView
    private lateinit var btnReCalculate: Button
    private data class ImcRange(val range: String, val colorReference: Int)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        initComponents()
        initListeners()

        val imc: Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        setNumberIMC(imc)

        val imcRange = calculateImcRange(imc)
        setRange(imcRange)

    }
    private fun initListeners() {
        btnReCalculate.setOnClickListener { finish() }
    }
    private fun initComponents() {
        tvRangeIMC = findViewById(R.id.tvRangeIMC)
        tvNumberIMC = findViewById(R.id.tvNumberIMC)
        btnReCalculate = findViewById(R.id.btnReCalculate)
    }

    private fun calculateImcRange(imc: Double): ImcRange {
         return when {
            imc < 18.5 -> ImcRange("Underweight", R.color.light_blue)
            imc < 25.0 -> ImcRange("Normal", R.color.green)
            imc < 30.0 -> ImcRange("Overweight", R.color.orange)
            else -> ImcRange("Obese", R.color.red)
        }
    }

    private fun setRange(imcRange: ImcRange) {
        val color = ContextCompat.getColor(this, imcRange.colorReference)

        tvRangeIMC.text = imcRange.range
        tvRangeIMC.setTextColor(color)
    }

    private fun setNumberIMC(imc: Double) {
        tvNumberIMC.text = imc.toString()
    }


}