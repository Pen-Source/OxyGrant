package com.pensource.shared.data.supply

import com.google.firebase.firestore.FirebaseFirestore
import com.pensource.model.Supply
import javax.inject.Inject

interface SupplyDataSource {

    fun sellSupply(supply: Supply)

    fun findSupply(lat: Long, lng: Long): List<Supply>
}

class FirebaseSupplyDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
): SupplyDataSource {
    override fun sellSupply(supply: Supply) {
        TODO("Not yet implemented")
    }

    override fun findSupply(lat: Long, lng: Long): List<Supply> {
        TODO("Not yet implemented")
    }

}