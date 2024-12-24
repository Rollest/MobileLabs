package ru.mirea.vasilev.domain.usecases;

import ru.mirea.vasilev.domain.models.Movie;
import ru.mirea.vasilev.domain.repository.MovieRepository;

public class SaveFilmToFavoriteUseCase {
    private MovieRepository movieRepository;

    public SaveFilmToFavoriteUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    public boolean execute(Movie movie) {
        return movieRepository.saveMovie(movie);
    }
}
