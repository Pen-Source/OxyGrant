package com.pensource.shared.data.supply

import com.google.firebase.firestore.DocumentReference
import com.pensource.model.Supply
import javax.inject.Inject

interface SupplyRepository {

    fun sellSupply(supply: Supply): DocumentReference

    fun updateSupply(supply: Supply)

    fun findSupply(filter: Map<String, String>? = null): List<Supply>

    fun deleteSupply(id: String)
}

class FirebaseSupplyRepository @Inject constructor(
    private val supplyDataSource: SupplyDataSource
) : SupplyRepository {

    override fun sellSupply(supply: Supply): DocumentReference {
        return supplyDataSource.sellSupply(supply)
    }

    override fun updateSupply(supply: Supply) {
        supplyDataSource.updateSupply(supply)
    }

    override fun findSupply(filter: Map<String, String>?): List<Supply> {
        return supplyDataSource.findSupply(filter)
    }

    override fun deleteSupply(id: String) {
        supplyDataSource.deleteSupply(id)
    }
}