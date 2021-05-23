package com.pensource.model

data class Supply(
    val id: String,
    val name: String,
    val phoneNumber: String,
    val isWhatsAppNumber: Boolean,
    val quantity: Double,
    val latitude: Float,
    val longitude: Float,
    val verified: Boolean = false
)