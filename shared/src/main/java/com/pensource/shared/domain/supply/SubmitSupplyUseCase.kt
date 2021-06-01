package com.pensource.shared.domain.supply

import com.google.firebase.firestore.DocumentReference
import com.pensource.model.Supply
import com.pensource.shared.data.supply.SupplyRepository
import com.pensource.shared.di.DispatcherIo
import com.pensource.shared.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SubmitSupplyUseCase @Inject constructor(
    private val supplyRepository: SupplyRepository,
    @DispatcherIo private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(supply: Supply): Result<DocumentReference> =
        withContext(dispatcher) {
            val result = try {
                supplyRepository.sellSupply(supply)
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }

            return@withContext Result.Success(result)
        }
}