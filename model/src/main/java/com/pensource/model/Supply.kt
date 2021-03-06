package com.pensource.model

import java.io.Serializable

data class Supply(
    val id: String?,

    /**
     * Fiebase auth user id of supplier
     */
    val supplierUserId: String,

    /**
     * Name of the supplier
     */
    val name: String,

    /**
     * Contact number of the supplier
     */
    val phoneNumber: String,

    /**
     * Indicate weather the number provided can
     * be contacted via WhatsApp
     */
    val isWhatsAppNumber: Boolean,

    /**
     * Quantity of the supply
     */
    val quantity: Double,

    /**
     * Time when the supply is submitted
     */
    val submitTime: Long,

    /**
     * Latest update time
     */
    val updateTime: Long? = null,

    val latitude: Double,
    val longitude: Double,

    val geoHash: String,

    /**
     * Additional info
     */
    val note: String = "",

    /**
     * To show a 'verified' badge
     */
    val verified: Boolean = false
) : Serializable