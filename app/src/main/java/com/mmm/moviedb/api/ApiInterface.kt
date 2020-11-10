package com.mmm.moviedb.api

import com.mmm.moviedb.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("now_playing")
    fun getNowPlaying(
        @Query("api-key") api_key : String,
        @Query("language") language : String,
        @Query("page") page : String
    ) : Call<Movie>

    @GET("popular")
    fun getPopular(
        @Query("api-key") api_key : String,
        @Query("language") language : String,
        @Query("page") page : String
    ) : Call<Movie>

    @GET("top_rated")
    fun getTopRated(
        @Query("api-key") api_key : String,
        @Query("language") language : String,
        @Query("page") page : String
    ) : Call<Movie>

    @GET("upcoming")
    fun getUpcoming(
        @Query("api-key") api_key : String,
        @Query("language") language : String,
        @Query("page") page : String
    ) : Call<Movie>

}