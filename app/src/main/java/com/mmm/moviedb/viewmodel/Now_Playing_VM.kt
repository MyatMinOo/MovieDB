package com.mmm.moviedb.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mmm.moviedb.api.ApiClient
import com.mmm.moviedb.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Now_Playing_VM :  ViewModel() {
    var now_playing  : MutableLiveData<Movie> = MutableLiveData()
    fun getNowPlayingMovie() : LiveData<Movie>
    {
        return now_playing
    }
    fun loadNowPlayingMovie()
    {
        var apiClient = ApiClient()
        var apiCall = apiClient.getNowPlaying(ApiClient.API_KEY,"en-US",1)
        apiCall.enqueue(object : Callback<Movie>
        {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                now_playing.value=response.body()
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d(">>",t.toString())
            }
        })
    }
}