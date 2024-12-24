package ru.mirea.vasilev.domain.usecases;

import ru.mirea.vasilev.domain.models.Movie;
import ru.mirea.vasilev.domain.repository.MovieRepository;

public class GetFavoriteFilmUseCase {
    private MovieRepository movieRepository;

    public GetFavoriteFilmUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie execute() {
        return movieRepository.getMovie();
    }
}
