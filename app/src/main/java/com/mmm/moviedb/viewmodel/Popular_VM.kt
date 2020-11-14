package com.mmm.moviedb.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mmm.moviedb.api.ApiClient
import com.mmm.moviedb.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Popular_VM : ViewModel()
{
    var popular :MutableLiveData<Movie> = MutableLiveData()
    fun getPopular() : LiveData<Movie> =popular
    fun loadPopular()
    {
        var apiClient = ApiClient()
        var apiCall=apiClient.getPopular(ApiClient.API_KEY,"en-US",1)
        apiCall.enqueue(object : Callback<Movie>
        {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                popular.value=response.body()
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d(">>>",t.toString())
            }

        }
        )
    }
}
