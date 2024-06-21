package com.example.hope

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class cycle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cycle)
        val heading = intent.getStringExtra("mainheading")
        val image = intent.getStringExtra("mainimage")
        val role = intent.getStringExtra("role")
        val secyname = intent.getStringExtra("secyname")
        val secyImageUrl = intent.getStringExtra("secyimg")
        val headingTextView: TextView = findViewById(R.id.mainHeading)
        val roleofclub: TextView = findViewById(R.id.role)
        val imageView: ImageView = findViewById(R.id.mainlogo)
        val secy: TextView = findViewById(R.id.secyname)
        val secyImg: ImageView = findViewById(R.id.secyimg)

        headingTextView.text = heading
        roleofclub.text = role
        Glide.with(this)
            .load(image)
            .into(imageView)
        Glide.with(this)
            .load(secyImageUrl)
            .into(secyImg)
        secy.text = secyname
    }
}