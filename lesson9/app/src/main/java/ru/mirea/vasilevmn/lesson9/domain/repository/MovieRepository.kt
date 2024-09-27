package ru.mirea.vasilevmn.lesson9.domain.repository

import ru.mirea.vasilevmn.lesson9.domain.models.Movie


interface MovieRepository {
    fun saveMovie(movie: Movie?): Boolean
    val movie: Movie?
}