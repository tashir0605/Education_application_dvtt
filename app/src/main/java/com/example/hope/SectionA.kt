package com.example.hope

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class SectionA : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_section)

        val secondPageButton = findViewById<TextView>(R.id.button_section_b)
        secondPageButton.setOnClickListener {
            val intent = Intent(this, SectionB::class.java)
            startActivity(intent)
        }

        val bioButton = findViewById<CardView>(R.id.button_bio)
        bioButton.setOnClickListener {
            val intent = Intent(this, ib::class.java)
            startActivity(intent)
        }

        val icButton = findViewById<CardView>(R.id.button_computer)
        icButton.setOnClickListener {
            val intent = Intent(this, ics::class.java)
            startActivity(intent)
        }

        val ieeButton = findViewById<CardView>(R.id.button_electrical)
        ieeButton.setOnClickListener {
            val intent = Intent(this, iee::class.java)
            startActivity(intent)
        }

        val mathButton = findViewById<CardView>(R.id.button_math)
        mathButton.setOnClickListener {
            val intent = Intent(this, math::class.java)
            startActivity(intent)
        }
    }
}
