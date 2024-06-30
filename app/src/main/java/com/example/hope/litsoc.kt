package com.example.hope

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore

class litsoc : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_litsoc)

        db = FirebaseFirestore.getInstance()

        // Initialize your views
        val headingTextView: TextView = findViewById(R.id.mainHeading)
        val roleofclub: TextView = findViewById(R.id.role)
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

        // Fetch data from Firestore document with ID "LitSoc"
        db.collection("Clubs").document("LitSoc")
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    headingTextView.text = document.getString("mainheading")
                    roleofclub.text = document.getString("role")
                    Glide.with(this).load(document.getString("mainimage")).into(imageView)
                    Glide.with(this).load(document.getString("secyimg")).into(secyImg)
                    secy.text = document.getString("secyname")
                    Glide.with(this).load(document.getString("wgchimg")).into(wgchImg)
                    wgchname.text = document.getString("wgch")
                    Glide.with(this).load(document.getString("ochimg")).into(ochImg)
                    ochname.text = document.getString("och")
                    Glide.with(this).load(document.getString("bchiimg")).into(bchImg)
                    bchname.text = document.getString("bch")
                    Glide.with(this).load(document.getString("cwpchiimg")).into(cwpchImg)
                    cwpchname.text = document.getString("cwpch")
                    Glide.with(this).load(document.getString("goonjiimg")).into(goonjImg)
                    goonjname.text = document.getString("goonj")
                } else {
                    // Handle case where document does not exist
                }
            }
            .addOnFailureListener { exception ->
                // Handle any errors
            }
    }
}
