package com.mmm.moviedb.Detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mmm.moviedb.api.ApiClient
import com.mmm.moviedb.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel(){
    private var detail : MutableLiveData<Movie> = MutableLiveData()

    fun getdetail() : LiveData<Movie> = detail

    fun loaddetail(movieID : String){
        var apiClient = ApiClient()
        val callapi = apiClient.getDetailClient(ApiClient.API_KEY,movieID)

        callapi.enqueue(object : Callback<Movie> {
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d("error", t.toString())
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                detail.value = response.body()!!
            }
        })
    }
}