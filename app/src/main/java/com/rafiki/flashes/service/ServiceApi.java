package com.rafiki.flashes.service;


import com.rafiki.flashes.model.Film;
import com.rafiki.flashes.model.FilmComment;
import com.rafiki.flashes.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServiceApi {

    @POST("flashes/authorization/signUp")
    Call<Void> signUp(@Body User user);

    @POST("flashes/authorization/signIn")
    Call<User> login(@Body User user);

    @POST("/flashes/films")
    Call<Film> createFilm(@Body Film film, @Header("Authorization")String authHeader);

    @GET("/flashes/films")
    Call<List<Film>> listFilms(@Header("Authorization")String authHeader);

    @GET("/flashes/films/{id}/filmComments")
    Call<List<FilmComment>> getAllCommentsByFilmId(@Header("Authorization")String authHeader, @Path("id") Integer id );

    @POST("/flashes/films/{id}/filmComments")
    Call<FilmComment> createComment(@Body FilmComment filmComment, @Header("Authorization")String authHeader, @Path("id") Integer id );
}
