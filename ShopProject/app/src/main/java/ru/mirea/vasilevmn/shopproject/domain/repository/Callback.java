package ru.mirea.vasilevmn.shopproject.domain.repository;

public interface Callback<T> {
    void onResult(T result);
}