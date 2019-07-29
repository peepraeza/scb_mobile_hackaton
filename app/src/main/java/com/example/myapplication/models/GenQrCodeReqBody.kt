package com.example.myapplication.models

data class GenQrCodeReqBody(
    val qrType: String,
    val ppType: String,
    val ppId: String,
    val amount: String,
    val ref1: String,
    val ref2: String,
    val ref3: String
)