package ru.mirea.vasilev.picassoapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UnsplashApi {

    @GET("/photos")
    Call<List<ImageItem>> getPhotos(
            @Query("client_id") String clientId,
            @Query("per_page") int perPage
    );
}

