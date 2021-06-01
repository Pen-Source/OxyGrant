package com.pensource.shared.domain.supply

import com.pensource.model.Supply
import com.pensource.shared.data.supply.FirebaseSupplyDataSource
import com.pensource.shared.data.supply.SupplyRepository
import com.pensource.shared.di.DispatcherIo
import com.pensource.shared.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class GetCurrentUserSupplyUseCase @Inject constructor(
    private val supplyRepository: SupplyRepository,
    @DispatcherIo private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(userId: String): Result<List<Supply>> = withContext(dispatcher) {
        val result = try {
            supplyRepository.findSupply(
                mapOf(FirebaseSupplyDataSource.SUPPLIER_USER_ID to userId)
            ).sortedByDescending {
                it.submitTime
            }
        } catch (e: Exception) {
            Timber.e("GetCurrentUserSupplyUseCase: $e")
            return@withContext Result.Error(e)
        }

        return@withContext Result.Success(result)
    }
}