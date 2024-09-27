package ru.mirea.vasilevmn.lesson9.domain.usecases

import ru.mirea.vasilevmn.lesson9.domain.models.Movie
import ru.mirea.vasilevmn.lesson9.domain.repository.MovieRepository

class SaveMovieToFavoriteUseCase(private val movieRepository: MovieRepository) {
    fun execute(movie: Movie?): Boolean {
        return movieRepository.saveMovie(movie)
    }
}