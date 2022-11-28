package com.ukadovlad21.dogapplication.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ukadovlad21.dogapplication.ApplicationData
import com.ukadovlad21.dogapplication.activity.MainActivity
import com.ukadovlad21.dogapplication.R
import com.ukadovlad21.dogapplication.model.Dog
import com.ukadovlad21.dogapplication.volume
import kotlinx.android.synthetic.main.item_dog.view.*


class BreedItemHolder(container: ViewGroup): RecyclerView.ViewHolder(
    LayoutInflater.from(container.context).inflate(
            R.layout.item_dog, container, false)
    ) {
    lateinit var adapter: SubBreedListAdapter
    fun bind(dog: Dog) {
        itemView.tv_dog_name.text = dog.name
        itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("DogName", dog.name)
            (itemView.context as MainActivity).navController
                .navigate(R.id.action_mainFragment_to_dogListPhotosFragment, args = bundle)
        }

        if (dog.subBreedList.size == 0) {
            itemView.ib_show_subbreed_list.visibility = View.GONE
            itemView.rv_subbreeds.visibility = View.GONE
        }

        //region set Image use Glide
        if (!ApplicationData.internetEconomy) {
            Glide.with(itemView.context)
                .load(dog.previewImageURL)
                //.centerCrop() //НЕ РАБОТАЕТ. Почему?
                .error(R.drawable.ic_baseline_error_outline_32).into(itemView.iv_dog_breed_image)
        }
        //endregion

        //region show list sub-breeds
        if(dog.subBreedList.size>=1) {
            adapter = SubBreedListAdapter()
            adapter.submitList(dog.subBreedList)
            var isClicked = false
            with(itemView) {
                rv_subbreeds.layoutManager = LinearLayoutManager(this.context)
                rv_subbreeds.adapter = adapter
                rv_subbreeds.visibility = View.GONE
                ib_show_subbreed_list.volume()
                ib_show_subbreed_list.isClickable = true
                ib_show_subbreed_list.visibility = View.VISIBLE
                ib_show_subbreed_list.setOnClickListener {
                    if (!isClicked) {
                        rv_subbreeds.visibility = View.VISIBLE
                        isClicked = true
                    } else {
                        rv_subbreeds.visibility = View.GONE
                        isClicked = false
                    }
                }
            }
        }
        //endregion


    }

}

