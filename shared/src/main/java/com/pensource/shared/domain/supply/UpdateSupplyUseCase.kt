package com.pensource.shared.domain.supply

import com.pensource.model.Supply
import com.pensource.shared.data.supply.SupplyRepository
import com.pensource.shared.di.DispatcherIo
import com.pensource.shared.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class UpdateSupplyUseCase @Inject constructor(
    private val supplyRepository: SupplyRepository,
    @DispatcherIo private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(supply: Supply): Result<Unit> = withContext(dispatcher) {
        val result = try {
            supplyRepository.updateSupply(supply)
        } catch (e: Exception) {
            Timber.e("UpdateSupplyUseCase: $e")
            return@withContext Result.Error(e)
        }

        return@withContext Result.Success(result)
    }
}