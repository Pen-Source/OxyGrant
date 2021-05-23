package com.pensource.oxygrant.ui.seller

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pensource.model.Supply
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SellerViewModel @Inject constructor() : ViewModel() {

    private val _supplyList = MutableLiveData<List<Supply>>().apply {
        // Load fake supply data for testing list view
        value = listOf(
            Supply("abc", "Supplier 1", "1234567890", false, quantity = 50.0, 0f, 0f),
            Supply("abc", "Supplier 1", "1234567890", true, quantity = 50.0, 0f, 0f, true)
        )
    }
    val supplyList: LiveData<List<Supply>> = _supplyList
}