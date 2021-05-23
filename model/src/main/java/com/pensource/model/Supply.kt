package com.pensource.model

data class Supply(
    val name: String,
    val phoneNumber: String,
    val isWhatsAppNumber: Boolean,
    val quantity: Double,
    val latitude: Float,
    val longitude: Float,
)