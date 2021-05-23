package com.pensource.oxygrant.ui.seller

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pensource.model.Supply

@BindingAdapter(value = ["supplyList", "viewModel"], requireAll = true)
fun supplyList(
    recyclerView: RecyclerView,
    items: List<Supply>?,
    viewModel: SellerViewModel
) {
    items ?: return

    if (recyclerView.adapter == null) {
        recyclerView.adapter = SellerAdapter(viewModel)
    }

    (recyclerView.adapter as SellerAdapter).apply {
        submitList(items)
    }
}