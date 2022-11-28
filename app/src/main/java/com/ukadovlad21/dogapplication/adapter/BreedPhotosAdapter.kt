package com.ukadovlad21.dogapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ukadovlad21.dogapplication.R
import kotlinx.android.synthetic.main.item_dog_photo.view.*

class BreedPhotosAdapter:ListAdapter<String,BreedItemPhotoHolder>(ItemComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedItemPhotoHolder =
        BreedItemPhotoHolder(parent)
    override fun onBindViewHolder(holder: BreedItemPhotoHolder, position: Int) {
        holder.bind(getItem(position))
    }
    class ItemComparator: DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

}
class BreedItemPhotoHolder(container: ViewGroup): RecyclerView.ViewHolder(
    LayoutInflater.from(container.context).inflate(R.layout.item_dog_photo, container, false)
) {
    fun bind(dogLink: String) {
        Glide.with(itemView.context).load(dogLink)
            .error(R.drawable.ic_baseline_error_outline_32)
            .into(itemView.image)
    }

}