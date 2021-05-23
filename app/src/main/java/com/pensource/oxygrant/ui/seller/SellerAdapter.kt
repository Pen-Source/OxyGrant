package com.pensource.oxygrant.ui.seller

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pensource.model.Supply
import com.pensource.oxygrant.databinding.ItemSellerSupplyBinding

class SellerAdapter(
    private val sellerViewModel: SellerViewModel
) : ListAdapter<Supply, SellerAdapter.SellerViewHolder>(SellerDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellerViewHolder {
        return SellerViewHolder(
            ItemSellerSupplyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SellerViewHolder, position: Int) {
        holder.bind(getItem(position), sellerViewModel)
    }

    class SellerViewHolder constructor(
        private val binding: ItemSellerSupplyBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(supply: Supply, viewModel: SellerViewModel) {
            binding.supply = supply
            binding.viewmodel = viewModel
            binding.executePendingBindings()
        }
    }

    class SellerDiffUtil : DiffUtil.ItemCallback<Supply>() {
        override fun areItemsTheSame(oldItem: Supply, newItem: Supply): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Supply, newItem: Supply): Boolean {
            return oldItem == newItem
        }
    }
}