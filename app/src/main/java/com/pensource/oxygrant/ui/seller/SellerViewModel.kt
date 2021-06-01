package com.pensource.oxygrant.ui.seller

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.pensource.model.Supply
import com.pensource.shared.domain.auth.GetFirebaseUserUseCase
import com.pensource.shared.domain.supply.GetCurrentUserSupplyUseCase
import com.pensource.shared.result.Event
import com.pensource.shared.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SellerViewModel @Inject constructor(
    private val getCurrentUserSupplyUseCase: GetCurrentUserSupplyUseCase,
    private val getFirebaseUserUseCase: GetFirebaseUserUseCase
) : ViewModel() {

    private val _actionAddSupply = MutableLiveData<Event<NavDirections>>()
    val actionAddSupply: LiveData<Event<NavDirections>> = _actionAddSupply

    private val _supplyList = MutableLiveData<List<Supply>>().apply {
        viewModelScope.launch {
            val userId = getFirebaseUserUseCase()?.uid ?: return@launch
            when (val result = getCurrentUserSupplyUseCase(userId)) {
                is Result.Success -> {
                    value = result.data
                }
            }
        }
    }
    val supplyList: LiveData<List<Supply>> = _supplyList

    fun addSupply() {
        val action = SellerFragmentDirections
            .actionSellerFragmentToNavigationSubmitSupply()

        _actionAddSupply.value = Event(action)
    }
}