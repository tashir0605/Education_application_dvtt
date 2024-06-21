package com.example.hope

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hope.Movie
import com.example.hope.R
import com.example.hope.RecyclerViewMovieAdapter

class bcca : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var recyclerViewMovieAdapter: RecyclerViewMovieAdapter? = null
    private var movieList = mutableListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_bcca)

        recyclerView = findViewById(R.id.rvMovieLists)
        recyclerViewMovieAdapter = RecyclerViewMovieAdapter(this@bcca, movieList)
        recyclerView!!.layoutManager = GridLayoutManager(this, 2)
        recyclerView!!.adapter = recyclerViewMovieAdapter

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        prepareMovieListData()
    }

    private fun prepareMovieListData() {
        // Add different movie items here
        var movie = Movie("Programming Club", R.drawable.programmingclub)
        movieList.add(movie)
        movie = Movie("GDSC", R.drawable.gdsc)
        movieList.add(movie)
        movie = Movie("Robotics Society", R.drawable.robotics)
        movieList.add(movie)
        movie = Movie("Nexus", R.drawable.nexus)
        movieList.add(movie)
        movie = Movie("Devlup Labs", R.drawable.devlup)
        movieList.add(movie)
        movie = Movie("RAID", R.drawable.raid)
        movieList.add(movie)
        movie = Movie("INSIDE", R.drawable.inside)
        movieList.add(movie)
        movie = Movie("Automobile Society", R.drawable.bolthead)
        movieList.add(movie)

        recyclerViewMovieAdapter!!.notifyDataSetChanged()
    }
}
