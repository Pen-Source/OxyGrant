package com.pensource.oxygrant.ui.submit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.pensource.oxygrant.R
import com.pensource.oxygrant.databinding.FragmentSubmitSupplyBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubmitSupplyFragment : Fragment() {
    
    private val submitViewModel: SubmitViewModel by viewModels()
    
    private lateinit var binding: FragmentSubmitSupplyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSubmitSupplyBinding.inflate(inflater, container, false).apply { 
            viewmodel = submitViewModel
            lifecycleOwner = this@SubmitSupplyFragment
        }
        return inflater.inflate(R.layout.fragment_submit_supply, container, false)
    }
}