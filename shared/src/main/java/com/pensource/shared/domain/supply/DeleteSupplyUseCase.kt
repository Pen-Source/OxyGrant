package com.pensource.shared.domain.supply

import com.pensource.shared.data.supply.SupplyRepository
import com.pensource.shared.di.DispatcherIo
import com.pensource.shared.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class DeleteSupplyUseCase @Inject constructor(
    private val supplyRepository: SupplyRepository,
    @DispatcherIo private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(id: String): Result<Unit> = withContext(dispatcher) {
        val result = try {
            supplyRepository.deleteSupply(id)
        } catch (e: Exception) {
            Timber.e("DeleteSupplyUseCase: $e")
            return@withContext Result.Error(e)
        }

        return@withContext Result.Success(result)
    }
}