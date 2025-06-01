package com.example.myrestapp_eboigbe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson


data class Movie(
    val Title: String,
    val Year: String,
    val imdbID: String,
    val Type: String,
    val Poster: String
)


data class MovieResponse(
    val Search: List<Movie>,
    val totalResults: String,
    val Response: String
)

class SearchActivity : AppCompatActivity() {
    lateinit var etSearchText: EditText
    lateinit var btnSearch: Button
    lateinit var recyclerViewResults: RecyclerView
    lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etSearchText = findViewById(R.id.SearchText)
        btnSearch = findViewById(R.id.btnSearch)
        recyclerViewResults = findViewById(R.id.mvResults)
        recyclerViewResults.layoutManager = LinearLayoutManager(this)

        btnSearch.setOnClickListener {
            val query = etSearchText.text.toString()
            if (query.isNotBlank()) {
                search(query)
            } else {
                showToast("Devi inserire un testo")
            }
        }
    }

    private fun search(searchedText: String) {
        val apiKey = "25b514a0"
        val url = "https://www.omdbapi.com/?s=$searchedText&apikey=$apiKey"

        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                if (response.contains("Error")) {
                    showToast(response)
                    Log.e("SearchActivity", response)
                    return@StringRequest
                }

                Log.d("SearchActivity", response)

                val gson = Gson()
                val movieResponse = gson.fromJson(response, MovieResponse::class.java)

                movieAdapter = MovieAdapter(movieResponse.Search) { movie ->
                    val intent = Intent(this, MovieDetailActivity::class.java)
                    intent.putExtra("title", movie.Title)
                    intent.putExtra("year", movie.Year)
                    intent.putExtra("poster", movie.Poster)
                    startActivity(intent)
                }

                recyclerViewResults.adapter = movieAdapter
            },
            { error ->
                error.printStackTrace()
                showToast("Errore nella richiesta")
            }
        )

        queue.add(stringRequest)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
