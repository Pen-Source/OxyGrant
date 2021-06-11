package com.pensource.oxygrant.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pensource.oxygrant.R
import com.pensource.oxygrant.databinding.FragmentHomeBinding
import com.pensource.shared.result.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            viewmodel = homeViewModel
            lifecycleOwner = this@HomeFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.actionSellSupply.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(it)
        })

        homeViewModel.actionFindSupply.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(it)
        })

        binding.chooseLocationButton.setOnClickListener {
            findNavController().navigate(R.id.navigation_choose_location)
        }
    }
}