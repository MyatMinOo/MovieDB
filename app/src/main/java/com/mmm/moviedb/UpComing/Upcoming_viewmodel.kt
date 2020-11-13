package com.mmm.moviedb.UpComing

import android.util.Log.d
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mmm.moviedb.api.ApiClient
import com.mmm.moviedb.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Upcoming_viewmodel : ViewModel(){
    private var upcoming : MutableLiveData<Movie> = MutableLiveData()

    fun getUpComing() : LiveData<Movie> = upcoming

    fun loadUpComing() {
        var apiClient = ApiClient()
        var callapi = apiClient.getUpComingClient(ApiClient.API_KEY,"en-US",1)

        callapi.enqueue(object : Callback<Movie> {
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                d("error" , t.toString())
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                upcoming.value = response.body()!!
            }
        })
    }
}