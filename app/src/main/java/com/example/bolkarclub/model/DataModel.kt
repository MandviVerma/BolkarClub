package com.example.bolkarclub.model

data class DataModel(
    val name: String?=null,
    val category:CategoryType ?=null,
    val profileImg: String?=null,
    var micStatus: Boolean?=null,
    val hostIcon: Boolean?=null
)

enum class CategoryType{
    Host,Moderator,Speaker,Member

}
