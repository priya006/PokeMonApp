package com.example.pokemonapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    const val BASE_URL = "https://pokeapi.co/api/v2/"

    fun create(): PokeMonApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeMonApiService::class.java)
    }
}