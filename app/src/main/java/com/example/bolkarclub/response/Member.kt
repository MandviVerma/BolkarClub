package com.example.bolkarclub.response


import com.google.gson.annotations.SerializedName

data class Member(
    @SerializedName("au")
    val au: Int,
    @SerializedName("_id")
    val id: String,
    @SerializedName("mic")
    val mic: Boolean,
    @SerializedName("mod")
    val mod: Boolean,
    @SerializedName("n")
    val n: String,
    @SerializedName("socketid")
    val socketid: String,
    @SerializedName("u")
    val u: String,
    @SerializedName("visible")
    val visible: Boolean
)