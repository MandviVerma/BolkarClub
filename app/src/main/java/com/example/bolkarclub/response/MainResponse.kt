package com.example.bolkarclub.response


import com.google.gson.annotations.SerializedName

data class MainResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val data: Data,
    @SerializedName("msg")
    val msg: Any
)

