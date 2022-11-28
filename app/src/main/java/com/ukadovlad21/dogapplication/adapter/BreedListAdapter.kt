package com.ukadovlad21.dogapplication.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.ukadovlad21.dogapplication.model.Dog

class BreedListAdapter:ListAdapter<Dog,BreedItemHolder>(ItemComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedItemHolder =
        BreedItemHolder(parent)
    override fun onBindViewHolder(holder: BreedItemHolder, position: Int) {
        holder.bind(getItem(position))

    }
    class ItemComparator:DiffUtil.ItemCallback<Dog>(){
        override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean {
            return oldItem == newItem
        }
    }
}
