package com.adria.activitat04part2

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider

class MainActivity : AppCompatActivity() {
    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var kgWeight: Int = 70
    private var yearsAge: Int = 18
    private var cmHeight: Int = 50

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider
    private lateinit var tvWeight: TextView
    private lateinit var btnMinusWeight: FloatingActionButton
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var tvAge: TextView
    private lateinit var btnMinusAge: FloatingActionButton
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var btnCalculate: Button

    companion object {
        const val IMC_KEY = "IMC"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponents()
        initListeners()
        initUI()
    }
    private fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale  = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        tvWeight = findViewById(R.id.tvWeight)
        btnMinusWeight = findViewById(R.id.btnMinusWeight)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        tvAge = findViewById(R.id.tvAge)
        btnMinusAge = findViewById(R.id.btnMinusAge)
        btnPlusAge = findViewById(R.id.btnPlusAge)
        btnCalculate = findViewById(R.id.btnCalculate)
    }
    private fun initListeners() {
        viewMale.setOnClickListener {
            if (isFemaleSelected) {
                changeGender()
                setGenderColor()
            }
        }
        viewFemale.setOnClickListener {
            if (isMaleSelected) {
                changeGender()
                setGenderColor()
            }
        }
        rsHeight.addOnChangeListener { _, value, _ ->
            cmHeight = value.toInt()
            setHeight()
        }
        btnMinusWeight.setOnClickListener {
            kgWeight--
            setWeight()
        }
        btnPlusWeight.setOnClickListener {
            kgWeight++
            setWeight()
        }
        btnMinusAge.setOnClickListener {
            yearsAge--
            setAge()
        }
        btnPlusAge.setOnClickListener {
            yearsAge++
            setAge()
        }
        btnCalculate.setOnClickListener {
            val imc = calculateIMC()
            openResult(imc)
        }
    }
    private fun initUI() {
        setGenderColor()
        setHeight()
        setWeight()
        setAge()
    }
    private fun changeGender() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }
    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackGroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackGroundColor(isFemaleSelected))
    }
    private fun getBackGroundColor(isSelectedComponent:Boolean):Int {
        val colorReference = if (isSelectedComponent) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }

        return ContextCompat.getColor(this, colorReference)
    }
    private fun setHeight() {
        tvHeight.text = "$cmHeight cm"
    }
    private fun setWeight() {
        tvWeight.text = "$kgWeight kg"
    }
    private fun setAge() {
        tvAge.text = "$yearsAge"
    }
    private fun calculateIMC():Double {
        val mHeight = cmHeight.toDouble()/100
        val imc = kgWeight/(mHeight*mHeight)

        val df = DecimalFormat("#.##")
        return df.format(imc).toDouble()
    }

    private fun openResult(imc: Double) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra(IMC_KEY, imc)
        startActivity(intent)
    }

}