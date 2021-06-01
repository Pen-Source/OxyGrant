package com.pensource.oxygrant.ui.submit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SubmitViewModel @Inject constructor() : ViewModel() {

    val name = MutableLiveData("")

    val contactNumber = MutableLiveData("")

    val isWhatsAppNumber = MutableLiveData<Boolean>()

    val supplyQuantity = MutableLiveData<String>()

    fun submit() {

    }
}