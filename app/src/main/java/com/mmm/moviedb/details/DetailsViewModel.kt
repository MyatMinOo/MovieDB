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
    private var loading : MutableLiveData<Boolean> = MutableLiveData()
    private var errorStatus : MutableLiveData<Boolean> = MutableLiveData()
    private var errorMessage : MutableLiveData<String> = MutableLiveData()

    fun getDetails() : LiveData<Details> = details
    fun getLoading() : LiveData<Boolean> = loading
    fun getErrorStatus() : LiveData<Boolean> = errorStatus
    fun getErrorMessage() : LiveData<String> = errorMessage

    fun loadDetails(id:String){
        var apiClient = ApiClient()
        var apiCall = apiClient.getDetailClient(id,ApiClient.API_KEY)
        apiCall.enqueue(object : Callback<Details>{
            override fun onResponse(call: Call<Details>, response: Response<Details>) {
                details.value = response.body()
                errorStatus.value = false
                loading.value = false
            }

            override fun onFailure(call: Call<Details>, t: Throwable) {
                errorStatus.value = true
                errorMessage.value = t.toString()
                loading.value = false

            }

        })
    }
}