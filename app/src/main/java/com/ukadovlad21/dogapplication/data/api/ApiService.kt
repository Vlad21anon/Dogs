package com.ukadovlad21.dogapplication.data.api

import com.ukadovlad21.dogapplication.model.DogName
import com.ukadovlad21.dogapplication.model.DogSubBreedList
import com.ukadovlad21.dogapplication.model.DogsList
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("api/breeds/list")
    suspend fun getAllDogList(): DogsList


    @GET("api/breed/{dogName}/images/random")
    suspend fun getDogImage(
        @Path("dogName") string: String
    ): DogName


    @GET("api/breed/{dogName}/list")
    suspend fun getDogSubBreed(
        @Path("dogName") string: String
    ): DogSubBreedList

}