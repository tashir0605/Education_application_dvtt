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

class Sacactivity : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore
    private lateinit var image1 : ImageView
    private lateinit var image2 : ImageView
    private lateinit var image3 : ImageView
    private lateinit var image4 : ImageView
    private lateinit var image5 : ImageView
    private lateinit var body1 : TextView
    private lateinit var body2 : TextView
    private lateinit var body3 : TextView
    private lateinit var body4 : TextView
    private lateinit var body5 : TextView

    private lateinit var subheading1 : TextView
    private lateinit var subheading2 : TextView
    private lateinit var subheading3 : TextView
    private lateinit var subheading4 : TextView
    private lateinit var subheading5 : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sacactivity)

        image1 = findViewById(R.id.image1)
        image2 = findViewById(R.id.image2)
        image3 = findViewById(R.id.image3)
        image4 = findViewById(R.id.image4)
        image5 = findViewById(R.id.image5)
        body1 = findViewById(R.id.body1)
        body2 = findViewById(R.id.body2)
        body3 = findViewById(R.id.body3)
        body4 = findViewById(R.id.body4)
        body5 = findViewById(R.id.body5)

        subheading1 = findViewById(R.id.subheading1)
        subheading2 = findViewById(R.id.subheading2)
        subheading3 = findViewById(R.id.subheading3)
        subheading4 = findViewById(R.id.subheading4)
        subheading5 = findViewById(R.id.subheading5)



        db = FirebaseFirestore.getInstance()
        val documentId = "Boards"
        fetchdata(documentId)



        val bacclick = findViewById<TextView>(R.id.heading2)
        bacclick.setOnClickListener{
            val intent = Intent(this,bac::class.java)
            startActivity(intent)
        }

        val bssclick = findViewById<TextView>(R.id.heading4)
        bssclick.setOnClickListener{
            val intent = Intent(this,bss::class.java)
            startActivity(intent)
        }


        val blaclick = findViewById<TextView>(R.id.heading3)
        blaclick.setOnClickListener{
            val intent = Intent(this,bla::class.java)
            startActivity(intent)
        }

        val bswclick=findViewById<TextView>(R.id.heading5)
        bswclick.setOnClickListener{
            val intent = Intent(this,bsw::class.java)
            startActivity(intent)
        }

    }


        private fun fetchdata(documentId: String) {
            val ref = db.collection("SAC").document(documentId)
                .get().addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val imageUrl1 = documentSnapshot.getString("image1")
                        val imageUrl2 = documentSnapshot.getString("image2")
                        val imageUrl3 = documentSnapshot.getString("image3")
                        val imageUrl4 = documentSnapshot.getString("image4")
                        val imageUrl5 = documentSnapshot.getString("image5")


                        val body1Text = documentSnapshot.getString("body1")
                        val body2Text = documentSnapshot.getString("body2")
                        val body3Text = documentSnapshot.getString("body3")
                        val body4Text = documentSnapshot.getString("body4")
                        val body5Text = documentSnapshot.getString("body5")


                        val subheading1Text = documentSnapshot.getString("subheading1")
                        val subheading2Text = documentSnapshot.getString("subheading2")
                        val subheading3Text = documentSnapshot.getString("subheading3")
                        val subheading4Text = documentSnapshot.getString("subheading4")
                        val subheading5Text = documentSnapshot.getString("subheading5")


                        if (imageUrl1 != null) {
                            Glide.with(this)
                                .load(imageUrl1)
                                .into(image1)
                        }
                        if (imageUrl2 != null) {
                            Glide .with(this)
                                .load(imageUrl2)
                                .into(image2)

                        }
                        if (imageUrl3 != null) {
                            Glide.with(this)
                                .load(imageUrl3)
                                .into(image3)

                        }
                        if (imageUrl4 != null) {
                            Glide.with(this)
                                .load(imageUrl4)
                                .into(image4)
                        }
                        if (imageUrl5 != null) {
                            Glide.with(this)
                                .load(imageUrl5)
                                .into(image5)

                        }

                        body1.text = body1Text
                        body2.text = body2Text
                        body3.text = body3Text
                        body4.text = body4Text
                        body5.text = body5Text


                        subheading1.text = subheading1Text
                        subheading2.text = subheading2Text
                        subheading3.text = subheading3Text
                        subheading4.text = subheading4Text
                        subheading5.text = subheading5Text



                    }
                }


    }
}