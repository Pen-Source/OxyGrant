package com.pensource.oxygrant.ui.submit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pensource.model.Supply
import com.pensource.shared.domain.auth.GetFirebaseUserUseCase
import com.pensource.shared.domain.supply.SubmitSupplyUseCase
import com.pensource.shared.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubmitViewModel @Inject constructor(
    private val sellSupplyUseCase: SubmitSupplyUseCase,
    private val getFirebaseUserUseCase: GetFirebaseUserUseCase
) : ViewModel() {

    val name = MutableLiveData("")

    val contactNumber = MutableLiveData("")

    val isWhatsAppNumber = MutableLiveData<Boolean>()

    val supplyQuantity = MutableLiveData<String>()

    fun submit() {
        viewModelScope.launch {
            val supply = Supply(
                id = null,
                supplierUserId = getFirebaseUserUseCase()?.uid ?: return@launch,
                name = name.value ?: "",
                phoneNumber = contactNumber.value ?: "",
                isWhatsAppNumber = isWhatsAppNumber.value ?: false,
                quantity = supplyQuantity.value?.toDouble() ?: 0.0,
                latitude = 0f,
                longitude = 0f
            )

            when (val result = sellSupplyUseCase(supply)) {
                is Result.Success -> {

                }

                is Result.Error -> {

                }
            }
        }
    }
}