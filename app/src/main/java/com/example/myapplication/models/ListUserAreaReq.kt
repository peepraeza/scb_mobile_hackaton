package com.example.myapplication.models

data class ListUserAreaReq(
    val status: Status,
    val data: List<Data>
) {

    data class Status(
        val code: Int,
        val message: String
    )

    data class Data(
        val citizenId: String,
        val name: String,
        val lastName: String,
        val phoneNumber: String,
        val gender: String,
        val alias: String,
        val point: Int,
        val latitude: Double,
        val longitude: Double,
        val moneyLimit: Int,
        val activeStatus: Boolean,
        val distance: Double
    )
}
