package com.ukadovlad21.dogapplication.model

import com.google.gson.annotations.SerializedName
import kotlin.String

data class DogName(
    @SerializedName("message")
    val link: String,
    val status: String
)