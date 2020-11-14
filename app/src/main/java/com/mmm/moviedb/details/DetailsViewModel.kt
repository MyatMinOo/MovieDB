package com.mmm.moviedb.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mmm.moviedb.api.ApiClient
import com.mmm.moviedb.model.Details
import com.mmm.moviedb.model.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsViewModel : ViewModel() {
    private var details : MutableLiveData<Details> = MutableLiveData()

    fun getDetails() : LiveData<Details> = details

    fun loadDetails(id:String){
        var apiClient = ApiClient()
        var apiCall = apiClient.getDetailClient(ApiClient.API_KEY,id)
        apiCall.enqueue(object : Callback<Details>{
            override fun onResponse(call: Call<Details>, response: Response<Details>) {
                details.value = response.body()
            }

            override fun onFailure(call: Call<Details>, t: Throwable) {
                Log.d("Error>>>",t.toString())
            }

        })
    }
}