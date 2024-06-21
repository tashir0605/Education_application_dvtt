package com.example.hope

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class RecyclerViewMovieAdapter(
    private val getActivity: AppCompatActivity,
    private val movieLists: List<Movie>
) : RecyclerView.Adapter<RecyclerViewMovieAdapter.MyViewHolder>() {
    private val db = FirebaseFirestore.getInstance()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_movie_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieLists.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val movie = movieLists[position]
        holder.tvMovieTitle.text = movie.title
        holder.ivMovieImg.setImageResource(movie.image)
        holder.itemView.setOnClickListener {
            // Determine the action based on movie title
            when (movie.title) {
                // Clubs with GenericActivity
                "Dramebaaz", "Quiz Society", "Pheme", "Shutterbugs", "Raw", "Tgt", "Sangam", "Framex", "Ateliers", "Designerds", "Automobile Society" -> {
                    fetchClubDetailsAndStartActivity(movie.title, GenericActivity::class.java)
                }
                // Clubs with generic2 ActivityZer5
                "Programming Club", "INSIDE", "Devlup Labs", "GDSC", "Nexus", "Robotics Society", "RAID" -> {
                    fetchClubDetailsAndStartActivity(movie.title, generic2::class.java)
                }
                // Clubs with generic3 Activity
                "Weightlifting Society", "Badminton Society", "Hockey Society", "Basketball Society", "Chess Society", "Cricket Society", "E-Sports Society", "Kabaddi Society", "VolleyBall Society", "Table Tennis Society", "Squash Society", "Lawn Tennis Society", "Athletic Society" -> {
                    fetchClubDetailsAndStartActivity(movie.title, generic3::class.java)
                }
                "Cycling Club" -> {
                    fetchClubDetailsAndStartActivity(movie.title, cycle::class.java)
                }
                "Self defence Club" -> {
                    fetchClubDetailsAndStartActivity(movie.title, selfdefence::class.java)
                }
                "LitSoc" ->{
                    val intent = Intent(getActivity, litsoc::class.java)
                    getActivity.startActivity(intent)
                }

                else -> {
                    Log.e("RecyclerViewMovieAdapter", "Unhandled title: ${movie.title}")
                    Toast.makeText(getActivity, "Unhandled title: ${movie.title}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun fetchClubDetailsAndStartActivity(title: String, activityClass: Class<*>) {
        val clubId = getClubId(title)
        if (clubId.isNotEmpty()) {
            val ref = db.collection("Clubs").document(clubId)
            ref.get().addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val intent = Intent(getActivity, activityClass)
                    val mainHeading = document.getString("mainheading") ?: "N/A"
                    val role = document.getString("role") ?: "N/A"
                    val joinSecyName1 = document.getString("joinsecyname1") ?: "N/A"
                    val joinSecyName2 = document.getString("joinsecyname2") ?: "N/A"
                    val joinSecyName3 = document.getString("joinsecyname3") ?: ""
                    val secyName = document.getString("secyname") ?: "N/A"
                    val secyImgUrl = document.getString("secyimg")
                    val image = document.getString("mainimage")
                    val joinsecyImgUrl1 = document.getString("joinsecyimg1")
                    val joinsecyImgUrl2 = document.getString("joinsecyimg2")
                    val joinsecyImgUrl3 = document.getString("joinsecyimg3")

                    val coordinator1 = document.getString("codname1")
                    val coordinator2 = document.getString("codname2")
                    val coordinatorImgUrl1= document.getString("codimg1")
                    val coordinatorImgUrl2= document.getString("codimg2")


                    val womancaptain = document.getString("capname2")
                    val womanvcaptain = document.getString("vcapname2")
                    val mancaptain = document.getString("capname")
                    val manvcaptain = document.getString("vcapname")
                    val womancaptainImgUrl = document.getString("capimg2")
                    val  womanvcaptainImgUrl = document.getString("vcapimg2")
                    val mancaptainImgUrl = document.getString("capimg1")
                    val  manvcaptainImgUrl = document.getString("vcapimg1")




                    intent.putExtra("mainheading", mainHeading)
                    intent.putExtra("role", role)
                    intent.putExtra("mainimage", image ?: "")
                    intent.putExtra("joinsecyname1", joinSecyName1)
                    intent.putExtra("joinsecyname2", joinSecyName2)
                    intent.putExtra("joinsecyname3", joinSecyName3)
                    intent.putExtra("secyname", secyName)
                    intent.putExtra("secyimg", secyImgUrl ?: "")
                    intent.putExtra("joinsecyimg1", joinsecyImgUrl1 ?: "")
                    intent.putExtra("joinsecyimg2", joinsecyImgUrl2 ?: "")
                    intent.putExtra("joinsecyimg3", joinsecyImgUrl3 ?: "")



                    intent.putExtra("codname1",  coordinator1 ?: "N/A")
                    intent.putExtra("codname2", coordinator2  ?: "N/A")
                    intent.putExtra("codimg1", coordinatorImgUrl1 ?: "")
                    intent.putExtra("codimg2", coordinatorImgUrl2 ?: "")



                    intent.putExtra("capname2", womancaptain ?: "")
                    intent.putExtra("vcapname2", womanvcaptain ?: "")
                    intent.putExtra("capname", mancaptain ?: "")
                    intent.putExtra("vcapname", manvcaptain ?: "")
                    intent.putExtra("capimg2", womancaptainImgUrl ?: "")
                    intent.putExtra("vcapimg2", womanvcaptainImgUrl ?: "")
                    intent.putExtra("capimg1", mancaptainImgUrl ?: "")
                    intent.putExtra("vcapimg1", manvcaptainImgUrl ?: "")



                    // Start activity
                    getActivity.startActivity(intent)
                } else {
                    Log.d("RecyclerViewMovieAdapter", "No such document")
                    Toast.makeText(getActivity, "Document does not exist", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener { exception ->
                Log.d("RecyclerViewMovieAdapter", "get failed with ", exception)
                Toast.makeText(getActivity, "Error fetching document", Toast.LENGTH_SHORT).show()
            }
        } else {
            Log.e("RecyclerViewMovieAdapter", "Club ID is empty for title: $title")
            Toast.makeText(getActivity, "Club ID is empty for title: $title", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getClubId(title: String): String {
        return when (title) {
            // Add club IDs based on their respective titles
            "Dramebaaz" -> "Dramebaaz"
            "Quiz Society" -> "Quiz Society"
            "Pheme" -> "Pheme"
            "Shutterbugs" -> "Shutterbugs"
            "Raw" -> "Raw"
            "Tgt" -> "Tgt"
            "Sangam" -> "Sangam"
            "Framex" -> "Framex"
            "Ateliers" -> "Ateliers"
            "Designerds" -> "Designerds"
            "Automobile Society" -> "Automobile Society"

            "Programming Club" -> "Programming Club"
            "INSIDE" -> "INSIDE"
            "Devlup Labs" -> "Devlup Labs"
            "GDSC" -> "GDSC"
            "Nexus" -> "Nexus"
            "Robotics Society" -> "Robotics Society"
            "RAID" -> "RAID"

            "Weightlifting Society" -> "Weightlifting Society"
            "Badminton Society" -> "Badminton Society"
            "Hockey Society" -> "Hockey Society"
            "Basketball Society" -> "Basketball Society"
            "Chess Society" -> "Chess Society"
            "Cricket Society" -> "Cricket Society"
            "E-Sports Society" -> "E-Sports Society"
            "Kabaddi Society" -> "Kabaddi Society"
            "VolleyBall Society" -> "VolleyBall Society"
            "Table Tennis Society" -> "Table Tennis Society"
            "Squash Society" -> "Squash Society"
            "Lawn Tennis Society" -> "Lawn Tennis Society"
            "Athletic Society" -> "Athletic Society"

            else -> ""
        }
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMovieTitle: TextView = itemView.findViewById(R.id.tvMovieTitle)
        val ivMovieImg: ImageView = itemView.findViewById(R.id.ivMovieImg)
        val cardView: CardView = itemView.findViewById(R.id.cardView)
    }
}
