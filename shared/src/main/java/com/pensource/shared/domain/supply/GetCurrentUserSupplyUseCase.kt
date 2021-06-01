package com.pensource.shared.domain.supply

import com.pensource.model.Supply
import com.pensource.shared.data.supply.FirebaseSupplyDataSource
import com.pensource.shared.data.supply.SupplyDataSource
import com.pensource.shared.di.DispatcherIo
import com.pensource.shared.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCurrentUserSupplyUseCase @Inject constructor(
    private val supplyDataSource: SupplyDataSource,
    @DispatcherIo private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(userId: String): Result<List<Supply>> = withContext(dispatcher) {
        val result = try {
            supplyDataSource.findSupply(
                mapOf(
                    FirebaseSupplyDataSource.SUPPLIER_USER_ID to userId
                )
            )
        } catch (e: Exception) {
            return@withContext Result.Error(e)
        }

        return@withContext Result.Success(result)
    }
}