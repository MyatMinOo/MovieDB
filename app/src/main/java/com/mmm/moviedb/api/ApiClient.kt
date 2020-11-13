package com.mmm.moviedb.api

import com.mmm.moviedb.model.Movie
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    var BASE_URL = "https://api.themoviedb.org/3/movie"
    var apiInterf : ApiInterface
    companion object{
        val API_KEY ="757007c7da260a44df4cd295d28ce2db"
    }
    init {
        var retrofit=Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiInterf=retrofit.create(ApiInterface::class.java)
    }
    fun getNowPlaying(api_key : String,language : String,page : Int) : Call<Movie>
    {
        return apiInterf.getNowPlaying(api_key,language,page)
    }
    fun getPopular(api_key: String,language: String,page: Int) : Call<Movie>
    {
        return apiInterf.getPopular(api_key,language,page)
    }
    fun getDetailClient(movie_id : String,api_key: String) : Call<Movie>
    {
        return apiInterf.getDetail(movie_id.toInt(),api_key)
    }
}