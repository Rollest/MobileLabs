package ru.mirea.vasilev.domain.repository;

import ru.mirea.vasilev.domain.models.Movie;

public interface MovieRepository {
    public boolean saveMovie(Movie movie);
    public Movie getMovie();
}
