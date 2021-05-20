package com.pensource.oxygrant.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pensource.shared.result.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _actionSellSupply = MutableLiveData<Event<Unit>>()
    val actionSellSupply: LiveData<Event<Unit>> = _actionSellSupply

    fun sellOxygen() {

    }
}