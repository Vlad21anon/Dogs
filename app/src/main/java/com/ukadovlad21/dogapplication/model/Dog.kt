package com.ukadovlad21.dogapplication.model

data class Dog(
    val name: String,
    val previewImageURL: String,
    val subBreedList: List<String>
)