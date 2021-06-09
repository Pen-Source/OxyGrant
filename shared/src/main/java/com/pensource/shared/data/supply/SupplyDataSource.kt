package com.pensource.shared.data.supply

import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.pensource.model.Supply
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface SupplyDataSource {

    fun sellSupply(supply: Supply): DocumentReference

    fun updateSupply(supply: Supply)

    fun findSupply(filter: Map<String, String>? = null): List<Supply>

    fun deleteSupply(id: String)
}

class FirebaseSupplyDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) : SupplyDataSource {

    override fun sellSupply(supply: Supply): DocumentReference {
        val task = firestore
            .collection(SUPPLY)
            .add(supply.toMap())

        return Tasks.await(task, 20, TimeUnit.SECONDS)
    }

    override fun updateSupply(supply: Supply) {
        supply.id ?: throw Exception("Supply ID can't be null")

        val task = firestore
            .collection(SUPPLY)
            .document(supply.id.toString())
            .update(supply.toMap())

        Tasks.await(task, 20, TimeUnit.SECONDS)
    }

    override fun findSupply(filter: Map<String, String>?): List<Supply> {
        var query = firestore.collection(SUPPLY).limit(100)

        filter?.forEach {
            query = query.whereEqualTo(it.key, it.value)
        }

        val task = query.get()

        val snapshot = Tasks.await(task, 20, TimeUnit.SECONDS)
        return snapshot.documents.map { parseSupply(it) }
    }

    override fun deleteSupply(id: String) {
        val task = firestore
            .collection(SUPPLY)
            .document(id)
            .delete()

        Tasks.await(task, 20, TimeUnit.SECONDS)
    }

    private fun parseSupply(snapshot: DocumentSnapshot): Supply {
        return Supply(
            id = snapshot.id,
            supplierUserId = snapshot[SUPPLIER_USER_ID] as String,
            name = snapshot[NAME] as String? ?: "N/A",
            phoneNumber = snapshot[CONTACT_NUMBER] as String? ?: "N/A",
            isWhatsAppNumber = snapshot[IS_WHATS_APP_NUMBER] as Boolean? ?: false,
            quantity = snapshot[SUPPLY_QUANTITY] as Double? ?: 0.0,
            latitude = snapshot[LATITUDE] as Double? ?: 0.0,
            longitude = snapshot[LONGITUDE] as Double? ?: 0.0,
            geoHash = snapshot[GEO_HASH] as String? ?: "",
            note = snapshot[NOTE] as String? ?: "",
            submitTime = snapshot[SUBMIT_TIME] as Long? ?: 0,
            updateTime = snapshot[UPDATE_TIME] as Long? ?: 0,
            verified = snapshot[IS_VERIFIED] as Boolean? ?: false
        )
    }

    private fun Supply.toMap(): Map<String, Any?> {
        return mapOf(
            SUPPLIER_USER_ID to supplierUserId,
            NAME to name,
            CONTACT_NUMBER to phoneNumber,
            IS_WHATS_APP_NUMBER to isWhatsAppNumber,
            SUPPLY_QUANTITY to quantity,
            SUBMIT_TIME to submitTime,
            UPDATE_TIME to updateTime,
            LATITUDE to latitude,
            LONGITUDE to longitude,
            GEO_HASH to geoHash,
            NOTE to note
        )
    }

    companion object {
        const val SUPPLY = "supply"
        const val SUPPLY_ID = "supplyId"
        const val SUPPLIER_USER_ID = "supplierUserId"
        const val NAME = "name"
        const val CONTACT_NUMBER = "contactNumber"
        const val IS_WHATS_APP_NUMBER = "isWhatsAppNumber"
        const val SUPPLY_QUANTITY = "supplyQuantity"
        const val SUBMIT_TIME = "submitTime"
        const val UPDATE_TIME = "updateTime"
        const val LATITUDE = "latitude"
        const val LONGITUDE = "longitude"
        const val GEO_HASH = "geohash"
        const val NOTE = "note"
        const val IS_VERIFIED = "isVerified"
    }
}