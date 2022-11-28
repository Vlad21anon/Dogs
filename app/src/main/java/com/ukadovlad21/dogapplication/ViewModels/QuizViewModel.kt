package com.ukadovlad21.dogapplication.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ukadovlad21.dogapplication.data.repository.Repository
import com.ukadovlad21.dogapplication.model.DogNameAndLink
import kotlinx.coroutines.launch

class QuizViewModel: ViewModel(){
    val repo = Repository()

    val listOfDogNameAndLink:MutableLiveData<List<DogNameAndLink>> = MutableLiveData()
    fun getDogLinkListFromList(list: List<String>){
        viewModelScope.launch {
            var linkListOfDogs = mutableListOf<DogNameAndLink>()
            for (name in list) {
                val currentLink = repo.getDogLink(name).link
                val currentDogData = DogNameAndLink(name,currentLink)
                linkListOfDogs.add(currentDogData)
            }
            listOfDogNameAndLink.postValue(linkListOfDogs)
        }
    }
}
