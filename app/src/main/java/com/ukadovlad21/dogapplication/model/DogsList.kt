package com.ukadovlad21.dogapplication.model

import com.google.gson.annotations.SerializedName
import kotlin.String

data class DogsList(
    @SerializedName("message")
    val dogList: List<String>,
    val status: String
)