package ru.mirea.vasilevmn.lesson9.presentation

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.mirea.vasilevmn.lesson9.R
import ru.mirea.vasilevmn.lesson9.data.repository.MovieRepositoryImpl
import ru.mirea.vasilevmn.lesson9.domain.models.Movie
import ru.mirea.vasilevmn.lesson9.domain.repository.MovieRepository
import ru.mirea.vasilevmn.lesson9.domain.usecases.GetFavoriteFilmUseCase
import ru.mirea.vasilevmn.lesson9.domain.usecases.SaveMovieToFavoriteUseCase


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val text = findViewById<EditText>(R.id.editTextMovie)
        val textView = findViewById<TextView>(R.id.textViewMovie)
        val movieRepository: MovieRepository = MovieRepositoryImpl(this)

        findViewById<View>(R.id.buttonSaveMovie).setOnClickListener {
            val result = SaveMovieToFavoriteUseCase(movieRepository).execute(
                Movie(2, text.text.toString())
            )
            textView.text = String.format("Save result %s", result)
        }

        findViewById<View>(R.id.buttonGetMovie).setOnClickListener {
            val movie: Movie? = GetFavoriteFilmUseCase(movieRepository).execute()
            textView.text = java.lang.String.format("Save result %s", movie?.name)
        }
    }
}