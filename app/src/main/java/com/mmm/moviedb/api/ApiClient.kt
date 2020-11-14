package com.mmm.moviedb.api

import com.mmm.moviedb.model.Details
import com.mmm.moviedb.model.Movie
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private var BASE_URL = "https://api.themoviedb.org/3/movie/"

   val apiInterface : ApiInterface

    companion object {
        val API_KEY = "757007c7da260a44df4cd295d28ce2db"
    }

    init {
        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiInterface=retrofit.create(ApiInterface::class.java)
    }

    //Top Rated
    fun getTopRatedClient(apiKey: String,language: String,page: Int): Call<Movie> {
        return apiInterface.getTopRated(apiKey,language,page)
    }

    //Up Coming
    fun getUpComingClient(apiKey: String , language: String , page: Int): Call<Movie>{
        return apiInterface.getUpcoming(apiKey,language,page)
    }

    //Detail
    fun getDetailClient(movieId: String, apiKey: String): Call<Details> {
        return apiInterface.getDetail(movieId.toInt(), apiKey)
    }
}