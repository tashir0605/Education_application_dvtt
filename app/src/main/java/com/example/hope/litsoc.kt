package com.example.hope

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class litsoc : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_litsoc)
        val heading = intent.getStringExtra("mainheading")
        val image = intent.getStringExtra("mainimage")
        val role = intent.getStringExtra("role")
        val secyname = intent.getStringExtra("secyname")
        val secyImageUrl = intent.getStringExtra("secyimg")
        val wgch = intent.getStringExtra("wgch")
        val wgchImageUrl = intent.getStringExtra("wgchimg")
        val och = intent.getStringExtra("och")
        val ochImageUrl = intent.getStringExtra("ochimg")
        val bch = intent.getStringExtra("bch")
        val bchImageUrl = intent.getStringExtra("bchiimg")
        val cwpch = intent.getStringExtra("cwpch")
        val cwpchImageUrl = intent.getStringExtra("cwpchiimg")
        val goonj= intent.getStringExtra("goonj")
        val goonjImageUrl = intent.getStringExtra("goonjiimg")




        val headingTextView: TextView = findViewById(R.id.mainHeading)
        val roleofclub: TextView= findViewById(R.id.role)
        val imageView: ImageView = findViewById(R.id.mainlogo)
        val secy: TextView = findViewById(R.id.secyname)
        val secyImg: ImageView = findViewById(R.id.secyimg)
        val wgchImg: ImageView = findViewById(R.id.wgchimg)
        val ochImg: ImageView = findViewById(R.id.ochimg)
        val bchImg: ImageView = findViewById(R.id.bchimg)
        val cwpchImg: ImageView = findViewById(R.id.cwpcimg)
        val goonjImg: ImageView = findViewById(R.id.goonjimg)
        val wgchname: TextView = findViewById(R.id.wgch)
        val ochname: TextView = findViewById(R.id.och)
        val bchname: TextView = findViewById(R.id.bch)
        val cwpchname: TextView = findViewById(R.id.cwpc)
        val goonjname: TextView = findViewById(R.id.goonj)






        headingTextView.text = heading
        roleofclub.text = role
        Glide.with(this)
            .load(image)
            .into(imageView)
        Glide.with(this)
            .load(secyImageUrl)
            .into(secyImg)
        secy.text = secyname
        Glide.with(this)
            .load(wgchImageUrl)
            .into(wgchImg)
        wgchname.text = wgch
         Glide.with(this)
            .load(ochImageUrl)
            .into(ochImg)
        ochname.text = och
        Glide.with(this)
            .load(bchImageUrl)
            .into(bchImg)
        bchname.text = bch
        Glide.with(this)
            .load(cwpchImageUrl)
            .into(cwpchImg)
        cwpchname.text = cwpch
        Glide.with(this)
            .load(goonjImageUrl)
            .into(goonjImg)
        goonjname.text = goonj

    }
}