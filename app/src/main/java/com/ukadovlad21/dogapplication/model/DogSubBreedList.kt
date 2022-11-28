package com.ukadovlad21.dogapplication.model

import com.google.gson.annotations.SerializedName
import kotlin.String

data class DogSubBreedList(
    @SerializedName("message")
    val dogSubBreedList: List<String>,
    val status: String
)