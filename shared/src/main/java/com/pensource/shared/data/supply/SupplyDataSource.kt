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

    fun findSupply(filter: Map<String, String>? = null): List<Supply>
}

class FirebaseSupplyDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) : SupplyDataSource {

    override fun sellSupply(supply: Supply): DocumentReference {
        val task = firestore
            .collection(SUPPLY)
            .add(
                mapOf(
                    SUPPLIER_USER_ID to supply.supplierUserId,
                    NAME to supply.name,
                    CONTACT_NUMBER to supply.phoneNumber,
                    IS_WHATS_APP_NUMBER to supply.isWhatsAppNumber,
                    SUPPLY_QUANTITY to supply.quantity,
                    SUBMIT_TIME to supply.submitTime,
                    UPDATE_TIME to supply.updateTime,
                    LATITUDE to supply.latitude,
                    LONGITUDE to supply.longitude,
                    NOTE to supply.note
                )
            )

        return Tasks.await(task, 20, TimeUnit.SECONDS)
    }

    override fun findSupply(filter: Map<String, String>?): List<Supply> {
        val task = firestore.collection(SUPPLY).apply {
            filter?.forEach {
                whereEqualTo(it.key, it.value)
            }
        }.get()

        val snapshot = Tasks.await(task, 20, TimeUnit.SECONDS)
        return snapshot.documents.map { parseSupply(it) }
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
            note = snapshot[NOTE] as String? ?: "",
            submitTime = snapshot[SUBMIT_TIME] as Long? ?: 0,
            updateTime = snapshot[UPDATE_TIME] as Long? ?: 0,
            verified = snapshot[IS_VERIFIED] as Boolean? ?: false
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
        const val NOTE = "note"
        const val IS_VERIFIED = "isVerified"
    }
}