package com.pensource.shared.data.supply

import com.pensource.model.Supply

interface SupplyRepository {

    fun sellSupply(supply: Supply)

    fun findSupply(lat: Long, lng: Long): List<Supply>
}

class FirebaseSupplyRepository : SupplyRepository {
    override fun sellSupply(supply: Supply) {
        TODO("Not yet implemented")
    }

    override fun findSupply(lat: Long, lng: Long): List<Supply> {
        TODO("Not yet implemented")
    }

}