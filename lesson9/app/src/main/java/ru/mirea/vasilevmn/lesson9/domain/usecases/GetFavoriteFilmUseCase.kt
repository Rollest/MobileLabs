package ru.mirea.vasilevmn.lesson9.domain.usecases

import ru.mirea.vasilevmn.lesson9.domain.models.Movie
import ru.mirea.vasilevmn.lesson9.domain.repository.MovieRepository


class GetFavoriteFilmUseCase(private val movieRepository: MovieRepository) {
    fun execute(): Movie? {
        return movieRepository.movie
    }
}