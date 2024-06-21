package com.example.hope

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.hope.R

class generic2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generic2)

        val heading = intent.getStringExtra("mainheading")
        val image = intent.getStringExtra("mainimage")
        val role = intent.getStringExtra("role")
        val coordinator1 = intent.getStringExtra("codname1")
        val coordinatorImgUrl1 = intent.getStringExtra("codimg1")
        val coordinator2 = intent.getStringExtra("codname2")
        val coordinatorImgUrl2 = intent.getStringExtra("codimg2")

        val headingTextView: TextView = findViewById(R.id.headingofbcca)
        val roleofclub: TextView = findViewById(R.id.roleofbcca)
        val imageView: ImageView = findViewById(R.id.logoofbcca)
        val cod: TextView = findViewById(R.id.coordinator1name)
        val codImg: ImageView = findViewById(R.id.coordinator1)
        val cod2: TextView = findViewById(R.id.coordinator2name)
        val codImg2: ImageView = findViewById(R.id.coordinator2)

        headingTextView.text = heading
        roleofclub.text = role
        Glide.with(this)
            .load(coordinatorImgUrl1)
            .into(codImg)
        cod.text = coordinator1
        Glide.with(this)
            .load(coordinatorImgUrl2)
            .into(codImg2)
        cod2.text = coordinator2
        Glide.with(this)
            .load(image)
            .into(imageView)
    }
}
