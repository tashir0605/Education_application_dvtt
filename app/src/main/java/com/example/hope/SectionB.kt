package com.example.hope

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SectionB : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_section_b)




        val secondpagebutton = findViewById<TextView>(R.id.button_second)
        secondpagebutton.setOnClickListener {
            val intent = Intent(this, SectionA::class.java)
            startActivity(intent)
        }


        val embutton = findViewById<TextView>(R.id.button_mechanics)
        embutton.setOnClickListener {
            val intent = Intent(this, em::class.java)
            startActivity(intent)

        }
        val eobutton = findViewById<TextView>(R.id.button_eo)
        eobutton.setOnClickListener {
            val intent = Intent(this, eo::class.java)
            startActivity(intent)

        }
        val chembutton = findViewById<TextView>(R.id.button_chemistry)
        chembutton.setOnClickListener {
            val intent = Intent(this, chem::class.java)
            startActivity(intent)

        }

        val mathbutton = findViewById<TextView>(R.id.button_math)
        mathbutton.setOnClickListener {
            val intent = Intent(this, math2::class.java)
            startActivity(intent)

        }
    }
}