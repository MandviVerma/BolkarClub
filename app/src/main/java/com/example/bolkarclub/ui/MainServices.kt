package com.example.bolkarclub.ui

import com.example.bolkarclub.response.MainResponse
import retrofit2.Call
import retrofit2.http.GET


interface MainServices {
    @GET("live/room.json")
    fun getList(): Call<MainResponse>
}
