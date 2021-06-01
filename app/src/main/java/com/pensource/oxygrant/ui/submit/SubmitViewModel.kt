package com.pensource.oxygrant.ui.submit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pensource.model.Supply
import com.pensource.oxygrant.util.TimeUtil
import com.pensource.shared.domain.auth.GetFirebaseUserUseCase
import com.pensource.shared.domain.supply.SubmitSupplyUseCase
import com.pensource.shared.result.Event
import com.pensource.shared.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubmitViewModel @Inject constructor(
    private val sellSupplyUseCase: SubmitSupplyUseCase,
    private val getFirebaseUserUseCase: GetFirebaseUserUseCase,
    private val timeUtil: TimeUtil
) : ViewModel() {

    private val _actionSubmissionSuccess = MutableLiveData<Event<Unit>>()
    val actionSubmissionSuccess: LiveData<Event<Unit>> = _actionSubmissionSuccess


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
                latitude = 0.0,
                longitude = 0.0,
                submitTime = timeUtil.getCurrentTime()
            )

            when (val result = sellSupplyUseCase(supply)) {
                is Result.Success -> {
                    _actionSubmissionSuccess.postValue(Event(Unit))
                }

                is Result.Error -> {

                }
            }
        }
    }
}