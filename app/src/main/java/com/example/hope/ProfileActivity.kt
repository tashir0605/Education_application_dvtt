package com.example.hope

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class ProfileActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var profileImageView: ImageView
    private lateinit var headerProfileImageView: ImageView
    private lateinit var welcomeTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var menuIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        val headerView = navigationView.getHeaderView(0)
        headerProfileImageView = headerView.findViewById(R.id.profileImageView)
        profileImageView = findViewById(R.id.profileImageView)
        emailTextView = headerView.findViewById(R.id.emailTextView)
        welcomeTextView = findViewById(R.id.welcomeTextView)

        menuIcon = findViewById(R.id.menuIcon)

        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val usernameFromPreferences = sharedPreferences.getString("Username", "User")
        val emailFromPreferences = sharedPreferences.getString("Email", "user@example.com")

        welcomeTextView.text = "Hi,\n$usernameFromPreferences"
        emailTextView.text = emailFromPreferences

        loadProfileImage(emailFromPreferences!!)

        headerProfileImageView.setOnClickListener {
            showImagePickerDialog(emailFromPreferences)
        }
        profileImageView.setOnClickListener {
            showImagePickerDialog(emailFromPreferences)
        }

        val academicsButton = findViewById<TextView>(R.id.academicsButton)
        academicsButton.setOnClickListener {
            val intent = Intent(this, SectionA::class.java)
            startActivity(intent)
        }
        val aboutCollegeButton = findViewById<TextView>(R.id.aboutCollegeButton)
        aboutCollegeButton.setOnClickListener {
            val intent = Intent(this, College::class.java)
            startActivity(intent)
        }
        val eventButton = findViewById<TextView>(R.id.eventButton)
        eventButton.setOnClickListener {
            val intent = Intent(this, EventActivity::class.java)
            startActivity(intent)
        }

        menuIcon.setOnClickListener {
            drawerLayout.openDrawer(navigationView)
        }

        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_about_us -> {
                    val intent = Intent(this, AboutUsActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_contact_us -> {
                    val intent = Intent(this, ContactUsActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_logout -> {
                    logout()
                }
            }
            drawerLayout.closeDrawers()
            true
        }
    }

    override fun onBackPressed() {
        finishAffinity()
    }
    private fun logout() {
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove("Username")
        editor.remove("Email")
        editor.apply()

        FirebaseAuth.getInstance().signOut()

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun showImagePickerDialog(email: String) {
        val images = arrayOf(
            R.drawable.image1, R.drawable.image2, R.drawable.image3,
            R.drawable.image4, R.drawable.image5, R.drawable.image6,
            R.drawable.image7, R.drawable.image8, R.drawable.image9,
            R.drawable.image10, R.drawable.image11, R.drawable.image12,
            R.drawable.image13, R.drawable.image14, R.drawable.image15,
            R.drawable.image16, R.drawable.image17
        )

        val dialogView = layoutInflater.inflate(R.layout.dialog_image_picker, null)
        val recyclerView: RecyclerView = dialogView.findViewById(R.id.imageRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = ImageAdapter(images.toList()) { imageResId ->
            saveProfileImage(email, imageResId)
            headerProfileImageView.setImageResource(imageResId)
            profileImageView.setImageResource(imageResId)
        }

        AlertDialog.Builder(this)
            .setTitle("Choose Profile Image")
            .setView(dialogView)
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun saveProfileImage(email: String, imageResId: Int) {
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("ProfileImage_$email", imageResId)
        editor.apply()
    }

    private fun loadProfileImage(email: String) {
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val imageResId = sharedPreferences.getInt("ProfileImage_$email", R.drawable.default_profile)
        headerProfileImageView.setImageResource(imageResId)
        profileImageView.setImageResource(imageResId)
    }
}

