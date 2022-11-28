package com.ukadovlad21.dogapplication.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ukadovlad21.dogapplication.data.repository.Repository
import kotlinx.coroutines.launch

class PhotosViewModel: ViewModel(){
    val repo = Repository()
    val dogLinkList : MutableLiveData<List<String>> = MutableLiveData()
    fun addPhotos(dogName: String, countPhoto:Int) {
        viewModelScope.launch {
            var listDogLink = mutableListOf<String>()
            for (i in 1..countPhoto){
                val element = repo.getDogLink(dogName).link
                if(!listDogLink.contains(element)) {
                    listDogLink.add(element)
                }
            }
            dogLinkList.postValue(listDogLink)
        }
    }
}