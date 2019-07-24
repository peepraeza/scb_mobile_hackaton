package com.example.myapplication.models

data class ResponseEntity (
    val status: Status,
    val data: Data
    )
{
    data class Status(
        val code: Int,
        val message: String
    )

    data class Data(
        val status: Boolean,
        val citizenId: String,
        val name: String,
        val lastName: String,
        val phoneNumber: String,
        val gender: String,
        val alias: String,
        val password: String,
        val point:Int
    )
}
