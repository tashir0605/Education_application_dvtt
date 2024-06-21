package com.example.hope

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore

class College : AppCompatActivity() {
    private lateinit var image1 : ImageView
    private lateinit var image2 : ImageView
    private lateinit var image3 : ImageView
    private lateinit var body1 : TextView
    private lateinit var body2 : TextView
    private lateinit var body3 : TextView
    private lateinit var subheading1 : TextView
    private lateinit var subheading2 : TextView
    private lateinit var subheading3 : TextView

    private lateinit var db: FirebaseFirestore



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_college)
        image1 = findViewById(R.id.image1)
        image2 = findViewById(R.id.image2)
        image3 = findViewById(R.id.image3)
        body1 = findViewById(R.id.body1)
        body2 = findViewById(R.id.body2)
        body3 = findViewById(R.id.body3)
        subheading1= findViewById(R.id.subheading1)
        subheading2= findViewById(R.id.subheading2)
        subheading3= findViewById(R.id.subheading3)

        db= FirebaseFirestore.getInstance()
        val documentId = "Cllg"
        fetchdata(documentId)


        val Acadbutton = findViewById<TextView>(R.id.heading1)
        Acadbutton.setOnClickListener {
            val intent = Intent(this, AcacActivity::class.java)
            startActivity(intent)
        }
        val Sacbutton = findViewById<TextView>(R.id.heading2)
        Sacbutton.setOnClickListener {
            val intent = Intent(this, Sacactivity::class.java)
            startActivity(intent)
        }
    }

    private fun fetchdata(documentId: String) {
       val ref = db.collection("College").document(documentId)
        ref.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val image1Url = documentSnapshot.getString("image1")
                val image2Url = documentSnapshot.getString("image2")
                val image3Url = documentSnapshot.getString("image3")
                val body1Text = documentSnapshot.getString("body1")
                val body2Text = documentSnapshot.getString("body2")
                val body3Text = documentSnapshot.getString("body3")

                val subheading1Text = documentSnapshot.getString("subheading1")
                val subheading2Text = documentSnapshot.getString("subheading2")
                val subheading3Text = documentSnapshot.getString("subheading3")
                if (image1Url != null) {
                    Glide.with(this)
                        .load(image1Url)
                        .into(image1)
                }
                if (image2Url != null) {
                    Glide.with(this)
                        .load(image2Url)
                        .into(image2)
                }
                if (image3Url != null) {
                    Glide.with(this)
                        .load(image3Url)
                        .into(image3)
                }
                if (body1Text != null) {
                    body1.text = body1Text
                }

                if (body2Text != null) {
                    body2.text = body2Text
                }
                if (body3Text != null) {
                    body3.text = body3Text
                }

                if (subheading1Text != null) {
                    subheading1.text = subheading1Text
                }

                if (subheading2Text != null) {
                    subheading2.text = subheading2Text
                }
                if (subheading3Text != null) {
                    subheading3.text = subheading3Text
                }
            }
        }



    }
}