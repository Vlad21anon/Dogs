package com.ukadovlad21.dogapplication.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter


class SubBreedListAdapter: ListAdapter<String, SubbreedItemHolder>(ItemComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubbreedItemHolder {
        return SubbreedItemHolder(parent)
    }
    override fun onBindViewHolder(holder: SubbreedItemHolder, position: Int) {
        holder.bind(getItem(position).toString())
    }

    class ItemComparator : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}



