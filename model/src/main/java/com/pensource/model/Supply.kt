package com.pensource.model

data class Supply(
    val id: String?,

    /**
     * Fiebase auth user id of supplier
     */
    val supplierUserId: String,

    val name: String,
    val phoneNumber: String,
    val isWhatsAppNumber: Boolean,
    val quantity: Double,
    val latitude: Float,
    val longitude: Float,

    /**
     * Additional info
     */
    val note: String = "",

    /**
     * To show a 'verified' badge
     */
    val verified: Boolean = false
)