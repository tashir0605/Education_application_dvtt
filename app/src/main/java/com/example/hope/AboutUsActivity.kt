package com.example.hope

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AboutUsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about_us)
        val needDescription: TextView = findViewById(R.id.need_discription)
        needDescription.text = Html.fromHtml(getString(R.string.need_description))

        val initiativeDescription: TextView = findViewById(R.id.initiative_discription)
        initiativeDescription.text = Html.fromHtml(getString(R.string.initiative_description))

        val instagramTashir: ImageView = findViewById(R.id.instagram_tashir)
        val linkedinTashir: ImageView = findViewById(R.id.linkedin_tashir)

        // Set click listeners
        instagramTashir.setOnClickListener {
            openUrl("https://www.instagram.com/tashir_8108/")
        }

        linkedinTashir.setOnClickListener {
            openUrl("https://www.linkedin.com/in/tashir-ahmed-5315b128b/")
        }
        val instagramDikshant: ImageView = findViewById(R.id.instagram_dikshant)
        val linkedinDikshant: ImageView = findViewById(R.id.linkedin_dikshant)

        // Set click listeners
        instagramDikshant.setOnClickListener {
            openUrl("https://www.instagram.com/dikshant_965/")
        }

        linkedinDikshant.setOnClickListener {
            openUrl("https://www.linkedin.com/in/dikshant-jha-13592824a/")
        }
        val instagramVishwendra: ImageView = findViewById(R.id.instagram_vishwendra)
        val linkedinVishwendra: ImageView = findViewById(R.id.linkedin_vishwendra)

        // Set click listeners
        instagramVishwendra.setOnClickListener {
            openUrl("https://www.instagram.com/okay_vishwendra/")
        }

        linkedinVishwendra.setOnClickListener {
            openUrl("https://www.linkedin.com/in/vishwendra-pratap-singh-0783b628a/")
        }

        }
    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
    }
