package com.mmm.moviedb.TopRated

import android.util.Log.d
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mmm.moviedb.api.ApiClient
import com.mmm.moviedb.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Toprated_viewModel : ViewModel(){
    private var toprated : MutableLiveData<Movie> = MutableLiveData()

    fun getTopRated() : LiveData<Movie> = toprated

    fun loadTopRated() {
        var apiClient = ApiClient()
        var callapi = apiClient.getTopRatedClient(ApiClient.API_KEY,"en-US",1)

        callapi.enqueue(object : Callback<Movie>{
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                d("error" , t.toString())
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                toprated.value = response.body()!!
            }
        })
    }
}