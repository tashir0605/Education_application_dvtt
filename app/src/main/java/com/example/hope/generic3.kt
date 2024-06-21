package com.example.hope

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.hope.R

class generic3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generic3)

        val heading = intent.getStringExtra("mainheading")
        val image = intent.getStringExtra("mainimage")
        val role = intent.getStringExtra("role")
        val secyname = intent.getStringExtra("secyname")
        val secyImageUrl = intent.getStringExtra("secyimg")
        val capname = intent.getStringExtra("capname")
        val mancaptainImgUrl = intent.getStringExtra("capimg")
        val vcapname = intent.getStringExtra("vcapname")
        val manvcaptainImgUrl = intent.getStringExtra("vcapimg")
        val capname2= intent.getStringExtra("capname2")
        val womancaptainImgUrl = intent.getStringExtra("capimg2")
        val vcapname2 = intent.getStringExtra("vcapname2")
        val womanvcaptainImgUrl = intent.getStringExtra("vcapimg2")


        val imageView: ImageView = findViewById(R.id.mainlogo)
        val headingTextView: TextView = findViewById(R.id.mainHeading)
        val roleofclub: TextView = findViewById(R.id.role)
        val secy: TextView = findViewById(R.id.secyname)
        val secyImg: ImageView = findViewById(R.id.secyimg)
        val mc: TextView = findViewById(R.id.mencaptain)
        val mcImg: ImageView = findViewById(R.id.mencaptainimg)
        val mvc: TextView = findViewById(R.id.menvicecaptain)
        val mvcImg: ImageView = findViewById(R.id.menvicecaptainimg)
        val wc: TextView = findViewById(R.id.womencaptain)
        val wcImg: ImageView = findViewById(R.id.womencaptainimg)
        val wvc: TextView = findViewById(R.id.womenvicecaptai)
        val wvcImg: ImageView = findViewById(R.id.womenvicecaptainimg)





        headingTextView.text = heading
        roleofclub.text = role
        secy.text = secyname

        Glide.with(this)
            .load(image)
            .into(imageView)
        Glide.with(this)
            .load(secyImageUrl)
            .into(secyImg)

        mc.text = capname
        Glide.with(this)
            .load(mancaptainImgUrl)
            .into(mcImg)

        mvc.text = vcapname
        Glide.with(this)
            .load(manvcaptainImgUrl)
            .into(mvcImg)
        wc.text = capname2
        Glide.with(this)
            .load(womancaptainImgUrl)
            .into(wcImg)
        wvc.text = vcapname2
        Glide.with(this)
            .load(womanvcaptainImgUrl)
            .into(wvcImg)


    }
}
