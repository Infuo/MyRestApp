package com.example.myrestapp_eboigbe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(
    private val movieList: List<Movie>,
    private val onItemClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleView: TextView = itemView.findViewById(R.id.titleTv)
        val yearView: TextView = itemView.findViewById(R.id.yearTv)
        val posterView: ImageView = itemView.findViewById(R.id.mvPoster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentMovie = movieList[position]

        holder.titleView.text = currentMovie.Title
        holder.yearView.text = currentMovie.Year

        val posterUrl = currentMovie.Poster
        if (posterUrl != "N/A" && posterUrl.isNotEmpty()) {
            Glide.with(holder.itemView.context)
                .load(posterUrl)
                .into(holder.posterView)
        } else {
            holder.posterView.setImageResource(R.drawable.ic_launcher_background)
        }

        holder.itemView.setOnClickListener {
            onItemClick(currentMovie)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}
