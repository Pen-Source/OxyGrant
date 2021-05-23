package com.pensource.oxygrant.ui.seller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pensource.oxygrant.R
import com.pensource.oxygrant.databinding.FragmentSellerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SellerFragment : Fragment() {

    private val sellerViewModel: SellerViewModel by viewModels()

    private lateinit var binding: FragmentSellerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSellerBinding.inflate(inflater, container, false).apply {
            viewmodel = sellerViewModel
            lifecycleOwner = this@SellerFragment
        }
        return binding.root
    }
}