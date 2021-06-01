package com.pensource.oxygrant.ui.signin

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.pensource.oxygrant.R

class SignInFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.PhoneBuilder().build()
        )

        val signIn =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

                if (it.resultCode == Activity.RESULT_OK) {
                    val action = SignInFragmentDirections
                        .actionSignInFragmentToSellerFragment()
                    findNavController().navigate(action)
                } else {
                    Toast.makeText(requireContext(), "Sign in failed", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                }
            }

        signIn.launch(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()
        )
    }
}