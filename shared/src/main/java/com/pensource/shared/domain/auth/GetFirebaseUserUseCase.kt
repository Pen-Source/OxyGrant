package com.pensource.shared.domain.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class GetFirebaseUserUseCase @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {

    operator fun invoke(): FirebaseUser? {
        return firebaseAuth.currentUser
    }
}