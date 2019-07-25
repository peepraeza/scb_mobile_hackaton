package com.example.myapplication.models
import com.google.gson.annotations.SerializedName


data class UserRegis(
    @SerializedName("citizenId")
    val citizenId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("alias")
    val alias: String
)