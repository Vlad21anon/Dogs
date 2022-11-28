package com.ukadovlad21.dogapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ukadovlad21.dogapplication.R
import kotlinx.android.synthetic.main.item_subbreed.view.*

class SubbreedItemHolder(container: ViewGroup)
    : RecyclerView.ViewHolder(
    LayoutInflater.from(container.context).inflate(
        R.layout.item_subbreed, container, false)) {
    fun bind(text: String) {
        itemView.tv_subbreed_name.text = text
    }
}