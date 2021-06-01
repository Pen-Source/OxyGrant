package com.pensource.shared.data.supply

import com.google.firebase.firestore.DocumentReference
import com.pensource.model.Supply
import javax.inject.Inject

interface SupplyRepository {

    fun sellSupply(supply: Supply): DocumentReference

    fun findSupply(filter: Map<String, String>? = null): List<Supply>
}

class FirebaseSupplyRepository @Inject constructor(
    private val supplyDataSource: SupplyDataSource
) : SupplyRepository {

    override fun sellSupply(supply: Supply): DocumentReference {
        return supplyDataSource.sellSupply(supply)
    }

    override fun findSupply(filter: Map<String, String>?): List<Supply> {
        return supplyDataSource.findSupply(filter)
    }
}