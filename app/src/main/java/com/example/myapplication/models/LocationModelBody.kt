package com.example.myapplication.models
import com.google.gson.annotations.SerializedName


data class LocationModelBody(
    @SerializedName("citizenId")
    val citizenId: String,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("moneyLimit")
    val moneyLimit: Int,
    @SerializedName("activeStatus")
    val activeStatus: Boolean
)