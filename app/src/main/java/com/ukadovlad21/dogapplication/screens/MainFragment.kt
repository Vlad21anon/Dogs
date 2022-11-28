package com.ukadovlad21.dogapplication.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ukadovlad21.dogapplication.*
import com.ukadovlad21.dogapplication.activity.MainActivity
import com.ukadovlad21.dogapplication.ViewModels.MainViewModel
import com.ukadovlad21.dogapplication.adapter.BreedListAdapter
import com.ukadovlad21.dogapplication.databinding.FragmentMainBinding
import com.ukadovlad21.dogapplication.toolbar.MainToolbar
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {
    private lateinit var adapter: BreedListAdapter
    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        MainToolbar(main_toolbar).updateAppData()//Обновляет значение Звука и Темы
        updateList()
        setupClickListeners()

    }

    private fun setupClickListeners() {
        binding.btnStartLvl.volume()
        binding.btnStartLvl.setOnClickListener {
            (this.activity as MainActivity).navController.navigate(R.id.action_mainFragment_to_quizFragment)
        }
        MainToolbar(main_toolbar).setupClickListeners(parentFragmentManager)//Добавляет реакцию на нажатие кнопок громкости и смены темы

    }


    private fun updateList() {
        adapter = BreedListAdapter()
        binding.lvDogs.layoutManager = LinearLayoutManager(context)
        binding.lvDogs.adapter = adapter
        if (DogData.dogList.isEmpty()) {
            val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
            viewModel.dogList.observe(viewLifecycleOwner) {
                DogData.dogList = it
                adapter.submitList(it)
                binding.progressBar.visibility =View.GONE
                binding.lvDogs.visibility = View.VISIBLE
            }
        }else {
            adapter.submitList(DogData.dogList)
            binding.progressBar.visibility =View.GONE
            binding.lvDogs.visibility = View.VISIBLE
        }

    }

}












