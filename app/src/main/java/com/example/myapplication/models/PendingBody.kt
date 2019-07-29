package com.example.myapplication.models
import com.google.gson.annotations.SerializedName


data class PendingBody(
    @SerializedName("citizenId")
    val citizenId: String,
    @SerializedName("atmCitizenId")
    val atmCitizenId: String,
    @SerializedName("amount")
    val amount: Int
)