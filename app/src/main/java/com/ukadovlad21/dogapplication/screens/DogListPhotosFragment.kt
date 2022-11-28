package com.ukadovlad21.dogapplication.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ukadovlad21.dogapplication.activity.MainActivity
import com.ukadovlad21.dogapplication.R
import com.ukadovlad21.dogapplication.ViewModels.PhotosViewModel
import com.ukadovlad21.dogapplication.adapter.BreedPhotosAdapter
import com.ukadovlad21.dogapplication.databinding.FragmentDogListPhotosBinding
import com.ukadovlad21.dogapplication.toolbar.MainToolbar
import kotlinx.android.synthetic.main.fragment_main.*

class DogListPhotosFragment : Fragment() {
    private lateinit var adapter: BreedPhotosAdapter
    private lateinit var binding: FragmentDogListPhotosBinding
    private lateinit var currentDogName: String
    private lateinit var viewModel:PhotosViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDogListPhotosBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        MainToolbar(main_toolbar).updateAppData()
        setupData()
        setupAdapter()
        addPhotos()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnBack.setOnClickListener {
            (this.activity as MainActivity).navController.navigate(R.id.action_dogListPhotosFragment_to_mainFragment)
        }
        binding.btnLoadMore.setOnClickListener {
            viewModel.addPhotos(currentDogName,15)
        }
        MainToolbar(main_toolbar).setupClickListeners(parentFragmentManager)
    }

    private fun setupAdapter() {
        adapter = BreedPhotosAdapter() // создаю адаптер, куда передаю список из Dog
        binding.lvDogsImages.layoutManager = LinearLayoutManager(requireContext())
        binding.lvDogsImages.adapter = adapter
    }

    private fun addPhotos() {
        viewModel = ViewModelProvider(this)[PhotosViewModel::class.java]
        viewModel.addPhotos(currentDogName,10)
        viewModel.dogLinkList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun setupData() {
        currentDogName = this.arguments?.get("DogName").toString()
        binding.mainToolbar.toolbarText.text = currentDogName

    }

}