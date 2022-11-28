package com.ukadovlad21.dogapplication.data.repository

import com.ukadovlad21.dogapplication.model.DogName
import com.ukadovlad21.dogapplication.model.DogSubBreedList
import com.ukadovlad21.dogapplication.model.DogsList
import com.ukadovlad21.dogapplication.data.api.RetrofitInstance

class Repository {

    suspend fun getAllDogsList(): DogsList {
        return RetrofitInstance.api.getAllDogList()
    }

    suspend fun getDogLink(string: String): DogName {
        return RetrofitInstance.api.getDogImage(string)
    }

    suspend fun getSubBreedList(string: String): DogSubBreedList {
        return RetrofitInstance.api.getDogSubBreed(string)
    }

}