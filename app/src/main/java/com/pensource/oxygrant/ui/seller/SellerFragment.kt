package com.pensource.oxygrant.ui.seller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pensource.oxygrant.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SellerFragment : Fragment() {

    private val sellerViewModel: SellerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seller, container, false)
    }
}