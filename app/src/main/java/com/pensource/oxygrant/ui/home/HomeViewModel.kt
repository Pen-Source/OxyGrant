package com.pensource.oxygrant.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.pensource.shared.domain.auth.GetFirebaseUserUseCase
import com.pensource.shared.result.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFirebaseUserUseCase: GetFirebaseUserUseCase
) : ViewModel() {

    private val _actionSellSupply = MutableLiveData<Event<NavDirections>>()
    val actionSellSupply: LiveData<Event<NavDirections>> = _actionSellSupply

    fun sellOxygen() {
        // Check if user is signed in
        if (getFirebaseUserUseCase() == null) {
            // User not signed in, go to sign in screen
            val action = HomeFragmentDirections.actionNavigationHomeToSignInFragment()
            _actionSellSupply.value = Event(action)
        } else {
            // User signed in, go to add supply screen
            val action = HomeFragmentDirections.navigationSeller()
            _actionSellSupply.value = Event(action)
        }
    }
}