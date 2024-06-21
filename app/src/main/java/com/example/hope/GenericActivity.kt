package com.example.hope

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.hope.R

class GenericActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generic)

        val heading = intent.getStringExtra("mainheading")
        val image = intent.getStringExtra("mainimage")
        val role = intent.getStringExtra("role")
        val secyname = intent.getStringExtra("secyname")
        val secyImageUrl = intent.getStringExtra("secyimg")
        val joinsecyname1 = intent.getStringExtra("joinsecyname1")
        val joinsecyImgUrl1 = intent.getStringExtra("joinsecyimg1")
        val joinsecyname2 = intent.getStringExtra("joinsecyname2")
        val joinsecyImgUrl2 = intent.getStringExtra("joinsecyimg2")
        val joinsecyname3 = intent.getStringExtra("joinsecyname3")
        val joinsecyImgUrl3 = intent.getStringExtra("joinsecyimg3")

        val headingTextView: TextView = findViewById(R.id.mainHeading)
        val roleofclub: TextView= findViewById(R.id.role)
        val imageView: ImageView = findViewById(R.id.mainlogo)
        val secy: TextView = findViewById(R.id.secyname)
        val secyImg: ImageView = findViewById(R.id.secyimg)
        val joinSecy1: TextView = findViewById(R.id.joinsecyname1)
        val joinSecyImg1: ImageView = findViewById(R.id.joinsecyimg1)
        val joinSecy2: TextView = findViewById(R.id.joinsecyname2)
        val joinSecyImg2: ImageView = findViewById(R.id.joinsecyimg2)
        val joinSecy3: TextView = findViewById(R.id.joinsecyname3)
        val joinSecyImg3: ImageView = findViewById(R.id.joinsecyimg3)

        headingTextView.text = heading
        roleofclub.text = role
        Glide.with(this)
            .load(image)
            .into(imageView)
        Glide.with(this)
            .load(secyImageUrl)
            .into(secyImg)
        secy.text = secyname
        joinSecy1.text = joinsecyname1
        Glide.with(this)
            .load(joinsecyImgUrl1)
            .into(joinSecyImg1)
        joinSecy2.text = joinsecyname2
        Glide.with(this)
            .load(joinsecyImgUrl2)
            .into(joinSecyImg2)
        joinSecy3.text = joinsecyname3
        Glide.with(this)
            .load(joinsecyImgUrl3)
            .into(joinSecyImg3)
    }
}
