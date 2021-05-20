package com.pensource.oxygrant.ui.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pensource.shared.result.Event

interface SignInViewModelDelegate {
    val actionSignIn: LiveData<Event<Unit>>

    fun isUserSignedIn(): Boolean
}

class FirebaseSignInViewModelDelegate : SignInViewModelDelegate {

    private val _actionSignIn = MutableLiveData<Event<Unit>>()
    override val actionSignIn: LiveData<Event<Unit>> = _actionSignIn

    override fun isUserSignedIn(): Boolean {
        TODO("Not yet implemented")
    }
}