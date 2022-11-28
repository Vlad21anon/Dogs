package com.ukadovlad21.dogapplication.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ukadovlad21.dogapplication.ApplicationData
import com.ukadovlad21.dogapplication.DogData
import com.ukadovlad21.dogapplication.data.repository.Repository
import com.ukadovlad21.dogapplication.model.Dog
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private var repo = Repository()


    val dogList : MutableLiveData<List<Dog>> = MutableLiveData()
    init {
        viewModelScope.launch {
            val listDog = repo.getAllDogsList()
            DogData.dogAllNamesList = listDog.dogList
            if (!ApplicationData.internetEconomy) {//загрузка без ограничений
                val map1 = listDog.dogList.map {
                    val link = repo.getDogLink(it).link
                    val subBreedList = repo.getSubBreedList(it).dogSubBreedList
                    return@map Dog(it, link, subBreedList)
                }
                dogList.postValue(map1)
            }else{//экономная загрузка
                val map1 = listDog.dogList.map {
                    val subBreedList = repo.getSubBreedList(it).dogSubBreedList
                    return@map Dog(it, "", subBreedList)
                }
                dogList.postValue(map1)
            }

        }
    }
}














