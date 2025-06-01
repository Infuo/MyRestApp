package com.example.myrestapp_eboigbe

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val title = intent.getStringExtra("title")
        val year = intent.getStringExtra("year")
        val poster = intent.getStringExtra("poster")

        findViewById<TextView>(R.id.tvDetailTitle).text = title
        findViewById<TextView>(R.id.tvDetailYear).text = year

        val ivPoster = findViewById<ImageView>(R.id.ivDetailPoster)
        if (!poster.isNullOrEmpty() && poster != "N/A") {
            Glide.with(this).load(poster).into(ivPoster)
        } else {
            ivPoster.setImageResource(R.drawable.ic_launcher_background)
        }
    }
}
