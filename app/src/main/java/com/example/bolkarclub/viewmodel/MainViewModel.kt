package com.example.bolkarclub.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bolkarclub.ui.Utils.getHttpClientBuilder
import com.example.bolkarclub.ui.MainServices
import com.example.bolkarclub.response.MainResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel:ViewModel() {
    var mainResponse = MutableLiveData<MainResponse>()
    var BASE_URL = "https://api.bolkarapp.com/"
    lateinit var httpClient: OkHttpClient
    lateinit var retrofit: Retrofit

    fun getData(){
        httpClient = getHttpClientBuilder()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient.newBuilder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val api = retrofit.create(MainServices::class.java)
        val call = api.getList()

        call.enqueue(object : Callback<MainResponse> {
            override fun onFailure(call: Call<MainResponse>, t: Throwable) {
                //Toast.makeText(this ,"On failure",Toast.LENGTH_SHORT).show()
                Log.i("h", "on fail" + t.toString())
            }

            override fun onResponse(call: Call<MainResponse>, response: retrofit2.Response<MainResponse>) {
                Log.i("d", response.body().toString())
//                progressBar.visibility= View.GONE
                mainResponse.postValue(response.body())
            }
        })
    }
}