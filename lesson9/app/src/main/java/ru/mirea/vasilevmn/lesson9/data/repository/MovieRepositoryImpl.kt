package ru.mirea.vasilevmn.lesson9.data.repository

import android.content.Context
import android.content.SharedPreferences
import ru.mirea.vasilevmn.lesson9.domain.models.Movie
import ru.mirea.vasilevmn.lesson9.domain.repository.MovieRepository


class MovieRepositoryImpl(context: Context) : MovieRepository {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("FavoriteMovies", Context.MODE_PRIVATE)

    override fun saveMovie(movie: Movie?): Boolean {
        return if (movie != null) {
            val editor = sharedPreferences.edit()
            editor.putInt("movie_id", movie.id)
            editor.putString("movie_name", movie.name)
            editor.apply()
            true
        } else {
            false
        }
    }

    override val movie: Movie?
        get() {
            val id = sharedPreferences.getInt("movie_id", -1)
            val name = sharedPreferences.getString("movie_name", null)
            return if (id != -1 && name != null) {
                Movie(id, name)
            } else {
                null
            }
        }
}