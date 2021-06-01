package com.pensource.oxygrant.ui.seller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pensource.oxygrant.databinding.FragmentSellerBinding
import com.pensource.shared.result.EventObserver
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Load supply
        sellerViewModel.loadSupply()

        sellerViewModel.actionAddSupply.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(it)
        })
    }
}