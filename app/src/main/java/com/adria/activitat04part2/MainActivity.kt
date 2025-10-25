package com.adria.activitat04part2

import android.os.Bundle
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
            val cmHeight = value.toInt().toString()
            tvHeight.text = "$cmHeight cm"
        }
        btnMinusWeight.setOnClickListener {
            kgWeight--
            tvWeight.text = "$kgWeight kg"
        }
        btnPlusWeight.setOnClickListener {
            kgWeight++
            tvWeight.text = "$kgWeight kg"
        }
        btnMinusAge.setOnClickListener {
            yearsAge--
            tvAge.text = "$yearsAge"
        }
        btnPlusAge.setOnClickListener {
            yearsAge++
            tvAge.text = "$yearsAge"
        }
    }

    private fun initUI() {
        setGenderColor()
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

}