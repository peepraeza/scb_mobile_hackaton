package com.example.myapplication.models

data class CustomerProfile(
    val status: Status,
    val data: Data
){
    data class Status(
        val code: Int,
        val description: String
    )

    data class Data(
        val profile: Profile
    )

    data class Profile(
        val citizenID: String,
        val engFirstName: String,
        val engLastName: String,
        val genderCode: String,
        val mobile: String,
        val email: String,
        val birthDate: String
    )
}




