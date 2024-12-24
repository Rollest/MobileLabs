package ru.mirea.vasilev.data.storage;

import ru.mirea.vasilev.data.storage.models.Movie;

public interface MovieStorage {
    public Movie get();
    public boolean save(Movie movie);
}
