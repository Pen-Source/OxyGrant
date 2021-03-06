package com.pensource.oxygrant.ui.submit

import androidx.lifecycle.*
import com.pensource.model.Supply
import com.pensource.oxygrant.util.TimeUtil
import com.pensource.shared.domain.auth.GetFirebaseUserUseCase
import com.pensource.shared.domain.supply.DeleteSupplyUseCase
import com.pensource.shared.domain.supply.SubmitSupplyUseCase
import com.pensource.shared.domain.supply.UpdateSupplyUseCase
import com.pensource.shared.result.Event
import com.pensource.shared.result.Result
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class SubmitViewModel @AssistedInject constructor(
    private val sellSupplyUseCase: SubmitSupplyUseCase,
    private val getFirebaseUserUseCase: GetFirebaseUserUseCase,
    private val updateSupplyUseCase: UpdateSupplyUseCase,
    private val deleteSupplyUseCase: DeleteSupplyUseCase,
    private val timeUtil: TimeUtil,
    @Assisted private val supply: Supply?
) : ViewModel() {

    private val _actionSuccess = MutableLiveData<Event<Unit>>()
    val actionSuccess: LiveData<Event<Unit>> = _actionSuccess

    /*
    * If the provided supply from previous screen is not null, this is an edit screen
    * If isUpdate is true, we can make delete button visible and change
    * submit button text to update
    * */
    private val _isUpdate = MutableLiveData(supply != null)
    val isUpdate: LiveData<Boolean> = _isUpdate


    val name = MutableLiveData(supply?.name ?: "")

    val contactNumber = MutableLiveData(supply?.phoneNumber ?: "")

    val isWhatsAppNumber = MutableLiveData(supply?.isWhatsAppNumber ?: false)

    val supplyQuantity = MutableLiveData(supply?.quantity?.toString() ?: "")

    fun submit() {
        viewModelScope.launch {
            val supply = Supply(
                id = supply?.id,
                supplierUserId = getFirebaseUserUseCase()?.uid ?: return@launch,
                name = name.value ?: "",
                phoneNumber = contactNumber.value ?: "",
                isWhatsAppNumber = isWhatsAppNumber.value ?: false,
                quantity = supplyQuantity.value?.toDouble()
                    ?: 0.0, // TODO: 04-06-2021 Handle number format exception
                latitude = 0.0,
                longitude = 0.0,
                geoHash = "",
                submitTime = timeUtil.getCurrentTime()
            )

            val isUpdate = isUpdate.value ?: false

            if (isUpdate) {
                // Update existing supply
                when (val result = updateSupplyUseCase(supply)) {
                    is Result.Success -> {
                        _actionSuccess.postValue(Event(Unit))
                    }
                }
            } else {
                // Submit new supply
                when (val result = sellSupplyUseCase(supply)) {
                    is Result.Success -> {
                        _actionSuccess.postValue(Event(Unit))
                    }
                }
            }
        }
    }

    fun delete() {
        viewModelScope.launch {
            supply?.id?.let {
                when (val result = deleteSupplyUseCase(it)) {
                    is Result.Success -> {
                        _actionSuccess.value = Event(Unit)
                    }
                }
            }
        }
    }

    companion object {
        fun provideFactory(
            submitViewModelFactory: SubmitViewModelFactory,
            supply: Supply? = null
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return submitViewModelFactory.create(supply) as T
            }
        }
    }
}

@AssistedFactory
interface SubmitViewModelFactory {
    fun create(supply: Supply?): SubmitViewModel
}