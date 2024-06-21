package com.example.hope

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class bss : AppCompatActivity() {
    private var recyclerView: RecyclerView? = null
    private var recyclerViewMovieAdapter: RecyclerViewMovieAdapter? = null
    private var movieList = mutableListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bss)

        recyclerView = findViewById(R.id.rvMovieLists)
        recyclerViewMovieAdapter = RecyclerViewMovieAdapter(this@bss, movieList)
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
        var movie = Movie("Athletic Society", R.drawable.atheletics)
        movieList.add(movie)
        movie = Movie("Basketball Society", R.drawable.basketball)
        movieList.add(movie)
        movie = Movie("Badminton Society", R.drawable.badminton)
        movieList.add(movie)
        movie = Movie("Cricket Society", R.drawable.cricket)
        movieList.add(movie)
        movie = Movie("Chess Society", R.drawable.chess)
        movieList.add(movie)
        movie = Movie("Hockey Society", R.drawable.hockey)
        movieList.add(movie)
        movie = Movie("E-sports Society", R.drawable.esport)
        movieList.add(movie)
        movie = Movie("Kabaddi Society", R.drawable.kabaddi)
        movieList.add(movie)
        movie = Movie("Lawn Tennis Society", R.drawable.tennis)
        movieList.add(movie)
        movie = Movie("Squash Society", R.drawable.squash)
        movieList.add(movie)
        movie = Movie("Table Tennis Society", R.drawable.tablet)
        movieList.add(movie)
        movie = Movie("VolleyBall Society", R.drawable.volleyball)
        movieList.add(movie)
        movie = Movie("Weightlifting Society", R.drawable.weigth)
        movieList.add(movie)
        movie = Movie("Self Defense Club", R.drawable.self)
        movieList.add(movie)
        movie = Movie("Cycling Club", R.drawable.cycle)
        movieList.add(movie)

        recyclerViewMovieAdapter!!.notifyDataSetChanged()
    }
}
