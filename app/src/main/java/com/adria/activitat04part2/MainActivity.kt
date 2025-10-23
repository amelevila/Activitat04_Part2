package com.adria.activitat04part2

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.slider.RangeSlider

class MainActivity : AppCompatActivity() {
    private var isMaleSelected : Boolean = true
    private var isFemaleSelected : Boolean = false

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponents()
        initListenters()
        initUI()
    }

    private fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale  = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
    }

    private fun initListenters() {
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
            val cm = value.toInt().toString()
            tvHeight.text = "$cm cm"
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