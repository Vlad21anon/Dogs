package com.ukadovlad21.dogapplication.data.api

import com.ukadovlad21.dogapplication.BASEURL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(
                GsonConverterFactory
                    .create())
            .build()
    }
    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}