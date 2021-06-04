package com.pensource.oxygrant.ui.submit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.pensource.oxygrant.databinding.FragmentSubmitSupplyBinding
import com.pensource.shared.result.EventObserver
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SubmitSupplyFragment : Fragment() {

    private val args: SubmitSupplyFragmentArgs by navArgs()

    @Inject
    lateinit var submitViewModelFactory: SubmitViewModelFactory

    private val submitViewModel: SubmitViewModel by viewModels {
        SubmitViewModel.provideFactory(submitViewModelFactory, args.supply)
    }

    private lateinit var binding: FragmentSubmitSupplyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubmitSupplyBinding.inflate(inflater, container, false).apply {
            viewmodel = submitViewModel
            lifecycleOwner = this@SubmitSupplyFragment
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submitViewModel.actionSuccess.observe(viewLifecycleOwner, EventObserver {
            Snackbar.make(binding.root, "Success", Snackbar.LENGTH_SHORT).show()
            findNavController().navigateUp()
        })
    }
}