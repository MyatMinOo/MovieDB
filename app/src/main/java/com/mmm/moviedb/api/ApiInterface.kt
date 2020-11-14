package com.mmm.moviedb.api


import com.mmm.moviedb.model.Details
import com.mmm.moviedb.model.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("now_playing")
    fun getNowPlaying(
        @Query("api_key") api_key : String,
        @Query("language") language : String,
        @Query("page") page : String
    ) : Call<Movie>

    @GET("popular")
    fun getPopular(
        @Query("api_key") api_key : String,
        @Query("language") language : String,
        @Query("page") page : String
    ) : Call<Movie>

    @GET("top_rated")
    fun getTopRated(
        @Query("api_key") api_key : String,
        @Query("language") language : String,
        @Query("page") page : Int
    ) : Call<Movie>

    @GET("upcoming")
    fun getUpcoming(
        @Query("api_key") api_key : String,
        @Query("language") language : String,
        @Query("page") page : Int
    ) : Call<Movie>

    //Detail
    @GET("{movie_id}")
    fun getDetail(
        @Path("movie_id") movieId : Int,
        @Query("api_key") apiKey : String
    ): Call<Details>
}