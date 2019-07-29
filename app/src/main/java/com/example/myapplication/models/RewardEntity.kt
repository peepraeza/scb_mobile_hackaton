package com.example.myapplication.models

data class RewardEntity(

    val status: Status,
    val data: Data
) {
    data class Status(
        val code: Int,
        val message: String
    )

    data class Data(
        val rewardId: Long,
        val rewardName: String,
        val rewardDescription: String,
        val rewardPoint: Int,
        val rewardCode: String
    )
}