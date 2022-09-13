package com.jrubiralta.beerlistapp.presentation.ui.beerlist.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.jrubiralta.beerlistapp.domain.model.BeerModel
import com.jrubiralta.beerlistapp.presentation.commons.ViewWrapper

class BeerListAdapter(
    private val onItemClickListener: ((BeerModel) -> Unit)
) : PagingDataAdapter<BeerModel, ViewWrapper<BeerItemView>>(BeerDiffUtil()) {

    override fun onBindViewHolder(holder: ViewWrapper<BeerItemView>, position: Int) {
        getItem(position)?.let {
            holder.view.bind(
                model = it,
                onClick = onItemClickListener
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewWrapper<BeerItemView> {
        return ViewWrapper(BeerItemView(parent.context))
    }

    class BeerDiffUtil : DiffUtil.ItemCallback<BeerModel>() {
        override fun areItemsTheSame(
            oldItem: BeerModel,
            newItem: BeerModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: BeerModel,
            newItem: BeerModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}
